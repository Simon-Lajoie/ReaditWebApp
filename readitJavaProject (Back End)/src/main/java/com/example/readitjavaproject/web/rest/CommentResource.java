package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.*;
import com.example.readitjavaproject.service.impl.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {
    private final CommentService commentService;

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        log.debug("REST Request to save downvote : {}", commentDTO);
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        log.debug("REST Request to delete comment {}", id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDTO> updatePost(@RequestBody CommentDTO commentDTO, @PathVariable Integer id) {
        log.debug("REST Request to update comment {}", commentDTO);
        return ResponseEntity.ok(commentService.updateComment(commentDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable Integer id) {
        log.debug("REST Request to find one comment by ID {}", id);
        return commentService.getById(id).map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    @GetMapping("/{username}")
    public List<CommentDTO> findAllCommentsByUsername(@PathVariable String username) {
        log.debug("REST Request to find all Comments by username {}", username);
        return commentService.getCommentByUsername(username);
    }

    @GetMapping("/post/{id}")
    public List<CommentDTO> findAllCommentsByPostId(@PathVariable Integer id) {
        log.debug("REST Request to find all Comments by Post ID {}", id);
        return commentService.getCommentByPostId(id);
    }
}
