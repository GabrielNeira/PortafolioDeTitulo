package com.feriantes.portafolio;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeClienteController {
    @GetMapping("/homeCliente")
    public String nuevoProceso(Model model, @AuthenticationPrincipal UserDetails userDetails){
        PerfilesService.seteaPerfil(model, userDetails);

        return "homeCliente";
    }
    
}
