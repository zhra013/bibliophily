package com.ase.application.Service;

import com.ase.application.Repository.UserRepository;
import com.ase.application.dto.UserDTO;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.transform.ResultTransformer;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void userRegistration(User user) {
        user.setUserName(UserServiceImpl.encrypt(user.getUserName()));
        user.setUserPassword(UserServiceImpl.encrypt(user.getUserPassword()));
        userRepository.save(user);
    }

    @Override
    public User login(User user) {
        List<User> users= userRepository.findAll();
      return  users.stream().filter(user1 -> user.getUserName().equals(UserServiceImpl.decrypt(user1.getUserName()))
        && user.getUserPassword().equals(UserServiceImpl.decrypt(user1.getUserPassword()))
              && user1.getUserType().equals(user.getUserType())).findAny().orElse(null);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User updateUserInformation(User updateUser, Long userId) {
       User user= userRepository.findById(userId).get();
       updateUser.setUserName(encrypt(updateUser.getUserName()));
       updateUser.setUserPassword(user.getUserPassword());
        updateUser.setId(user.getId());
        return userRepository.save(updateUser);
    }

    @Override
    public User findUserByUserMail(String userMail) {
        return userRepository.findByUserMail(userMail);
    }

    @Override
    public User findUserByUserContact(String userContact) {
        return userRepository.findByUserContact(userContact);
    }

    @Override
    public User findUserByUserName(String userName) {
        List<User> users= userRepository.findAll();
        return  users.stream().filter(user1 -> userName.equals(UserServiceImpl.decrypt(user1.getUserName()))).findAny().orElse(null);
    }

    @Override
    public User updateUserPassword(User updateUser, Long userId) {
        User user = userRepository.findById(userId).get();
        user.setUserPassword(updateUser.getUserPassword());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getTopContributor() {
        return userRepository.findTopContributor();
    }

    @Override
    public User findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }

    private static final String SECRET_KEY = "my_super_secret_key_ha_ha_ha";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";

    public static String encrypt(String strToEncrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void decryptUser(User user){
        user.setUserPassword(decrypt(user.getUserPassword()));
        user.setUserName(decrypt(user.getUserName()));
    }

    public static void decryptUserDTO(UserDTO user){
        user.setUserPassword(decrypt(user.getUserPassword()));
        user.setUserName(decrypt(user.getUserName()));
    }
}
