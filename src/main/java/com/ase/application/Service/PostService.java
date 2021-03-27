package com.ase.application.Service;

import com.ase.application.entity.Post;

import java.util.List;

public interface PostService {
    void uploadPost(Post post);

    List<Post> getPosts();

    List<Post> getPostsByUploaderId(Long uploaderId);

    List<Post> getPostsBySearch(String searchParam);

    List<Post> getPostsByUploaderIdAndSearch(Long uploaderId, String searchParam);

    Post getPostById(Long postId);

    void deletePost(Long postId);

    List<Post> getFilteredPostList(Long userId, int pageNo, boolean excludeOwner);

    void sharePost(Long userId, Long postId, String comment);
}
