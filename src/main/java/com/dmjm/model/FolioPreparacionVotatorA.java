package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class FolioPreparacionVotatorA implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioVotatorA;
	private String observaciones;

	public FolioPreparacionVotatorA() {
	}

	public FolioPreparacionVotatorA(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionVotatorA(int idFolioPrep, Date fecha, Integer folioVotatorA, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioVotatorA = folioVotatorA;
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

	public Integer getFolioVotatorA() {
		return this.folioVotatorA;
	}

	public void setFolioVotatorA(Integer folioVotatorA) {
		this.folioVotatorA = folioVotatorA;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
