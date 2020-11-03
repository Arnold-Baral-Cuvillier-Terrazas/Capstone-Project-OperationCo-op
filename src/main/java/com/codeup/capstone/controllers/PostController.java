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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping("/groups/posts/{id}")
    public String viewAllPosts(@PathVariable long id, Model model) {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = userRepo.getOne(thisAuthor.getId());
        model.addAttribute("group_id", id);
        for (Group group : thisUser.getGroups()) {
            if (group.getId() == id) {
                model.addAttribute("posts", group.getPosts());
                model.addAttribute("post", new Post());
                return "posts/showPosts";
            }
        }
        return "redirect:/profile";
    }


//    creating the posts

    @PostMapping("/groups/posts/{group_id}/submit")
    public String createMessage(@ModelAttribute Post post, @PathVariable long group_id) throws ParseException, ParseException {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(thisAuthor);
        Group thisGroup = groupDao.getOne(group_id);
        post.setGroup(thisGroup);
//        printing out the date in format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = format.format(new Date());
        Date date = format.parse(createDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        post.setDate(sqlDate);
//        saving the information
        postDao.save(post);
        return "redirect:/groups/posts/" + thisGroup.getId();
    }

    //    editing posts
    @GetMapping("/groups/posts/edit/{id}")
    public String EditPost(@PathVariable long id, Model model) {
        model.addAttribute("editPost", postDao.getOne(id));
        return "posts/editPost";
    }

    @PostMapping("/groups/posts/edit/{id}")
    public String newEditPost(@PathVariable long id,
                              @RequestParam(name = "Message_body") String MessageBody) {
        Post post = postDao.getOne(id);
//        post.setParent_post_id(parentPostId);
        post.setMessage_body(MessageBody);
        postDao.save(post);
        return "redirect:/groups/posts";
    }

    //    for deleting the posts
    @GetMapping("/groups/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/groups/posts";
    }


}
