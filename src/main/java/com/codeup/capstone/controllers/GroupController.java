package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupController {
    private final GroupRepository groupDao;

    public GroupController(GroupRepository groupDao) {
        this.groupDao = groupDao;
    }

    //Will need this one in final result?
    @RequestMapping(path = "/groups", method = RequestMethod.GET)
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupDao.findAll());
        return "/groups/index";
    }


    //Double check on this mapping for displaying group profile.
    @GetMapping("group/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        return "/groups/profile";
    }

//    (Group) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
}
