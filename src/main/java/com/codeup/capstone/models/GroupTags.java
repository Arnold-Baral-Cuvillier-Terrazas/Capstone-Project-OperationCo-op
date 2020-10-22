package com.codeup.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_tags")
public class GroupTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private String tagId;

}
