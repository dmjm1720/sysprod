package com.dmjm.model;

import java.util.Date;
public class OrdenMantenimientoSecadorB  implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
     private FolioPreparacionSecadorB folioPreparacionSecadorB;
     private Integer noOrden;
     private Date horaInicio;
     private Date horaFin;
     private String descripcion;

    public OrdenMantenimientoSecadorB() {
    }

	
    public OrdenMantenimientoSecadorB(int idOrdenManto) {
        this.idOrdenManto = idOrdenManto;
    }
    public OrdenMantenimientoSecadorB(int idOrdenManto, FolioPreparacionSecadorB folioPreparacionSecadorB, Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
       this.idOrdenManto = idOrdenManto;
       this.folioPreparacionSecadorB = folioPreparacionSecadorB;
       this.noOrden = noOrden;
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.descripcion = descripcion;
    }
   
    public int getIdOrdenManto() {
        return this.idOrdenManto;
    }
    
    public void setIdOrdenManto(int idOrdenManto) {
        this.idOrdenManto = idOrdenManto;
    }
    public FolioPreparacionSecadorB getFolioPreparacionSecadorB() {
        return this.folioPreparacionSecadorB;
    }
    
    public void setFolioPreparacionSecadorB(FolioPreparacionSecadorB folioPreparacionSecadorB) {
        this.folioPreparacionSecadorB = folioPreparacionSecadorB;
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


