package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remondis.remap.Mapper;
import org.apache.catalina.filters.ExpiresFilter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    @Autowired
    private Mapper<PostDTO, Post> dtoToPostMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/search", method = RequestMethod.GET)
    public List<PostDTO> postListSearch(@RequestParam(required = false) Long userId,
                                        @RequestParam(required = false) boolean excludeOwner,
                                        @RequestParam String searchParam) {
        List<Post> postList = new ArrayList<>();

        if (userId == null || userId == 0) {
            postList.addAll(postService.getPostsBySearch(searchParam));
        }
        if (excludeOwner && (userId != null && userId != 0)) {
            //code to sort and remove post of user id
            postList.addAll(postService.getPostsBySearch(searchParam).stream().filter(post -> !post.getUploader().getId().equals(userId)).collect(Collectors.toList())
                    .stream().sorted((post, t1) -> post.getDate().compareTo(t1.getDate())).collect(Collectors.toList()));

        } else {
            postList.addAll(postService.getPostsByUploaderIdAndSearch(userId, searchParam));
        }

        List<PostDTO> postDTOS = new ArrayList<>();
        AtomicInteger rating = new AtomicInteger();
        AtomicInteger total = new AtomicInteger();
        postList.forEach(post -> {
            post.getPostReview().forEach(postReview -> {
                if (postReview.getRating() != 0) {
                    rating.addAndGet(postReview.getRating());
                    total.addAndGet(1);
                }
            });
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setAuthor(post.getAuthor());
            postDTO.setTitle(post.getTitle());
            postDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
            postDTOS.add(postDTO);
        });
        postDTOS.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        return postDTOS;
    }


}
