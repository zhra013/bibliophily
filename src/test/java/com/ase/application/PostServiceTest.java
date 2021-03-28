package com.ase.application;

import com.ase.application.Repository.PostRepository;
import com.ase.application.Service.PostServiceImpl;
import com.ase.application.Service.UserService;
import com.ase.application.entity.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {
    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserService userService;

    @Test
    void uploadPost() {
        doReturn(this.getUser()).when(this.userService).findUserById(ArgumentMatchers.anyLong());
        doReturn(this.savePost()).when(this.postRepository).save(this.savePost());
        this.postServiceImpl.uploadPost(this.savePost());
        assertThat(this.savePost().toString()).contains("xyz");
    }

    @Test
    void getPosts() {
        doReturn(List.of(this.savePost())).when(this.postRepository).findAll();
       List<Post> posts = this.postServiceImpl.getPosts();
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void getPostByUploaderId() {
        doReturn(List.of(this.savePost())).when(this.postRepository).findPostByUploaderId(ArgumentMatchers.anyLong());
        List<Post> posts = this.postServiceImpl.getPostsByUploaderId(ArgumentMatchers.anyLong());
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void getPostBySearch() {
        doReturn(List.of(this.savePost())).when(this.postRepository).findPostBySearch(ArgumentMatchers.anyString());
        List<Post> posts = this.postServiceImpl.getPostsBySearch(ArgumentMatchers.anyString());
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void getPostsByUploaderIdAndSearch() {
        doReturn(List.of(this.savePost())).when(this.postRepository).findPostByUploaderIdAndSearch(ArgumentMatchers.anyLong(),ArgumentMatchers.anyString());
        List<Post> posts = this.postServiceImpl.getPostsByUploaderIdAndSearch(ArgumentMatchers.anyLong(),ArgumentMatchers.anyString());
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void getPostById() {
        doReturn(Optional.of(this.savePost())).when(this.postRepository).findById(ArgumentMatchers.anyLong());
        Post posts = this.postServiceImpl.getPostById(ArgumentMatchers.anyLong());
        assertThat(posts.toString()).contains("xyz");
    }

    private Post savePost(){
        Post post=new Post();
        post.setIsShared(Boolean.FALSE);
        post.setShareCounter(1);
        post.setBlog("sgshjdjdk");
        post.setDate(LocalDate.now());
        post.setUploader(this.getUser());
        post.setAuthor("xyz");
        post.setEdition("1");
        post.setPostReview(List.of(new PostReview()));
        post.setPostType(PostType.Article);
        post.setTitle("abcd");
        return post;
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
