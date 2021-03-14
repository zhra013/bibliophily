package com.ase.application.controller;

import com.ase.application.Service.PostReviewService;
import com.ase.application.Service.PostService;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.dto.SharedPostDTO;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remondis.remap.Mapper;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ValidationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostReviewService postReviewService;

    @Autowired
    private Mapper<PostDTO, Post> dtoToPostMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @Autowired
    private Mapper<Post, SharedPostDTO> postToSharePostDTOMapper;


    @RequestMapping(value = "/validate", method = GET)
    public void ValidateLogin(@RequestParam("action") String action, HttpServletResponse response, HttpServletRequest request) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper obj = new ObjectMapper();

        switch (action){
            case "validateEmail":{
                String userMail = request.getParameter("userMail");
                User user = userService.findUserByUserMail(userMail);

                if(Objects.isNull(user)){
                    out.print("Allow");
                }
                else{
                    out.print("User Already Exist");
                }
                break;
            }
            case "validateContact":{
                String userContact = request.getParameter("userContact");
                User user = userService.findUserByUserContact(userContact);

                if(Objects.isNull(user)){
                    out.print("Allow");
                }
                else{
                    out.print("Contact Number Already Exist");
                }
                break;
            }
            case "validateUserName":{
                String userName = request.getParameter("userName");

                User user = userService.findUserByUserName(userName);

                if(user==null){
                    out.print("Allow");
                } else {
                    out.print("UserName Already Exist");
                }
                break;
            }
            default:
                break;
        }
    }

    @RequestMapping(value = "/validate/review", method = RequestMethod.GET)
    public void ValidatePostReview(@RequestParam("postId") Long postId, HttpServletResponse response, @RequestParam("userId") Long userId) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Post post = postService.getPostById(postId);
        if (post.getPostReview().stream().anyMatch(postReview -> postReview.getReviewer().getId().equals(userId))) {
            out.print("No");
        } else {
            out.print("Allow");
        }
    }

    @RequestMapping(value = "/list/pagination/page", method = RequestMethod.GET)
    public void postListPaginated(@RequestParam(required = false) Long userId,
                                  @RequestParam(required = false) boolean excludeOwner,
                                  @RequestParam int page,
                                  HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Post> postList = new ArrayList<>();
        PrintWriter out = response.getWriter();
        ObjectMapper obj = new ObjectMapper();
        if (userId == null || userId == 0) {
            //modelMap.put("isDeleted", "no");
            postList.addAll(postService.getFilteredPostList(0L, page, excludeOwner));
//            postList.addAll(postService.getPosts());
        }
        if (excludeOwner && (userId != null || userId != 0)) {
            postList.addAll(postService.getFilteredPostList(userId, page, true));
            //code to sort and remove post of user id
//            postList.addAll(postService.getPosts().stream().filter(post -> !post.getUploader().getId().equals(userId)).collect(Collectors.toList())
//                    .stream().sorted((post, t1) -> post.getDate().compareTo(t1.getDate())).collect(Collectors.toList()));
            //modelMap.put("isDeleted", "no");
        } else {
            //modelMap.put("isDeleted", "yes");
            postList.addAll(postService.getFilteredPostList(userId, page, excludeOwner));
//            postList.addAll(postService.getPostsByUploaderId(userId));
        }

        List<PostDTO> postDTOS = new ArrayList<>();

        postList.forEach(post -> {

            PostDTO postDTO = postToDTOMapper.map(post);

            if (postDTO.getIsShared() != null && postDTO.getIsShared().equals(Boolean.TRUE)) {
                Post sharedPost = postService.getPostById(postDTO.getSharedPostId());
                SharedPostDTO sharePostDTO = postToSharePostDTOMapper.map(sharedPost);
                AtomicInteger rating = new AtomicInteger();
                AtomicInteger total = new AtomicInteger();
                sharedPost.getPostReview().forEach(postReview -> {
                    if (postReview.getRating() != 0) {
                        rating.addAndGet(postReview.getRating());
                        total.addAndGet(1);
                    }
                });
                sharePostDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
                UserServiceImpl.decryptUserDTO(sharePostDTO.getUploader());
                postDTO.setPostShared(sharePostDTO);
            } else {
                AtomicInteger rating = new AtomicInteger();
                AtomicInteger total = new AtomicInteger();
                post.getPostReview().forEach(postReview -> {
                    if (postReview.getRating() != 0) {
                        rating.addAndGet(postReview.getRating());
                        total.addAndGet(1);
                    }
                });
                postDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
            }
            postDTOS.add(postDTO);

        });
        postDTOS.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        JSONArray arr = new JSONArray(postDTOS);
       //String posts = obj.writeValueAsString(postDTOS);
        out.print(arr);
    }

}
