package com.ase.application.Service;

import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

        public void SendEmailForgotPassword(User user) {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(user.getUserMail());

            msg.setSubject("User password");
            msg.setText("the password is " + user.getUserPassword());

            javaMailSender.send(msg);
        }

    public void SendEmailChangePassword(User user) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getUserMail());

        msg.setSubject("User password Changed");
        msg.setText("the updated password is " + user.getUserPassword());

        javaMailSender.send(msg);
    }

}
