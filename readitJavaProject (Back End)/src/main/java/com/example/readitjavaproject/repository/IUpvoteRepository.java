package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IUpvoteRepository extends JpaRepository<Upvote, Integer> {
    List<Upvote> findByUserId(Integer id);
}
