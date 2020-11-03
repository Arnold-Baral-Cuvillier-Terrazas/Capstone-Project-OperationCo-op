package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Group;
import com.codeup.capstone.models.Post;
import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.PostRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    //--------- Dependency Injection
    private final PostRepository postDao;
    private final UserRepository userRepo;
    private final GroupRepository groupDao;

    //    constructor
    public PostController(PostRepository postDao, UserRepository userRepo, GroupRepository groupDao) {
        this.postDao = postDao;
        this.groupDao = groupDao;
        this.userRepo = userRepo;
    }

//    showing all the posts
@GetMapping("/groups/posts")
public String index(Model model) {
    model.addAttribute("posts", postDao.findAll());
    return "posts/index";
}

    @GetMapping("/groups/posts/{id}")
    public String viewAllMessagesWithAjax(@PathVariable long id, Model model) {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = userRepo.getOne(thisAuthor.getId());
        model.addAttribute("group", groupDao.getOne(id));
        for (Group group : thisUser.getGroups()) {
            if (group.getId() == id) {
                model.addAttribute("posts", group.getPosts());
                return "posts/index";
            }
        }
        return "redirect:/profile";
    }


    @PostMapping("/group/posts/submit")
    public String createMessage(@ModelAttribute Post post, @ModelAttribute Group group) {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(thisAuthor);
        post.setGroup(group);
        postDao.save(post);
        return "redirect:/groups/posts/" + group.getId();
    }


//    for deleting the posts
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/groups/posts";

    }

    // Create Post Method
//    @PostMapping("/create")
//    public String savePost(@ModelAttribute Post post) throws ParseException, ParseException {
//
//        if (post.getId() == 0) {
//            // New Post
//            User author = (User) SecurityContextHolder
//                    .getContext()
//                    .getAuthentication()
//                    .getPrincipal();
//
//            // Set the currently logged in user to the newly created post
//            post.setUser(author);
//
//            // Get the current date/time and set in to the post
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String createDate = format.format(new Date());
//            Date date = format.parse(createDate);
//            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//            post.setDate(sqlDate);
//        }
//        postDao.save(post);
//        return "redirect:/posts";
//    }

}
