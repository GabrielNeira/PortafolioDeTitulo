package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import com.feriantes.portafolio.dao.ContratoDao;
import com.feriantes.portafolio.to.ContratoTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/administracionContratos")
public class administracionContratosController {

    @Autowired
	private ContratoDao ContratoDao;

    @GetMapping()
    public String administracionContratos(Model model){
    	cargaContrato(model);
        model.addAttribute("contratoCrear", new ContratoTO());
        return "/administracionContratos";
    }

        
    @GetMapping("/{idContrato}")
    public String buscarIdContrato(Model model, @PathVariable int idContrato){
    	ContratoTO proceso = null;
    	try {
    		proceso = ContratoDao.obtenerContratoId(idContrato);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaContrato(model);
    	
    	
    	model.addAttribute("contratoCrear",proceso);
    	model.addAttribute("llamado","actualizar");
    	return "/administracionContratos";
    }
	@PostMapping()
    public String crearContrato(Model model ,
    		@ModelAttribute(value = "contratoCrear") ContratoTO contratoCrear){
    	try {
    		if(contratoCrear.getFuncion().equals("actualizarContrato"))
            ContratoDao.editaContrato(contratoCrear);
    		else
            ContratoDao.crearContrato(contratoCrear);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaContrato(model);
    	model.addAttribute("contratoCrear", new ContratoTO());
    	return "redirect:/administracionContratos";
    }

    private void cargaContrato(Model model ) {
    	List<ContratoTO> listaContratos = null;
    	try {
			listaContratos = ContratoDao.obtenerContrato();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	model.addAttribute("llamado","");
        if(listaContratos != null)
        	model.addAttribute("listaContrato",listaContratos);
        
    }

	@DeleteMapping("/{idContrato}")
    public String borrarContrato(Model model, @PathVariable int idContrato){
    	try {
    		ContratoDao.eliminaContrato(idContrato);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaContrato(model);
    	
    	
    	model.addAttribute("contratoCrear",new ContratoTO());
    	model.addAttribute("llamado","actualizar");
		model.addAttribute("eliminado",true);
    	return "/administracionContratos";
    }

}
