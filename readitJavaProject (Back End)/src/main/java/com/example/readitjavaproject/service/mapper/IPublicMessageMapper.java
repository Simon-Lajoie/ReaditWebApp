package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.PublicMessage;
import com.example.readitjavaproject.service.DTO.PublicMessageDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {IUserMapper.class})
public interface IPublicMessageMapper extends IEntityMapper<PublicMessageDTO, PublicMessage> {
}
