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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homeProductor")
public class homeProductorController {
    
	@Autowired
	private ProcesoDao ProcesoDao;
	@Autowired
    private PerfilesService PerfilesService;
   @GetMapping("/getById/{idProceso}")
    public String home(Model model, @PathVariable Integer idProceso,@AuthenticationPrincipal UserDetails userDetails){
       ProcesoTO proceso=null;
		try {
			proceso = ProcesoDao.obteneProcesoId(idProceso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("proceso",proceso);
		PerfilesService.seteaPerfil(model, userDetails);
        return "homeProductor";
    }

    @GetMapping("")
    public String llenarTabla(Model model,@AuthenticationPrincipal UserDetails userDetails){
        List<ProcesoTO> procesos=null;
		try {
			procesos = ProcesoDao.obtenerProceso();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("procesos",procesos);
		PerfilesService.seteaPerfil(model, userDetails);
        return "homeProductor";
    }


}
