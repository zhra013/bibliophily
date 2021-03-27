package com.ase.application.Mappers;

import com.ase.application.dto.FriendDTO;
import com.ase.application.dto.UserDTO;
import com.ase.application.entity.Friend;
import com.ase.application.entity.User;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FriendMapper {


    private Mapper<User, UserDTO> userToDTOMapper
            = Mapping.from(User.class).to(UserDTO.class)
            .omitInSource(User::getPosts)
            .omitInSource(User::getPostReview)
            .omitInSource(User::getFriends)
            .omitInSource(User::getFriendStatus)
            .mapper();


    @Bean
    Mapper<Friend, FriendDTO> friendToDTOMapper() {
        return Mapping.from(Friend.class).to(FriendDTO.class)
                .useMapper(userToDTOMapper)
                .mapper();
    }
}
