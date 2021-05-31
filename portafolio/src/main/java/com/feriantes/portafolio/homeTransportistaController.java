package com.feriantes.portafolio;


import java.sql.SQLException;
import java.util.List;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.dao.ProductoDao;
import com.feriantes.portafolio.to.ProcesoTO;
import com.feriantes.portafolio.to.ProductoTO;
import com.feriantes.portafolio.to.enums.EnumEstados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping(value = "/homeTransportista")
public class homeTransportistaController {
    @Autowired
	private ProductoDao ProductoDao;
    @Autowired
    private ProcesoDao procesoDao;
 
   @GetMapping()
   public String obtenerProductosProceso(Model model){
    
    List<ProductoTO> listaProductos = null;
    try {
        listaProductos = ProductoDao.obtenerProductosProcesos();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       model.addAttribute("listaProductosProceso", listaProductos);
       return "/homeTransportista";
   }
}
