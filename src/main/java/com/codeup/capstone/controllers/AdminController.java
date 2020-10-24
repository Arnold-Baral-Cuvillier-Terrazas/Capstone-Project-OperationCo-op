package com.codeup.capstone.controllers;

import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private UserRepository userDao;


    public AdminController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/admin")
    public String siteAdmin(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if (user.isSiteAdmin()) {
//            model.addAttribute("users", userDao.findAll());
//            return "admin/admin";
//        } else {
            return "users/login";
//        }
    }

    //delete user
    @PostMapping("/admin/delete")
    public String deleteProfile(@RequestParam long userId) {
        User deleteProfile = userDao.getOne(userId);
        userDao.delete(deleteProfile);
        return "redirect:/admin";
    }
}
