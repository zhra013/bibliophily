package com.ase.application.Service;

import com.ase.application.entity.Friend;
import com.ase.application.entity.User;

import java.util.List;

public interface FriendService {
    public void sendFriendRequest(long userId,long friendId);
    public List<Friend> getFriendRequests(long userId);
    public void acceptFriendRequest(long userId,long friendRequestId);
    public List<User> getFriends(long userId);
    public Friend validateFriend(long userId, long friendId);
}
