package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.Friend;
import com.example.readitjavaproject.repository.IFriendRepository;
import com.example.readitjavaproject.service.DTO.FriendDTO;
import com.example.readitjavaproject.service.IFriendService;
import com.example.readitjavaproject.service.mapper.IFriendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService implements IFriendService {

    private final IFriendMapper friendMapper;

    private final IFriendRepository friendsRepository;

    private final Logger log = LoggerFactory.getLogger(FriendService.class);

    public FriendService(IFriendRepository friendsRepository, IFriendMapper friendMapper) {
        this.friendsRepository = friendsRepository;
        this.friendMapper = friendMapper;
    }

    @Override
    public FriendDTO createFriend(FriendDTO friendDTO) {
        log.debug("Request to create friend {}", friendDTO);
        Friend friend = friendMapper.toEntity(friendDTO);
        friend = friendsRepository.save(friend);
        return friendMapper.toDto(friend);
    }

    @Override
    public void deleteFriend(Integer id) {
        log.debug("Request to delete friend {}", id);
        friendsRepository.deleteById(id);
    }

    @Override
    public List<FriendDTO> getFriendByUserId(Integer userId) {
        log.debug("REST Request to find all friends by User ID {}", userId);
        return friendsRepository.findByUserId(userId).stream().map(friendMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isAlreadyFriend(Integer userId, Integer userId2) {
        if (friendsRepository.isFriend(userId, userId2) != null)
        {
            return true;
        }
        return false;
    }
}
