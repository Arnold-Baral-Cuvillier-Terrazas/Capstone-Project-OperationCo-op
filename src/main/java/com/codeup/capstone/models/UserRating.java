package com.codeup.capstone.models;

import javax.persistence.*;

@Entity
@Table(name="userRating")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 500)
    private String rating;

    @Column(nullable = false, length = 500)
    private String rating_user_id;

    @Column(nullable = false, length = 500)
    private String rated_user_id;

}
