package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/index";
    }

    //Mapping to get group/create.html
    @GetMapping("/group/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "groups/create";
    }


    //This should save the new Group should there be a user authentication for the user to access this page?
    // Also the redirect mapping doesn't show the
    @PostMapping("/group/create")
    public String saveGroup(@ModelAttribute Group group,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description) {

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        return "groups/profile";
    }

    //Double check on this mapping for displaying group profile.
    @GetMapping("group/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/profile";
    }

//    @GetMapping("/groups/edit/{id}")
//    public String editAd(@PathVariable long id, Model model) {
//        Group group = groupDao.getOne(id);
//        model.addAttribute("group", group);
//        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return "groups/profile";
//    }
//
//    @PostMapping("/groups/edit")
//    public String updateGroup(@ModelAttribute Group group) {
//        groupDao.save(group);
//        return "groups/profile" + groupDao.getOne(group.getId());
//    }

    @GetMapping("/groups/edit")
    public String showUserEditForm(Model model, Group group) {
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("group", group);
        return "/groups/edit";
    }

    @PostMapping("/group/edit")
    public String updateGroup(@ModelAttribute Group group, Model model,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description) {

        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        return "groups/profile";

    }
}