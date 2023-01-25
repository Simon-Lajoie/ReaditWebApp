package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.Comment;
import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.service.DTO.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IPostMapper.class})
public interface ICommentMapper extends IEntityMapper<CommentDTO, Comment> {
}
