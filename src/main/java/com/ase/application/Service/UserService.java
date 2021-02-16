package com.ase.application.Service;

import com.ase.application.entity.User;

public interface UserService {

    public void userRegistration(User user);
    public User login(User user);
    public User findUserById(Long userId);
    public User updateUserInformation(User updateUser,Long userId);
    public User findUserByEmail(User user);
}
