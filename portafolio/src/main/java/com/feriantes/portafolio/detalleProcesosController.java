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
import com.feriantes.portafolio.dao.ProductoDao;
import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;
import com.feriantes.portafolio.to.ProductoTO;


@Controller
@RequestMapping("/detalleProcesos")
public class detalleProcesosController {
    
	@Autowired
	private ProcesoDao ProcesoDao;

    @Autowired
	private ProductoDao ProductoDao;


   @GetMapping("/{idProceso}")
    public String home(Model model, @PathVariable Integer idProceso,  @AuthenticationPrincipal UserDetails userDetails){
       ProcesoTO proceso=null;
		try {
			proceso = ProcesoDao.obteneProcesoId(idProceso);
			List<DetalleProcesoTO> lista= ProcesoDao.obtenerDetalleProceso(idProceso);
			proceso.setListaDetalleProceso(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<ProductoTO> listaRetorno = null;
		try {
			listaRetorno = ProductoDao.obtenerListaProductosPorMail(userDetails.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("listaProductosUsuario", listaRetorno);
		model.addAttribute("proceso",proceso);
        return "/detalleProcesos";
    }

}
