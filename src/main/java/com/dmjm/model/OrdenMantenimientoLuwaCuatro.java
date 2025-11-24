package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoLuwaCuatro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoLuwaCuatro;
	private FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoLuwaCuatro() {
	}

	public OrdenMantenimientoLuwaCuatro(int idOrdenMantoLuwaCuatro) {
		this.idOrdenMantoLuwaCuatro = idOrdenMantoLuwaCuatro;
	}

	public OrdenMantenimientoLuwaCuatro(int idOrdenMantoLuwaCuatro,
			FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro, Integer noOrden, Date horaInicio, Date horaFin,
			String descripcion) {
		this.idOrdenMantoLuwaCuatro = idOrdenMantoLuwaCuatro;
		this.folioPreparacionLuwaCuatro = folioPreparacionLuwaCuatro;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoLuwaCuatro() {
		return this.idOrdenMantoLuwaCuatro;
	}

	public void setIdOrdenMantoLuwaCuatro(int idOrdenMantoLuwaCuatro) {
		this.idOrdenMantoLuwaCuatro = idOrdenMantoLuwaCuatro;
	}

	public FolioPreparacionLuwaCuatro getFolioPreparacionLuwaCuatro() {
		return this.folioPreparacionLuwaCuatro;
	}

	public void setFolioPreparacionLuwaCuatro(FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro) {
		this.folioPreparacionLuwaCuatro = folioPreparacionLuwaCuatro;
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
