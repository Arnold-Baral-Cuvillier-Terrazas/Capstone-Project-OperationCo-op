package com.codeup.capstone.models;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "tags")
public class GameTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

//    @ManytoMany(mappedBy = "tags")
//    private  List<Game> tags;

    public GameTag(){
    }
    public GameTag(long id, String name)
//    , List<Game> games
    {
        this.id = id;
        this.name = name;
//        this.games = games;
    }
}
