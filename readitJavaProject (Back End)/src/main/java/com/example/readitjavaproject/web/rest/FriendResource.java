package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.service.DTO.FriendDTO;
import com.example.readitjavaproject.service.DTO.UpvoteDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.impl.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendResource {
    private final FriendService friendService;

    private final Logger log = LoggerFactory.getLogger(FriendResource.class);

    public FriendResource(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    public ResponseEntity<FriendDTO> createFriend(@RequestBody FriendDTO friendDTO) {
        log.debug("REST Request to save friend : {}", friendDTO);
        return ResponseEntity.ok(friendService.createFriend(friendDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Integer id) {
        log.debug("REST Request to delete friend {}", id);
        friendService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public List<FriendDTO> findAllFriendsById(@PathVariable Integer id) {
        log.debug("REST Request to find all Friends by user ID {}", id);
        return friendService.getFriendByUserId(id);
    }

    /*


    List<FriendDTO> getFriendByUserId(Integer userId);

    Boolean isAlreadyFriend(Integer userId, Integer userId2);
     */
}
