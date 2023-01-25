package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Comment;
import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.repository.ICommentRepository;
import com.example.readitjavaproject.repository.IUserRepository;
import com.example.readitjavaproject.service.DTO.CommentDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.ICommentService;
import com.example.readitjavaproject.service.mapper.ICommentMapper;
import com.example.readitjavaproject.service.mapper.IUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final ICommentMapper commentMapper;

    private final ICommentRepository commentRepository;

    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    public CommentService(ICommentRepository commentRepository, ICommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        log.debug("Request to create comment {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public Optional<CommentDTO> getById(Integer id) {
        log.debug("REST Request to find one comment by ID {}", id);
        return commentRepository.findById(id).map(commentMapper::toDto);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(Integer id) {
        log.debug("REST Request to find all comments by post ID {}", id);
        return commentRepository.findByPostId(id).stream().map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentByUsername(String username) {
        log.debug("Request to find all comments by username {}", username);
        return commentRepository.findByUsername(username).stream().map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Integer id) {
        log.debug("Request to delete comment {}", id);
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, Integer id) {
        log.debug("Request to update comment {} {}", commentDTO, id);
        CommentDTO commentToUpdate = getById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        commentToUpdate.setUser(commentDTO.getUser());
        commentToUpdate.setDescription(commentDTO.getDescription());
        return createComment(commentToUpdate);
    }
}
