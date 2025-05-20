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
	private Integer noCocedor;
	private String operacion;

	public Purgas() {
	}

	public Purgas(int idPurgas) {
		this.idPurgas = idPurgas;
	}

	public Purgas(int idPurgas, FolioPreparacionCocedores folioPreparacionCocedores, Date hora, Integer cm,
			Integer concentrado, Integer ph, Integer noCocedor, String operacion) {
		this.idPurgas = idPurgas;
		this.folioPreparacionCocedores = folioPreparacionCocedores;
		this.hora = hora;
		this.cm = cm;
		this.concentrado = concentrado;
		this.ph = ph;
		this.noCocedor = noCocedor;
		this.operacion = operacion;
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

	public Integer getNoCocedor() {
		return noCocedor;
	}

	public void setNoCocedor(Integer noCocedor) {
		this.noCocedor = noCocedor;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
