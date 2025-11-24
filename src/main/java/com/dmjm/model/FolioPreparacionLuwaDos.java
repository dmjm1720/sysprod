package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionLuwaDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioLuwaDos;
	private String observaciones;

	public FolioPreparacionLuwaDos() {
	}

	public FolioPreparacionLuwaDos(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionLuwaDos(int idFolioPrep, Date fecha, Integer folioLuwaDos, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioLuwaDos = folioLuwaDos;
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

	public Integer getFolioLuwaDos() {
		return this.folioLuwaDos;
	}

	public void setFolioLuwaDos(Integer folioLuwaDos) {
		this.folioLuwaDos = folioLuwaDos;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
