package com.example.readitjavaproject.service;

import com.example.readitjavaproject.service.DTO.UpvoteDTO;

import java.util.List;

public interface IUpvoteService {

    UpvoteDTO createUpvote(UpvoteDTO upvoteDTO);

    void deleteUpvote(Integer id);

    List<UpvoteDTO> getUpvoteByUserId(Integer userId);

}
