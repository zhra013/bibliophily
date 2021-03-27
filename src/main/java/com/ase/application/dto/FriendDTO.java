package com.ase.application.dto;

import com.ase.application.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
public class FriendDTO {

    private long id;

    private UserDTO user;

    private UserDTO friend;

    private boolean acceptance;
}
