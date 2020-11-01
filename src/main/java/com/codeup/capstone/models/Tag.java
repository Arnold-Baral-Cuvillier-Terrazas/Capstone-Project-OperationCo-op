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


// ----------- Creating Relationship for tags and users
    @ManyToMany(mappedBy = "tags")
    private List<User> users;

    // ----------- Creating Relationship for tags and users
    @ManyToMany(mappedBy = "tags")
    private List<Group> groups;


//    @ManyToOne
//    @JoinColumn (name = "group_id")
//    private Group group;


// ---------------- Constructor

    public Tag(long id, String name, List<User> users, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.groups = groups;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
