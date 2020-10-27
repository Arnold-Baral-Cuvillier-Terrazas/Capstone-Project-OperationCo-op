package com.codeup.capstone.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "group_user")

public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //    @Column(nullable = false)
//    private String userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Group> groups;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<User> users;


    @Column(length = 100)
    @ColumnDefault("true")
    private Boolean isGroupAdmin;


//    @Column(nullable = false)
//    private String groupId;

    @Column(length = 100)
    private Boolean isApproved;


    public GroupUser(long id, List<Group> groups, Boolean isGroupAdmin, Boolean isApproved) {
        this.id = id;
        this.groups = groups;
        this.isGroupAdmin = isGroupAdmin;
        this.isApproved = isApproved;
    }

    public GroupUser() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
