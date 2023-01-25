package com.example.readitjavaproject.service.DTO;

import com.example.readitjavaproject.domain.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PublicMessageDTO implements Serializable {
    private Integer id;
    private SmallUserDTO user;
    private Timestamp publicationDate;
    private String content;
    private MessageType message_type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

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

    public MessageType getMessage_type() {
        return message_type;
    }

    public void setMessage_type(MessageType message_type) {
        this.message_type = message_type;
    }
}
