package com.ase.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
@NoArgsConstructor
public class TopActiveUserDTO {

    private String userName;

    @Email
    private String userMail;

    private int totalPost;
}
