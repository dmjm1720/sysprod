package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Etapas implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idEtapa;
	private PreparacionPieles preparacionPieles;
	private String etapa;
	private Date diaInicio;
	private Date horaInicio;
	private Date diaFin;
	private Date horaFin;
	private BigDecimal phSol;
	private BigDecimal normalidad;
	private String operador;
	private String agua;
	private Boolean estado;
	private Integer alcalinidad;
	private Integer conductividad;
	private String lavadora;
	private String estadoEtapa;
	private String quimico1;
	private String cantidad1;
	private String lote1;
	private String quimico2;
	private String cantidad2;
	private String lote2;

	public Etapas() {
	}

	public Etapas(int idEtapa) {
		this.idEtapa = idEtapa;
	}

	public Etapas(int idEtapa, PreparacionPieles preparacionPieles, String etapa, Date diaInicio, Date horaInicio,
			Date diaFin, Date horaFin, BigDecimal phSol, BigDecimal normalidad, String operador, String agua,
			Boolean estado, Integer alcalinidad, Integer conductividad, String lavadora, String estadoEtapa,
			String quimico1, String cantidad1, String lote1, String quimico2, String cantidad2, String lote2) {
		super();
		this.idEtapa = idEtapa;
		this.preparacionPieles = preparacionPieles;
		this.etapa = etapa;
		this.diaInicio = diaInicio;
		this.horaInicio = horaInicio;
		this.diaFin = diaFin;
		this.horaFin = horaFin;
		this.phSol = phSol;
		this.normalidad = normalidad;
		this.operador = operador;
		this.agua = agua;
		this.estado = estado;
		this.alcalinidad = alcalinidad;
		this.conductividad = conductividad;
		this.lavadora = lavadora;
		this.estadoEtapa = estadoEtapa;
		this.quimico1 = quimico1;
		this.cantidad1 = cantidad1;
		this.lote1 = lote1;
		this.quimico2 = quimico2;
		this.cantidad2 = cantidad2;
		this.lote2 = lote2;
	}

	public int getIdEtapa() {
		return this.idEtapa;
	}

	public void setIdEtapa(int idEtapa) {
		this.idEtapa = idEtapa;
	}

	public PreparacionPieles getPreparacionPieles() {
		return this.preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public Date getDiaInicio() {
		return this.diaInicio;
	}

	public void setDiaInicio(Date diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getDiaFin() {
		return this.diaFin;
	}

	public void setDiaFin(Date diaFin) {
		this.diaFin = diaFin;
	}

	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getPhSol() {
		return this.phSol;
	}

	public void setPhSol(BigDecimal phSol) {
		this.phSol = phSol;
	}

	public BigDecimal getNormalidad() {
		return this.normalidad;
	}

	public void setNormalidad(BigDecimal normalidad) {
		this.normalidad = normalidad;
	}

	public String getOperador() {
		return this.operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getAgua() {
		return this.agua;
	}

	public void setAgua(String agua) {
		this.agua = agua;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getAlcalinidad() {
		return alcalinidad;
	}

	public void setAlcalinidad(Integer alcalinidad) {
		this.alcalinidad = alcalinidad;
	}

	public Integer getConductividad() {
		return conductividad;
	}

	public void setConductividad(Integer conductividad) {
		this.conductividad = conductividad;
	}

	public String getLavadora() {
		return lavadora;
	}

	public void setLavadora(String lavadora) {
		this.lavadora = lavadora;
	}

	public String getEstadoEtapa() {
		return estadoEtapa;
	}

	public void setEstadoEtapa(String estadoEtapa) {
		this.estadoEtapa = estadoEtapa;
	}

	public String getQuimico1() {
		return quimico1;
	}

	public void setQuimico1(String quimico1) {
		this.quimico1 = quimico1;
	}

	public String getCantidad1() {
		return cantidad1;
	}

	public void setCantidad1(String cantidad1) {
		this.cantidad1 = cantidad1;
	}

	public String getLote1() {
		return lote1;
	}

	public void setLote1(String lote1) {
		this.lote1 = lote1;
	}

	public String getQuimico2() {
		return quimico2;
	}

	public void setQuimico2(String quimico2) {
		this.quimico2 = quimico2;
	}

	public String getCantidad2() {
		return cantidad2;
	}

	public void setCantidad2(String cantidad2) {
		this.cantidad2 = cantidad2;
	}

	public String getLote2() {
		return lote2;
	}

	public void setLote2(String lote2) {
		this.lote2 = lote2;
	}

}
