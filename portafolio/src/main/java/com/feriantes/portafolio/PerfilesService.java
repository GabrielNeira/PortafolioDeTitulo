package com.feriantes.portafolio;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.feriantes.portafolio.dao.UsuarioDao;
import com.feriantes.portafolio.to.UsuarioTO;

@Service
public class PerfilesService {
	
	@Autowired
	private  UsuarioDao usuarioDao; 
	
	public  void seteaPerfil(Model model, UserDetails userDetails) {
		String perfil = null;
		for (GrantedAuthority aut : userDetails.getAuthorities()) {
			perfil = aut.getAuthority();
		}
		try {
			UsuarioTO user = usuarioDao.obtenerUsuarioMail(userDetails.getUsername());
			model.addAttribute("nombreUsuario", user.getNombre()+ " "+ user.getApellido());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("perfil", Integer.parseInt(perfil));
	}

}
