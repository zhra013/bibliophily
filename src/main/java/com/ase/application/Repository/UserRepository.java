package com.ase.application.Repository;

import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUserNameAndUserPasswordAndUserType(String userName, String userPassword, UserType userType);

    User findByUserMail(String userMail);

    User findByUserContact(String userContact);

    User findByUserName(String userName);

    @Override
    List<User> findAll();

    @Query(value = "SELECT user FROM User user JOIN Post post ON (user.id = post.uploader.id) GROUP BY post.uploader.id ORDER BY COUNT(post.uploader.id)")
    List<User> findTopContributor();

    User findByUserType(UserType userType);

}
