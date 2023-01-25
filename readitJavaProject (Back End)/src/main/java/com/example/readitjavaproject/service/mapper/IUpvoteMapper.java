package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.Comment;
import com.example.readitjavaproject.domain.Upvote;
import com.example.readitjavaproject.service.DTO.CommentDTO;
import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IPostMapper.class})
public interface IUpvoteMapper extends IEntityMapper<UpvoteDTO, Upvote> {
}
