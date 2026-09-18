package com.dmjm.model;

import java.util.Date;

public class ResumenTraspasos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idResumenTraspaso;
	private Integer operacionOrigen;
	private Integer kgCantidad;
	private String tipo;
	private Integer lavadoraGCT;
	private Integer operacionDestino;
	private Integer kgCantidadRestante;
	private String observaciones;
	private Integer lavadoraGCTDestino;
	private Date fechaInicial;
	private Date fechaFinal;
	private Date horaInicial;
	private Date horaFinal;

	public ResumenTraspasos() {
	}

	public ResumenTraspasos(int idResumenTraspaso) {
		this.idResumenTraspaso = idResumenTraspaso;
	}

	public ResumenTraspasos(int idResumenTraspaso, Integer operacionOrigen, Integer kgCantidad, String tipo,
			Integer lavadoraGCT, Integer operacionDestino, Integer kgCantidadRestante, String observaciones,
			Integer lavadoraGCTDestino, Date fechaInicial, Date fechaFinal, Date horaInicial, Date horaFinal) {
		this.idResumenTraspaso = idResumenTraspaso;
		this.operacionOrigen = operacionOrigen;
		this.kgCantidad = kgCantidad;
		this.tipo = tipo;
		this.lavadoraGCT = lavadoraGCT;
		this.operacionDestino = operacionDestino;
		this.kgCantidadRestante = kgCantidadRestante;
		this.observaciones = observaciones;
		this.lavadoraGCTDestino = lavadoraGCTDestino;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public int getIdResumenTraspaso() {
		return this.idResumenTraspaso;
	}

	public void setIdResumenTraspaso(int idResumenTraspaso) {
		this.idResumenTraspaso = idResumenTraspaso;
	}

	public Integer getOperacionOrigen() {
		return this.operacionOrigen;
	}

	public void setOperacionOrigen(Integer operacionOrigen) {
		this.operacionOrigen = operacionOrigen;
	}

	public Integer getKgCantidad() {
		return this.kgCantidad;
	}

	public void setKgCantidad(Integer kgCantidad) {
		this.kgCantidad = kgCantidad;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getLavadoraGCT() {
		return this.lavadoraGCT;
	}

	public void setLavadoraGCT(Integer lavadoraGCT) {
		this.lavadoraGCT = lavadoraGCT;
	}

	public Integer getOperacionDestino() {
		return this.operacionDestino;
	}

	public void setOperacionDestino(Integer operacionDestino) {
		this.operacionDestino = operacionDestino;
	}

	public Integer getKgCantidadRestante() {
		return this.kgCantidadRestante;
	}

	public void setKgCantidadRestante(Integer kgCantidadRestante) {
		this.kgCantidadRestante = kgCantidadRestante;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getLavadoraGCTDestino() {
		return lavadoraGCTDestino;
	}

	public void setLavadoraGCTDestino(Integer lavadoraGCTDestino) {
		this.lavadoraGCTDestino = lavadoraGCTDestino;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	
	

}
