package com.example.readitjavaproject.repository;

import com.example.readitjavaproject.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findByUserId(Integer userId);
    Friend isFriend(Integer userId, Integer userId2);
}
