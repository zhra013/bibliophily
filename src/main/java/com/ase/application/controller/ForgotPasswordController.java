package com.ase.application.controller;

import com.ase.application.Service.EmailServiceImpl;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * This controoller will handle request regarding forgotPassword
 */
@Controller
@RequestMapping(value = "/forgotPassword")
public class ForgotPasswordController {
    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private UserService userService;

    /**
     * This method will fetch the password of user
     * @param modelMap to transfer data between FE and BE
     * @return redirect to forgotPassword.jsp
     */
    @RequestMapping(method = GET)
    public String view(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "forgotPassword";
    }

    /**
     * This method will send mail on forgot password request
     * @param user user Id
     * @param bindingResult for error handling
     * @param modelMap to transfer data between FE and BE
     * @return redirect to login.jsp
     */
    @RequestMapping(method = POST)
    public String sendEmail(@ModelAttribute User user,
                       BindingResult bindingResult,
                       ModelMap modelMap) {

        user = userService.findUserByUserMail(user.getUserMail());

        if (Objects.isNull(user)) {
            ObjectError objectError = new ObjectError("username", "User name or password is invalid");
            bindingResult.addError(objectError);
        }

        if (bindingResult.hasErrors()) {
            modelMap.put("error",bindingResult);
            modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
            return "entry";
        } else {
            UserServiceImpl.decryptUser(user);
            email.SendEmailForgotPassword(user);
        }
        return "redirect:http://localhost:9090/login";
    }
}
