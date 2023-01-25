package com.example.readitjavaproject.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "public_message")
public class PublicMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "publication_date", nullable = false)
    private Timestamp publicationDate;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "message_type", nullable = false)
    private String type;

    public PublicMessage() {
    }

    public PublicMessage(Integer id, String username, User user, Timestamp publicationDate, String message_type, String content) {
        this.id = id;
        this.username = username;
        this.user = user;
        this.publicationDate = publicationDate;
        this.type = message_type;
        this.content = content;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String message_type) {
        this.type = message_type;
    }
}