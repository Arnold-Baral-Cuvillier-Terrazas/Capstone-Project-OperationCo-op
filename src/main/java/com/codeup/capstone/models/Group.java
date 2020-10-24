package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;


//Group will most likely have a OneToMany relationship with User

@Entity
@Table(name = "groups")

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String discordUserId;

    @Column(nullable = false)
    private String gameId;

    //This is the OneToMany relationship. One Group should have many Posts.
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
//    private List<Post> posts;


    //Check out user_rating table and Group_table to see if it can me in one rating.


    // Determine whether it will be ManyToMany or ManyToOne below.

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<User> users;


    public Group(long id, String name, String description, String discordUserId, String gameId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discordUserId = discordUserId;
        this.gameId = gameId;
    }

    public Group() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(String discordUserId) {
        this.discordUserId = discordUserId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;


    }
}
