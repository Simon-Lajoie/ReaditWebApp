package com.example.readitjavaproject.service;

import com.example.readitjavaproject.domain.User;
import com.example.readitjavaproject.service.DTO.SmallUserDTO;
import com.example.readitjavaproject.service.DTO.LoginDTO;
import com.example.readitjavaproject.service.DTO.UserDTO;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    LoginDTO createUser(LoginDTO loginDTO);

    List<SmallUserDTO> readAllUsers();

    List<SmallUserDTO> readAllOnlineUsers();

    void deleteUser(Integer id);

    Optional<SmallUserDTO> getUserById(Integer id);

    Optional<SmallUserDTO> getUserByUsername(String username);

    LoginDTO updateUser(LoginDTO userDTO, Integer id);

    SmallUserDTO setUserOnline(SmallUserDTO userDTO, Integer id, Boolean online);

    Optional<User> getFullUserById(Integer id);
}
