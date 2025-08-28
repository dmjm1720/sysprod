package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionMolienda implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioMolienda;
	private String observaciones;

	public FolioPreparacionMolienda() {
	}

	public FolioPreparacionMolienda(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionMolienda(int idFolioPrep, Date fecha, Integer folioMolienda, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioMolienda = folioMolienda;
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

	public Integer getFolioMolienda() {
		return this.folioMolienda;
	}

	public void setFolioMolienda(Integer folioMolienda) {
		this.folioMolienda = folioMolienda;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
