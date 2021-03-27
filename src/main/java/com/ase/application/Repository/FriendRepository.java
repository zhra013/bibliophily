package com.ase.application.Repository;

import com.ase.application.entity.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository  extends QuerydslPredicateExecutor<Friend>, PagingAndSortingRepository<Friend, Long> {

    @Query(value = "select friends FROM Friend friends WHERE friends.friend.id = :userId AND friends.acceptance = :accept")
    List<Friend> getFriendRequests(long userId,boolean accept);

    @Query(value = "select friends FROM Friend friends WHERE friends.friend.id = :userId AND friends.acceptance = :accept And friends.id = :friendRequestId")
    Friend getFriendRequestById(long userId,long friendRequestId);

    @Query(value = "select friends FROM Friend friends WHERE friends.acceptance = :accept AND (friends.user.id = :userId OR friends.friend.id = :userId)")
    List<Friend> getFriends(long userId,boolean accept);

    @Query(value = "select friends FROM Friend friends WHERE  (friends.user.id = :userId AND friends.friend.id = :friendId) OR (friends.user.id = :friendId AND friends.friend.id = :userId)")
    Friend getFriendShip(long userId,long friendId);
}
