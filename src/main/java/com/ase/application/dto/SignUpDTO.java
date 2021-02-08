package com.ase.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class SignUpDTO {
    @NotEmpty
    @Size(min = 5, max = 50, message = "Your full name must be between 5 to 50  characters long.")
    private String fullName;

    @NotNull
    @Size(min = 5, max = 20, message = "Username must be between 5 to 20  characters long.")
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20, message = "User password must be between 0 to 20  characters long.")
    private String userPassword;

    @Email
    private String userMail;

    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "Your Contact number must be numberic ")
    private String userContact;
}
