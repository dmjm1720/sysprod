package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class FolioPreparacionEstA implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioEstA;
	private String observaciones;

	public FolioPreparacionEstA() {
	}

	public FolioPreparacionEstA(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionEstA(int idFolioPrep, Date fecha, Integer folioEstA, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioEstA = folioEstA;
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

	public Integer getFolioEstA() {
		return folioEstA;
	}

	public void setFolioEstA(Integer folioEstA) {
		this.folioEstA = folioEstA;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
