package com.feriantes.portafolio;

import java.sql.SQLException;
import java.util.List;

import com.feriantes.portafolio.dao.ProductoDao;
import com.feriantes.portafolio.to.ProductoTO;

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

@Controller
@RequestMapping(value = "/administracionProductos")
public class administracionProductosController {

    @Autowired
	private ProductoDao ProductoDao;

    @GetMapping()
    public String administracionProductos(Model model,@AuthenticationPrincipal UserDetails userDetails){
    	cargaProducto(model);
        model.addAttribute("productoCrear", new ProductoTO());
        PerfilesService.seteaPerfil(model, userDetails);
        return "/administracionProductos";
    }

        
    @GetMapping("/{idProducto}")
    public String buscarIdProducto(Model model, @PathVariable int idProducto, @AuthenticationPrincipal UserDetails userDetails){
    	ProductoTO proceso = null;
    	try {
    		proceso = ProductoDao.obtenerProductoId(idProducto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProducto(model);
    
    	
    	model.addAttribute("productoCrear",proceso);
    	model.addAttribute("llamado","actualizar");
    	PerfilesService.seteaPerfil(model, userDetails);
    	return "/administracionProductos";
    }
	@PostMapping()
    public String crearProducto(Model model ,
    		@ModelAttribute(value = "productoCrear") ProductoTO productoCrear,@AuthenticationPrincipal UserDetails userDetails){
    	try {
    		if(productoCrear.getFuncion().equals("actualizarProducto"))
            ProductoDao.editarProducto(productoCrear);
    		else
            ProductoDao.crearProducto(productoCrear);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProducto(model);
    	model.addAttribute("productoCrear", new ProductoTO());
    	PerfilesService.seteaPerfil(model, userDetails);
    	return "redirect:/administracionProductos";
    }

    private void cargaProducto(Model model ) {
    	List<ProductoTO> listaProductos = null;
    	try {
			listaProductos = ProductoDao.obtenerProducto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	model.addAttribute("llamado","");
        if(listaProductos != null)
        	model.addAttribute("listaProducto",listaProductos);
        
    }

	@DeleteMapping("/{idProducto}")
    public String borrarProducto(Model model, @PathVariable int idProducto,@AuthenticationPrincipal UserDetails userDetails){
    	try {
    		ProductoDao.eliminaProducto(idProducto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	cargaProducto(model);
    	
    	
    	model.addAttribute("productoCrear",new ProductoTO());
    	model.addAttribute("llamado","actualizar");
		model.addAttribute("eliminado",true);
		PerfilesService.seteaPerfil(model, userDetails);
    	return "/administracionProductos";
    }
	
}
