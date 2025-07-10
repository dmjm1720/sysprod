package com.dmjm.model;

import java.util.Date;

public class FoliosPreparacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolio;
	private int folio;
	private Date fecha;

	public FoliosPreparacion() {
	}

	public FoliosPreparacion(int idFolio) {
		this.idFolio = idFolio;
	}

	public FoliosPreparacion(int idFolio, int folio, Date fecha) {
		this.idFolio = idFolio;
		this.folio = folio;
		this.fecha = fecha;
	}

	public int getIdFolio() {
		return this.idFolio;
	}

	public void setIdFolio(int idFolio) {
		this.idFolio = idFolio;
	}

	public int getFolio() {
		return this.folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
