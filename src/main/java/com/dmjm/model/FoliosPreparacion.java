package com.dmjm.model;

public class FoliosPreparacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolio;
	private int folio;

	public FoliosPreparacion() {
	}

	public FoliosPreparacion(int idFolio) {
		this.idFolio = idFolio;
	}

	public FoliosPreparacion(int idFolio, int folio) {
		this.idFolio = idFolio;
		this.folio = folio;
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

}
