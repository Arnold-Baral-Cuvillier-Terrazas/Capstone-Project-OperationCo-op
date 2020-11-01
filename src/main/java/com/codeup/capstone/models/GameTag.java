package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tags")

public class GameTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Game> games;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
