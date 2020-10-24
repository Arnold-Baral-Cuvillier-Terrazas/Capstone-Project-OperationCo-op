package com.codeup.capstone.controllers;

import com.codeup.capstone.repositories.PostRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    private final PostRepository adDao;
    private final UserRepository userRepo;

    public PostController(PostRepository adDao, UserRepository userRepo) {
        this.adDao = adDao;
        this.userRepo = userRepo;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", adDao.findAll());
        return "posts/index";
    }
}
