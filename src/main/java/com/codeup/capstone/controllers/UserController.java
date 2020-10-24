package com.codeup.capstone.controllers;

import com.codeup.capstone.models.User;
import com.codeup.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final  PasswordEncoder passwordEncoder;

//---------constructor
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

// ---------methods for work flow
    @GetMapping("/")
    public String welcome() {
        return "home";
    }

    // Want the user to be able to access a sign-up page
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/signup";
    }

    // What happens when a new user submits the register form?
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam(name="confirmPassword")  String confirmPassword, @RequestParam(name="password") String password)  {
        if(user.getPassword() != confirmPassword){
            return "users/register";
        }
        String hash = passwordEncoder.encode(user.getPassword()); // ~plaintext password
        user.setPassword(hash); // immediately no longer have access to the plaintext password. It's hashed
        if(user.getId()==0){
            userDao.save(user);
            return "redirect:/login";
        }else {
            userDao.save(user);
            return "users/profile";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "users/login";
    }

    @GetMapping("/profile")
    public String profilePage( Model model){
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "users/profile";
    }

    @GetMapping("/user/edit")
    public String editUserInformation(@ModelAttribute User user,Model model){
        model.addAttribute("user",(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "users/signup";
    }

//    @GetMapping("user/{id}")
//    public String profilePage(@PathVariable long id, Model model) {
//        model.addAttribute("user", userDao.getOne(id));
//        return "/users/profile";
//    }

}
