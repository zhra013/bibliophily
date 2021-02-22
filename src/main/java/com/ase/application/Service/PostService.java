package com.ase.application.Service;

import com.ase.application.entity.Post;

import java.util.List;

public interface PostService {
    void uploadBook(Post post);

    List<Post> getPosts();

    List<Post> getPostsByUploaderId(Long uploaderId);

    Post getPostById(Long postId);
}
