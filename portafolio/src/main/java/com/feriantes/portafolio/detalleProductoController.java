package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.ProcesoTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class detalleProductoController {
	@Autowired
    private PerfilesService PerfilesService;
	
    @GetMapping("/detalleProducto")
    public String home(Model model,@AuthenticationPrincipal UserDetails userDetails){
    	PerfilesService.seteaPerfil(model, userDetails);
        return "detalleProducto";
    }
}