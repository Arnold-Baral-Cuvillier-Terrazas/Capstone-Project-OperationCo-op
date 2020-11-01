package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private
    Date release_date;

    @Column
    private
    long critics_rating;

    @Column
    String art_cover;

    @Column
    private long igdb_api_id;


    public Game(){
    }

    public Game(long id, String title, String description, Date release_date, Long critics_rating, String art_cover, Long igdb_api_id, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.critics_rating = critics_rating;
        this.art_cover = art_cover;
        this.igdb_api_id = igdb_api_id;
//        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public long getCritics_rating() {
        return critics_rating;
    }

    public void setCritics_rating(long critics_rating) {
        this.critics_rating = critics_rating;
    }

    public String getArt_cover() {
        return art_cover;
    }

    public void setArt_cover(String art_cover) {
        this.art_cover = art_cover;
    }

    public long getIgdb_api_id() {
        return igdb_api_id;
    }

    public void setIgdb_api_id(long igdb_api_id) {
        this.igdb_api_id = igdb_api_id;
    }

//    public List<Tag> tags(){
//        return tags;
//    }
//    public void setGames(List<Tag> tags){
//        this.tags = tags;
//    }
}
