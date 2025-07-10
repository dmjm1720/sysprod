package com.dmjm.model;

public class FolioCocedores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioCocedores;
	private Integer year;
	private Integer folio;

	public FolioCocedores() {
	}

	public FolioCocedores(int idFolioCocedores) {
		this.idFolioCocedores = idFolioCocedores;
	}

	public FolioCocedores(int idFolioCocedores, Integer year, Integer folio) {
		this.idFolioCocedores = idFolioCocedores;
		this.year = year;
		this.folio = folio;
	}

	public int getIdFolioCocedores() {
		return this.idFolioCocedores;
	}

	public void setIdFolioCocedores(int idFolioCocedores) {
		this.idFolioCocedores = idFolioCocedores;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getFolio() {
		return this.folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

}
