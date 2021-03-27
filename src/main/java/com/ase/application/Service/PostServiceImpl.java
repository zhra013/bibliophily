package com.ase.application.Service;

import com.ase.application.Repository.PostRepository;
import com.ase.application.entity.Post;
import com.ase.application.entity.QPost;
import com.ase.application.entity.User;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Override
    public void uploadPost(Post post) {
        User uploader = userService.findUserById(post.getUploader().getId());
        post.setDate(LocalDate.now());
        post.setShareCounter(0);
        post.setUploader(uploader);
        post.setIsShared(Boolean.FALSE);
        postRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUploaderId(Long uploaderId) {
        return postRepository.findPostByUploaderId(uploaderId);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).get();
        List<Post> sharedPosts = postRepository.findPostBySharedPostId(post.getId());
        postRepository.deleteAll(sharedPosts);
        if (post.getIsShared() != null && post.getIsShared().equals(Boolean.TRUE)) {
            Post postShared = postRepository.findById(post.getSharedPostId()).get();
            postShared.setShareCounter(postShared.getShareCounter()-1);
        }
        postRepository.delete(post);
    }

    @Override
    public List<Post> getPostsBySearch(String searchParam) {
        return postRepository.findPostBySearch(searchParam);
    }

    @Override
    public List<Post> getPostsByUploaderIdAndSearch(Long uploaderId, String searchParam) {
        return postRepository.findPostByUploaderIdAndSearch(uploaderId, searchParam);
    }

    @Override
    public List<Post> getFilteredPostList(Long userId, int pageNo, boolean excludeOwner) {

        Pageable requestedElement = PageRequest.of(pageNo, 4,
                Sort.Direction.DESC, "date");

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (userId != null && userId != 0 && !excludeOwner) {
            booleanBuilder.and(QPost.post.uploader.id.eq(userId));
        }
        if (excludeOwner && userId != null && userId != 0) {
            booleanBuilder.and(QPost.post.uploader.id.ne(userId));
        }
        return this.postRepository.findAll(booleanBuilder.getValue(), requestedElement).getContent();
    }

    @Override
    public void sharePost(Long userId, Long postId, String comment) {
        User user = userService.findUserById(userId);
        Post post = postRepository.findById(postId).get();
        post.setShareCounter(post.getShareCounter() + 1);
        Post sharePost = new Post();
        sharePost.setUploader(user);
        sharePost.setDate(LocalDate.now());
        sharePost.setIsShared(Boolean.TRUE);
        sharePost.setBlog(comment);
        sharePost.setSharedPostId(post.getId());
        postRepository.save(sharePost);
        postRepository.save(post);
    }
}
