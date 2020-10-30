package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.GroupUserRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupController {
    private final GroupRepository groupDao;
    private final UserRepository userDao;
    private final GroupUserRepository groupUserDao;

    public GroupController(GroupRepository groupDao, UserRepository userDao, GroupUserRepository groupUserDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
        this.groupUserDao = groupUserDao;
    }

    //To show all groups
    @GetMapping("/groups")
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupDao.findAll());
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/index";
    }

    //Mapping to get group/create.html
    @GetMapping("/groups/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "groups/create";
    }


    //This should save the new Group should there be a user authentication for the user to access this page?
    // Also the redirect mapping doesn't show the
    @PostMapping("/groups/create")
    public String saveGroup(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description) {

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(obj instanceof UserDetails)) {
            return "redirect:/login";
        }
        User user = (User) obj;
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        GroupUserKey groupUserKey = new GroupUserKey(user.getId(), group.getId());
        GroupUser groupUser = new GroupUser(groupUserKey,group, user,true, true);
        groupUserDao.save(groupUser);
        return "redirect:/groups/profile/" + group.getId();
    }

    //Double check on this mapping for displaying group profile.
    @GetMapping("/groups/profile/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/profile";
    }

    @GetMapping("/groups/edit/{id}")
    public String EditGroup(@PathVariable long id, Model model) {
        model.addAttribute("editGroup", groupDao.getOne(id));
        return "/groups/edit";
    }

    @PostMapping("/groups/edit/{id}")
    public String postEditGroup(@PathVariable long id, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description) {
        Group group = groupDao.getOne(id);
        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        return "redirect:/groups/profile/" + group.getId();
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable long id) {
        Group group = groupDao.getOne(id);
        groupDao.delete(group);
        return "redirect:/groups";
    }

}