package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Tag;
import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.TagRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagDao;


    //---------constructor
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, TagRepository tagDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tagDao = tagDao;
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
        List<Tag> tagsList = tagDao.findAll();
        model.addAttribute("tagsList", tagsList);
        return "users/profile";
    }

    @PostMapping("/profile")
    public String createTag( @RequestParam List<Tag> tags) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        for(Tag tag: tags){
//            tag.getName();
//        }
        user.setTags(tags);
        userDao.save(user);
        return "users/profile";
    }

    //user's biography area
    @PostMapping("/profile/bio")
    public String saveProfileBio(@RequestParam long userId, @RequestParam String url, @ModelAttribute User user) {
        User saveProfileBio = userDao.getOne(userId);
        saveProfileBio.setBio(user.getBio());
        saveProfileBio.setProfilePic(url);
        userDao.save(saveProfileBio);
        return "redirect:/profile";
    }


}

