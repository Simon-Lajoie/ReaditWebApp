package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.Friend;
import com.example.readitjavaproject.service.DTO.FriendDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class})
public interface IFriendMapper extends IEntityMapper<FriendDTO, Friend> {
}
