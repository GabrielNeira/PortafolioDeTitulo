package com.feriantes.portafolio;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public class PerfilesService {
	
	public static void seteaPerfil(Model model, UserDetails userDetails) {
		String perfil = null;
		for (GrantedAuthority aut : userDetails.getAuthorities()) {
			perfil = aut.getAuthority();
		}
		model.addAttribute("perfil", Integer.parseInt(perfil));
	}

}
