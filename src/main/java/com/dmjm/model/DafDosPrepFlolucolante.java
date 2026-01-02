package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class DafDosPrepFlolucolante implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idDafDos;
	private int folioDaf;
	private BigDecimal agua;
	private Date hora;
	private BigDecimal kgFloculante;
	private String lote;
	private Date fecha;
	private int estado;

	public DafDosPrepFlolucolante() {
	}

	public DafDosPrepFlolucolante(int idDafDos) {
		this.idDafDos = idDafDos;
	}

	public DafDosPrepFlolucolante(int idDafDos, int folioDaf, BigDecimal agua, Date hora, BigDecimal kgFloculante,
			String lote, Date fecha, int estado) {
		this.idDafDos = idDafDos;
		this.folioDaf = folioDaf;
		this.agua = agua;
		this.hora = hora;
		this.kgFloculante = kgFloculante;
		this.lote = lote;
		this.fecha = fecha;
		this.estado = estado;
	}

	public int getIdDafDos() {
		return idDafDos;
	}

	public void setIdDafDos(int idDafDos) {
		this.idDafDos = idDafDos;
	}

	public int getFolioDaf() {
		return folioDaf;
	}

	public void setFolioDaf(int folioDaf) {
		this.folioDaf = folioDaf;
	}

	public BigDecimal getAgua() {
		return agua;
	}

	public void setAgua(BigDecimal agua) {
		this.agua = agua;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public BigDecimal getKgFloculante() {
		return kgFloculante;
	}

	public void setKgFloculante(BigDecimal kgFloculante) {
		this.kgFloculante = kgFloculante;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
