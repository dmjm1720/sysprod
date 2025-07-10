package com.dmjm.model;

import java.util.Date;

public class FolioPreparacionCocedores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioPrep;
	private Date fecha;
	private int folioCocedor;
	private String observaciones;

	public FolioPreparacionCocedores() {
	}

	public FolioPreparacionCocedores(int idFolioPrep) {
		this.idFolioPrep = idFolioPrep;
	}

	public FolioPreparacionCocedores(int idFolioPrep, Date fecha, int folioCocedor, String observaciones) {
		this.idFolioPrep = idFolioPrep;
		this.fecha = fecha;
		this.folioCocedor = folioCocedor;
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

	public int getFolioCocedor() {
		return folioCocedor;
	}

	public void setFolioCocedor(int folioCocedor) {
		this.folioCocedor = folioCocedor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
