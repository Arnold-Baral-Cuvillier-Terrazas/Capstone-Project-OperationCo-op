package com.codeup.capstone.models;

import javax.persistence.*;


@Entity
@Table(name = "group_user")

public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Boolean isGroupAdmin;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private Boolean isApproved;
}
