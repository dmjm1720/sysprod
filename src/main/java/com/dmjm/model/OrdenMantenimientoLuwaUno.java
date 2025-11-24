package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoLuwaUno implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private int idOrdenMantoLuwaUno;
	private FolioPreparacionLuwaUno folioPreparacionLuwaUno;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoLuwaUno() {
	}

	public OrdenMantenimientoLuwaUno(int idOrdenMantoLuwaUno) {
		this.idOrdenMantoLuwaUno = idOrdenMantoLuwaUno;
	}

	public OrdenMantenimientoLuwaUno(int idOrdenMantoLuwaUno, FolioPreparacionLuwaUno folioPreparacionLuwaUno,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoLuwaUno = idOrdenMantoLuwaUno;
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoLuwaUno() {
		return this.idOrdenMantoLuwaUno;
	}

	public void setIdOrdenMantoLuwaUno(int idOrdenMantoLuwaUno) {
		this.idOrdenMantoLuwaUno = idOrdenMantoLuwaUno;
	}

	public FolioPreparacionLuwaUno getFolioPreparacionLuwaUno() {
		return this.folioPreparacionLuwaUno;
	}

	public void setFolioPreparacionLuwaUno(FolioPreparacionLuwaUno folioPreparacionLuwaUno) {
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
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
