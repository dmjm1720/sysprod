package com.dmjm.model;
// Generated 30-sep-2025 19:41:55 by Hibernate Tools 4.3.1


import java.util.Date;


public class OrdenMantenimientoMolienda  implements java.io.Serializable {


     private static final long serialVersionUID = 1L;
	private int idOrdenMantoMolienda;
     private FolioPreparacionMolienda folioPreparacionMolienda;
     private Integer noOrden;
     private Date horaInicio;
     private Date horaFin;
     private String descripcion;

    public OrdenMantenimientoMolienda() {
    }

	
    public OrdenMantenimientoMolienda(int idOrdenMantoMolienda) {
        this.idOrdenMantoMolienda = idOrdenMantoMolienda;
    }
    public OrdenMantenimientoMolienda(int idOrdenMantoMolienda, FolioPreparacionMolienda folioPreparacionMolienda, Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
       this.idOrdenMantoMolienda = idOrdenMantoMolienda;
       this.folioPreparacionMolienda = folioPreparacionMolienda;
       this.noOrden = noOrden;
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.descripcion = descripcion;
    }
   
    public int getIdOrdenMantoMolienda() {
        return this.idOrdenMantoMolienda;
    }
    
    public void setIdOrdenMantoMolienda(int idOrdenMantoMolienda) {
        this.idOrdenMantoMolienda = idOrdenMantoMolienda;
    }
    public FolioPreparacionMolienda getFolioPreparacionMolienda() {
        return this.folioPreparacionMolienda;
    }
    
    public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
        this.folioPreparacionMolienda = folioPreparacionMolienda;
    }
    public Integer getNoOrden() {
        return this.noOrden;
    }
    
    public void setNoOrden(Integer noOrden) {
        this.noOrden = noOrden;
    }
    public Date getHoraInicio() {
        return this.horaInicio;
    }
    
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Date getHoraFin() {
        return this.horaFin;
    }
    
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


