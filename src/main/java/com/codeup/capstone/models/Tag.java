package com.codeup.capstone.models;

import javax.persistence.*;


@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100,unique = true)
    private String name;

// ---------------- Constructor

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
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
}
