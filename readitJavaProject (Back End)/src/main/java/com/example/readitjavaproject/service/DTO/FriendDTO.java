package com.example.readitjavaproject.service.DTO;

import com.example.readitjavaproject.domain.User;

import java.io.Serializable;

public class FriendDTO implements Serializable {
    private Integer id;
    private UserDTO one_user_id;
    private UserDTO two_user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getOne_user_id() {
        return one_user_id;
    }

    public void setOne_user_id(UserDTO one_user_id) {
        this.one_user_id = one_user_id;
    }

    public UserDTO getTwo_user_id() {
        return two_user_id;
    }

    public void setTwo_user_id(UserDTO two_user_id) {
        this.two_user_id = two_user_id;
    }
}
