package com.ase.application.Mappers;

import com.ase.application.dto.PostDTO;
import com.ase.application.dto.SharedPostDTO;
import com.ase.application.dto.UserDTO;
import com.ase.application.entity.Post;
import com.ase.application.entity.User;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapper {

    private Mapper<User, UserDTO> userToDTOMapper
            = Mapping.from(User.class).to(UserDTO.class)
            .omitInSource(User::getPosts)
            .omitInSource(User::getPostReview)
            .mapper();


    private Mapper<UserDTO, User> dtoToUserMapper
        = Mapping.from(UserDTO.class).to(User.class)
                .omitInDestination(User::getPosts)
                .omitInDestination(User::getPostReview)
            .mapper();

    @Bean
    Mapper<Post, PostDTO> postToDTOMapper() {
        return Mapping.from(Post.class).to(PostDTO.class)
                .useMapper(userToDTOMapper)
                .omitInSource(Post::getCoverPhoto)
                .omitInDestination(PostDTO::getUploadedCoverPhoto)
                .omitInSource(Post::getPostReview)
                .omitInDestination(PostDTO::getRating)
                .omitInDestination(PostDTO::getPostShared)
                .mapper();
    }

    @Bean
    Mapper<Post, SharedPostDTO> postToSharePostDTOMapper() {
        return Mapping.from(Post.class).to(SharedPostDTO.class)
                .useMapper(userToDTOMapper)
                .omitInSource(Post::getCoverPhoto)
                .omitInDestination(SharedPostDTO::getUploadedCoverPhoto)
                .omitInSource(Post::getPostReview)
                .omitInDestination(SharedPostDTO::getRating)
                .mapper();
    }

    @Bean
    Mapper<PostDTO, Post> dtoToPostMapper() {
        return Mapping.from(PostDTO.class).to(Post.class)
                .useMapper(dtoToUserMapper)
                .omitInDestination(Post::getCoverPhoto)
                .omitInSource(PostDTO::getUploadedCoverPhoto)
                .omitInDestination(Post::getPostReview)
                .omitInSource(PostDTO::getRating)
                .omitInSource(PostDTO::getPostShared)
                .mapper();
    }


}
