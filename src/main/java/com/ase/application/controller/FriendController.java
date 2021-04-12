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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This controller will handle request regarding friends
 */
@Controller
@RequestMapping(value = "/friend/")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * this method will send friend request to another user
     * @param friendId another userId
     * @param userId loggedIn user Id
     * @return redirect to users.jsp
     */
    @RequestMapping(value = "{userId}/sendRequest/{friendId}", method = RequestMethod.GET)
    public String sendFriendRequest(@PathVariable(value = "friendId") long friendId,
                                    @PathVariable(value = "userId") long userId) {
        friendService.sendFriendRequest(userId,friendId);
        return "redirect:/users?userId="+userId;
    }

    /**
     * It will fetch all the friends of user
     * @param userId loggedId userId
     * @param modelMap to transfer data between FE and BE
     * @return redirect to user.jsp
     */
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

    /**
     * This method used to decline the friend request
     * @param userId loggedIn userId
     * @param friendId friendRequestId
     * @return redirect to users.jsp
     */
    @RequestMapping(value = "/{friendId}/user/{userId}", method = RequestMethod.GET)
    public String declineFriendRequest(@PathVariable(value = "userId") long userId,
                                     @PathVariable(value = "friendId") long friendId) {
        friendService.deleteFriend(userId,friendId);
        return "redirect:/friend/"+userId+"/getFriends";
    }

}
