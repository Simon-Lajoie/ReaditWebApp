package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Post;
import com.example.readitjavaproject.repository.IPostRepository;
import com.example.readitjavaproject.service.DTO.SmallPostDTO;
import com.example.readitjavaproject.service.IPostService;
import com.example.readitjavaproject.service.mapper.IPostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    private final IPostMapper postMapper;

    private final IPostRepository postRepository;

    private final Logger log = LoggerFactory.getLogger(PostService.class);

    public PostService(IPostRepository postRepository, IPostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public SmallPostDTO createPost(SmallPostDTO postDTO) {
        log.debug("Request to create post {}", postDTO);
        Post post = postMapper.toEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public List<SmallPostDTO> readAllpost() {
        log.debug("Request to read all posts");
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(Integer id) {
        log.debug("Request to delete post {}", id);
        postRepository.deleteById(id);
    }

    @Override
    public Optional<SmallPostDTO> getPostById(Integer id) {
        log.debug("REST Request to find one post by ID {}", id);
        return postRepository.findById(id).map(postMapper::toDto);
    }

    @Override
    public List<SmallPostDTO> getPostByUserId(Integer userId) {
        log.debug("REST Request to find all post by User ID {}", userId);
        return postRepository.findByUserId(userId).stream().map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SmallPostDTO updatePost(SmallPostDTO postDTO, Integer id) {
        log.debug("Request to update post {} {}", postDTO, id);
        SmallPostDTO postToUpdate = getPostById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        postToUpdate.setDescription(postDTO.getDescription());
        postToUpdate.setLink(postDTO.getLink());
        postToUpdate.setTitle(postDTO.getTitle());
        postToUpdate.setNumber_upvotes(postDTO.getNumber_upvotes());
        postToUpdate.setPublicationDate(postDTO.getPublicationDate());
        return createPost(postToUpdate);
    }
}
