package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nuevoProcesoController {
    @GetMapping("/nuevoProceso")
    public String nuevoProceso(Model model){
        
        return "/nuevoProceso";
    }
    
}
