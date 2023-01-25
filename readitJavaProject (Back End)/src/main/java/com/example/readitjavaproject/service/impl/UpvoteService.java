package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Upvote;
import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.repository.IUpvoteRepository;
import com.example.readitjavaproject.repository.IUserRepository;
import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import com.example.readitjavaproject.service.IUpvoteService;
import com.example.readitjavaproject.service.mapper.IUpvoteMapper;
import com.example.readitjavaproject.service.mapper.IUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpvoteService implements IUpvoteService {

    private final IUpvoteMapper upvoteMapper;

    private final IUpvoteRepository upvoteRepository;

    private final Logger log = LoggerFactory.getLogger(UpvoteService.class);

    public UpvoteService(IUpvoteRepository upvoteRepository, IUpvoteMapper upvoteMapper) {
        this.upvoteRepository = upvoteRepository;
        this.upvoteMapper = upvoteMapper;
    }

    @Override
    public UpvoteDTO createUpvote(UpvoteDTO upvoteDTO) {
        log.debug("Request to create upvote {}", upvoteDTO);
        Upvote upvote = upvoteMapper.toEntity(upvoteDTO);
        upvote = upvoteRepository.save(upvote);
        return upvoteMapper.toDto(upvote);
    }

    @Override
    public void deleteUpvote(Integer id) {
        log.debug("Request to delete upvote {}", id);
        upvoteRepository.deleteById(id);
    }

    @Override
    public List<UpvoteDTO> getUpvoteByUserId(Integer userId) {
        log.debug("REST Request to find all upvotes by User ID {}", userId);
        return upvoteRepository.findByUserId(userId).stream().map(upvoteMapper::toDto)
                .collect(Collectors.toList());
    }
}
