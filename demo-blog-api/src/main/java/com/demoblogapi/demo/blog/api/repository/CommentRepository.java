package com.demoblogapi.demo.blog.api.repository;
import com.demoblogapi.demo.blog.api.entity.Comment;
import com.demoblogapi.demo.blog.api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
