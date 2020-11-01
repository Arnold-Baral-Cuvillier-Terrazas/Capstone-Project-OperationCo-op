package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Game;
import com.codeup.capstone.models.Group;
import com.codeup.capstone.models.Tag;
import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.GameRepository;
import com.codeup.capstone.repositories.GroupRepository;
import com.codeup.capstone.repositories.TagRepository;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class GameController {
    private final GameRepository gameRepo;
    private final UserRepository userRepo;
    private final TagRepository tagRepo;
    private final GroupRepository groupRepo;


    public GameController(GameRepository gameRepo, UserRepository userRepo, TagRepository tagRepo, GroupRepository groupRepo) {
        this.userRepo = userRepo;
        this.gameRepo = gameRepo;
        this.tagRepo = tagRepo;
        this.groupRepo = groupRepo;
    }

    @GetMapping("/games")
    public String index(Model model) {
        model.addAttribute("games", gameRepo.findAll());
        return "/games/games";
    }


    @RequestMapping(path = "/games/{id}", method = RequestMethod.GET)

    public String showGame(@PathVariable long id, Model model){
        User user = userRepo.findAll().get(0);
        Game game = gameRepo.getOne(id);
        Tag tag = tagRepo.getOne(id);
        Group group = groupRepo.findAll().get(0);
        model.addAttribute("game", gameRepo.getOne(id));
        return "/games/search";
    }
//    @GetMapping("game/create")
//    public String showCreateView(Model model) {
//        public String createGame(@ModelAttribute Game game) {
//            String update;
////            Here will go the email Service if needed later
//            return "redirect:/games/" + game.getId();
//
//        }

//        @GetMapping("games/delete/{id}")
//                public String deleteGame(@PathVariable long id) {
//            Game game = gameRepo.getOne(id);
//            game.setIgdb_api_id(gameRepo.getOne(id).getIgdb_api_id()); //get api info from DB
//            gameRepo.delete(game);
//
//            //Send Delete Notification
//            //this will be added in later, if needed
////            emailService.prepareAndSend(game.getOwner().getEmail(),
////            "Delete Game: " + game.getTitle(),
////            game.getTitle() + "\n\n" + game.getDescription());
////            return "redirect:/games";
//        }

//        @GetMapping("games/edit/{id}")
//                public String editGame(@PathVariable long id, Model model) {
//            Game game = gameRepo.getOne(id);
//            model.addAttribute("game", game);
//            return "games/create";
//        }

//    }
}
