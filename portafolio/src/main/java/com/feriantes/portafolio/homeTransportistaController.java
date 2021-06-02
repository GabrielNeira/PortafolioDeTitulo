package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.dao.ProductoDao;
import com.feriantes.portafolio.to.ProcesoTO;
import com.feriantes.portafolio.to.ProductoTO;

@Controller
@RequestMapping(value = "/homeTransportista")
public class homeTransportistaController {
	@Autowired
	private ProductoDao ProductoDao;
	@Autowired
	private ProcesoDao procesoDao;

	@GetMapping()
	public String obtenerProductosProceso(Model model, @AuthenticationPrincipal UserDetails userDetails) {

		List<ProductoTO> listaProductos = null;
		PerfilesService.seteaPerfil(model, userDetails);
		try {
			listaProductos = ProductoDao.obtenerProductosProcesos();
			for (ProductoTO productoTO : listaProductos) {
				ProcesoTO proceso = procesoDao.obteneProcesoId(productoTO.getIdProceso());
				productoTO.setEstadoProceso(proceso.getEstadoProceso());
				productoTO.setGlosaEstado(proceso.getGlosaEstado());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("listaProductosProceso", listaProductos);
		return "/homeTransportista";
	}
}
