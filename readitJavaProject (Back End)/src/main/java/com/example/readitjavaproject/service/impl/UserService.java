package com.example.readitjavaproject.service.impl;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.repository.IUserRepository;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import com.example.readitjavaproject.service.DTO.LoginDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import com.example.readitjavaproject.service.IUserService;
import com.example.readitjavaproject.service.mapper.IUserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserMapper userMapper;

    private final IUserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public LoginDTO createUser(LoginDTO loginDTO) {
        log.debug("Request to create user {}", loginDTO);
        String encryptPwd = new BCryptPasswordEncoder().encode(loginDTO.getPassword()); //encrypt password
        loginDTO.setPassword(encryptPwd);
        User user = new User(loginDTO.getUsername(), loginDTO.getPassword());
        userRepository.save(user);
        return loginDTO;
    }

    @Override
    public Optional<SmallUserDTO> getUserById(Integer id) {
        log.debug("REST Request to find one user by ID {}", id);
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Optional<User> getFullUserById(Integer id) {  //with password
        log.debug("REST Request to find one user by ID {}", id);
        return userRepository.findById(id);
    }

    @Override
    public List<SmallUserDTO> readAllUsers() {
        log.debug("Request to read all users");
        return userRepository.findAll().stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SmallUserDTO> readAllOnlineUsers() {
        log.debug("Request to read all online users");
        return userRepository.findAllByOnlineTrue().stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SmallUserDTO> getUserByUsername(String username) {
        log.debug("Request to find one user by username {}", username);
        return userRepository.findByUsername(username).map(userMapper::toDto);
    }

    @Override
    public void deleteUser(Integer id) {
        log.debug("Request to delete user {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public LoginDTO updateUser(LoginDTO loginDTO, Integer id) {
        log.debug("Request to update user {} {}", loginDTO, id);
        User userToUpdate = getFullUserById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (userToUpdate.getPassword() != loginDTO.getPassword()) { //encrypt password if it changed
            String encryptPwd = new BCryptPasswordEncoder().encode(loginDTO.getPassword()); //encrypt password
            userToUpdate.setPassword(encryptPwd);
        }
        userToUpdate.setUsername(loginDTO.getUsername());
        userRepository.save(userToUpdate);
        return loginDTO;
    }

    @Override
    public SmallUserDTO setUserOnline(SmallUserDTO userDTO, Integer id, Boolean online) {
        log.debug("Request to set user online {} {}", userDTO, id);
        User userToUpdate = getFullUserById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userToUpdate.setOnline(userDTO.setOnline(online));
        userRepository.save(userToUpdate);
        return userDTO;
    }
}
