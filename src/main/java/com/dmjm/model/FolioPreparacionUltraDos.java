package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionUltraDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioUltraDos;
	private String observaciones;

	public FolioPreparacionUltraDos() {
	}

	public FolioPreparacionUltraDos(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionUltraDos(int idFolioPrep, Date fecha, Integer folioUltraDos, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioUltraDos = folioUltraDos;
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

	public Integer getFolioUltraDos() {
		return this.folioUltraDos;
	}

	public void setFolioUltraDos(Integer folioUltraDos) {
		this.folioUltraDos = folioUltraDos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
