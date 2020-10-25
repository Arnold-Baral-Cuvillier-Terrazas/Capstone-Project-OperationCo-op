package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Post;
import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.PostRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

//--------- Dependency Injection
    private final PostRepository adDao;
    private final UserRepository userRepo;


//    constructor
    public PostController(PostRepository adDao, UserRepository userRepo) {
        this.adDao = adDao;
        this.userRepo = userRepo;
    }

//    showing all the posts
    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", adDao.findAll());
        return "posts/index";
    }

//    // Create Post Get Method
    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // Create Post Method
    @PostMapping("/create")
    public String savePost(@ModelAttribute Post post) throws ParseException, ParseException {

        if (post.getId() == 0) {
            // New Post
            User author = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            // Set the currently logged in user to the newly created post
            post.setUser(author);

            // Get the current date/time and set in to the post
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = format.format(new Date());
            Date date = format.parse(createDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            post.setDate(sqlDate);
        }

        // save Post in the database
        adDao.save(post);
        return "redirect:/posts";
    }

}
