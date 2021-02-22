package com.ase.application.Repository;

import com.ase.application.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @Override
    List<Post> findAll();

    @Query(value = "select post from Post post where post.uploader.id = :id")
    List<Post> findPostByUploaderId(Long id);
}
