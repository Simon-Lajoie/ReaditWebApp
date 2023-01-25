package com.example.readitjavaproject.service.DTO;


import java.io.Serializable;

public class SmallUserDTO implements Serializable {
    private Integer id;
    private String username;
    private Boolean online = false;

    public Integer getId() {
        return id;
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

    public boolean setOnline(Boolean online) {
        this.online = online;
        return online;
    }

}