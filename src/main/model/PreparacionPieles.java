package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class PreparacionPieles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idPreparacion;
	private Date fecha;
	private Integer idOperacionLavadoras;
	private Integer noOperacion;
	private BigDecimal totalKilos;
	private String estado;

	public PreparacionPieles() {
	}

	public PreparacionPieles(int idPreparacion) {
		this.idPreparacion = idPreparacion;
	}

	public PreparacionPieles(int idPreparacion, Date fecha, Integer idOperacionLavadoras, Integer noOperacion,
			BigDecimal totalKilos, String estado) {
		this.idPreparacion = idPreparacion;
		this.fecha = fecha;
		this.idOperacionLavadoras = idOperacionLavadoras;
		this.noOperacion = noOperacion;
		this.totalKilos = totalKilos;
		this.estado = estado;

	}

	public int getIdPreparacion() {
		return this.idPreparacion;
	}

	public void setIdPreparacion(int idPreparacion) {
		this.idPreparacion = idPreparacion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdOperacionLavadoras() {
		return this.idOperacionLavadoras;
	}

	public void setIdOperacionLavadoras(Integer idOperacionLavadoras) {
		this.idOperacionLavadoras = idOperacionLavadoras;
	}

	public Integer getNoOperacion() {
		return this.noOperacion;
	}

	public void setNoOperacion(Integer noOperacion) {
		this.noOperacion = noOperacion;
	}

	public BigDecimal getTotalKilos() {
		return this.totalKilos;
	}

	public void setTotalKilos(BigDecimal totalKilos) {
		this.totalKilos = totalKilos;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
