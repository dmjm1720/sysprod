package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionSecadorA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioSecadorA;
	private String observaciones;

	public FolioPreparacionSecadorA() {
	}

	public FolioPreparacionSecadorA(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionSecadorA(int idFolioPrep, Date fecha, Integer folioSecadorA, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioSecadorA = folioSecadorA;
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

	public Integer getFolioSecadorA() {
		return this.folioSecadorA;
	}

	public void setFolioSecadorA(Integer folioSecadorA) {
		this.folioSecadorA = folioSecadorA;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
