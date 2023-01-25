package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.DownvoteDTO;
import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import com.example.readitjavaproject.service.impl.DownvoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/downvote")
public class DownvoteResource {
    private final DownvoteService downvoteService;

    private final Logger log = LoggerFactory.getLogger(DownvoteResource.class);

    public DownvoteResource(DownvoteService downvoteService) {
        this.downvoteService = downvoteService;
    }

    @PostMapping
    public ResponseEntity<DownvoteDTO> createDownvote(@RequestBody DownvoteDTO downvoteDTO) {
        log.debug("REST Request to save downvote : {}", downvoteDTO);
        return ResponseEntity.ok(downvoteService.createDownvote(downvoteDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDownvote(@PathVariable Integer id) {
        log.debug("REST Request to delete upvote {}", id);
        downvoteService.deleteDownvote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public List<DownvoteDTO> findAllDownvotesByUserId(@PathVariable Integer id) {
        log.debug("REST Request to find all Downvotes by user ID {}", id);
        return downvoteService.getDownvoteByUserId(id);
    }
}
