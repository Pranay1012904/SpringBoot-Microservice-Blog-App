package com.blog.app.one.repository;

import com.blog.app.one.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
   Optional<Post> findPostById(Long id);
}
