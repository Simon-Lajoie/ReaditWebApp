package com.example.readitjavaproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "friend")
@NamedQueries({
        @NamedQuery(name = "Friend.isFriend", query = "SELECT f FROM Friend f WHERE (f.one_user_id.id = :userId AND f.two_user_id.id = :userId2) OR (f.one_user_id.id = :userId AND f.two_user_id.id = :userId2)"),
        @NamedQuery(name = "Friend.findByUserId", query = "SELECT f FROM Friend f WHERE f.one_user_id.id = :userId OR f.two_user_id.id = :userId")
})
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "one_user_id", nullable = false)
    private User one_user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "two_user_id", nullable = false)
    private User two_user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOne_user_id() {
        return one_user_id;
    }

    public void setOne_user_id(User one_user_id) {
        this.one_user_id = one_user_id;
    }

    public User getTwo_user_id() {
        return two_user_id;
    }

    public void setTwo_user_id(User two_user_id) {
        this.two_user_id = two_user_id;
    }
}