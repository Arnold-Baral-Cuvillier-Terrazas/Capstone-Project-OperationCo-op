package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupController {
    private final GroupRepository groupDao;

    public GroupController(GroupRepository groupDao) {
        this.groupDao = groupDao;
    }

   //To show all groups
    @GetMapping("/groups")
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupDao.findAll());
        return "/groups/index";
    }

    @GetMapping("/group/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "groups/create";
    }


    //This should save the new Group
    @PostMapping("/group/create")
    public String saveGroup(@ModelAttribute Group group,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description) {

        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        return "groups/index";

    }


    //Double check on this mapping for displaying group profile.
    @GetMapping("group/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        return "/groups/profile";
    }

}
