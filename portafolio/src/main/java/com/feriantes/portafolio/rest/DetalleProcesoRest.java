package com.feriantes.portafolio.rest;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.DetalleProcesoTO;

@RestController
@RequestMapping("/detalleProcesosrest")
public class DetalleProcesoRest {
	
	@Autowired
	private ProcesoDao ProcesoDao;
	@Autowired
	private ProcesoDao procesoDao;


	@PostMapping()
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

	@GetMapping("/cambiaEstado/{idProceso}/{estado}")
	public String actualizaEstadoProcesoTranporte(@PathVariable Integer idProceso,@PathVariable Integer estado ){
		 
		 try {
			 procesoDao.actualizaEstadoProceso(idProceso, estado);
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
 
		 return "OK";
		 
		 
	}

}
