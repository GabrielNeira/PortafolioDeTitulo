package com.feriantes.portafolio;


import java.sql.SQLException;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/detalleProcesos")
public class detalleProcesosController {
    
	@Autowired
	private ProcesoDao ProcesoDao;

   @GetMapping("/{idProceso}")
    public String home(Model model, @PathVariable Integer idProceso){
       ProcesoTO proceso=null;
		try {
			proceso = ProcesoDao.obteneProcesoId(idProceso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("proceso",proceso);
        return "/detalleProcesos";
    }



	@PostMapping
    public ResponseEntity<Object> create(@RequestBody DetalleProcesoTO detalle) {
		try {
			System.out.println(detalle.toString());
			ProcesoDao.crearDetalleProceso(detalle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return  new ResponseEntity<>( "OK", HttpStatus.OK);
    }

	

	

   
	


}
