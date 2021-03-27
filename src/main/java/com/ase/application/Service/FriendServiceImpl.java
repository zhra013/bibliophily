package com.ase.application.Service;

import com.ase.application.Repository.FriendRepository;
import com.ase.application.entity.Friend;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendRepository friendRepository;
    @Override
    public void sendFriendRequest(long userId, long friendId) {
        User user=userService.findUserById(userId);
        User friendToBe=userService.findUserById(friendId);
        Friend friend=new Friend();
        friend.setUser(user);
        friend.setFriend(friendToBe);
        friend.setAcceptance(false);
        friendRepository.save(friend);
    }

    @Override
    public List<Friend> getFriendRequests(long userId) {
        return friendRepository.getFriendRequests(userId,false);
    }

    @Override
    public void acceptFriendRequest(long userId, long friendRequestId) {
       Friend friend = friendRepository.getFriendRequestById(userId,friendRequestId);
       friend.setAcceptance(true);
       friendRepository.save(friend);
    }

    @Override
    public List<User> getFriends(long userId) {
       List<Friend> friends= friendRepository.getFriends(userId,true);
        List<User> users = new ArrayList<>();
        if(friends.size()!=0){
            friends.forEach(friend -> {

                if(friend.getUser().getId()==userId){
                    users.add(friend.getFriend());
                }
                if(friend.getFriend().getId()==userId){
                    users.add(friend.getUser());
                }
            });

        }
       return users;
    }

    @Override
    public Friend validateFriend(long userId, long friendId) {
        return friendRepository.getFriendShip(userId,friendId);
    }

    @Override
    public void declineFriendRequest(long userId, long friendRequestId) {
        Friend friend = friendRepository.getFriendRequestById(userId,friendRequestId);
        friendRepository.delete(friend);
    }
}
