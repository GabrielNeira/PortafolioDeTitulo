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
import com.feriantes.portafolio.to.UsuarioTO;

import oracle.jdbc.OracleTypes;

@Component
public class UsuarioDao {
	
	@Autowired
	private Conexion conexion;
	
	public List<UsuarioTO> obtenerUsuario() throws SQLException {
			List<UsuarioTO> listaRetorno = new ArrayList<>();
			try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL OBTENER_USUARIO_SP(?)");) {
				call.registerOutParameter (1, OracleTypes.CURSOR);
				call.execute ();
				try (ResultSet rs = (ResultSet)call.getObject (1);) {  
					
					while (rs.next()) {
						UsuarioTO user  = new UsuarioTO();
						user.setIdUsuario(rs.getInt("id_usuario"));
						user.setNombre(rs.getString("nombre"));
						user.setApellido(rs.getString("apelllido"));
						user.setEmail(rs.getString("email"));
						listaRetorno.add(user);
					}
				}
			}
			return listaRetorno;
	}
	
	public UsuarioTO obtenerUsuarioId(int id) throws SQLException {
		UsuarioTO usuario = new UsuarioTO();
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL BUSCAR_USUARIO_ID(?,?)");) {
			call.setInt("p_id", id);
			call.registerOutParameter ("p_resultado", OracleTypes.CURSOR);
			call.execute ();
			try (ResultSet rs = (ResultSet)call.getObject ("p_resultado");) {  
				
				while (rs.next()) {
					usuario.setIdUsuario(rs.getInt("id_usuario"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellido(rs.getString("apelllido"));
					usuario.setEmail(rs.getString("email"));
				}
			}
		}
		return usuario;
	}
	
	public void crearUsuario(UsuarioTO user) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL AGREGAR_USUARIO(?,?,?)");) {
			call.setString("p_nombre", user.getNombre());
			call.setString("p_apellido", user.getApellido());
			call.setString("p_email", user.getEmail());
			call.execute ();
		}
	}
	
	public void editarUsuario(UsuarioTO user) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL ACTUALIZA_USUARIO(?,?,?,?)");) {
			call.setString("p_nombre", user.getNombre());
			call.setString("p_apellido", user.getApellido());
			call.setString("p_email", user.getEmail());
			call.setInt("p_id", user.getIdUsuario());
			call.execute ();
		}
	}

}
