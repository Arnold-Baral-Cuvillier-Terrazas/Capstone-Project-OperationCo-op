package com.codeup.capstone.controllers;

import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.GroupUserRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupAdminController {

    private final UserRepository userDao;
    private final GroupRepository groupDao;
    private final GroupUserRepository groupUserDao;


    public GroupAdminController(UserRepository userDao, GroupRepository groupDao, GroupUserRepository groupUserDao) {
        this.userDao = userDao;
        this.groupDao = groupDao;
        this.groupUserDao = groupUserDao;
    }

    //    print-out all the users
//    @GetMapping("/groups/admin/{id}")
//    public String groupAdmin(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        Group group = (Group) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (groupUserDao.findAllByGroupAdmin(true)) {
//            model.addAttribute("users", userDao.findAll());
//            return "admin/group_admin";
//        } else {
//            return "users/login";
//        }
//    }

    //groupAdmin join request with other users.
    //groups/admin/request


    //delete the user
//    @PostMapping("/admin/delete")
//    public String deleteProfile(@RequestParam long userId) {
//        User deleteProfile = userDao.getOne(userId);
//        userDao.delete(deleteProfile);
//        return "redirect:/admin";
//    }
}
