package com.feriantes.portafolio.to;


public class DetalleProcesoTO {

    private int idDetalleProceso;
    private int idProceso;
    private int cantidad;
    private int tipoVenta;
    private int idProducto;
    private String funcion;
    private String nombreProducto;
    private String glosaTipoVenta;

    public String getGlosaTipoVenta() {
        return glosaTipoVenta;
    }
    public void setGlosaTipoVenta(String glosaTipoVenta) {
        this.glosaTipoVenta = glosaTipoVenta;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public int getIdDetalleProceso() {
        return idDetalleProceso;
    }
    public void setIdDetalleProceso(int idDetalleProceso) {
        this.idDetalleProceso = idDetalleProceso;
    }
    public String getFuncion() {
        return funcion;
    }
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
    public int getIdProceso() {
        return idProceso;
    }
    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getTipoVenta() {
        return tipoVenta;
    }
    public void setTipoVenta(int tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    @Override
    public String toString() {
        return "DetalleProcesoTO [cantidad=" + cantidad + ", funcion=" + funcion + ", idDetalleProceso="
                + idDetalleProceso + ", idProceso=" + idProceso + ", idProducto=" + idProducto + ", tipoVenta="
                + tipoVenta + "]";
    }
    

    
}