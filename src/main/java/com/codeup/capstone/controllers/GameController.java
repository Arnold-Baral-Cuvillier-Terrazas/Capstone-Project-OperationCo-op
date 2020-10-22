package com.codeup.capstone.controllers;

import com.codeup.capstone.models.Game;
import com.codeup.capstone.repositories.GameRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class GameController {
    private final GameRepository gameRepo;



    public GameController(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @GetMapping("/games")
    public String index(Model model) {
        model.addAttribute("games", gameRepo.findAll());
        return " games/index";
    }

    @RequestMapping(path = "games/{id}", method = RequestMethod.GET)
    public String showSingleGame(@PathVariable long id, Model model){
        Game game = gameRepo.getOne(id);
        model.addAttribute("game", gameRepo.getOne(id));
        return "games/show";
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
