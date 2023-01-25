package com.example.readitjavaproject.service;

import com.example.readitjavaproject.domain.Comment;
import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.service.DTO.CommentDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

    CommentDTO createComment(CommentDTO commentDTO);

    List<CommentDTO> getCommentByPostId(Integer id);

    List<CommentDTO> getCommentByUsername(String username);

    Optional<CommentDTO> getById(Integer id);

    void deleteComment(Integer id);

    CommentDTO updateComment(CommentDTO commentDTO, Integer id);
}
