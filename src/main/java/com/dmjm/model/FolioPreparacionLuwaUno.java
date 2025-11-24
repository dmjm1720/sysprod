package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionLuwaUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioLuwaUno;
	private String observaciones;

	public FolioPreparacionLuwaUno() {
	}

	public FolioPreparacionLuwaUno(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionLuwaUno(int idFolioPrep, Date fecha, Integer folioLuwaUno, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioLuwaUno = folioLuwaUno;
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

	public Integer getFolioLuwaUno() {
		return this.folioLuwaUno;
	}

	public void setFolioLuwaUno(Integer folioLuwaUno) {
		this.folioLuwaUno = folioLuwaUno;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
