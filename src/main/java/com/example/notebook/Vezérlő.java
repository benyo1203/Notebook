package com.example.notebook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Vezérlő {
    @Autowired private GepRepo gepRepo;
    @Autowired private OprendszerRepo oprendszerRepo;
    @Autowired private ProcesszorRepo processzorRepo;

    @GetMapping("/index")
    public String Fooldal() {
        return "index";
    }

    @GetMapping("/termekek")
    public String Termekek(Model model) {
        List<Gep> gepek = (List<Gep>) gepRepo.findAll();
        model.addAttribute("gepek", gepek);
        return "termekek";
    }

    @GetMapping("/kapcsolat")
    public String Kapcsolat(Model model) {
        
    }

}
