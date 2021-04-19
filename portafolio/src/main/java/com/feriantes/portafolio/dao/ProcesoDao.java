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
import com.feriantes.portafolio.to.ProcesoTO;

import oracle.jdbc.OracleTypes;

@Component
public class ProcesoDao {
	
	@Autowired
	private Conexion conexion;
	
	public List<ProcesoTO> obtenerProceso() throws SQLException {
		List<ProcesoTO> listaRetorno = new ArrayList<>();
		try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL OBTENER_PROCESO_SP(?)");) {
			call.registerOutParameter (1, OracleTypes.CURSOR);
			call.execute ();
			try (ResultSet rs = (ResultSet)call.getObject (1);) {  
				
				while (rs.next()) {
					ProcesoTO proceso  = new ProcesoTO();
					proceso.setIdProceso(rs.getInt("id_proceso"));
					proceso.setEstadoProceso(rs.getInt("estado"));
					proceso.setNombreProceso(rs.getString("nombre_proceso"));
					proceso.setFechaInicio(rs.getString("fecha_inicio"));
					proceso.setFechaTermino(rs.getString("fecha_termino"));
					listaRetorno.add(proceso);
				}
			}
		}
		return listaRetorno;
}

public ProcesoTO obteneProcesoId(int id) throws SQLException {
	ProcesoTO proceso = new ProcesoTO();
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL BUSCAR_USUARIO_ID(?,?)");) {
		call.setInt("p_id", id);
		call.registerOutParameter ("p_resultado", OracleTypes.CURSOR);
		call.execute ();
		try (ResultSet rs = (ResultSet)call.getObject ("p_resultado");) {  
			
			while (rs.next()) {
				proceso.setIdProceso(rs.getInt("idProceso"));
				proceso.setEstadoProceso(rs.getInt("estadoProceso"));
				proceso.setNombreProceso(rs.getString("nombreProceso"));
				proceso.setFechaInicio(rs.getString("fechaInicio"));
				proceso.setFechaTermino(rs.getString("fechaTermino"));
			}
		}
	}
	return proceso;
}
public void crearProceso(ProcesoTO proceso) throws SQLException {
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL AGREGAR_PROCESO(?,?,?)");) {
		call.setInt("p_estado", proceso.getEstadoProceso());
		call.setString("p_nombre", proceso.getNombreProceso());
		call.setString("p_fechaini", proceso.getFechaInicio());
		call.setString("p_fechafin", proceso.getFechaTermino());
		call.execute ();
	}
}

public void editarProceso(ProcesoTO proceso) throws SQLException {
	try (Connection con = conexion.getConnection();
			CallableStatement  call = con.prepareCall ("CALL ACTUALIZA_PROCESO(?,?,?,?)");) {
			call.setInt("p_estado", proceso.getEstadoProceso());
			call.setString("p_nombre", proceso.getNombreProceso());
			call.setString("p_fechaini", proceso.getFechaInicio());
			call.setString("p_fechafin", proceso.getFechaTermino());
		call.execute ();
	}
}

}
