package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionUltraUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioUltraUno;
	private String observaciones;

	public FolioPreparacionUltraUno() {
	}

	public FolioPreparacionUltraUno(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionUltraUno(int idFolioPrep, Date fecha, Integer folioUltraUno, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioUltraUno = folioUltraUno;
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

	public Integer getFolioUltraUno() {
		return this.folioUltraUno;
	}

	public void setFolioUltraUno(Integer folioUltraUno) {
		this.folioUltraUno = folioUltraUno;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
