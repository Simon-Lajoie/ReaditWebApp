package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.SmallPostDTO;
import com.example.readitjavaproject.service.impl.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostResource {
    private final PostService postService;

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<SmallPostDTO> createPost(@RequestBody SmallPostDTO postDTO) {
        log.debug("REST Request to save post : {}", postDTO);
        return ResponseEntity.ok(postService.createPost(postDTO));
    }

    @GetMapping
    public List<SmallPostDTO> findAllPosts() {
        return postService.readAllpost();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        log.debug("REST Request to delete post {}", id);
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmallPostDTO> findById(@PathVariable Integer id) {
        log.debug("REST Request to find one post by ID {}", id);
        return postService.getPostById(id).map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/user/{id}")
    public List<SmallPostDTO> findAllPostsById(@PathVariable Integer id) {
        log.debug("REST Request to find all post by user ID {}", id);
        return postService.getPostByUserId(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SmallPostDTO> updatePost(@RequestBody SmallPostDTO postDTO, @PathVariable Integer id) {
        log.debug("REST Request to update post {}", postDTO);
        return ResponseEntity.ok(postService.updatePost(postDTO, id));
    }
}
