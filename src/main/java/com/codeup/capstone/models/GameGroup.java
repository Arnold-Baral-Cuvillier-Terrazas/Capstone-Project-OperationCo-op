package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="groups")
public class GameGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    private List<Game> games;
}
