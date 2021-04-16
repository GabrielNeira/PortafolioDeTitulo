package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class administracionContratosController {


  
    @GetMapping("/administracionContratos")
    public String administracionContratos(Model model){
        
        return "/administracionContratos";
    }

}
