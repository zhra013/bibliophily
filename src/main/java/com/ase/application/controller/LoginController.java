package com.ase.application.controller;

import com.ase.application.Service.UserService;
import com.ase.application.dto.SignUpDTO;
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

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public String view(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "entry";
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute User user,
                       BindingResult bindingResult,
                       ModelMap modelMap,
                       HttpSession session) {
        user = userService.login(user);

        if (Objects.isNull(user)) {
            ObjectError objectError = new ObjectError("username", "User name or password is invalid");
            bindingResult.addError(objectError);
        }

        if (bindingResult.hasErrors()) {
            modelMap.put("error",bindingResult);
            modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
            return "entry";
        } else {
            session.setAttribute("currentUser", user);
        }
        return "redirect:http://localhost:9090/profile";
    }
}
