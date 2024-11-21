package com.example.notebook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        String str = Terméknév();
        model.addAttribute("str", str);
        return "termekek";
    }

    String Terméknév() {
        String str = "";
        for (Gep gep : gepRepo.findAll()) {
            str += gep.getGyártó() + " " + gep.getTípus() + "<br>";
        }
        return str;
    }


}
