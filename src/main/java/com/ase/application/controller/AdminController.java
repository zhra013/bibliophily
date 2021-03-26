package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.dto.SharedPostDTO;
import com.ase.application.dto.TopActiveUserDTO;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.remondis.remap.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private Mapper<User, TopActiveUserDTO> userToTopContributorDTOMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String topContributors(ModelMap modelMap) {

        List<User> topContributor = userService.getTopContributor();
        List<TopActiveUserDTO> topActiveUserDTOS =new ArrayList<>();
        topContributor.forEach(user -> {
            UserServiceImpl.decryptUser(user);
            TopActiveUserDTO topActiveUserDTO =userToTopContributorDTOMapper.map(user);
            topActiveUserDTO.setTotalPost(user.getPosts().size());
            topActiveUserDTOS.add(topActiveUserDTO);

        });
        modelMap.put("topContributor", topActiveUserDTOS.stream().limit(10).collect(Collectors.toList()));

        List<Post> topSharedPost =  postService.getPosts();
        List<PostDTO> sharedPostDTO = postToDTOMapper.map(topSharedPost);
        List<PostDTO> sharedPostDTO1 = sharedPostDTO.stream().filter(postDTO -> postDTO.getIsShared().equals(Boolean.FALSE)).collect(Collectors.toList());
        sharedPostDTO1.sort(Comparator.comparingLong(PostDTO::getShareCounter).reversed());

        modelMap.put("postContributionList",  sharedPostDTO1.stream().limit(10).collect(Collectors.toList()));

        List<Post> posts= postService.getPosts();
        List<PostDTO> postDTOS = new ArrayList<>();
        posts.forEach(post -> {

                    PostDTO postDTO = postToDTOMapper.map(post);

                    if (postDTO.getIsShared() != null && postDTO.getIsShared().equals(Boolean.FALSE)) {
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
        postDTOS.sort(Comparator.comparingLong(PostDTO::getRating).reversed());
        modelMap.put("postRatingList",  postDTOS.stream().limit(10).collect(Collectors.toList()));
        return "adminPage";
    }

}
