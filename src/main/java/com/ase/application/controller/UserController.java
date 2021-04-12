package com.ase.application.controller;

import com.ase.application.Service.EmailService;
import com.ase.application.Service.FriendService;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.entity.Friend;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This controller will handle user details requests
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;
    @Autowired
    private FriendService friendService;

    /**
     * This method is used fetch user details
     * @param modelMap to transfer data between FE and BE
     * @param session to fetch LoggedIn user
     * @return redirect to profile.jsp
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String viewProfile(ModelMap modelMap, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        user= userService.findUserById(user.getId());
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "profile";
    }

    /**
     * this method used to redirect to home page
     * @return redirect to home.jsp
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * This method is used fetch user details for update
     * @param userId LoggedIn userId
     * @param modelMap  to transfer data between FE and BE
     * @return redirect to editProfile.jsp
     */
    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String viewEditProfilePage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "editProfile";
    }

    /**
     * This method used to update profile of user
     * @param user user's updated data
     * @param userId LoggedIn userId
     * @return redirect to profile.jsp
     */
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String updateUserProfile(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        userService.updateUserInformation(user, userId);
        return "redirect:http://localhost:9090/profile";
    }

    /**
     * This method user to get password which need to be changed
     * @param userId LoggedIn user Id
     * @param modelMap to transfer data between FE and BE
     * @return redirect to changePassword.jsp
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String viewUpdatePasswordPage(@RequestParam("userId") Long userId, ModelMap modelMap) {
        User user = userService.findUserById(userId);
        UserServiceImpl.decryptUser(user);
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "changePassword";
    }

    /**
     * This method is used to update a password of user
     * @param user updated password
     * @param userId LoggedIn userId
     * @return redirect to profile.jsp
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String updateUserPassword(@ModelAttribute User user, @RequestParam("userId") Long userId) {
        user = userService.updateUserPassword(user, userId);
        UserServiceImpl.decryptUser(user);
        emailService.SendEmailChangePassword(user);
        return "redirect:http://localhost:9090/profile";
    }

    /**
     * This method is used for logout of useer
     * @param session current user session
     * @return redirect to login.jsp
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser");
        session.invalidate();
        return "redirect:login";
    }

    /**
     * This method used to fetch all registered user in the system
     * @param userId LoggedIn user Id
     * @param modelMap to transfer data between FE and BE
     * @return redirect to users.jsp
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(@RequestParam("userId") Long userId, ModelMap modelMap) {
        List<User> users = userService.getUsers();
        users=users.stream().filter(user -> user.getUserType().equals(UserType.USER)).collect(Collectors.toList());
        List<User> newUsers = new ArrayList<>();
        newUsers.addAll(users.stream().filter(user -> !user.getId().equals(userId)).collect(Collectors.toList()));
        newUsers.forEach(UserServiceImpl::decryptUser);
        newUsers.forEach(user -> {
            Friend friend = friendService.validateFriend(userId,user.getId());
            if(Objects.isNull(friend)){
                user.setFriendStatus("Allow");
            }else if(friend.isAcceptance()){
                user.setFriendStatus("Friends");
            }else {
                user.setFriendStatus("Requested");
            }
        });

        modelMap.put("usersList", newUsers);
        modelMap.put("friend","no");
        return "users";
    }

    /**
     * This method used to get post of user
     * @param userId LoggedIn user Id
     * @param modelMap to transfer data between FE and BE
     * @return redirect to PostsView.jsp
     */
    @RequestMapping(value = "/view/post", method = RequestMethod.GET)
    public String getPosts(@RequestParam("userId") Long userId, ModelMap modelMap) {
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
