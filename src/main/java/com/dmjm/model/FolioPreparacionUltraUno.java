package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionUltraUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private Integer folioUltraUno;

	public FolioPreparacionUltraUno() {
	}

	public FolioPreparacionUltraUno(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionUltraUno(int idFolioPrep, Date fecha, Integer folioUltraUno) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioUltraUno = folioUltraUno;
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

	public Integer getFolioUltraUno() {
		return this.folioUltraUno;
	}

	public void setFolioUltraUno(Integer folioUltraUno) {
		this.folioUltraUno = folioUltraUno;
	}

}
