package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class OrdenMantenimientoVotatorA implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoVotatorA;
	private FolioPreparacionVotatorA folioPreparacionVotatorA;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoVotatorA() {
	}

	public OrdenMantenimientoVotatorA(int idOrdenMantoVotatorA) {
		this.idOrdenMantoVotatorA = idOrdenMantoVotatorA;
	}

	public OrdenMantenimientoVotatorA(int idOrdenMantoVotatorA, FolioPreparacionVotatorA folioPreparacionVotatorA,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoVotatorA = idOrdenMantoVotatorA;
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoVotatorA() {
		return this.idOrdenMantoVotatorA;
	}

	public void setIdOrdenMantoVotatorA(int idOrdenMantoVotatorA) {
		this.idOrdenMantoVotatorA = idOrdenMantoVotatorA;
	}

	public FolioPreparacionVotatorA getFolioPreparacionVotatorA() {
		return this.folioPreparacionVotatorA;
	}

	public void setFolioPreparacionVotatorA(FolioPreparacionVotatorA folioPreparacionVotatorA) {
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
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
