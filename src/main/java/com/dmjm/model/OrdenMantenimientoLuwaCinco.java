package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoLuwaCinco implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoLuwaCinco;
	private FolioPreparacionLuwaCinco folioPreparacionLuwaCinco;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoLuwaCinco() {
	}

	public OrdenMantenimientoLuwaCinco(int idOrdenMantoLuwaCinco) {
		this.idOrdenMantoLuwaCinco = idOrdenMantoLuwaCinco;
	}

	public OrdenMantenimientoLuwaCinco(int idOrdenMantoLuwaCinco, FolioPreparacionLuwaCinco folioPreparacionLuwaCinco,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoLuwaCinco = idOrdenMantoLuwaCinco;
		this.folioPreparacionLuwaCinco = folioPreparacionLuwaCinco;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoLuwaCinco() {
		return this.idOrdenMantoLuwaCinco;
	}

	public void setIdOrdenMantoLuwaCinco(int idOrdenMantoLuwaCinco) {
		this.idOrdenMantoLuwaCinco = idOrdenMantoLuwaCinco;
	}

	public FolioPreparacionLuwaCinco getFolioPreparacionLuwaCinco() {
		return this.folioPreparacionLuwaCinco;
	}

	public void setFolioPreparacionLuwaCinco(FolioPreparacionLuwaCinco folioPreparacionLuwaCinco) {
		this.folioPreparacionLuwaCinco = folioPreparacionLuwaCinco;
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
