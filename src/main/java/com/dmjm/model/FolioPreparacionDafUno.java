package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionDafUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioDafUno;
	private String observaciones;

	public FolioPreparacionDafUno() {
	}

	public FolioPreparacionDafUno(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionDafUno(int idFolioPrep, Date fecha, Integer folioDafUno, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioDafUno = folioDafUno;
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

	public Integer getFolioDafUno() {
		return this.folioDafUno;
	}

	public void setFolioDafUno(Integer folioDafUno) {
		this.folioDafUno = folioDafUno;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
