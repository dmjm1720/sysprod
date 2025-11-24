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
	private Integer folioLuwaUno;
	private Integer folioLuwaDos;
	private Integer folioLuwaTres;
	private Integer folioLuwaCuatro;
	private Integer folioLuwaCinco;

	public FolioProcesos() {
	}

	public FolioProcesos(int idFolioProcesos) {
		this.idFolioProcesos = idFolioProcesos;
	}

	public FolioProcesos(int idFolioProcesos, Integer year, Integer folioEstPa, Integer folioEstPb,
			Integer folioUltraUno, Integer folioUltraDos, Integer folioVotatorA, Integer folioVotatorB,
			Integer folioLuwaUno, Integer folioLuwaDos, Integer folioLuwaTres, Integer folioLuwaCuatro,
			Integer folioLuwaCinco) {
		this.idFolioProcesos = idFolioProcesos;
		this.year = year;
		this.folioEstPa = folioEstPa;
		this.folioEstPb = folioEstPb;
		this.folioUltraUno = folioUltraUno;
		this.folioUltraDos = folioUltraDos;
		this.folioVotatorA = folioVotatorA;
		this.folioVotatorB = folioVotatorB;
		this.folioLuwaUno = folioLuwaUno;
		this.folioLuwaDos = folioLuwaDos;
		this.folioLuwaTres = folioLuwaTres;
		this.folioLuwaCuatro = folioLuwaCuatro;
		this.folioLuwaCinco = folioLuwaCinco;
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

	public Integer getFolioLuwaUno() {
		return folioLuwaUno;
	}

	public void setFolioLuwaUno(Integer folioLuwaUno) {
		this.folioLuwaUno = folioLuwaUno;
	}

	public Integer getFolioLuwaDos() {
		return folioLuwaDos;
	}

	public void setFolioLuwaDos(Integer folioLuwaDos) {
		this.folioLuwaDos = folioLuwaDos;
	}

	public Integer getFolioLuwaTres() {
		return folioLuwaTres;
	}

	public void setFolioLuwaTres(Integer folioLuwaTres) {
		this.folioLuwaTres = folioLuwaTres;
	}

	public Integer getFolioLuwaCuatro() {
		return folioLuwaCuatro;
	}

	public void setFolioLuwaCuatro(Integer folioLuwaCuatro) {
		this.folioLuwaCuatro = folioLuwaCuatro;
	}

	public Integer getFolioLuwaCinco() {
		return folioLuwaCinco;
	}

	public void setFolioLuwaCinco(Integer folioLuwaCinco) {
		this.folioLuwaCinco = folioLuwaCinco;
	}

}
