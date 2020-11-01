package com.codeup.capstone.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    // Made nullable true to make Create Group DB to work.
    @Column(unique = true)
    @ColumnDefault("true")
    private String discordUserId;

    @Column(unique = true)
    private String gameId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    List<GroupUser> users;

    @OneToMany(mappedBy = "group")
    List<Game> games;

    public Group(long id, String name, String description, String discordUserId, String gameId, List<Post> posts, List<GroupUser> users, List<Game> games) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discordUserId = discordUserId;
        this.gameId = gameId;
        this.posts = posts;
        this.users = users;
        this.games = games;
    }

    public Group() {

    }

    public List<GroupUser> getUsers() {
        return users;
    }

    public void setUsers(List<GroupUser> users) {
        this.users = users;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }

}
