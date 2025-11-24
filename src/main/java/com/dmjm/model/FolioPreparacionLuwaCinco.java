package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionLuwaCinco implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioLuwaCinco;
	private String observaciones;

	public FolioPreparacionLuwaCinco() {
	}

	public FolioPreparacionLuwaCinco(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionLuwaCinco(int idFolioPrep, Date fecha, Integer folioLuwaCinco, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioLuwaCinco = folioLuwaCinco;
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

	public Integer getFolioLuwaCinco() {
		return this.folioLuwaCinco;
	}

	public void setFolioLuwaCinco(Integer folioLuwaCinco) {
		this.folioLuwaCinco = folioLuwaCinco;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
