package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.dto.PostDTO;
import com.ase.application.entity.Post;
import com.remondis.remap.Mapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostUploadController {

    @Autowired
    private Mapper<PostDTO, Post> dtoToPostMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String viewPostUploadPage(ModelMap modelMap) {
        PostDTO post = new PostDTO();
        modelMap.put("post", post);
        modelMap.put("postTypeList", new ArrayList<>(Arrays.asList("Book", "Article", "Magazine", "Comic")));
        return "postUpload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPost(@ModelAttribute PostDTO postDTO, BindingResult result, ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            modelMap.put("post", postDTO);
            modelMap.put("postTypeList", new ArrayList<>(Arrays.asList("Book", "Article", "Magazine", "Comic")));
            return "postUpload";
        }
        Post post = dtoToPostMapper.map(postDTO);
        post.setCoverPhoto(org.apache.commons.io.IOUtils.toByteArray(postDTO.getUploadedCoverPhoto().getInputStream()));
        System.out.println(post.getUploader().getId());
        postService.uploadBook(post);
        return "/post/list?userId=" + post.getUploader().getId();

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String postList(@RequestParam(required = false) Long userId,
                           @RequestParam(required = false) boolean excludeOwner,
                           ModelMap modelMap,
                           HttpSession session) {
        List<Post> postList = new ArrayList<>();

        if (userId == null || userId == 0) {
            postList.addAll(postService.getPosts());
        } else {
            postList.addAll(postService.getPostsByUploaderId(userId));
        }

        modelMap.put("posts", postList);
        return "PostsView";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String PostDetails(ModelMap modelMap, @RequestParam("postId") Long postId, @RequestParam(required = false) Long users) {

        Post post = postService.getPostById(postId);
        modelMap.put("post", post);
        if (users != null)
            return "userPostDetail";
        else
            return "postDetail";
    }

    @RequestMapping(value = "/coverPhoto", method = RequestMethod.GET)
    public void getImage(@RequestParam("postId") Long postId, HttpServletResponse response) throws IOException {

        Post post = postService.getPostById(postId);

        InputStream inputStream = new ByteArrayInputStream(post.getCoverPhoto());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
