package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.PublicMessageDTO;
import com.example.readitjavaproject.service.impl.PublicMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicmessage")
public class PublicMessageResource {
    private final PublicMessageService publicMessageService;

    private final Logger log = LoggerFactory.getLogger(PublicMessageResource.class);

    public PublicMessageResource(PublicMessageService publicMessageService) {
        this.publicMessageService = publicMessageService;
    }

    @PostMapping
    public ResponseEntity<PublicMessageDTO> createPublicMessage(@RequestBody PublicMessageDTO publicMessageDTO) {
        log.debug("REST Request to save upvote : {}", publicMessageDTO);
        return ResponseEntity.ok(publicMessageService.createPublicMessage(publicMessageDTO));
    }

    @GetMapping
    public List<PublicMessageDTO> findAllPublicMessage() {
        return publicMessageService.readAllPublicMessage();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublicMessage(@PathVariable Integer id) {
        log.debug("REST Request to delete public message {}", id);
        publicMessageService.deletePublicMessage(id);
        return ResponseEntity.noContent().build();
    }
}
