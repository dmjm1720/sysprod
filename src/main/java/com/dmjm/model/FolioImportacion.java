package com.dmjm.model;

import java.io.Serializable;

public class FolioImportacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolio;
	private Long folio;

	public FolioImportacion() {
	}

	public FolioImportacion(int idFolio) {
		this.idFolio = idFolio;
	}

	public FolioImportacion(int idFolio, Long folio) {
		this.idFolio = idFolio;
		this.folio = folio;
	}

	public int getIdFolio() {
		return this.idFolio;
	}

	public void setIdFolio(int idFolio) {
		this.idFolio = idFolio;
	}

	public Long getFolio() {
		return this.folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

}
