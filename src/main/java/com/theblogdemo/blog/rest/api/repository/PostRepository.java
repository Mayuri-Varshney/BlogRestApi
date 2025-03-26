package com.theblogdemo.blog.rest.api.repository;

import com.theblogdemo.blog.rest.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

