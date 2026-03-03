package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionSecadorB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioSecadorB;
	private String observaciones;

	public FolioPreparacionSecadorB() {
	}

	public FolioPreparacionSecadorB(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionSecadorB(int idFolioPrep, Date fecha, Integer folioSecadorB, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioSecadorB = folioSecadorB;
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

	public Integer getFolioSecadorB() {
		return this.folioSecadorB;
	}

	public void setFolioSecadorB(Integer folioSecadorB) {
		this.folioSecadorB = folioSecadorB;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
