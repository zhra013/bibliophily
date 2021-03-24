package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.dto.PostDTO;
import com.ase.application.dto.TopContributorDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private Mapper<User, TopContributorDTO> userToTopContributorDTOMapper;

    @Autowired
    private Mapper<Post, PostDTO> postToDTOMapper;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String reportPage(ModelMap modelMap) {
        return "reportPage";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String topContributors(ModelMap modelMap) {
        List<User> topContributor = userService.getTopContributor();
        List<TopContributorDTO> topContributorDTOs=new ArrayList<>();
        topContributor.forEach(user -> {
            UserServiceImpl.decryptUser(user);
            TopContributorDTO topContributorDTO=userToTopContributorDTOMapper.map(user);
            topContributorDTO.setTotalPost(user.getPosts().size());
            topContributorDTOs.add(topContributorDTO);

        });
        modelMap.put("topContributor", topContributorDTOs);
        List<Post> topSharedPost =  postService.getPosts();
        List<PostDTO> sharedPostDTO = postToDTOMapper.map(topSharedPost);
        //sharedPostDTO.forEach(postDTO -> UserServiceImpl.decryptUserDTO(postDTO.getUploader()));
        List<PostDTO> sharedPostDTO1 = sharedPostDTO.stream().filter(postDTO -> postDTO.getIsShared().equals(Boolean.FALSE)).collect(Collectors.toList());
        sharedPostDTO1.sort(Comparator.comparingLong(PostDTO::getShareCounter).reversed());
        modelMap.put("postContributionList", sharedPostDTO1);
        return "reportPage";
    }

    /*@RequestMapping(value = "/topSharedPost", method = RequestMethod.GET)
    public String topSharedBooks(ModelMap modelMap) {

        return "topSharedBooksPage";
    }*/
}
