package com.ase.application.Repository;

import com.ase.application.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends QuerydslPredicateExecutor<Post>, PagingAndSortingRepository<Post, Long> {

    @Override
    List<Post> findAll();

    @Query(value = "select post from Post post where post.uploader.id = :id")
    List<Post> findPostByUploaderId(Long id);

    @Query(value = "select post from Post post where post.uploader.id = :id and (LOWER(post.title) like %:searchParameter% or LOWER(post.edition) like %:searchParameter% or  LOWER(post.author) like %:searchParameter%)")
    List<Post> findPostByUploaderIdAndSearch(Long id, String searchParameter);

    @Query(value = "select post from Post post where (LOWER(post.title) like %:searchParameter% or LOWER(post.edition) like %:searchParameter% or LOWER(post.author) like %:searchParameter%)")
    List<Post> findPostBySearch(String searchParameter);

    List<Post> findPostBySharedPostId(Long sharedPostId);
}
