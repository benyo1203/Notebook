package com.example.nootbook;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Vezérlő {

    @GetMapping("/")
    public String Fooldal() {

        return "index";
    }


}
