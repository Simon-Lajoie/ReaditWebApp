package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface IUserMapper extends IEntityMapper<SmallUserDTO, User> {
}