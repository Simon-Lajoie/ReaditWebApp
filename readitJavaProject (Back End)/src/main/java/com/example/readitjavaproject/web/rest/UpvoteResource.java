package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.impl.UpvoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/upvote")
public class UpvoteResource {
    private final UpvoteService upvoteService;

    private final Logger log = LoggerFactory.getLogger(UpvoteResource.class);

    public UpvoteResource(UpvoteService upvoteService) {
        this.upvoteService = upvoteService;
    }

    @PostMapping
    public ResponseEntity<UpvoteDTO> createUpvote(@RequestBody UpvoteDTO upvoteDTO) {
        log.debug("REST Request to save upvote : {}", upvoteDTO);
        return ResponseEntity.ok(upvoteService.createUpvote(upvoteDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUpvote(@PathVariable Integer id) {
        log.debug("REST Request to delete upvote {}", id);
        upvoteService.deleteUpvote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public List<UpvoteDTO> findAllUpvotesByUserId(@PathVariable Integer id) {
        log.debug("REST Request to find all Upvotes by user ID {}", id);
        return upvoteService.getUpvoteByUserId(id);
    }
}
