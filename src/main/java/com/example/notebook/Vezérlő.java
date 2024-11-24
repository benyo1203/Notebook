package com.example.notebook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Vezérlő {
    @Autowired private GepRepo gepRepo;
    @Autowired private OprendszerRepo oprendszerRepo;
    @Autowired private ProcesszorRepo processzorRepo;

    @Autowired CustomUserDetailsService userService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserDetailsService userDetailsService;

    @GetMapping("/index")
    public String Főoldal() {
        return "index";
    }

    @GetMapping("/termekek")
    public String Termekek(Model model) {
        List<Gep> gepek = (List<Gep>) gepRepo.findAll();
        model.addAttribute("gepek", gepek);
        return "termekek";
    }

    @GetMapping("/register")
    public String RegisztrációsŰrlap(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @Autowired
    private UserRepository userRepo;
    @PostMapping("/register_feldolgozas")
    public String Regisztálás(@ModelAttribute User user, Model model) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword("USER");
        userRepo.save(user);
        model.addAttribute("id", user.getUsername());
        return "login";
    }

    @GetMapping("/login")
    public String Bejelentkezés(){
        return "login";
    }





}
