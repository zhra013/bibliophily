package com.ase.application.Service;

import com.ase.application.Repository.UserRepository;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void userRegistration(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(User user) {
     return userRepository.findByUserNameAndUserPasswordAndUserType(user.getUserName(),user.getUserPassword(),user.getUserType());
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User updateUserInformation(User updateUser, Long userId) {
       User user= userRepository.findById(userId).get();
       updateUser.setId(user.getId());
        return userRepository.save(updateUser);
    }

    @Override
    public User findUserByuserMail(String userMail) {
        return userRepository.findByUserMail(userMail);
    }

    @Override
    public User findUserByuserContact(String userContact) {
        return userRepository.findByUserContact(userContact);
    }
}
