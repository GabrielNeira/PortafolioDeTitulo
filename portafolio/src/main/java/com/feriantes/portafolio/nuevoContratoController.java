package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nuevoContratoController {

    @GetMapping("/nuevoContrato")
    public String nuevoContrato(Model model){
        
        return "nuevoContrato";

}
}
