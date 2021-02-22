package com.ase.application.Mappers;

import com.ase.application.dto.PostDTO;
import com.ase.application.entity.Post;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapper {
    @Bean
    Mapper<Post, PostDTO> postToDTOMapper() {
        return Mapping.from(Post.class).to(PostDTO.class)
                .omitInSource(Post::getCoverPhoto)
                .omitInDestination(PostDTO::getUploadedCoverPhoto)
                .omitInSource(Post::getPostReview)
                .mapper();
    }

    @Bean
    Mapper<PostDTO, Post> dtoToPostMapper() {
        return Mapping.from(PostDTO.class).to(Post.class)
                .omitInDestination(Post::getCoverPhoto)
                .omitInSource(PostDTO::getUploadedCoverPhoto)
                .omitInDestination(Post::getPostReview)
                .mapper();
    }
}
