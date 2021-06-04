package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nuevoUsuarioController {
    
    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model){
        
        return "nuevoUsuario";
    }
}
