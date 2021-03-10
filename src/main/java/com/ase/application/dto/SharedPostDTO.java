package com.ase.application.dto;

import com.ase.application.entity.PostType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Setter
@Getter
public class SharedPostDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private String author;

    private String edition;

    private String title;

    private MultipartFile uploadedCoverPhoto;

    private UserDTO uploader;

    private String blog;

    private LocalDate date;

    private int rating;

    private Boolean isShared;

    private long sharedPostId;

    private long shareCounter;
}
