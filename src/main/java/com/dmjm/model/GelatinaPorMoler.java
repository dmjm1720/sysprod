package com.dmjm.model;

import java.util.Date;

public class GelatinaPorMoler implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idGelatina;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private Integer folio;
	private Date fecha;
	private String operacion;
	private Integer kgTolvaA;
	private Integer kgTolvaB;
	private Integer kgTolvaC;
	private Integer kgTotal;

	public GelatinaPorMoler() {
	}

	public GelatinaPorMoler(int idGelatina) {
		this.idGelatina = idGelatina;
	}

	public GelatinaPorMoler(int idGelatina, FolioPreparacionMolienda folioPreparacionMolienda, Integer folio,
			Date fecha, String operacion, Integer kgTolvaA, Integer kgTolvaB, Integer kgTolvaC, Integer kgTotal) {
		this.idGelatina = idGelatina;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.folio = folio;
		this.fecha = fecha;
		this.operacion = operacion;
		this.kgTolvaA = kgTolvaA;
		this.kgTolvaB = kgTolvaB;
		this.kgTolvaC = kgTolvaC;
		this.kgTotal = kgTotal;
	}

	public int getIdGelatina() {
		return this.idGelatina;
	}

	public void setIdGelatina(int idGelatina) {
		this.idGelatina = idGelatina;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return this.folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public Integer getFolio() {
		return this.folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Integer getKgTolvaA() {
		return this.kgTolvaA;
	}

	public void setKgTolvaA(Integer kgTolvaA) {
		this.kgTolvaA = kgTolvaA;
	}

	public Integer getKgTolvaB() {
		return this.kgTolvaB;
	}

	public void setKgTolvaB(Integer kgTolvaB) {
		this.kgTolvaB = kgTolvaB;
	}

	public Integer getKgTolvaC() {
		return this.kgTolvaC;
	}

	public void setKgTolvaC(Integer kgTolvaC) {
		this.kgTolvaC = kgTolvaC;
	}

	public Integer getKgTotal() {
		return this.kgTotal;
	}

	public void setKgTotal(Integer kgTotal) {
		this.kgTotal = kgTotal;
	}

}
