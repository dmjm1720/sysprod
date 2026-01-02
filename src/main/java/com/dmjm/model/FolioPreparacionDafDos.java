package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionDafDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioDafDos;
	private String observaciones;

	public FolioPreparacionDafDos() {
	}

	public FolioPreparacionDafDos(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionDafDos(int idFolioPrep, Date fecha, Integer folioDafDos, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioDafDos = folioDafDos;
		this.observaciones = observaciones;

	}

	public int getIdFolioPrep() {
		return this.idFolioPrep;
	}

	public void setIdFolioPrep(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFolioDafDos() {
		return this.folioDafDos;
	}

	public void setFolioDafDos(Integer folioDafDos) {
		this.folioDafDos = folioDafDos;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
