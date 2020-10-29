package com.codeup.capstone.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GroupUserKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "group_id")
    Long groupId;


    public GroupUserKey(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public GroupUserKey() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
