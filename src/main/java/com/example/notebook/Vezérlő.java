package com.example.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired private UzenetRepo uzenetRepo;

    @ModelAttribute
    public void addUsernameToModel(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
    }

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
        model.addAttribute("user", new User()); // Üres objektum átadása a sablonnak
        return "register";
    }

    @Autowired
    private UserRepository userRepo;
    @PostMapping("/register")
    public String Regisztálás(@ModelAttribute User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Titkosítás
        user.setRole("USER");
        userRepo.save(user);
        model.addAttribute("username", user.getUsername());
        return "login";
    }

    @GetMapping("/login")
    public String Bejelentkezés(Model model){
        return "login";
    }

    @GetMapping("/contact")
    public String Kapcsolat(Model model) {
        model.addAttribute("uzenet", new Uzenet());
        return "uzenet";// A fenti üzenet űrlapja
    }


    @PostMapping("/sendMessage")
    public String ÜzenetKüldése(@ModelAttribute Uzenet uzenet, @AuthenticationPrincipal User user, Model model) {

        if (user != null) {
            // Ha be van jelentkezve felhasználó, ő küldi az üzenetet
            uzenet.setSender(user);
        } else {
            // Ha nincs bejelentkezve felhasználó, akkor hozzuk létre a "Vendég" felhasználót
            User guest = userRepo.findByUsername("Vendég").orElse(null);

            if (guest == null) {
                guest = new User();
                guest.setUsername("Vendég");
                guest.setPassword(passwordEncoder.encode("vendeg")); // Jelszó titkosítása
                guest.setEmail("vendeg@example.com");
                guest.setRole("GUEST");
                userRepo.save(guest);
            }

            uzenet.setSender(guest); // Ha nincs bejelentkezve felhasználó, akkor a vendég küldi az üzenetet
        }

        uzenetRepo.save(uzenet);
        return "redirect:/contact";
    }

    @GetMapping("/messages")
    public String ÜzenetElérése(Model model) {
        // Lekérjük az üzeneteket időrendben, a legfrissebb legyen elöl
        List<Uzenet> uzenetek = uzenetRepo.findAllByOrderByTimestampDesc();
        model.addAttribute("uzenetek", uzenetek);
        return "üzenetekTábla";  // A 'messages' a HTML oldal neve
    }


}
