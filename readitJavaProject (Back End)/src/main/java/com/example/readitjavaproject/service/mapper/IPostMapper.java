package com.example.readitjavaproject.service.mapper;

import com.example.readitjavaproject.domain.Post;
import com.example.readitjavaproject.service.DTO.SmallPostDTO;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IUserMapper.class})
public interface IPostMapper extends IEntityMapper<SmallPostDTO, Post> {

    @Override
    default SmallPostDTO toDto(Post entity) {
        if ( entity == null ) {
            return null;
        }

        SmallPostDTO smallPostDTO = new SmallPostDTO();

        smallPostDTO.setId( entity.getId() );
        smallPostDTO.setDescription( entity.getDescription() );
        smallPostDTO.setPublicationDate( entity.getPublicationDate() );
        smallPostDTO.setTitle( entity.getTitle() );
        smallPostDTO.setLink( entity.getLink() );
        smallPostDTO.setNumber_upvotes( entity.getNumber_upvotes() );
        SmallUserDTO smallUserDTO = new SmallUserDTO();
        smallUserDTO.setUsername(entity.getUser().getUsername());
        smallUserDTO.setId(entity.getUser().getId());
        smallUserDTO.setOnline(entity.getUser().getOnline());
        smallPostDTO.setUser(smallUserDTO);
        smallPostDTO.setComments(entity.getComments());

        return smallPostDTO;
    }
}
