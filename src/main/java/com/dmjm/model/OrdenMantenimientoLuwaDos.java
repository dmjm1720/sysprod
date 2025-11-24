package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoLuwaDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoLuwaDos;
	private FolioPreparacionLuwaDos folioPreparacionLuwaDos;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoLuwaDos() {
	}

	public OrdenMantenimientoLuwaDos(int idOrdenMantoLuwaDos) {
		this.idOrdenMantoLuwaDos = idOrdenMantoLuwaDos;
	}

	public OrdenMantenimientoLuwaDos(int idOrdenMantoLuwaDos, FolioPreparacionLuwaDos folioPreparacionLuwaDos,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoLuwaDos = idOrdenMantoLuwaDos;
		this.folioPreparacionLuwaDos = folioPreparacionLuwaDos;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoLuwaDos() {
		return this.idOrdenMantoLuwaDos;
	}

	public void setIdOrdenMantoLuwaDos(int idOrdenMantoLuwaDos) {
		this.idOrdenMantoLuwaDos = idOrdenMantoLuwaDos;
	}

	public FolioPreparacionLuwaDos getFolioPreparacionLuwaDos() {
		return this.folioPreparacionLuwaDos;
	}

	public void setFolioPreparacionLuwaDos(FolioPreparacionLuwaDos folioPreparacionLuwaDos) {
		this.folioPreparacionLuwaDos = folioPreparacionLuwaDos;
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
