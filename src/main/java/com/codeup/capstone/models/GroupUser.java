package com.codeup.capstone.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;


@Entity
@Table(name = "group_user")

public class GroupUser {
    @EmbeddedId
    private GroupUserKey id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    @ColumnDefault("true")
    private Boolean isGroupAdmin;


    @Column(length = 100)
    private Boolean isApproved;

    public GroupUser(GroupUserKey id, Group group, User user, Boolean isGroupAdmin, Boolean isApproved) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.isGroupAdmin = isGroupAdmin;
        this.isApproved = isApproved;
    }

    public GroupUser(Group group, User user, Boolean isGroupAdmin, Boolean isApproved) {
        this.group = group;
        this.user = user;
        this.isGroupAdmin = isGroupAdmin;
        this.isApproved = isApproved;
    }

    public GroupUser() {

    }

    public GroupUserKey getId() {
        return id;
    }

    public void setId(GroupUserKey id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getGroupAdmin() {
        return isGroupAdmin;
    }

    public void setGroupAdmin(Boolean groupAdmin) {
        isGroupAdmin = groupAdmin;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
