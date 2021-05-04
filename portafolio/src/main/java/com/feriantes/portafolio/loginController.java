package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping(path = {"/", "/login"})
    public String welcome(Model model){
        
        return "/login";
    }

}
