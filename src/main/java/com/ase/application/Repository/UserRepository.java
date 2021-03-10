package com.ase.application.Repository;

import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
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
}
