package com.ase.application.controller;

import com.ase.application.Service.EmailService;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.remondis.remap.Mapper;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String viewProfile(ModelMap modelMap, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        user= userService.findUserById(user.getId());
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "profile";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap modelMap, HttpSession session) {
        return "home";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String viewEditProfilePage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String updateUserProfile(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        userService.updateUserInformation(user, userId);
        return "redirect:http://localhost:9090/profile";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String viewUpdatePasswordPage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String updateUserPassword(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        user = userService.updateUserPassword(user, userId);
        UserServiceImpl.decryptUser(user);
        emailService.SendEmailChangePassword(user);
        return "redirect:http://localhost:9090/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap modelMap, HttpSession session) {
        session.removeAttribute("currentUser");
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(@RequestParam("userId") Long userId, ModelMap modelMap, HttpSession session) {
        List<User> users = userService.getUsers();
        List<User> newUsers = new ArrayList<>();
        newUsers.addAll(users.stream().filter(user -> !user.getId().equals(userId)).collect(Collectors.toList()));
        newUsers.forEach(UserServiceImpl::decryptUser);
        modelMap.put("usersList", newUsers);
        return "users";
    }

    @RequestMapping(value = "/view/post", method = RequestMethod.GET)
    public String getPosts(@RequestParam("userId") Long userId, ModelMap modelMap, HttpSession session) {
        User user = userService.findUserById(userId);
        List<PostDTO> postDTOS = new ArrayList<>();
        AtomicInteger rating = new AtomicInteger();
        AtomicInteger total = new AtomicInteger();
        user.getPosts().forEach(post -> {
            post.getPostReview().forEach(postReview -> {
                if (postReview.getRating() != 0) {
                    rating.addAndGet(postReview.getRating());
                    total.addAndGet(1);
                }
            });
            PostDTO postDTO = postToDTOMapper.map(post);
            postDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
            postDTOS.add(postDTO);
        });
        postDTOS.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        modelMap.put("posts", postDTOS);
        return "PostsView";
    }

}
