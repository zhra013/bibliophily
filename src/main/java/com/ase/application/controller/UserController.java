package com.ase.application.controller;

import com.ase.application.Service.EmailService;
import com.ase.application.Service.UserService;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String viewProfile(ModelMap modelMap, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "profile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String viewEditProfilePage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String updateUserProfile(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        userService.updateUserInformation(user, userId);
        return "/profile";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String viewUpdatePasswordPage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String updateUserPassword(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        userService.updateUserPassword(user, userId);
        emailService.SendEmailChangePassword(user);
        return "/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap modelMap, HttpSession session) {
        session.removeAttribute("currentUser");
        session.invalidate();
        return "redirect:login";
    }

}
