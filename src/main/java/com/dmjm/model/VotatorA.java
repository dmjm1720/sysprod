package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VotatorA implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idVotator;
	private FolioPreparacionVotatorA folioPreparacionVotatorA;
	private String hora;
	private String operacion;
	private BigDecimal concRefractomentro;
	private BigDecimal ph;
	private Integer redoxHumeda;
	private Integer temEntVotator;
	private Integer tempSalVotator;
	private BigDecimal receptorPsi;
	private BigDecimal succion;
	private BigDecimal descarga;
	private BigDecimal aceite;
	private Integer motor;
	private Integer bombaAlim;
	private Integer redoxSeco;
	private String estadoA;
	private String estadoR;
	private Integer folioVotator;
	private Date fecha;
	private Boolean estadoAR;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;

	public VotatorA() {
	}

	public VotatorA(int idVotator) {
		this.idVotator = idVotator;
	}

	public VotatorA(int idVotator, FolioPreparacionVotatorA folioPreparacionVotatorA, String hora, String operacion,
			BigDecimal concRefractomentro, BigDecimal ph, Integer redoxHumeda, Integer temEntVotator,
			Integer tempSalVotator, BigDecimal receptorPsi, BigDecimal succion, BigDecimal descarga, BigDecimal aceite,
			Integer motor, Integer bombaAlim, Integer redoxSeco, String estadoA, String estadoR, Integer folioVotator,
			Date fecha, Boolean estadoAR, Boolean estadoManto, Boolean estadoLimpieza) {
		this.idVotator = idVotator;
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
		this.hora = hora;
		this.operacion = operacion;
		this.concRefractomentro = concRefractomentro;
		this.ph = ph;
		this.redoxHumeda = redoxHumeda;
		this.temEntVotator = temEntVotator;
		this.tempSalVotator = tempSalVotator;
		this.receptorPsi = receptorPsi;
		this.succion = succion;
		this.descarga = descarga;
		this.aceite = aceite;
		this.motor = motor;
		this.bombaAlim = bombaAlim;
		this.redoxSeco = redoxSeco;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.folioVotator = folioVotator;
		this.fecha = fecha;
		this.estadoAR = estadoAR;
		this.estadoManto = estadoManto;
		this.estadoLimpieza = estadoLimpieza;
	}

	public int getIdVotator() {
		return this.idVotator;
	}

	public void setIdVotator(int idVotator) {
		this.idVotator = idVotator;
	}

	public FolioPreparacionVotatorA getFolioPreparacionVotatorA() {
		return this.folioPreparacionVotatorA;
	}

	public void setFolioPreparacionVotatorA(FolioPreparacionVotatorA folioPreparacionVotatorA) {
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getConcRefractomentro() {
		return this.concRefractomentro;
	}

	public void setConcRefractomentro(BigDecimal concRefractomentro) {
		this.concRefractomentro = concRefractomentro;
	}

	public BigDecimal getPh() {
		return this.ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public Integer getRedoxHumeda() {
		return this.redoxHumeda;
	}

	public void setRedoxHumeda(Integer redoxHumeda) {
		this.redoxHumeda = redoxHumeda;
	}

	public Integer getTemEntVotator() {
		return this.temEntVotator;
	}

	public void setTemEntVotator(Integer temEntVotator) {
		this.temEntVotator = temEntVotator;
	}

	public Integer getTempSalVotator() {
		return this.tempSalVotator;
	}

	public void setTempSalVotator(Integer tempSalVotator) {
		this.tempSalVotator = tempSalVotator;
	}

	public BigDecimal getReceptorPsi() {
		return this.receptorPsi;
	}

	public void setReceptorPsi(BigDecimal receptorPsi) {
		this.receptorPsi = receptorPsi;
	}

	public BigDecimal getSuccion() {
		return this.succion;
	}

	public void setSuccion(BigDecimal succion) {
		this.succion = succion;
	}

	public BigDecimal getDescarga() {
		return this.descarga;
	}

	public void setDescarga(BigDecimal descarga) {
		this.descarga = descarga;
	}

	public BigDecimal getAceite() {
		return this.aceite;
	}

	public void setAceite(BigDecimal aceite) {
		this.aceite = aceite;
	}

	public Integer getMotor() {
		return this.motor;
	}

	public void setMotor(Integer motor) {
		this.motor = motor;
	}

	public Integer getBombaAlim() {
		return this.bombaAlim;
	}

	public void setBombaAlim(Integer bombaAlim) {
		this.bombaAlim = bombaAlim;
	}

	public Integer getRedoxSeco() {
		return this.redoxSeco;
	}

	public void setRedoxSeco(Integer redoxSeco) {
		this.redoxSeco = redoxSeco;
	}

	public String getEstadoA() {
		return this.estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoR() {
		return this.estadoR;
	}

	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}

	public Integer getFolioVotator() {
		return this.folioVotator;
	}

	public void setFolioVotator(Integer folioVotator) {
		this.folioVotator = folioVotator;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstadoAR() {
		return this.estadoAR;
	}

	public void setEstadoAR(Boolean estadoAR) {
		this.estadoAR = estadoAR;
	}

	public Boolean getEstadoManto() {
		return this.estadoManto;
	}

	public void setEstadoManto(Boolean estadoManto) {
		this.estadoManto = estadoManto;
	}

	public Boolean getEstadoLimpieza() {
		return this.estadoLimpieza;
	}

	public void setEstadoLimpieza(Boolean estadoLimpieza) {
		this.estadoLimpieza = estadoLimpieza;
	}

}
