package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.Downvote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDownvoteRepository extends JpaRepository<Downvote, Integer> {
    List<Downvote> findByUserId(Integer id);
}
