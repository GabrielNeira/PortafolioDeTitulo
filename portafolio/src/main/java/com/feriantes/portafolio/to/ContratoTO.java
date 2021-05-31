package com.feriantes.portafolio.to;

public class ContratoTO {
	
	private int idContrato;
	private String codProductor;
	private int estadoContrato;
	private String fechaGeneracion;
	private String fechaVencimiento;
	private String funcion;
	private String glosaEstado;

	

	public String getGlosaEstado() {
		return glosaEstado;
	}

	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public String getCodProductor() {
		return codProductor;
	}

	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}

	public int getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(int estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public String getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

}