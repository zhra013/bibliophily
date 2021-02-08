package com.ase.application.Mappers;

import com.ase.application.dto.SignUpDTO;
import com.ase.application.dto.UserDTO;
import com.ase.application.entity.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
@Configuration
public class UserMapper {

    @Bean
    Mapper<User, UserDTO> userToDTOMapper() {
        return Mapping.from(User.class).to(UserDTO.class)
                .mapper();
    }

    @Bean
    Mapper<UserDTO, User> dtoToUserMapper() {
        return Mapping.from(UserDTO.class).to(User.class)
                .mapper();
    }

    @Bean
    Mapper<SignUpDTO, User> SignUpDTOToUserMapper() {
        return Mapping.from(SignUpDTO.class).to(User.class)
                .omitInDestination(User::getId)
                .omitInDestination(User::getUserType)
                .mapper();
    }
}
