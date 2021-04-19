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
import com.feriantes.portafolio.to.ContratoTO;

import oracle.jdbc.OracleTypes;

@Component
public class ContratoDao {
	
	@Autowired
	private Conexion conexion;
	
	public List<ContratoTO> obtenerContrato() throws SQLException {
			List<ContratoTO> listaRetorno = new ArrayList<>();
			try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL OBTENER_CONTRATO_SP(?)");) {
				call.registerOutParameter (1, OracleTypes.CURSOR);
				call.execute ();
				try (ResultSet rs = (ResultSet)call.getObject (1);) {  
					
					while (rs.next()) {
						ContratoTO contrato  = new ContratoTO();
						contrato.setIdContrato(rs.getInt("id_contrato"));
						contrato.setCodProductor(rs.getString("productor"));
						contrato.setEstadoContrato(rs.getInt("estado"));
						contrato.setFechaGeneracion(rs.getString("fecha_generacion"));
						contrato.setFechaVencimiento(rs.getString("fecha_vencimiento"));
						listaRetorno.add(contrato);
					}
				}
			}
			return listaRetorno;
	}
	
	public ContratoTO obtenerContratoId(int id) throws SQLException {
		ContratoTO contrato = new ContratoTO();
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL BUSCAR_CONTRATO_ID(?,?)");) {
			call.setInt("p_id", id);
			call.registerOutParameter ("p_resultado", OracleTypes.CURSOR);
			call.execute ();
			try (ResultSet rs = (ResultSet)call.getObject ("p_resultado");) {  
				
				while (rs.next()) {
					contrato.setIdContrato(rs.getInt("id_contrato"));
					contrato.setCodProductor(rs.getString("productor"));
					contrato.setEstadoContrato(rs.getInt("estado"));
					contrato.setFechaGeneracion(rs.getString("fecha_generacion"));
					contrato.setFechaVencimiento(rs.getString("fecha_vencimiento"));
				}
			}
		}
		return contrato;
	}
	
	public void crearContrato(ContratoTO contrato) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL AGREGAR_CONTRATO(?,?,?)");) {

			call.setString("p_productor", contrato.getCodProductor());
			call.setInt("p_estado", contrato.getEstadoContrato());
			call.setString("p_fechagen", contrato.getFechaGeneracion());
			call.setString("p_fechavenc", contrato.getFechaVencimiento());

			call.execute ();
		}
	}
	
	public void editaContrato(ContratoTO contrato) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement  call = con.prepareCall ("CALL ACTUALIZA_CONTRATO(?,?,?,?)");) {
			call.setString("p_productor", contrato.getCodProductor());
			call.setInt("p_estado", contrato.getEstadoContrato());
			call.setString("p_fechagen", contrato.getFechaGeneracion());
			call.setString("p_fechavenc", contrato.getFechaVencimiento());
			call.execute ();
		}
	}

}
