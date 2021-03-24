package com.ase.application.controller;

import com.ase.application.Service.PostService;
import com.ase.application.Service.UserService;
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

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private Mapper<User, TopContributorDTO> userToTopContributorDTOMapper;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String reportPage(ModelMap modelMap) {
        return "reportPage";
    }

    @RequestMapping(value = "/topUsers", method = RequestMethod.GET)
    public String topContributors(ModelMap modelMap) {
        List<User> topContributor = userService.getTopContributor();
        List<TopContributorDTO> topContributorDTOs=new ArrayList<>();
        topContributor.forEach(user -> {
            TopContributorDTO topContributorDTO=userToTopContributorDTOMapper.map(user);
            topContributorDTO.setTotalPost(user.getPosts().size());
            topContributorDTOs.add(topContributorDTO);
        });
        modelMap.put("topContributor", topContributorDTOs);
        return "contributionPage";
    }

    @RequestMapping(value = "/topSharedPost", method = RequestMethod.GET)
    public String topSharedBooks(ModelMap modelMap) {
        List<Post> topSharedPost =  postService.getPosts();
        topSharedPost.sort(Comparator.comparingLong(Post::getShareCounter));
        modelMap.put("postContributionList", topSharedPost);
        return "topSharedBooksPage";
    }
}
