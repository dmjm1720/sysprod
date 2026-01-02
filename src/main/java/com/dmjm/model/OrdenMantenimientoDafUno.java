package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoDafUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoDafUno;
	private FolioPreparacionDafUno folioPreparacionDafUno;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoDafUno() {
	}

	public OrdenMantenimientoDafUno(int idOrdenMantoDafUno) {
		this.idOrdenMantoDafUno = idOrdenMantoDafUno;
	}

	public OrdenMantenimientoDafUno(int idOrdenMantoDafUno, FolioPreparacionDafUno folioPreparacionDafUno,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoDafUno = idOrdenMantoDafUno;
		this.folioPreparacionDafUno = folioPreparacionDafUno;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoDafUno() {
		return this.idOrdenMantoDafUno;
	}

	public void setIdOrdenMantoDafUno(int idOrdenMantoDafUno) {
		this.idOrdenMantoDafUno = idOrdenMantoDafUno;
	}

	public FolioPreparacionDafUno getFolioPreparacionDafUno() {
		return this.folioPreparacionDafUno;
	}

	public void setFolioPreparacionDafUno(FolioPreparacionDafUno folioPreparacionDafUno) {
		this.folioPreparacionDafUno = folioPreparacionDafUno;
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
