package com.example.notebook;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Vezérlő {

    @GetMapping("/")
    public String Fooldal() {

        return "index";
    }


}
