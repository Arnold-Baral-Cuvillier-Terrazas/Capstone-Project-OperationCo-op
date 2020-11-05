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
        return "groups/index";
    }

    //Mapping to get group/create.html
    @GetMapping("/groups/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "/groups/create";
    }


    //This should save the new Group should there be a user authentication for the user to access this page?
    // Also the redirect mapping doesn't show the
    @PostMapping("/groups/create")
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
        return "redirect:/groups/" + group.getId();
    }

    //Double check on this mapping for displaying group profile.
    @GetMapping("/groups/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("group", groupDao.getOne(id));
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "groups/profile";
    }

    @GetMapping("/groups/edit/{id}")
    public String EditGroup(@PathVariable long id, Model model) {
        model.addAttribute("editGroup", groupDao.getOne(id));
<<<<<<< HEAD
        return "/groups/edit";
=======
        List<Tag> tagsList = tagDao.findAll();
        model.addAttribute("tagsList", tagsList);
        return "groups/edit";
>>>>>>> 078a2a675a54bd853b9214da648172898feb1024
    }

    @PostMapping("/groups/edit/{id}")
    public String postEditGroup(@PathVariable long id, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description) {
        Group group = groupDao.getOne(id);
        group.setName(name);
        group.setDescription(description);
        groupDao.save(group);
        return "redirect:/groups/" + group.getId();
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable long id) {
        Group group = groupDao.getOne(id);
        groupDao.delete(group);
        return "redirect:/groups";
    }

<<<<<<< HEAD
=======
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
        return "groups/profile";
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
>>>>>>> 078a2a675a54bd853b9214da648172898feb1024
}