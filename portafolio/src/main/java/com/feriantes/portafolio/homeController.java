package com.feriantes.portafolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	@Autowired
    private PerfilesService PerfilesService;
	
    @GetMapping("/home")
    public String home(Model model,@AuthenticationPrincipal UserDetails userDetails){
    	PerfilesService.seteaPerfil(model, userDetails);
        return "home";
    }
}
