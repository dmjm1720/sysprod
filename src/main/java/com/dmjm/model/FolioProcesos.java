package com.dmjm.model;

import java.io.Serializable;

public class FolioProcesos implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFolioProcesos;
	private Integer year;
	private Integer folioEstPa;
	private Integer folioEstPb;
	private Integer folioUltraUno;
	private Integer folioUltraDos;
	private Integer folioVotatorA;
	private Integer folioVotatorB;

	public FolioProcesos() {
	}

	public FolioProcesos(int idFolioProcesos) {
		this.idFolioProcesos = idFolioProcesos;
	}

	public FolioProcesos(int idFolioProcesos, Integer year, Integer folioEstPa, Integer folioEstPb,
			Integer folioUltraUno, Integer folioUltraDos, Integer folioVotatorA, Integer folioVotatorB) {
		this.idFolioProcesos = idFolioProcesos;
		this.year = year;
		this.folioEstPa = folioEstPa;
		this.folioEstPb = folioEstPb;
		this.folioUltraUno = folioUltraUno;
		this.folioUltraDos = folioUltraDos;
		this.folioVotatorA = folioVotatorA;
		this.folioVotatorB = folioVotatorB;
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

	public Integer getFolioUltraUno() {
		return folioUltraUno;
	}

	public void setFolioUltraUno(Integer folioUltraUno) {
		this.folioUltraUno = folioUltraUno;
	}

	public Integer getFolioUltraDos() {
		return folioUltraDos;
	}

	public void setFolioUltraDos(Integer folioUltraDos) {
		this.folioUltraDos = folioUltraDos;
	}

	public Integer getFolioVotatorA() {
		return folioVotatorA;
	}

	public void setFolioVotatorA(Integer folioVotatorA) {
		this.folioVotatorA = folioVotatorA;
	}

	public Integer getFolioVotatorB() {
		return folioVotatorB;
	}

	public void setFolioVotatorB(Integer folioVotatorB) {
		this.folioVotatorB = folioVotatorB;
	}

}
