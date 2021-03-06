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
import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;
import com.feriantes.portafolio.to.enums.EnumEstados;

import oracle.jdbc.OracleTypes;

@Component
public class ProcesoDao {

	@Autowired
	private Conexion conexion;

	public List<ProcesoTO> obtenerProceso() throws SQLException {
		List<ProcesoTO> listaRetorno = new ArrayList<>();
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL OBTENER_PROCESO_SP(?)");) {
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			try (ResultSet rs = (ResultSet) call.getObject(1);) {

				while (rs.next()) {
					ProcesoTO proceso = new ProcesoTO();
					proceso.setIdProceso(rs.getInt("id_proceso"));
					proceso.setEstadoProceso(rs.getInt("estado"));
					proceso.setGlosaEstado(EnumEstados.obtenerEstado(proceso.getEstadoProceso()).name());
					proceso.setNombreProceso(rs.getString("nombre_proceso"));
					proceso.setFechaInicio(rs.getString("fecha_inicio"));
					proceso.setFechaTermino(rs.getString("fecha_termino"));
					proceso.setUrlDetalle(
							"window.location.href='./detalleProcesos/getById/" + proceso.getIdProceso() + "'");
					listaRetorno.add(proceso);
				}
			}
		}
		return listaRetorno;
	}

	public ProcesoTO obteneProcesoId(int id) throws SQLException {
		ProcesoTO proceso = new ProcesoTO();
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL BUSCAR_PROCESO_ID(?,?)");) {
			call.setInt("p_id", id);
			call.registerOutParameter("p_resultado", OracleTypes.CURSOR);
			call.execute();
			try (ResultSet rs = (ResultSet) call.getObject("p_resultado");) {

				while (rs.next()) {
					proceso.setIdProceso(rs.getInt("id_proceso"));
					proceso.setEstadoProceso(rs.getInt("estado"));
					proceso.setGlosaEstado(EnumEstados.obtenerEstado(proceso.getEstadoProceso()).name());
					proceso.setNombreProceso(rs.getString("nombre_proceso"));
					proceso.setFechaInicio(rs.getString("fecha_inicio"));
					proceso.setFechaTermino(rs.getString("fecha_termino"));
				}
			}
		}
		return proceso;
	}

	public List<DetalleProcesoTO> obtenerDetalleProceso(int id) throws SQLException {
		List<DetalleProcesoTO> detalleProceso = new ArrayList<DetalleProcesoTO>();
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL BUSCAR_DETALLE_PROCESO_ID(?,?)");) {
			call.setInt("p_id", id);
			call.registerOutParameter("p_resultado", OracleTypes.CURSOR);
			call.execute();
			try (ResultSet rs = (ResultSet) call.getObject("p_resultado");) {

				while (rs.next()) {
					DetalleProcesoTO det = new DetalleProcesoTO();
					det.setIdDetalleProceso(rs.getInt("id_detalle_proceso"));
					det.setIdProceso(rs.getInt("id_proceso"));
					det.setCantidad(rs.getInt("cantidad"));
					det.setTipoVenta(rs.getInt("tipo_venta"));
					det.setIdProducto(rs.getInt("id_producto"));
					det.setNombreProducto(rs.getString("nombre"));
					det.setGlosaTipoVenta(det.getTipoVenta() == 1 ? "NACIONAL" : "EXPORTACION");
					detalleProceso.add(det);
				}
			}
		}
		return detalleProceso;
	}

	public void crearProceso(ProcesoTO proceso) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL AGREGAR_PROCESO(?,?,?,?)");) {
			call.setInt("p_estado", proceso.getEstadoProceso());
			call.setString("p_nombre", proceso.getNombreProceso());
			call.setString("p_fechaini", proceso.getFechaInicio());
			call.setString("p_fechafin", proceso.getFechaTermino());
			call.execute();
		}
	}

	public void crearDetalleProceso(DetalleProcesoTO detalleProceso) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL AGREGAR_DETALLE_PROCESO(?,?,?,?)");) {
			System.out.println(detalleProceso.toString());
			call.setInt("p_id_proceso", detalleProceso.getIdProceso());
			call.setInt("p_cantidad", detalleProceso.getCantidad());
			call.setInt("p_tipo_venta", detalleProceso.getTipoVenta());
			call.setInt("p_id_producto", detalleProceso.getIdProducto());
			call.execute();
		}
	}

	public void editarProceso(ProcesoTO proceso) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL ACTUALIZA_PROCESO(?,?,?,?,?)");) {

			call.setInt("p_id", proceso.getIdProceso());
			call.setInt("p_estado", proceso.getEstadoProceso());
			call.setString("p_nombre", proceso.getNombreProceso());
			call.setString("p_fechaini", proceso.getFechaInicio());
			call.setString("p_fechafin", proceso.getFechaTermino());
			call.execute();
		}
	}

	public void eliminaProceso(int idProceso) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL ELIMINAR_PROCESO(?)");) {
			call.setInt("p_id", idProceso);
			call.execute();
		}
	}

	public void actualizaEstadoProceso(int idProceso, int idEstado) throws SQLException {
		try (Connection con = conexion.getConnection();
				CallableStatement call = con.prepareCall("CALL ACTUALIZA_ESTADO_PROCESO(?,?)");) {
			call.setInt("p_id_proceso", idProceso);
			call.setInt("p_nuevo_estado", idEstado);
			call.execute();
		}
	}
}
