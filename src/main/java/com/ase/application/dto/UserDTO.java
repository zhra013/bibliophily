package com.ase.application.dto;


import com.ase.application.entity.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Objects;

@Setter
@Getter
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Your full name must be between 5 to 50  characters long.")
    private String fullName;

    @NotNull
    @Size(min = 5, max = 20, message = "Username must be between 5 to 20  characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9] + $", message = "Username must be alphanumeric with no space")
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20, message = "User password must be between  to 20  characters long.")
    private String userPassword;

    @Email
    private String userMail;

    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "Your Contact number must be numberic ")
    private String userContact;

    private UserType userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id &&
                Objects.equals(fullName, userDTO.fullName) &&
                Objects.equals(userName, userDTO.userName) &&
                Objects.equals(userPassword, userDTO.userPassword) &&
                Objects.equals(userMail, userDTO.userMail) &&
                Objects.equals(userContact, userDTO.userContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, userName, userPassword, userMail, userContact, userType);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userType=" + userType +
                '}';
    }
}
