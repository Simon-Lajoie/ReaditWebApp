package com.example.readitjavaproject.service;

import com.example.readitjavaproject.service.DTO.SmallPostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    SmallPostDTO createPost(SmallPostDTO postDTO);

    List<SmallPostDTO> readAllpost();

    void deletePost(Integer id);

    Optional<SmallPostDTO> getPostById(Integer id);

    List<SmallPostDTO> getPostByUserId(Integer userId);

    SmallPostDTO updatePost(SmallPostDTO smallPostDTO, Integer id);
}
