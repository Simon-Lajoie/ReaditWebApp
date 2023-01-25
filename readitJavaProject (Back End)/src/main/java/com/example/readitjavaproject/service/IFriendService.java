package com.example.readitjavaproject.service;

import com.example.readitjavaproject.service.DTO.FriendDTO;

import java.util.List;

public interface IFriendService {

    FriendDTO createFriend(FriendDTO friendDTO);

    void deleteFriend(Integer id);

    List<FriendDTO> getFriendByUserId(Integer userId);

    Boolean isAlreadyFriend(Integer userId, Integer userId2);
}
