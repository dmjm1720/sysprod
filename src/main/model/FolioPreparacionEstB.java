package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionEstB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioEstB;

	public FolioPreparacionEstB() {
	}

	public FolioPreparacionEstB(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionEstB(int idFolioPrep, Date fecha, Integer folioEstB) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioEstB = folioEstB;

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

	public Integer getFolioEstB() {
		return folioEstB;
	}

	public void setFolioEstB(Integer folioEstB) {
		this.folioEstB = folioEstB;
	}

}
