package com.ase.application.Service;

import com.ase.application.entity.User;

public interface EmailService {
    public void SendEmailForgotPassword(User user);

    public void SendEmailChangePassword(User user);
}
