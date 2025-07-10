package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoUltraDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoUltraDos;
	private FolioPreparacionUltraDos folioPreparacionUltraDos;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoUltraDos() {
	}

	public OrdenMantenimientoUltraDos(int idOrdenMantoUltraDos) {
		this.idOrdenMantoUltraDos = idOrdenMantoUltraDos;
	}

	public OrdenMantenimientoUltraDos(int idOrdenMantoUltraDos, FolioPreparacionUltraDos folioPreparacionUltraDos,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoUltraDos = idOrdenMantoUltraDos;
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoUltraDos() {
		return this.idOrdenMantoUltraDos;
	}

	public void setIdOrdenMantoUltraDos(int idOrdenMantoUltraDos) {
		this.idOrdenMantoUltraDos = idOrdenMantoUltraDos;
	}

	public FolioPreparacionUltraDos getFolioPreparacionUltraDos() {
		return this.folioPreparacionUltraDos;
	}

	public void setFolioPreparacionUltraDos(FolioPreparacionUltraDos folioPreparacionUltraDos) {
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
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
