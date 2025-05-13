package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionCocedores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;

	public FolioPreparacionCocedores() {
	}

	public FolioPreparacionCocedores(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionCocedores(int idFolioPrep, Date fecha) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;

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

}
