package com.example.readitjavaproject.service.DTO;

import com.example.readitjavaproject.domain.Comment;
import com.example.readitjavaproject.domain.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class SmallPostDTO {
    private Integer id;
    private String description;
    private Timestamp publicationDate;
    private String title;
    private String link;
    private SmallUserDTO user;
    private Integer number_upvotes = 0;
    private List<Comment> comments;

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

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SmallUserDTO getUser() {
        return user;
    }

    public void setUser(SmallUserDTO user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getNumber_upvotes() {
        return number_upvotes;
    }

    public void setNumber_upvotes(Integer number_upvotes) {
        this.number_upvotes = number_upvotes;
    }
}
