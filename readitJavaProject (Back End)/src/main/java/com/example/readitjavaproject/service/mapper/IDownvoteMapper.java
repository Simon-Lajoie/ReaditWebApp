package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.Downvote;
import com.example.readitjavaproject.service.DTO.DownvoteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IPostMapper.class})
public interface IDownvoteMapper extends IEntityMapper<DownvoteDTO, Downvote> {

}


