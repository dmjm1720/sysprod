package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class FolioPreparacionVotatorB implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioVotatorB;
	private String observaciones;

	public FolioPreparacionVotatorB() {
	}

	public FolioPreparacionVotatorB(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionVotatorB(int idFolioPrep, Date fecha, Integer folioVotatorB, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioVotatorB = folioVotatorB;
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

	public Integer getFolioVotatorB() {
		return this.folioVotatorB;
	}

	public void setFolioVotatorB(Integer folioVotatorB) {
		this.folioVotatorB = folioVotatorB;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
