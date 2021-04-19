package com.feriantes.portafolio.to;

public class ProcesoTO {

    private int idProceso;
    private int estadoProceso;
    private String nombreProceso;
    private String fechaInicio;
    private String fechaTermino;
    private String funcion;


    public int getIdProceso() {
        return idProceso;
    }
    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }
    public int getEstadoProceso() {
        return estadoProceso;
    }
    public void setEstadoProceso(int estadoProceso) {
        this.estadoProceso = estadoProceso;
    }
    public String getNombreProceso() {
        return nombreProceso;
    }
    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaTermino() {
        return fechaTermino;
    }
    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
    public String getFuncion() {
        return funcion;
    }
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }


   
}