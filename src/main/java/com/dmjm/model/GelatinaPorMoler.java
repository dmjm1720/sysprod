package com.dmjm.model;

import java.util.Date;

public class GelatinaPorMoler implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idGelatina;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private Integer folio;
	private Date fecha;
	private String operacionA;
	private Integer kgTolvaA;
	private Integer kgTolvaB;
	private Integer kgTolvaC;
	private Integer kgTotal;
	private String operacionB;
	private String operacionC;
	private String tolva;
	private String operacion;

	public GelatinaPorMoler() {
	}

	public GelatinaPorMoler(int idGelatina) {
		this.idGelatina = idGelatina;
	}

	

	public GelatinaPorMoler(int idGelatina, FolioPreparacionMolienda folioPreparacionMolienda, Integer folio,
			Date fecha, String operacionA, Integer kgTolvaA, Integer kgTolvaB, Integer kgTolvaC, Integer kgTotal,
			String operacionB, String operacionC, String tolva, String operacion) {

		this.idGelatina = idGelatina;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.folio = folio;
		this.fecha = fecha;
		this.operacionA = operacionA;
		this.kgTolvaA = kgTolvaA;
		this.kgTolvaB = kgTolvaB;
		this.kgTolvaC = kgTolvaC;
		this.kgTotal = kgTotal;
		this.operacionB = operacionB;
		this.operacionC = operacionC;
		this.tolva = tolva;
		this.operacion = operacion;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public int getIdGelatina() {
		return idGelatina;
	}

	public void setIdGelatina(int idGelatina) {
		this.idGelatina = idGelatina;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacionA() {
		return operacionA;
	}

	public void setOperacionA(String operacionA) {
		this.operacionA = operacionA;
	}

	public Integer getKgTolvaA() {
		return kgTolvaA;
	}

	public void setKgTolvaA(Integer kgTolvaA) {
		this.kgTolvaA = kgTolvaA;
	}

	public Integer getKgTolvaB() {
		return kgTolvaB;
	}

	public void setKgTolvaB(Integer kgTolvaB) {
		this.kgTolvaB = kgTolvaB;
	}

	public Integer getKgTolvaC() {
		return kgTolvaC;
	}

	public void setKgTolvaC(Integer kgTolvaC) {
		this.kgTolvaC = kgTolvaC;
	}

	public Integer getKgTotal() {
		return kgTotal;
	}

	public void setKgTotal(Integer kgTotal) {
		this.kgTotal = kgTotal;
	}

	public String getOperacionB() {
		return operacionB;
	}

	public void setOperacionB(String operacionB) {
		this.operacionB = operacionB;
	}

	public String getOperacionC() {
		return operacionC;
	}

	public void setOperacionC(String operacionC) {
		this.operacionC = operacionC;
	}

	public String getTolva() {
		return tolva;
	}

	public void setTolva(String tolva) {
		this.tolva = tolva;
	}
	

}
