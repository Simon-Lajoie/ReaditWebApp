package com.example.readitjavaproject.service;


import com.example.readitjavaproject.service.DTO.PublicMessageDTO;

import java.util.List;

public interface IPublicMessageService {

    PublicMessageDTO createPublicMessage(PublicMessageDTO publicMessageDTO);

    List<PublicMessageDTO> readAllPublicMessage();

    void deletePublicMessage(Integer id);
}
