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

<<<<<<< HEAD
    @Column(nullable = false, length = 500)
    private String rating_user_id;
=======
    @ManyToOne
    @JoinColumn(name = "rating_user_id")
    private User rating_user;

    @ManyToOne
    @JoinColumn(name = "rated_user_id")
    private User rated_user;


    public UserRating(long id, Integer rating, User rating_user, User rated_user) {
        this.id = id;
        this.rating = rating;
        this.rating_user = rating_user;
        this.rated_user = rated_user;
    }

    public UserRating(Integer rating, User rating_user, User rated_user) {
        this.rating = rating;
        this.rating_user = rating_user;
        this.rated_user = rated_user;
    }

    public UserRating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getRating_user() {
        return rating_user;
    }

    public void setRating_user(User rating_user) {
        this.rating_user = rating_user;
    }
>>>>>>> 078a2a675a54bd853b9214da648172898feb1024

    @Column(nullable = false, length = 500)
    private String rated_user_id;

}
