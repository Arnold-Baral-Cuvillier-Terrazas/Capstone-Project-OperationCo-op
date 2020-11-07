package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.*;
import org.hibernate.validator.constraints.URL;
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
    private final GameRepository gameRepo;
    private final TagRepository tagDao;

    public GroupController(GroupRepository groupDao, UserRepository userDao,
                           GameRepository gameRepo, TagRepository tagDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
        this.gameRepo = gameRepo;
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
//        User user = userDao.getOne(((User)obj ).getId());
        User user = (User) obj;
        user = userDao.getOne(user.getId());
        Group group = new Group();
        List<User> users = new ArrayList<>();
        List<Group> groups = user.getGroups();
        users.add(user);
        group.setName(name);
        group.setDescription(description);
        group.setOwner(user);
        group.setUsers(users);
//        user.setGroup(group);
        groupDao.save(group);
        groups.add(group);
        user.setGroups(groups);
        userDao.save(user);
        return "redirect:/groups/profile/" + group.getId();
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
    @GetMapping("/groups/profile/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        Group group = groupDao.getOne(id);
        model.addAttribute("group", group);
//        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/profile";
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
        return "redirect:/groups/profile/" + group.getId();
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable long id) {
        Group group = groupDao.getOne(id);
        groupDao.delete(group);
        return "redirect:/groups";
    }

//    ---------- Games that the unique Group plays
//    ----------Inserting Favorites
//@PostMapping("/groups/favorite")
//public String groupFavorite(@RequestParam long gameId, @ModelAttribute Group group ) {
//    User tempGroup = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    Group groupFav = (groupDao.getOne(tempGroup.getId()));
//    Game gameFavorite = gameRepo.getOne(gameId);
//    List<Game> favorites = groupFav.getFavorites();
//    favorites.add(gameFavorite);
//    groupFav.setFavorites(favorites);
//    groupDao.save(groupFav);
//    return "redirect:/groups/profile";
//}

// ---------- Shows the users within the groups page
   @GetMapping("/groups/users")
    public String showUsers(Model model) {
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "/groups/profile";
    }

    @PostMapping("/groups/join/{id}")
    public String joinGroup(@PathVariable long id, @RequestParam long UserId) {
        Group group = groupDao.getOne(id);
        User user = userDao.getOne(UserId);
        List<Group> groups = user.getGroups();
        if(!groups.contains(group)){
            groups.add(group);
            user.setGroups(groups);
            userDao.save(user);
        }
        return "redirect:/groups/profile/" + id;
    }
    @GetMapping("/groups/userDelete/{id}")
    public String deleteGroupUser(@PathVariable long id, @RequestParam long UserId) {
        Group group = groupDao.getOne(id);
        User user = userDao.getOne(UserId);
        List<Group> groups = user.getGroups();
        groups.remove(group);
        user.setGroups(groups);
        userDao.save(user);
        return "redirect:/groups/profile/" + id;
    }

////    ---------- Groups Search
//    @GetMapping("/groups/search")
//    public String showGroup(@RequestParam String term, Model model) {
//        List<Group> groups = groupDao.searchByNameLike(term);
//        model.addAttribute("groups", groups);
//        return "/groups/search";
//    }
}