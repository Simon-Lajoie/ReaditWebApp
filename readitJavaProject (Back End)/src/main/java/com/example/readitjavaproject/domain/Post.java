package com.example.readitjavaproject.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Column(name = "publication_date", nullable = false)
    private Timestamp publicationDate;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "link", nullable = true, length = 255)
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "number_upvotes", nullable = false)
    private Integer number_upvotes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
    }

    public Post(Integer id, String description, Timestamp publicationDate, String title, String link, User user) {
        this.id = id;
        this.description = description;
        this.publicationDate = publicationDate;
        this.title = title;
        this.link = link;
        this.user = user;
        this.number_upvotes = 0;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber_upvotes() {
        return number_upvotes;
    }

    public void setNumber_upvotes(Integer number_upvotes) {
        this.number_upvotes = number_upvotes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}