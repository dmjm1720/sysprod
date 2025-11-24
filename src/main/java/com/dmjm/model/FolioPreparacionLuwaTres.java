package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionLuwaTres implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioLuwaTres;
	private String observaciones;

	public FolioPreparacionLuwaTres() {
	}

	public FolioPreparacionLuwaTres(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionLuwaTres(int idFolioPrep, Date fecha, Integer folioLuwaTres, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioLuwaTres = folioLuwaTres;
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

	public Integer getFolioLuwaTres() {
		return this.folioLuwaTres;
	}

	public void setFolioLuwaTres(Integer folioLuwaTres) {
		this.folioLuwaTres = folioLuwaTres;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
