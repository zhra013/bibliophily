package com.ase.application.Mappers;

import com.ase.application.entity.UserDTO;
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
                .omitInDestination(UserDTO::getUserId)
                .mapper();
    }

    @Bean
    Mapper<UserDTO, User> dtoToUserMapper() {
        return Mapping.from(UserDTO.class).to(User.class)
                .omitInSource(UserDTO::getUserId)
                .mapper();
    }
}
