package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feriantes.portafolio.dao.UsuarioDao;
import com.feriantes.portafolio.to.UsuarioTO;

@Controller
@RequestMapping(value = "/administracionUsuarios")
public class administracionUsuariosController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
    @GetMapping()
    public String administracionUsuarios(Model model){
    	cargaUsuarios(model);
        model.addAttribute("usuarioCrear", new UsuarioTO());
        return "/administracionUsuarios";
    }
    
    @GetMapping("/{idUsuario}")
    public String buscarUsuarioIdUsuarios(Model model, @PathVariable int idUsuario){
    	UsuarioTO usuario = null;
    	try {
    		usuario = usuarioDao.obtenerUsuarioId(idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaUsuarios(model);
    	
    	
    	model.addAttribute("usuarioCrear",usuario);
    	model.addAttribute("llamado","actualizar");
    	return "/administracionUsuarios";
    }
    
    @PostMapping()
    public String crearUsuario(Model model ,
    		@ModelAttribute(value = "usuarioCrear") UsuarioTO usuarioCrear){
    	try {
    		if(usuarioCrear.getFuncion().equals("actualizarUsuario"))
    			usuarioDao.editarUsuario(usuarioCrear);
    		else
    			usuarioDao.crearUsuario(usuarioCrear);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaUsuarios(model);
    	model.addAttribute("usuarioCrear", new UsuarioTO());
    	return "/administracionUsuarios";
    }
    
    private void cargaUsuarios(Model model ) {
    	List<UsuarioTO> listaUsuarios = null;
    	try {
			listaUsuarios = usuarioDao.obtenerUsuario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	model.addAttribute("llamado","");
        if(listaUsuarios != null)
        	model.addAttribute("listaUsuario",listaUsuarios);
        
    }

}
