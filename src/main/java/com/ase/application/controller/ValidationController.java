package com.ase.application.controller;

import com.ase.application.Service.UserService;
import com.ase.application.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/validate")
public class ValidationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public void ValidateLogin(@RequestParam("action") String action, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("Inside Validation");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper obj = new ObjectMapper();

        switch (action){
            case "validateEmail":{
                String userMail = request.getParameter("userMail");
                User user = userService.findUserByUserMail(userMail);

                if(Objects.isNull(user)){
                    out.print("Allow");
                }
                else{
                    out.print("User Already Exist");
                }
                break;
            }
            case "validateContact":{
                String userContact = request.getParameter("userContact");
                User user = userService.findUserByUserContact(userContact);

                if(Objects.isNull(user)){
                    out.print("Allow");
                }
                else{
                    out.print("Contact Number Already Exist");
                }
                break;
            }
            case "validateUserName":{
                String userName = request.getParameter("userName");

                User user = userService.findUserByUserName(userName);

                if(Objects.isNull(user)){
                    out.print("Allow");
                }
                else{
                    out.print("UserName Already Exist");
                }
                break;
            }
            default:
                break;
        }
    }

}
