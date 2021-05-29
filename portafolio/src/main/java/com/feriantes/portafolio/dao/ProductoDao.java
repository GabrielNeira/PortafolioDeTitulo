package com.feriantes.portafolio.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feriantes.portafolio.conf.Conexion;
import com.feriantes.portafolio.to.ProductoTO;

import oracle.jdbc.OracleTypes;

@Component
public class ProductoDao {
	
	@Autowired
	private Conexion conexion;
	
	public List<ProductoTO> obtenerProducto() throws SQLException {
		List<ProductoTO> listaRetorno = new ArrayList<>();
		try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL OBTENER_PRODUCTO_SP(?)");) {
			call.registerOutParameter (1, OracleTypes.CURSOR);
			call.execute ();
			try (ResultSet rs = (ResultSet)call.getObject (1);) {  
				
				while (rs.next()) {
					ProductoTO producto  = new ProductoTO();
					producto.setIdProducto(rs.getInt("id_producto"));
					producto.setNombreProducto(rs.getString("nombre"));
					producto.setCantidadProducto(rs.getInt("cantidad"));
					producto.setPesoProducto(rs.getInt("peso"));
					producto.setVolumenProducto(rs.getInt("volumen"));
					producto.setEstadoProducto(rs.getInt("estado"));
					producto.setRefrigeracion(rs.getInt("refrigeracion"));
					producto.setFechaLlegada(rs.getString("fecha_llegada"));
					producto.setCodProductor(rs.getInt("codigo_productor"));
					listaRetorno.add(producto);
				}
			}
		}
		return listaRetorno;
}

public ProductoTO obtenerProductoId(int id) throws SQLException {
	ProductoTO producto = new ProductoTO();
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL BUSCAR_PRODUCTO_ID(?,?)");) {
		call.setInt("p_id", id);
		call.registerOutParameter ("p_resultado", OracleTypes.CURSOR);
		call.execute ();
		try (ResultSet rs = (ResultSet)call.getObject ("p_resultado");) {  
			
			while (rs.next()) {
				producto.setIdProducto(rs.getInt("id_producto"));
				producto.setNombreProducto(rs.getString("nombre"));
				producto.setCantidadProducto(rs.getInt("cantidad"));
				producto.setPesoProducto(rs.getInt("peso"));
				producto.setVolumenProducto(rs.getInt("volumen"));
				producto.setEstadoProducto(rs.getInt("estado"));
				producto.setRefrigeracion(rs.getInt("refrigeracion"));
				producto.setFechaLlegada(rs.getString("fecha_llegada"));
				producto.setCodProductor(rs.getInt("codigo_productor"));
			}
		}
	}
	return producto;
}
public void crearProducto(ProductoTO producto) throws SQLException {
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL AGREGAR_PRODUCTO(?,?,?,?,?,?,?,?)");) {
		call.setString("p_nombre", producto.getNombreProducto());
		call.setInt("p_cantidad", producto.getCantidadProducto());
		call.setInt("p_peso",producto.getPesoProducto());
		call.setInt("p_volumen",producto.getVolumenProducto());
		call.setInt("p_estado",producto.getEstadoProducto());
		call.setInt("p_refrigeracion",producto.getRefrigeracion());
		call.setString("p_fecha_llegada",producto.getFechaLlegada());
		call.setInt("p_id_productor",producto.getCodProductor());
		call.execute ();
	}
}

public void editarProducto(ProductoTO producto) throws SQLException {
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL ACTUALIZA_PRODUCTO(?,?,?,?,?,?,?,?,?)");) {
				call.setInt("p_id", producto.getIdProducto());
				call.setString("p_nombre", producto.getNombreProducto());
				call.setInt("p_cantidad", producto.getCantidadProducto());
				call.setInt("p_peso",producto.getPesoProducto());
				call.setInt("p_volumen",producto.getVolumenProducto());
				call.setInt("p_estado",producto.getEstadoProducto());
				call.setInt("p_refrigeracion",producto.getRefrigeracion());
				call.setString("p_fecha_llegada",producto.getFechaLlegada());
				call.setInt("p_id_productor",producto.getCodProductor());
				call.execute ();
	}
}

public void eliminaProducto(int idProducto) throws SQLException {
	try (Connection con = conexion.getConnection();
	CallableStatement  call = con.prepareCall ("CALL ELIMINAR_PRODUCTO(?)");) {
		call.setInt("p_id", idProducto);
		call.execute ();
	}
}


public List<ProductoTO> obtenerListaProductosPorMail(String email) throws SQLException {
	List<ProductoTO> listaRetorno = new ArrayList<>();
	try (Connection con = conexion.getConnection();
		CallableStatement  call = con.prepareCall ("OBTIENE_PRODUCTOS_USUARIO(?)");) {
		call.setString("p_email", email);
		call.registerOutParameter (2, OracleTypes.CURSOR);
		call.execute ();
		try (ResultSet rs = (ResultSet)call.getObject (1);) {  
			
			while (rs.next()) {
				ProductoTO producto =new ProductoTO();
				producto.setIdProducto(rs.getInt("id_producto"));
				producto.setNombreProducto(rs.getString("nombre"));
				producto.setCantidadProducto(rs.getInt("cantidad"));
				producto.setPesoProducto(rs.getInt("peso"));
				producto.setVolumenProducto(rs.getInt("volumen"));
				producto.setEstadoProducto(rs.getInt("estado"));
				producto.setRefrigeracion(rs.getInt("refrigeracion"));
				producto.setFechaLlegada(rs.getString("fecha_llegada"));
				producto.setCodProductor(rs.getInt("codigo_productor"));
			}
		}
	}
	return listaRetorno;
}

}
