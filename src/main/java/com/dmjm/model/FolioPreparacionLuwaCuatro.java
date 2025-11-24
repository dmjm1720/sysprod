package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionLuwaCuatro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioLuwaCuatro;
	private String observaciones;

	public FolioPreparacionLuwaCuatro() {
	}

	public FolioPreparacionLuwaCuatro(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionLuwaCuatro(int idFolioPrep, Date fecha, Integer folioLuwaCuatro, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioLuwaCuatro = folioLuwaCuatro;
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

	public Integer getFolioLuwaCuatro() {
		return this.folioLuwaCuatro;
	}

	public void setFolioLuwaCuatro(Integer folioLuwaCuatro) {
		this.folioLuwaCuatro = folioLuwaCuatro;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
