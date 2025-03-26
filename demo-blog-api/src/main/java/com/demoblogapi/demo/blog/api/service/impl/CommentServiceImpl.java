package com.demoblogapi.demo.blog.api.service.impl;

import com.demoblogapi.demo.blog.api.entity.Comment;
import com.demoblogapi.demo.blog.api.entity.Post;
import com.demoblogapi.demo.blog.api.exception.BlogApiException;
import com.demoblogapi.demo.blog.api.exception.ResourceNotFoundException;
import com.demoblogapi.demo.blog.api.repository.CommentRepository;
import com.demoblogapi.demo.blog.api.repository.PostRepository;
import com.demoblogapi.demo.blog.api.service.CommentService;
import com.demoblogapi.demo.blog.api.entity.Comment;
import com.demoblogapi.demo.blog.api.entity.Post;
import com.demoblogapi.demo.blog.api.exception.BlogApiException;
import com.demoblogapi.demo.blog.api.exception.ResourceNotFoundException;
import com.demoblogapi.demo.blog.api.repository.CommentRepository;
import com.demoblogapi.demo.blog.api.repository.PostRepository;
import com.demoblogapi.demo.blog.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long postId, Comment newComment) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        newComment.setPost(post);

        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {

        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment updateComment) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        comment.setName(updateComment.getName());
        comment.setEmail(updateComment.getEmail());
        comment.setBody(updateComment.getBody());
        return commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }
}
