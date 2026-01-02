package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoDafDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoDafDos;
	private FolioPreparacionDafDos folioPreparacionDafDos;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoDafDos() {
	}

	public OrdenMantenimientoDafDos(int idOrdenMantoDafDos) {
		this.idOrdenMantoDafDos = idOrdenMantoDafDos;
	}

	public OrdenMantenimientoDafDos(int idOrdenMantoDafDos, FolioPreparacionDafDos folioPreparacionDafDos,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoDafDos = idOrdenMantoDafDos;
		this.folioPreparacionDafDos = folioPreparacionDafDos;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoDafDos() {
		return this.idOrdenMantoDafDos;
	}

	public void setIdOrdenMantoDafDos(int idOrdenMantoDafDos) {
		this.idOrdenMantoDafDos = idOrdenMantoDafDos;
	}

	public FolioPreparacionDafDos getFolioPreparacionDafDos() {
		return this.folioPreparacionDafDos;
	}

	public void setFolioPreparacionDafDos(FolioPreparacionDafDos folioPreparacionDafDos) {
		this.folioPreparacionDafDos = folioPreparacionDafDos;
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
