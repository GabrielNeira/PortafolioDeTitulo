package com.feriantes.portafolio;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feriantes.portafolio.dao.ProcesoDao;
import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;
import com.itextpdf.text.DocumentException;


@Controller
@RequestMapping("/informeVenta")
public class informeVentaInternaController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ProcesoDao ProcesoDao;
	
	  
    @GetMapping()
    public String nuevoUsuario(Model model,@AuthenticationPrincipal UserDetails userDetails){
    	PerfilesService.seteaPerfil(model, userDetails);
        return "/informeVentaInterna";
    }
	
    @GetMapping("/Interna")
    public ResponseEntity<ByteArrayResource> informeVentaInterna(Model model,@AuthenticationPrincipal UserDetails userDetails) throws SQLException{
    	PerfilesService.seteaPerfil(model, userDetails);
    	ByteArrayOutputStream archivo = null;
    	List<ProcesoTO> listaPDF = obtenerListaDetalles(1);
    	try {
    		archivo = PdfService.generaPDF(listaPDF);
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

        ByteArrayResource  resource = new ByteArrayResource(archivo.toByteArray());;
        
        String mineType = servletContext.getMimeType("InformeInterna.pdf");
		MediaType mediaType;
		try {
			mediaType = MediaType.parseMediaType(mineType);
		} catch (Exception e) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=InformeInterna.pdf")
				.header("Estado", "ok").contentType(mediaType).contentLength(resource.contentLength()).body(resource);
    	
    }
    
    
    @GetMapping("/externa")
    public ResponseEntity<ByteArrayResource> informeVentaExterna(Model model,@AuthenticationPrincipal UserDetails userDetails) throws SQLException{
    	PerfilesService.seteaPerfil(model, userDetails);
    	ByteArrayOutputStream archivo = null;
    	List<ProcesoTO> listaPDF = obtenerListaDetalles(2);
    	try {
    		archivo = PdfService.generaPDF(listaPDF);
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

        ByteArrayResource  resource = new ByteArrayResource(archivo.toByteArray());;
        
        String mineType = servletContext.getMimeType("InformeExterna.pdf");
		MediaType mediaType;
		try {
			mediaType = MediaType.parseMediaType(mineType);
		} catch (Exception e) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=InformeExterna.pdf")
				.header("Estado", "ok").contentType(mediaType).contentLength(resource.contentLength()).body(resource);
    	
    }
    
    
    private List<ProcesoTO> obtenerListaDetalles(int estado) throws SQLException {
    	List<ProcesoTO> listProceso = ProcesoDao.obtenerProceso();
    	List<ProcesoTO> listaProceso = new ArrayList<>();
    	for (ProcesoTO procesoTO : listProceso) {
    		List<DetalleProcesoTO> detalleProceso = ProcesoDao.obtenerDetalleProceso(procesoTO.getIdProceso());
    		for (DetalleProcesoTO  item : detalleProceso) {
				if(item.getTipoVenta() ==estado ) {
					if(procesoTO.getListaDetalleProceso() == null) {
						procesoTO.setListaDetalleProceso(new ArrayList<>());
					}
					procesoTO.getListaDetalleProceso().add(item);
				}
				listaProceso.add(procesoTO);
			}
		}
    	return listaProceso;
    }
    
}
