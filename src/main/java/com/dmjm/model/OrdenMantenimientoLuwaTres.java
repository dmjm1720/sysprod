package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoLuwaTres implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoLuwaTres;
	private FolioPreparacionLuwaTres folioPreparacionLuwaTres;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoLuwaTres() {
	}

	public OrdenMantenimientoLuwaTres(int idOrdenMantoLuwaTres) {
		this.idOrdenMantoLuwaTres = idOrdenMantoLuwaTres;
	}

	public OrdenMantenimientoLuwaTres(int idOrdenMantoLuwaTres, FolioPreparacionLuwaTres folioPreparacionLuwaTres,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoLuwaTres = idOrdenMantoLuwaTres;
		this.folioPreparacionLuwaTres = folioPreparacionLuwaTres;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoLuwaTres() {
		return this.idOrdenMantoLuwaTres;
	}

	public void setIdOrdenMantoLuwaTres(int idOrdenMantoLuwaTres) {
		this.idOrdenMantoLuwaTres = idOrdenMantoLuwaTres;
	}

	public FolioPreparacionLuwaTres getFolioPreparacionLuwaTres() {
		return this.folioPreparacionLuwaTres;
	}

	public void setFolioPreparacionLuwaTres(FolioPreparacionLuwaTres folioPreparacionLuwaTres) {
		this.folioPreparacionLuwaTres = folioPreparacionLuwaTres;
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
