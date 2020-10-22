package com.codeup.capstone.controllers;

import com.codeup.capstone.models.*;
import com.codeup.capstone.repositories.GroupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupController {
    private final GroupRepository groupDao;

    public GroupController(GroupRepository groupDao) {
        this.groupDao = groupDao;
    }

    @RequestMapping(path = "/groups", method = RequestMethod.GET)
    public String showAllGroups(Model model) {
        model.addAttribute("groups", groupDao.findAll());
        return "/groups/index";
    }

}
