package com.ase.application.Service;

import com.ase.application.Repository.PostReviewRepository;
import com.ase.application.entity.Post;
import com.ase.application.entity.PostReview;
import com.ase.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostReviewServiceImpl implements PostReviewService {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostReviewRepository postReviewRepository;

    @Override
    public void saveReview(PostReview postReview) {
        User reviewedBy = userService.findUserById(postReview.getReviewer().getId());
        Post post = postService.getPostById(postReview.getPost().getId());
        postReview.setPost(post);
        postReview.setDate(LocalDate.now());
        postReview.setReviewer(reviewedBy);
        postReviewRepository.save(postReview);
    }
}
