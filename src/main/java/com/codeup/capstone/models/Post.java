package com.codeup.capstone.models;


import javax.persistence.*;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column(nullable = false, length = 500)
    private String parent_post_id;

    @Column(nullable = false, length = 500)
    private String message_body;


    //  ----------- Constructors
    public Post() {
    }

    public Post(long id, Group group, User user, String parent_post_id, String message_body) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.parent_post_id = parent_post_id;
        this.message_body = message_body;
    }

//    getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getParent_post_id() {
        return parent_post_id;
    }

    public void setParent_post_id(String parent_post_id) {
        this.parent_post_id = parent_post_id;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }
}