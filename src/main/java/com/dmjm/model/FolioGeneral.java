package com.dmjm.model;

public class FolioGeneral implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioGeneral;
	private Integer folio;
	private String tipo;

	public FolioGeneral() {
	}

	public FolioGeneral(Integer folio, String tipo) {
		this.folio = folio;
		this.tipo = tipo;
	}

	public int getIdFolioGeneral() {
		return idFolioGeneral;
	}

	public void setIdFolioGeneral(int idFolioGeneral) {
		this.idFolioGeneral = idFolioGeneral;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
