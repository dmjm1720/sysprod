package com.dmjm.model;

public class FolioProcesos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioProcesos;
	private Integer year;
	private Integer folioEstPa;
	private Integer folioEstPb;

	public FolioProcesos() {
	}

	public FolioProcesos(int idFolioProcesos) {
		this.idFolioProcesos = idFolioProcesos;
	}

	public FolioProcesos(int idFolioProcesos, Integer year, Integer folioEstPa, Integer folioEstPb) {
		this.idFolioProcesos = idFolioProcesos;
		this.year = year;
		this.folioEstPa = folioEstPa;
		this.folioEstPb = folioEstPb;
	}

	public int getIdFolioProcesos() {
		return this.idFolioProcesos;
	}

	public void setIdFolioProcesos(int idFolioProcesos) {
		this.idFolioProcesos = idFolioProcesos;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getFolioEstPa() {
		return this.folioEstPa;
	}

	public void setFolioEstPa(Integer folioEstPa) {
		this.folioEstPa = folioEstPa;
	}

	public Integer getFolioEstPb() {
		return this.folioEstPb;
	}

	public void setFolioEstPb(Integer folioEstPb) {
		this.folioEstPb = folioEstPb;
	}

}
