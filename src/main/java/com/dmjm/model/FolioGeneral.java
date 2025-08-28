package com.dmjm.model;

public class FolioGeneral implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioGeneral;
	private Integer folioMolienda;

	public FolioGeneral() {
	}

	public FolioGeneral(int idFolioGeneral) {
		this.idFolioGeneral = idFolioGeneral;
	}

	public FolioGeneral(int idFolioGeneral, Integer folioMolienda) {
		this.idFolioGeneral = idFolioGeneral;
		this.folioMolienda = folioMolienda;
	}

	public int getIdFolioGeneral() {
		return this.idFolioGeneral;
	}

	public void setIdFolioGeneral(int idFolioGeneral) {
		this.idFolioGeneral = idFolioGeneral;
	}

	public Integer getFolioMolienda() {
		return this.folioMolienda;
	}

	public void setFolioMolienda(Integer folioMolienda) {
		this.folioMolienda = folioMolienda;
	}

}
