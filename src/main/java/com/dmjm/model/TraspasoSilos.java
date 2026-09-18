package com.dmjm.model;

import java.util.Date;

public class TraspasoSilos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTraspaso;
	private Operador operador;
	private Usuarios usuarios;
	private PreparacionPieles preparacionPieles;
	private Date fechaInicial;
	private Date horaInicial;
	private Date fechaFinal;
	private Date horaFinal;
	private Integer noOperacion;
	private Integer idOperacion;
	private Integer cantidad;

	public TraspasoSilos() {
	}

	public TraspasoSilos(Integer idTraspaso) {
		this.idTraspaso = idTraspaso;
	}

	public TraspasoSilos(Integer idTraspaso, Operador operador, PreparacionPieles preparacionPieles, Usuarios usuarios,
			Date fechaInicial, Date horaInicial, Date fechaFinal, Date horaFinal, Integer noOperacion,
			Integer idOperacion, Integer cantidad) {
		this.idTraspaso = idTraspaso;
		this.operador = operador;
		this.preparacionPieles = preparacionPieles;
		this.usuarios = usuarios;
		this.fechaInicial = fechaInicial;
		this.horaInicial = horaInicial;
		this.fechaFinal = fechaFinal;
		this.horaFinal = horaFinal;
		this.noOperacion = noOperacion;
		this.idOperacion = idOperacion;
		this.cantidad = cantidad;
	}

	public Integer getIdTraspaso() {
		return this.idTraspaso;
	}

	public void setIdTraspaso(Integer idTraspaso) {
		this.idTraspaso = idTraspaso;
	}

	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getNoOperacion() {
		return noOperacion;
	}

	public void setNoOperacion(Integer noOperacion) {
		this.noOperacion = noOperacion;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public PreparacionPieles getPreparacionPieles() {
		return preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
