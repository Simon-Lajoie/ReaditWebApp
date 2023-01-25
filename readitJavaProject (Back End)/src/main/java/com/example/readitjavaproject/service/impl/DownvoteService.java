package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Downvote;
import com.example.readitjavaproject.domain.Upvote;
import com.example.readitjavaproject.repository.IDownvoteRepository;
import com.example.readitjavaproject.repository.IUpvoteRepository;
import com.example.readitjavaproject.service.DTO.DownvoteDTO;
import com.example.readitjavaproject.service.DTO.PostDTO;
import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import com.example.readitjavaproject.service.IDownvoteService;
import com.example.readitjavaproject.service.mapper.IDownvoteMapper;
import com.example.readitjavaproject.service.mapper.IUpvoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownvoteService implements IDownvoteService {

    private final IDownvoteMapper downvoteMapper;

    private final IDownvoteRepository downvoteRepository;

    private final Logger log = LoggerFactory.getLogger(DownvoteService.class);

    public DownvoteService(IDownvoteRepository downvoteRepository, IDownvoteMapper downvoteMapper) {
        this.downvoteRepository = downvoteRepository;
        this.downvoteMapper = downvoteMapper;
    }

    @Override
    public DownvoteDTO createDownvote(DownvoteDTO downvoteDTO) {
        log.debug("Request to create downvote {}", downvoteDTO);
        Downvote downvote = downvoteMapper.toEntity(downvoteDTO);
        downvote = downvoteRepository.save(downvote);
        return downvoteMapper.toDto(downvote);
    }

    @Override
    public void deleteDownvote(Integer id) {
        log.debug("Request to delete downvote {}", id);
        downvoteRepository.deleteById(id);
    }


    @Override
    public List<DownvoteDTO> getDownvoteByUserId(Integer userId) {
        log.debug("REST Request to find all downvote by User ID {}", userId);
        return downvoteRepository.findByUserId(userId).stream().map(downvoteMapper::toDto)
                .collect(Collectors.toList());
    }
}
