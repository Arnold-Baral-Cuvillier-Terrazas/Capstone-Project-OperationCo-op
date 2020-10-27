package com.codeup.capstone.controllers;

import com.codeup.capstone.repositories.GameRepository;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class TagController {
    private final UserRepository userDao;
    private final GroupRepository groupDao;
    private final GameRepository gameRepo;

    public TagController(UserRepository userDao, GroupRepository groupDao, GameRepository gameRepo) {
        this.userDao = userDao;
        this.groupDao = groupDao;
        this.gameRepo = gameRepo;
    }

//    methods


}
