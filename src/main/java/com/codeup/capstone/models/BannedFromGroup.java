package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banned_from_group")
public class BannedFromGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group_id;


}

//    @Column(nullable = false)
//    private String groupId;


