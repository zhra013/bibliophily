package com.ase.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String fullName;

    @NotNull
//    @Pattern(regexp = "^[a-zA-Z0-9] + $", message = "Username must be alphanumeric with no space")
    private String userName;

    @NotEmpty
    private String userPassword;

    @Email
    private String userMail;

    @NotEmpty
    private String userContact;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<PostReview> postReview;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(fullName, user.fullName) &&
                        Objects.equals(userName, user.userName) &&
                        Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userMail, user.userMail) &&
                Objects.equals(userContact, user.userContact) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash( fullName, userName, userPassword, userMail, userContact, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userType=" + userType +
                '}';
    }
}
