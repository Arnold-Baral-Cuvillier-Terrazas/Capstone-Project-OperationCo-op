package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.GroupUserRepository;
import com.codeup.capstone.repositories.TagRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {
    private final GroupRepository groupDao;
    private final UserRepository userDao;
    private final GroupUserRepository groupUserDao;
    private final TagRepository tagDao;


    public GroupController(GroupRepository groupDao, UserRepository userDao, GroupUserRepository groupUserDao, TagRepository tagDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
        this.groupUserDao = groupUserDao;
        this.tagDao = tagDao;
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
        return "redirect:/groups/" + group.getId();
    }

    //group's Profile pic area
    @PostMapping("/groups/pic")
    public String saveProfile(@RequestParam long groupId, @RequestParam String url, @ModelAttribute Group group) {
        Group saveProfile = groupDao.getOne(groupId);
        saveProfile.setProfilePic(url);
        groupDao.save(saveProfile);
        return "redirect:/groups/{id}";
    }

    //Double check on this mapping for displaying group profile.
    @GetMapping("/groups/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/groupProfile";
    }

//-------------for editing group profile information

    @GetMapping("/groups/edit/{id}")
    public String EditGroup(@PathVariable long id, Model model) {
        model.addAttribute("editGroup", groupDao.getOne(id));
        List<Tag> tagsList = tagDao.findAll();
        model.addAttribute("tagsList", tagsList);
        return "/groups/edit";
    }

    @PostMapping("/groups/edit/{id}")
    public String postEditGroup(@PathVariable long id, @RequestParam(name = "name") String name,
                                @RequestParam List<Long> tags,
                                @RequestParam(name = "description") String description) {
        List<Tag> tagLists = new ArrayList<>();
        for(int i= 0; i< tags.size(); i++){
            Tag thisTag = tagDao.getOne(tags.get(i));
            tagLists.add(thisTag);
        }
        Group group = groupDao.getOne(id);
        group.setName(name);
        group.setDescription(description);
        group.setTags(tagLists);
        groupDao.save(group);
        return "redirect:/groups/" + group.getId();
    }


    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable long id) {
        Group group = groupDao.getOne(id);
        groupDao.delete(group);
        return "redirect:/groups";
    }


}