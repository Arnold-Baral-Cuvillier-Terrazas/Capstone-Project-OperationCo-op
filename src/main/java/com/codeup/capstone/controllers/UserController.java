package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Tag;
import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.TagRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagDao;
    private final GroupRepository groupDao;


    //---------constructor
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, TagRepository tagDao, GroupRepository groupDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tagDao = tagDao;
        this.groupDao = groupDao;
    }

// ---------methods for work flow------------

    // Want the user to be able to access a sign-up page
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/signup";
    }

    // What happens when a new user submits the register form?
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "confirmPassword") String confirmPassword,
                           @RequestParam(name = "password") String password) {
        if (!user.getPassword().equals(confirmPassword)) {
            return "users/signup";
        }
        String hash = passwordEncoder.encode(user.getPassword()); // ~plaintext password
        user.setPassword(hash); // immediately no longer have access to the plaintext password. It's hashed
        if (user.getId() == 0) {
            userDao.save(user);
            return "redirect:/login";
        } else {
            userDao.save(user);
            return "users/profile";
        }
    }

    // redirecting login user into their profile page
    @GetMapping("/profile")
    public String profilePage(Model model) {
        User getUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDao.getOne(getUser.getId()));
        model.addAttribute("photoUrl", userDao.getOne(getUser.getId()).getProfilePic());
        return "users/profile";
    }

    //user's biography area
    @PostMapping("/profile/pic")
    public String saveProfile(@RequestParam long userId, @RequestParam String url, @ModelAttribute User user) {
        User saveProfile = userDao.getOne(userId);
        saveProfile.setProfilePic(url);
        userDao.save(saveProfile);
        return "redirect:/profile";
    }

//    editing user profile information like bio and tags
    @GetMapping("/users/edit/{id}")
    public String EditProfile(@PathVariable long id, Model model) {
        model.addAttribute("editUser", userDao.getOne(id));
        List<Tag> tagsList = tagDao.findAll();
        model.addAttribute("tagsList", tagsList);
        return "/users/editProfile";
    }

    @PostMapping("/users/edit/{id}")
    public String postEditGroup(@PathVariable long id, @RequestParam List<Long> tags, @RequestParam String bio , @RequestParam String psnInfo) {
        List<Tag> tagList = new ArrayList<>();
        for(int i= 0; i< tags.size(); i++){
            Tag thisTag = tagDao.getOne(tags.get(i));
            tagList.add(thisTag);
        }
        User user = userDao.getOne(id);
        user.setTags(tagList);
        user.setPsnInfo(psnInfo);
        userDao.save(user);
        return "redirect:/profile";
    }


}

