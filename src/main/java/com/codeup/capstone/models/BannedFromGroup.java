package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banned_from_group")
public class BannedFromGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String groupId;


}
