package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimiento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
	private FolioPreparacionCocedores folioPreparacionCocedores;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimiento() {
	}

	public OrdenMantenimiento(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public OrdenMantenimiento(int idOrdenManto, FolioPreparacionCocedores folioPreparacionCocedores, Integer noOrden,
			Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenManto = idOrdenManto;
		this.folioPreparacionCocedores = folioPreparacionCocedores;
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

	public FolioPreparacionCocedores getFolioPreparacionCocedores() {
		return this.folioPreparacionCocedores;
	}

	public void setFolioPreparacionCocedores(FolioPreparacionCocedores folioPreparacionCocedores) {
		this.folioPreparacionCocedores = folioPreparacionCocedores;
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
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
