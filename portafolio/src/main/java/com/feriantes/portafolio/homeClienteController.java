package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.ProcesoTO;

@Controller
public class homeClienteController {
	
	@Autowired
    private PerfilesService PerfilesService;
	
	@Autowired
	private ProcesoDao ProcesoDao;
	
	
    @GetMapping("/homeCliente")
    public String nuevoProceso(Model model, @AuthenticationPrincipal UserDetails userDetails){
        PerfilesService.seteaPerfil(model, userDetails);
        List<ProcesoTO> list = new ArrayList<>();
        try {
        	list = ProcesoDao.obtenerProceso();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        model.addAttribute("procesos", list);
        return "homeCliente";
    }
    
}
