package com.dmjm.model;

import java.util.Date;

public class Purgas implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idPurgas;
	private FolioPreparacionCocedores folioPreparacionCocedores;
	private Date hora;
	private Integer cm;
	private Integer concentrado;
	private Integer ph;
	private Integer coc01;
	private Integer coc02;
	private Integer coc03;
	private Integer coc04;
	private Integer coc05;

	public Purgas() {
	}

	public Purgas(int idPurgas) {
		this.idPurgas = idPurgas;
	}

	public Purgas(int idPurgas, FolioPreparacionCocedores folioPreparacionCocedores, Date hora, Integer cm,
			Integer concentrado, Integer ph, Integer coc01, Integer coc02, Integer coc03, Integer coc04,
			Integer coc05) {
		this.idPurgas = idPurgas;
		this.folioPreparacionCocedores = folioPreparacionCocedores;
		this.hora = hora;
		this.cm = cm;
		this.concentrado = concentrado;
		this.ph = ph;
		this.coc01 = coc01;
		this.coc02 = coc02;
		this.coc03 = coc03;
		this.coc04 = coc04;
		this.coc05 = coc05;
	}

	public int getIdPurgas() {
		return this.idPurgas;
	}

	public void setIdPurgas(int idPurgas) {
		this.idPurgas = idPurgas;
	}

	public FolioPreparacionCocedores getFolioPreparacionCocedores() {
		return this.folioPreparacionCocedores;
	}

	public void setFolioPreparacionCocedores(FolioPreparacionCocedores folioPreparacionCocedores) {
		this.folioPreparacionCocedores = folioPreparacionCocedores;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Integer getCm() {
		return this.cm;
	}

	public void setCm(Integer cm) {
		this.cm = cm;
	}

	public Integer getConcentrado() {
		return this.concentrado;
	}

	public void setConcentrado(Integer concentrado) {
		this.concentrado = concentrado;
	}

	public Integer getPh() {
		return this.ph;
	}

	public void setPh(Integer ph) {
		this.ph = ph;
	}

	public Integer getCoc01() {
		return this.coc01;
	}

	public void setCoc01(Integer coc01) {
		this.coc01 = coc01;
	}

	public Integer getCoc02() {
		return this.coc02;
	}

	public void setCoc02(Integer coc02) {
		this.coc02 = coc02;
	}

	public Integer getCoc03() {
		return this.coc03;
	}

	public void setCoc03(Integer coc03) {
		this.coc03 = coc03;
	}

	public Integer getCoc04() {
		return this.coc04;
	}

	public void setCoc04(Integer coc04) {
		this.coc04 = coc04;
	}

	public Integer getCoc05() {
		return this.coc05;
	}

	public void setCoc05(Integer coc05) {
		this.coc05 = coc05;
	}

}
