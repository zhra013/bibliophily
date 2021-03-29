package com.ase.application;

import com.ase.application.Repository.UserRepository;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test
    void userRegistration() {
        doReturn(this.saveUser()).when(this.userRepository).save(this.saveUser());
        this.userServiceImpl.userRegistration(this.saveUser());
        assertThat(this.saveUser().toString()).contains("xyz");
    }

    @Test
    void login() {
        given(this.userRepository.findAll())
                .willReturn(null);
        User user =this.userServiceImpl.login(null);
        assertThat(user).isEqualTo(null);
    }

    @Test
    void findUserById() {
        given(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(this.saveUser()));
        User user =this.userServiceImpl.findUserById(ArgumentMatchers.anyLong());
        assertThat(user.toString()).contains("xyz");
    }

    @Test
    void findUserByEmail() {
        given(this.userRepository.findByUserMail(ArgumentMatchers.anyString()))
                .willReturn(this.saveUser());
        User user =this.userServiceImpl.findUserByUserMail(ArgumentMatchers.anyString());
        assertThat(user.toString()).contains("xyz");
    }

    @Test
    void findUserByContact() {
        given(this.userRepository.findByUserContact(ArgumentMatchers.anyString()))
                .willReturn(this.saveUser());
        User user =this.userServiceImpl.findUserByUserContact(ArgumentMatchers.anyString());
        assertThat(user.toString()).contains("xyz");
    }

    @Test
    void findUserByUserName() {
        given(this.userRepository.findByUserName(ArgumentMatchers.anyString()))
                .willReturn(null);
        User user =this.userServiceImpl.findUserByUserName("xyz");
        assertThat(user).isEqualTo(null);
    }

    @Test
    void UpdateUserPassword() {
        given(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(this.saveUser()));
        doReturn(this.saveUser()).when(this.userRepository).save(this.saveUser());
        User user =this.userServiceImpl.updateUserPassword(this.getUser(),ArgumentMatchers.anyLong());
        assertThat(user.toString()).contains("xyz");
    }

    @Test
    void getAllUser() {
        given(this.userRepository.findAll())
                .willReturn(List.of(this.saveUser()));
        List<User> user =this.userServiceImpl.getUsers();
        assertThat(user.size()).isEqualTo(1);
    }

    @Test
    void getTopContributor() {
        given(this.userRepository.findTopContributor())
                .willReturn(List.of(this.saveUser()));
        List<User> user =this.userServiceImpl.getTopContributor();
        assertThat(user.size()).isEqualTo(1);
    }

    @Test
    void getUserByUserType() {
        given(this.userRepository.findByUserType(ArgumentMatchers.any()))
                .willReturn(this.saveUser());
        User user =this.userServiceImpl.findByUserType(UserType.USER);
        assertThat(user.toString()).contains("xyz");
    }

    private User saveUser(){
        User user =new User();
        user.setFullName("xyz");
        user.setUserType(UserType.USER);
        user.setUserContact("1234567890");
        user.setUserMail("xyz@gmail.com");
        user.setUserPassword("12345");
        user.setUserName("xyzzz");
        return user;
    }

    private User getUser(){
        User user =new User();
        user.setId(1L);
        user.setFullName("xyz");
        user.setUserType(UserType.USER);
        user.setUserContact("1234567890");
        user.setUserMail("xyz@gmail.com");
        user.setUserPassword("12345");
        user.setUserName("xyzzz");
        return user;
    }
}
