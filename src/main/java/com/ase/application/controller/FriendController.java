package com.ase.application.controller;

import com.ase.application.Service.FriendService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.entity.Friend;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/friend/")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "{userId}/sendRequest/{friendId}", method = RequestMethod.GET)
    public String sendFriendRequest(@PathVariable(value = "friendId") long friendId,
                                    @PathVariable(value = "userId") long userId) {
        friendService.sendFriendRequest(userId,friendId);
        return "redirect:/users?userId="+userId;
    }

    @RequestMapping(value = "{userId}/getFriends", method = RequestMethod.GET)
    public String getFriends(@PathVariable(value = "userId") long userId, ModelMap modelMap) {
        List<User> friends =friendService.getFriends(userId);
        friends=friends.stream().filter(user -> user.getUserType().equals(UserType.USER)).collect(Collectors.toList());
        List<User> newUsers = new ArrayList<>();
        newUsers.addAll(friends.stream().filter(user -> !user.getId().equals(userId)).collect(Collectors.toList()));
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
        modelMap.put("friend","yes");
        return "users";
    }

    @RequestMapping(value = "/{friendId}/user/{userId}", method = RequestMethod.GET)
    public String declineFriendRequest(@PathVariable(value = "userId") long userId,
                                     @PathVariable(value = "friendId") long friendId, HttpServletResponse response) {
        friendService.deleteFriend(userId,friendId);
        return "/friend/"+userId+"/getFriends";
    }


}
