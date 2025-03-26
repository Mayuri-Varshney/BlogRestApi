package com.demoblogapi.demo.blog.api.repository;

import com.demoblogapi.demo.blog.api.entity.Post;
import com.demoblogapi.demo.blog.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

