package com.example.readitjavaproject.service.DTO;

import com.example.readitjavaproject.domain.Downvote;
import com.example.readitjavaproject.domain.Post;
import com.example.readitjavaproject.domain.Upvote;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    private Integer id;
    private String username;
    private Boolean online = false;
    private String password;
    private List<Downvote> downvotes;
    private List<Upvote> upvotes;

    public Integer getId() {
        return id;
    }

    public List<Downvote> getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(List<Downvote> downvotes) {
        this.downvotes = downvotes;
    }

    public List<Upvote> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(List<Upvote> upvotes) {
        this.upvotes = upvotes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
