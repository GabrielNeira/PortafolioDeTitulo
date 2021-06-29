package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;


@Controller
@RequestMapping("/detalleProducto")
public class detalleProductoController {
	@Autowired
    private PerfilesService PerfilesService;
	
	@Autowired
	private ProcesoDao ProcesoDao;
    
    @GetMapping("/{idProceso}")
    public String home(Model model,@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer idProceso){
    	PerfilesService.seteaPerfil(model, userDetails);
    	 ProcesoTO proceso=null;
    	try {
			proceso = ProcesoDao.obteneProcesoId(idProceso);
			List<DetalleProcesoTO> lista= ProcesoDao.obtenerDetalleProceso(idProceso);
			proceso.setListaDetalleProceso(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	model.addAttribute("proceso",proceso);
		PerfilesService.seteaPerfil(model, userDetails);
		
		
        return "detalleProducto";
    }
}