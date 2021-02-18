package com.ase.application.Service;

import com.ase.application.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailService {
    public void SendEmailWithAttach(User user);
}
