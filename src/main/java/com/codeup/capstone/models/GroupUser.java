package com.codeup.capstone.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@Table(name = "group_user")

public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column( length = 100 )
    @ColumnDefault("true")
    private Boolean isGroupAdmin;

    @Column(nullable = false)
    private String groupId;

    @Column( length = 100)
    private Boolean isApproved;

    public GroupUser(long id, String userId, Boolean isGroupAdmin, String groupId, Boolean isApproved) {
        this.id = id;
        this.userId = userId;
        this.isGroupAdmin = isGroupAdmin;
        this.groupId = groupId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getGroupAdmin() {
        return isGroupAdmin;
    }

    public void setGroupAdmin(Boolean groupAdmin) {
        isGroupAdmin = groupAdmin;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
