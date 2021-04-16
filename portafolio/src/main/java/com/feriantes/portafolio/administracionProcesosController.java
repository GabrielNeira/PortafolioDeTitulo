package com.feriantes.portafolio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class administracionProcesosController {


  
    @GetMapping("/administracionProcesos")
    public String administracionProcesos(Model model){
        
        return "/administracionUsuarios";
    }

}
