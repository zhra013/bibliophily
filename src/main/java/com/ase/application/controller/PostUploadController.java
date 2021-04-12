package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.dto.SharedPostDTO;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This controller will handle request regarding sharing and uploading post
 */
@Controller
@RequestMapping(value = "/post")
public class PostUploadController {

    @Autowired
    private Mapper<PostDTO, Post> dtoToPostMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private Mapper<Post, SharedPostDTO> postToSharePostDTOMapper;

    /**
     * this method fill fetch data to be needed at FE while uploading post
     * @param modelMap to transfer data between FE and BE
     * @return redirect to postUpload.jsp
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String viewPostUploadPage(ModelMap modelMap) {
        PostDTO post = new PostDTO();
        modelMap.put("post", post);
        modelMap.put("postTypeList", new ArrayList<>(Arrays.asList("Book", "Article", "Magazine", "Comic")));
        return "postUpload";
    }

    /**
     * This method will upload the post on user timeline
     * @param postDTO data of post to be uploaded
     * @param result for error handling
     * @param modelMap to transfer data between FE and BE
     * @return redirect to PostViews.jsp
     * @throws IOException
     */
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
        postService.uploadPost(post);

        return "redirect:/post/list/page?userId=" + post.getUploader().getId() + "&excludeOwner=false&page=0";
    }

    /**
     * This method will fetch the posts based on user request
     * @param userId LoggedIn userId
     * @param excludeOwner to view post of other user or not
     * @param modelMap to transfer data between FE and BE
     * @return redirect to PostViews.jsp
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String postList(@RequestParam(required = false) Long userId,
                           @RequestParam(required = false) boolean excludeOwner,
                           ModelMap modelMap) {
        List<Post> postList = new ArrayList<>();

        if (userId == null || userId == 0) {
            modelMap.put("isDeleted", "no");
            postList.addAll(postService.getPosts());
        }
        if (excludeOwner && (userId != null || userId != 0)) {
            //code to sort and remove post of user id
            postList.addAll(postService.getPosts().stream().filter(post -> !post.getUploader().getId().equals(userId)).collect(Collectors.toList())
                    .stream().sorted((post, t1) -> post.getDate().compareTo(t1.getDate())).collect(Collectors.toList()));
            modelMap.put("isDeleted", "no");
        } else {
            modelMap.put("isDeleted", "yes");
            postList.addAll(postService.getPostsByUploaderId(userId));
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
            PostDTO postDTO = postToDTOMapper.map(post);
            postDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
            postDTOS.add(postDTO);
        });

        postDTOS.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        modelMap.put("posts", postDTOS);
        return "PostsView";
    }

    /**
     * This method will fetch the post details
     * @param modelMap to transfer data between FE and BE
     * @param postId Id of Post
     * @param users LoggedInd user Id
     * @return redirect to postDetail.jsp
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String PostDetails(ModelMap modelMap, @RequestParam("postId") Long postId, @RequestParam(required = false) Long users) {

        Post post = postService.getPostById(postId);
        UserServiceImpl.decryptUser(post.getUploader());
        modelMap.put("post", post);
        if (users != null)
            return "userPostDetail";
        else
            return "postDetail";
    }

    /**
     * this method is used to delete the post
     * @param modelMap to transfer data between FE and BE
     * @param postId Id of post to be deleted
     * @param userId LoggedIn userId
     * @return redirect to PostsView.jsp
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String PostDelete(ModelMap modelMap, @RequestParam("postId") Long postId, @RequestParam Long userId) {

        postService.deletePost(postId);
        return "redirect:/post/list/page?userId=" + userId + "&excludeOwner=false&page=0";
    }

    /**
     * this method is used to get cover photo of post
     * @param postId Id of Post
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/coverPhoto", method = RequestMethod.GET)
    public void getImage(@RequestParam("postId") Long postId, HttpServletResponse response) throws IOException {

        Post post = postService.getPostById(postId);

        InputStream inputStream = new ByteArrayInputStream(post.getCoverPhoto());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        IOUtils.copy(inputStream, response.getOutputStream());
    }

    /**
     * This method is used to search the post
     * @param userId LoggedIn userId
     * @param excludeOwner to show post of LoggedIn user or not
     * @param searchParam search input of user
     * @param response
     * @return the post matches the searchParam
     */
    @RequestMapping(value = "/list/search", method = RequestMethod.GET)
    public List<PostDTO> postListSearch(@RequestParam(required = false) Long userId,
                                        @RequestParam(required = false) boolean excludeOwner,
                                        @RequestParam String searchParam,
                                        HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            PostDTO postDTO = postToDTOMapper.map(post);
            postDTO.setRating(total.get() == 0 ? 0 : rating.get() / total.get());
            postDTOS.add(postDTO);
        });
        postDTOS.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        return postDTOS;
    }

    /**
     * This method is used to fetch paginated response of Posts
     * @param userId LoggedIn userId
     * @param excludeOwner to exclude LoggedIn user post or not
     * @param users to verify user
     * @param page page number
     * @param modelMap to transfer data between FE and BE
     * @return redirect to postView.jsp
     */
    @RequestMapping(value = "/list/page", method = RequestMethod.GET)
    public String postListPaginated(@RequestParam(required = false) Long userId,
                                    @RequestParam(required = false) boolean excludeOwner,
                                    @RequestParam(required = false) boolean users,
                                    @RequestParam int page,
                                    ModelMap modelMap) {
        List<Post> postList = new ArrayList<>();

        if (userId == null || userId == 0) {
            modelMap.put("isDeleted", "no");
            postList.addAll(postService.getFilteredPostList(0L, page, excludeOwner));
//            postList.addAll(postService.getPosts());
        }
        if (excludeOwner && (userId != null || userId != 0)) {
            postList.addAll(postService.getFilteredPostList(userId, page, true));
//            postList.addAll(postService.getPosts().stream().filter(post -> !post.getUploader().getId().equals(userId)).collect(Collectors.toList())
//                    .stream().sorted((post, t1) -> post.getDate().compareTo(t1.getDate())).collect(Collectors.toList()));
            modelMap.put("isDeleted", "no");
        } else if (users && excludeOwner == false && (userId != null || userId != 0)) {
            modelMap.put("isDeleted", "no");
            postList.addAll(postService.getFilteredPostList(userId, page, excludeOwner));
        } else {
            modelMap.put("isDeleted", "yes");
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
        modelMap.put("posts", postDTOS);
        return "PostsView";
    }

    /**
     * This method used to share a post
     * @param userId LoggedIn user Id
     * @param postId Id of post ehich to be shared
     * @param influencerId the UserId who influence
     * @param session to get current user
     * @param comment comment while sharing post
     * @return redirect to postView.jsp
     * @throws IOException
     */
    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String sharePost(@RequestParam(required = false) Long userId,
                            @RequestParam(required = false) Long postId,
                            @RequestParam(required = false) Long influencerId,
                            HttpSession session,
                            @RequestParam String comment) throws IOException {

       User user = (User)session.getAttribute("currentUser");

       if(user.getId().equals(influencerId))
           influencerId=0L;

        postService.sharePost(userId, postId, comment,influencerId);

        return "redirect:/post/list/page?userId=" + userId + "&excludeOwner=false&page=0";
    }
}
