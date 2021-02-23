package com.ase.application.Service;

import com.ase.application.Repository.PostRepository;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Override
    public void uploadBook(Post post) {
        User uploader = userService.findUserById(post.getUploader().getId());
        post.setDate(LocalDate.now());
        post.setUploader(uploader);
        postRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUploaderId(Long uploaderId) {
        return postRepository.findPostByUploaderId(uploaderId);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).get();
    }
}
