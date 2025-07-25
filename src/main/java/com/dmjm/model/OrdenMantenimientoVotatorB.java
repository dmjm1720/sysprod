package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class OrdenMantenimientoVotatorB implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoVotatorB;
	private FolioPreparacionVotatorB folioPreparacionVotatorB;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoVotatorB() {
	}

	public OrdenMantenimientoVotatorB(int idOrdenMantoVotatorB) {
		this.idOrdenMantoVotatorB = idOrdenMantoVotatorB;
	}

	public OrdenMantenimientoVotatorB(int idOrdenMantoVotatorB, FolioPreparacionVotatorB folioPreparacionVotatorB,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoVotatorB = idOrdenMantoVotatorB;
		this.folioPreparacionVotatorB = folioPreparacionVotatorB;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoVotatorB() {
		return this.idOrdenMantoVotatorB;
	}

	public void setIdOrdenMantoVotatorB(int idOrdenMantoVotatorB) {
		this.idOrdenMantoVotatorB = idOrdenMantoVotatorB;
	}

	public FolioPreparacionVotatorB getFolioPreparacionVotatorB() {
		return this.folioPreparacionVotatorB;
	}

	public void setFolioPreparacionVotatorB(FolioPreparacionVotatorB folioPreparacionVotatorB) {
		this.folioPreparacionVotatorB = folioPreparacionVotatorB;
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
