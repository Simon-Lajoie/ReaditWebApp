package com.example.readitjavaproject.web.rest;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import com.example.readitjavaproject.service.DTO.LoginDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.impl.DownvoteService;
import com.example.readitjavaproject.service.impl.UpvoteService;
import com.example.readitjavaproject.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;
    private final DownvoteService downvoteService;
    private final UpvoteService upvoteService;
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    public UserResource(UserService userService, DownvoteService downvoteService, UpvoteService upvoteService) {
        this.userService = userService;
        this.upvoteService = upvoteService;
        this.downvoteService = downvoteService;
    }

    @GetMapping
    public List<SmallUserDTO> findAllUsers() {
        return userService.readAllUsers();
    }

    @GetMapping("/online")
    public List<SmallUserDTO> findAllOnlineUsers() {
        return userService.readAllOnlineUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmallUserDTO> findById(@PathVariable Integer id) {
        log.debug("REST Request to find one user by ID {}", id);
        return userService.getUserById(id).map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<SmallUserDTO> findByUsername(@PathVariable String username) {
        log.debug("REST Request to find one user by username {}", username);
        return userService.getUserByUsername(username).map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping
    public ResponseEntity<LoginDTO> createUser(@RequestBody LoginDTO loginDTO) {
        log.debug("REST Request to save user : {}", loginDTO);
        return ResponseEntity.ok(userService.createUser(loginDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SmallUserDTO> updateUser(@RequestBody LoginDTO loginDTO, @PathVariable Integer id) {
        log.debug("REST Request to update user {}", loginDTO);
        userService.updateUser(loginDTO, id);
        return findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.debug("REST Request to delete user {}", id);
        //downvoteService.getDownvoteByUserId(id);
        //upvoteService.getUpvoteByUserId(id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateOnline/{id}")
    public ResponseEntity<SmallUserDTO> setUserOnline(@RequestBody SmallUserDTO userDTO, Boolean online, @PathVariable Integer id) {
        log.debug("REST Request to update user {}", userDTO);
        userService.setUserOnline(userDTO, id, online);
        return findById(id);
    }

}