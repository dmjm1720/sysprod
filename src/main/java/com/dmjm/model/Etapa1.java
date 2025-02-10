package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class Etapa1 implements java.io.Serializable {

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
	private String tipo;
	private String cantidad;
	private String lote;
	private String operador;
	private String agua;
	private Boolean estado;
	private Integer alcalinidad;
	private Integer conductividad;
	private String lavadora;

	public Etapa1() {
	}

	public Etapa1(int idEtapa) {
		this.idEtapa = idEtapa;
	}

	public Etapa1(int idEtapa, PreparacionPieles preparacionPieles, String etapa, Date diaInicio, Date horaInicio,
			Date diaFin, Date horaFin, BigDecimal phSol, BigDecimal normalidad, String tipo, String cantidad,
			String lote, String operador, String agua, Boolean estado, Integer alcalinidad, Integer conductividad, String lavadora) {
		this.idEtapa = idEtapa;
		this.preparacionPieles = preparacionPieles;
		this.etapa = etapa;
		this.diaInicio = diaInicio;
		this.horaInicio = horaInicio;
		this.diaFin = diaFin;
		this.horaFin = horaFin;
		this.phSol = phSol;
		this.normalidad = normalidad;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.lote = lote;
		this.operador = operador;
		this.agua = agua;
		this.estado = estado;
		this.alcalinidad =alcalinidad;
		this.conductividad = conductividad;
		this.lavadora = lavadora;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
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
	
	
	

}
