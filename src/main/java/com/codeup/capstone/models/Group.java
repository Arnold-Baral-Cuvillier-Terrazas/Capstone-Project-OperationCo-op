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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    // Made nullable true to make Create Group DB to work.
    @Column(unique = true)
    private String discordUserId;

    //Check if no parameters in this is ok.
    @Column()
    private String gameId;

    //This is the OneToMany relationship. One Group should have many Posts.
    //This should be right.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Post> posts;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    //ManytoMany to group and users



    //Check out user_rating table and Group_table to see if it can me in one rating.


    // Determine whether it will be ManyToMany or ManyToOne below.

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<User> users;



    }


