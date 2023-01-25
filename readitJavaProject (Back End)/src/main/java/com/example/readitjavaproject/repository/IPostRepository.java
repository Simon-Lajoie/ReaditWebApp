package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer id);
}
