package com.example.readitjavaproject.service.DTO;

import com.example.readitjavaproject.domain.User;

import java.io.Serializable;

public class DownvoteDTO implements Serializable {
    private Integer id;
    private SmallUserDTO user;
    private SmallPostDTO post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SmallUserDTO getUser() {
        return user;
    }

    public void setUser(SmallUserDTO user) {
        this.user = user;
    }

    public SmallPostDTO getPost() {
        return post;
    }

    public void setPost(SmallPostDTO post) {
        this.post = post;
    }
}
