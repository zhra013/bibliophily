package com.ase.application.controller;

import com.ase.application.Service.FriendService;
import com.ase.application.entity.Friend;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "{userId}/getRequest", method = RequestMethod.GET)
    public String getFriendRequest(@PathVariable(value = "userId") long userId) {
        List<Friend> friendRequests = friendService.getFriendRequests(userId);
        return "null";
    }

    @RequestMapping(value = "{userId}/acceptRequest/{friendRequestId}", method = RequestMethod.GET)
    public String acceptFriendRequest(@PathVariable(value = "userId") long userId,
                                      @PathVariable(value = "friendRequestId") long friendRequestId) {
        friendService.acceptFriendRequest(userId,friendRequestId);
        return "null";
    }

    @RequestMapping(value = "{userId}/getFriends", method = RequestMethod.GET)
    public String getFriends(@PathVariable(value = "userId") long userId) {
        List<User> friends =friendService.getFriends(userId);
        return "null";
    }

    @RequestMapping(value = "{userId}/declineRequest/{friendRequestId}", method = RequestMethod.GET)
    public String declineFriendRequest(@PathVariable(value = "userId") long userId,
                                      @PathVariable(value = "friendRequestId") long friendRequestId) {
        friendService.declineFriendRequest(userId,friendRequestId);
        return "null";
    }

}
