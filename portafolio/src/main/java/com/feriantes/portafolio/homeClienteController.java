package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String nuevoProceso(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		PerfilesService.seteaPerfil(model, userDetails);
		List<ProcesoTO> list = new ArrayList<>();
		try {
			list = ProcesoDao.obtenerProceso();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Map<Integer, List<ProcesoTO>> map = new HashMap<>();
		int contador = 1;
		List<ProcesoTO> listProceso = new ArrayList<>();
		for (ProcesoTO procesoTO : list) {

			listProceso.add(procesoTO);
			if (contador == 3) {
				contador = 1;
				map.put(procesoTO.getIdProceso(), listProceso);
				listProceso = new ArrayList<>();
				continue;
			}
			contador++;
		}
		if(!listProceso.isEmpty()) {
			map.put(-9999, listProceso);
		}
		model.addAttribute("procesos", map);
		return "homeCliente";
	}

}
