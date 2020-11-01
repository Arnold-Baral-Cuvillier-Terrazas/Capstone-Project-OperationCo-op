package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100,unique = true)
    private String name;


// ----------- Creating Relationship for tags and group
    @ManyToMany(mappedBy = "tags")
    private List<User> users;

//    @ManyToOne
//    @JoinColumn (name = "group_id")
//    private Group group;

// ------------ Creating Relation for the tags and games
    @ManyToMany(mappedBy = "tags")
    private List<Game> games;

// ---------------- Constructor

    public Tag(long id, String name, List<User> users, Group group, List<Game> games) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.games = games;
//        this.group = group;
    }

    public Tag() {
    }

//----------------getters and setters

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }

//    ---------- Adding in Games List
    public List<Game> getGames(){
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }

}
