package com.ase.application.controller;

import com.ase.application.Service.PostReviewService;
import com.ase.application.Service.PostService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.entity.Post;
import com.ase.application.entity.PostReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/post")
public class PostReviewController {

    @Autowired
    PostService postService;

    @Autowired
    PostReviewService postReviewService;

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String getReviewDetails(@RequestParam("postId") Long postId, ModelMap modelMap, @RequestParam(required = false) Long users) {
        Post post = postService.getPostById(postId);

        AtomicInteger rating = new AtomicInteger();
        AtomicInteger total = new AtomicInteger();
        post.getPostReview().forEach(postReview -> {
            if (postReview.getRating() != 0) {
                rating.addAndGet(postReview.getRating());
                total.addAndGet(1);
            }
        });

        modelMap.put("rating", total.get() == 0 ? 0 : rating.get() / total.get());
        modelMap.put("postReview", new PostReview());
        UserServiceImpl.decryptUser(post.getUploader());
        modelMap.put("post", post);

        Map<Integer, String> ratings = new LinkedHashMap<>();

        ratings.put(1, "1 Star");
        ratings.put(2, "2 Star");
        ratings.put(3, "3 Star");
        ratings.put(4, "4 Star");
        ratings.put(5, "5 Star");


        modelMap.put("ratings", ratings);
        modelMap.put("reviewsList", post.getPostReview().stream().filter(postReview -> postReview.getComment() != null).collect(Collectors.toList()));
        if (users == null)
            return "postReview";
        else
            return "userPostReview";
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String savePostReview(@ModelAttribute PostReview review, @RequestParam(required = false) Long users) {
        postReviewService.saveReview(review);
        if (users == null)
            return "redirect:http://localhost:9090/post/review?postId=" + review.getPost().getId();
        else
            return "redirect:http://localhost:9090/post/review?postId=" + review.getPost().getId() + "&users=1";
    }
}
