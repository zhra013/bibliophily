package com.ase.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FriendDTO {

    private long id;

    private UserDTO user;

    private UserDTO friend;

    private boolean acceptance;
}
