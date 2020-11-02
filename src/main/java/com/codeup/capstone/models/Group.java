package com.codeup.capstone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Made nullable true to make Create Group DB to work.
    @Column(unique = true)
    @ColumnDefault("true")
    private String discordUserId;

    @Column(unique = true)
    private String gameId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @JsonIgnore
    private List<Post> posts;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<User> users;


//    establishing relationship for group and tags
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_tags",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private List<Tag> tags;

//-----------Constructor

    public Group(long id, String name, String description, String discordUserId, String gameId, List<Post> posts, List<GroupUser> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profilePic = profilePic;
        this.discordUserId = discordUserId;
        this.gameId = gameId;
        this.posts = posts;
        this.tags = tags;
    }


    public Group() {}

//----------getters and setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(String discordUserId) {
        this.discordUserId = discordUserId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
