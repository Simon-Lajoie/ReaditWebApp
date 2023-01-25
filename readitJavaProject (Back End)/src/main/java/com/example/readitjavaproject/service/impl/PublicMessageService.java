package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Post;
import com.example.readitjavaproject.domain.PublicMessage;
import com.example.readitjavaproject.repository.IPublicMessageRepository;
import com.example.readitjavaproject.service.DTO.PublicMessageDTO;
import com.example.readitjavaproject.service.IPublicMessageService;
import com.example.readitjavaproject.service.mapper.IPublicMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicMessageService implements IPublicMessageService {

    private final IPublicMessageMapper publicMessageMapper;

    private final IPublicMessageRepository publicMessageRepository;

    private final Logger log = LoggerFactory.getLogger(UpvoteService.class);

    public PublicMessageService(IPublicMessageRepository publicMessageRepository, IPublicMessageMapper publicMessageMapper) {
        this.publicMessageRepository = publicMessageRepository;
        this.publicMessageMapper = publicMessageMapper;
    }

    @Override
    public PublicMessageDTO createPublicMessage(PublicMessageDTO publicMessageDTO) {
        log.debug("Request to create public message {}", publicMessageDTO);
        PublicMessage publicMessage = publicMessageMapper.toEntity(publicMessageDTO);
        publicMessage = publicMessageRepository.save(publicMessage);
        return publicMessageMapper.toDto(publicMessage);
    }

    @Override
    public List<PublicMessageDTO> readAllPublicMessage() {
        log.debug("Request to read all public message");
        return publicMessageRepository.findAll().stream().map(publicMessageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePublicMessage(Integer id) {
        log.debug("Request to delete public message {}", id);
        publicMessageRepository.deleteById(id);
    }
}
