package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
    List<Comment> findByUsername(String username);
}
