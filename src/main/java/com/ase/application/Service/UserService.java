package com.ase.application.Service;

import com.ase.application.entity.User;
import com.ase.application.entity.UserType;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void userRegistration(User user);

    public User login(User user);

    public User findUserById(Long userId);

    public User updateUserInformation(User updateUser, Long userId);

    public User findUserByUserMail(String userMail);

    public User findUserByUserContact(String userContact);

    public User findUserByUserName(String userName);

    public User updateUserPassword(User updateUser, Long userId);

    public List<User> getUsers();

    public List<User> getTopContributor();

    public User findByUserType(UserType userType);
}
