package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class administracionUsuariosController {

    @GetMapping("/administracionUsuarios")
    public String administracionUsuarios(Model model){
        
        return "/administracionUsuarios";
    }

}
