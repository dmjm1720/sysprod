package com.dmjm.model;

import java.util.Date;

public class TraspasoSilos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idTraspaso;
	private Operador operador;
	private Usuarios usuarios;
	private PreparacionPieles preparacionPieles;
	private Date fechaInicial;
	private Date horaInicial;
	private Date fechaFinal;
	private Date horaFinal;
	private int noOperacion;
	private int idOperacion;

	public TraspasoSilos() {
	}

	public TraspasoSilos(int idTraspaso) {
		this.idTraspaso = idTraspaso;
	}

	public TraspasoSilos(int idTraspaso, Operador operador, PreparacionPieles preparacionPieles, Usuarios usuarios,
			Date fechaInicial, Date horaInicial, Date fechaFinal, Date horaFinal, Integer noOperacion,
			Integer idOperacion) {
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
	}

	public int getIdTraspaso() {
		return this.idTraspaso;
	}

	public void setIdTraspaso(int idTraspaso) {
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

	public int getNoOperacion() {
		return noOperacion;
	}

	public void setNoOperacion(int noOperacion) {
		this.noOperacion = noOperacion;
	}

	public int getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}

	public PreparacionPieles getPreparacionPieles() {
		return preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

}
