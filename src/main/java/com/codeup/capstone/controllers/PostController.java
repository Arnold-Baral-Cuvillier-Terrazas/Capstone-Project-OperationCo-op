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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public PostController(PostRepository postDao,  UserRepository userRepo, GroupRepository groupDao) {
        this.postDao = postDao;
        this.groupDao = groupDao;
        this.userRepo = userRepo;
    }

//    showing all the posts
@GetMapping("/messages.json")
public @ResponseBody List<Post> viewAllMessagesInJSONFormat() {
    User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User thisUser = userRepo.getOne(thisAuthor.getId());
    Group group = groupDao.findById(thisUser.getGroup()).orElse(null);
    if (group == null){
        //Returns empty list
        return new ArrayList<Post>();
    }
    return group.getMessages();
//          return messageDao.findAll();
}

    @GetMapping("/messages")
    public String redirectToId() {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = userRepo.getOne(thisAuthor.getId());
        return "redirect:/messages/" + thisUser.getGroup().getId();
    }


    @GetMapping("/messages/{id}")
    public String viewAllMessagesWithAjax(@PathVariable long id, Model model) {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User thisUser = userRepo.getOne(thisAuthor.getId());
        model.addAttribute("id", thisUser.getGroup().getId());
        if (thisUser.getGroup().getId() == id) {
            model.addAttribute("message", new Message());
            return "messages/ajax";
        }
        return "/home";
    }

    // if group_id == current group display messages
    // grab group_id from messages --> if group_id in messages == group_id in users --> display messages

    @PostMapping("/messages/submit")
    public String createMessage(@ModelAttribute Message message) {
        User thisAuthor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setOwner(thisAuthor);
        User thisUser = userRepo.getOne(thisAuthor.getId());
        Group authorGroup = thisUser.getGroup();
        long groupId = authorGroup.getId();
        Group currentGroup = groupDao.getOne(groupId);
        message.setGroup(currentGroup);
        postDao.save(message);
        return "redirect:/messages";
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
