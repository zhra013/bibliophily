package com.ase.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private String author;

    private String edition;

    private String title;

    @Lob
    private byte[] coverPhoto;

    private String blog;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "uploader_id", referencedColumnName = "id")
    private User uploader;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostReview> postReview;

    private Boolean isShared;

    private long sharedPostId;

    private long shareCounter;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                postType == post.postType &&
                Objects.equals(author, post.author) &&
                Objects.equals(edition, post.edition) &&
                Objects.equals(title, post.title) &&
                Arrays.equals(coverPhoto, post.coverPhoto) &&
                Objects.equals(uploader, post.uploader);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, postType, author, edition, title, uploader);
        result = 31 * result + Arrays.hashCode(coverPhoto);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postType=" + postType +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", title='" + title + '\'' +
                ", coverPhoto=" + Arrays.toString(coverPhoto) +
                ", uploader=" + uploader +
                '}';
    }
}
