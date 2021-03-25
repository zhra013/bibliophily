package com.ase.application.Mappers;

import com.ase.application.dto.SignUpDTO;
import com.ase.application.dto.TopActiveUserDTO;
import com.ase.application.dto.UserDTO;
import com.ase.application.entity.User;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class UserMapper {

    @Bean
    Mapper<User, UserDTO> userToDTOMapper() {
        return Mapping.from(User.class).to(UserDTO.class)
                .omitInSource(User::getPosts)
                .omitInSource(User::getPostReview)
                .mapper();
    }

    @Bean
    Mapper<UserDTO, User> dtoToUserMapper() {
        return Mapping.from(UserDTO.class).to(User.class)
                .omitInDestination(User::getPosts)
                .omitInDestination(User::getPostReview)
                .mapper();
    }

    @Bean
    Mapper<SignUpDTO, User> SignUpDTOToUserMapper() {
        return Mapping.from(SignUpDTO.class).to(User.class)
                .omitInDestination(User::getId)
                .omitInDestination(User::getUserType)
                .omitInDestination(User::getPosts)
                .omitInDestination(User::getPostReview)
                .mapper();
    }

    @Bean
    Mapper<User, TopActiveUserDTO> userToToContributorDTOMapper() {
        return Mapping.from(User.class).to(TopActiveUserDTO.class)
                .omitInSource(User::getPosts)
                .omitInSource(User::getPostReview)
                .omitInSource(User::getId)
                .omitInSource(User::getUserPassword)
                .omitInSource(User::getUserType)
                .omitInSource(User::getFullName)
                .omitInSource(User::getUserContact)
                .omitInDestination(TopActiveUserDTO::getTotalPost)
                .mapper();
    }
}
