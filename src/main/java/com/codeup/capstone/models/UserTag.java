package com.codeup.capstone.models;


import javax.persistence.*;

@Entity
@Table(name="user_tag")
public class UserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

//--------constructor

    public UserTag() {
    }

    public UserTag(long id, User user, Tag tag) {
        this.id = id;
        this.user = user;
        this.tag = tag;
    }

//    getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
