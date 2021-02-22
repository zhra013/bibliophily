package com.ase.application.controller;

import com.ase.application.Service.UserService;
import com.ase.application.dto.SignUpDTO;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import com.remondis.remap.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;
    private static Mapper<SignUpDTO, User> signUpDTOToUserMapper;

    public SignUpController(UserService userService,Mapper<SignUpDTO, User> signUpDTOToUserMapper) {
        this.userService = userService;
        this.signUpDTOToUserMapper=signUpDTOToUserMapper;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUpForm(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
        return "entry";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute SignUpDTO signUp) {
        User user=signUpDTOToUserMapper.map(signUp);
        user.setUserType(UserType.USER);
        userService.userRegistration(user);
        return "redirect:http://localhost:9090/login";
    }

}
