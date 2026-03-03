package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoSecadorA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
	private FolioPreparacionSecadorA folioPreparacionSecadorA;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoSecadorA() {
	}

	public OrdenMantenimientoSecadorA(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public OrdenMantenimientoSecadorA(int idOrdenManto, FolioPreparacionSecadorA folioPreparacionSecadorA,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenManto = idOrdenManto;
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
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

	public FolioPreparacionSecadorA getFolioPreparacionSecadorA() {
		return this.folioPreparacionSecadorA;
	}

	public void setFolioPreparacionSecadorA(FolioPreparacionSecadorA folioPreparacionSecadorA) {
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
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
