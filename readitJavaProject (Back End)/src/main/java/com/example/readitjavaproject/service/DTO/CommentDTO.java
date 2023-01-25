package com.example.readitjavaproject.service.DTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class CommentDTO implements Serializable {
    private Integer id;
    private String description;
    private SmallUserDTO user;
    private Timestamp publicationDate;
    private SmallPostDTO post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SmallUserDTO getUser() {
        return user;
    }

    public void setUser(SmallUserDTO user) {
        this.user = user;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public SmallPostDTO getPost() {
        return post;
    }

    public void setPost(SmallPostDTO post) {
        this.post = post;
    }
}
