package com.example.readitjavaproject.service;

import com.example.readitjavaproject.service.DTO.DownvoteDTO;

import java.util.List;

public interface IDownvoteService {

    DownvoteDTO createDownvote(DownvoteDTO downvoteDTO);

    void deleteDownvote(Integer id);

    List<DownvoteDTO> getDownvoteByUserId(Integer id);
}
