package com.feriantes.portafolio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.ProcesoTO;

@Controller
@RequestMapping(value = "/administracionProcesos")
public class administracionProcesosController {

    @Autowired
	private ProcesoDao ProcesoDao;

    @GetMapping()
    public String administracionProcesos(Model model,@AuthenticationPrincipal UserDetails userDetails){
    	cargaProceso(model);
        model.addAttribute("procesoCrear", new ProcesoTO());
        PerfilesService.seteaPerfil(model, userDetails);
        return "/administracionProcesos";
    }

        
    @GetMapping("/{idProceso}")
    public String buscarIdProceso(Model model, @PathVariable int idProceso,@AuthenticationPrincipal UserDetails userDetails){
    	ProcesoTO proceso = null;
    	try {
    		proceso = ProcesoDao.obteneProcesoId(idProceso);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProceso(model);
    	
    	
    	model.addAttribute("procesoCrear",proceso);
    	model.addAttribute("llamado","actualizar");
    	PerfilesService.seteaPerfil(model, userDetails);
    	return "/administracionProcesos";
    }
	@PostMapping()
    public String crearProceso(Model model ,
    		@ModelAttribute(value = "procesoCrear") ProcesoTO procesoCrear){
    	try {
    		if(procesoCrear.getFuncion().equals("actualizarProceso"))
    			ProcesoDao.editarProceso(procesoCrear);
    		else
    			ProcesoDao.crearProceso(procesoCrear);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProceso(model);
    	model.addAttribute("procesoCrear", new ProcesoTO());
    	return "redirect:/administracionProcesos";
    }

    private void cargaProceso(Model model ) {
    	List<ProcesoTO> listaProcesos = null;
    	try {
			listaProcesos = ProcesoDao.obtenerProceso();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	model.addAttribute("llamado","");
        if(listaProcesos != null)
        	model.addAttribute("listaProcesos",listaProcesos);
        
    }

	@DeleteMapping("/{idProceso}")
    public String borrarContrato(Model model, @PathVariable int idProceso,@AuthenticationPrincipal UserDetails userDetails){
    	try {
    		ProcesoDao.eliminaProceso(idProceso);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProceso(model);
    	
    	
    	model.addAttribute("procesoCrear",new ProcesoTO());
    	model.addAttribute("llamado","actualizar");
		model.addAttribute("eliminado",true);
		PerfilesService.seteaPerfil(model, userDetails);
    	return "/administracionProcesos";
    }
    

    

}
