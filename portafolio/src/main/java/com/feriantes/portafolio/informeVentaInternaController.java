package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class informeVentaInternaController {
    @GetMapping("/informeVentaInterna")
    public String informeVentaInterna(Model model){
        
        return "/informeVentaInterna";
    }
}
