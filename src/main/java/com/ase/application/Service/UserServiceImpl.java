package com.ase.application.Service;

import com.ase.application.Repository.UserRepository;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.findByUserName(userName);
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
}
