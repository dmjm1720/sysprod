package com.dmjm.model;

import java.math.BigDecimal;

public class ResumenLavadoras implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idResumenLavadoras;
	private Integer numero;
	private String tipo;
	private Integer noOperacion;
	private BigDecimal cantidad;

	public ResumenLavadoras() {
	}

	public ResumenLavadoras(int idResumenLavadoras) {
		this.idResumenLavadoras = idResumenLavadoras;
	}

	public ResumenLavadoras(int idResumenLavadoras, Integer numero, String tipo, Integer noOperacion,
			BigDecimal cantidad) {
		this.idResumenLavadoras = idResumenLavadoras;
		this.numero = numero;
		this.tipo = tipo;
		this.noOperacion = noOperacion;
		this.cantidad = cantidad;
	}

	public int getIdResumenLavadoras() {
		return this.idResumenLavadoras;
	}

	public void setIdResumenLavadoras(int idResumenLavadoras) {
		this.idResumenLavadoras = idResumenLavadoras;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNoOperacion() {
		return this.noOperacion;
	}

	public void setNoOperacion(Integer noOperacion) {
		this.noOperacion = noOperacion;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

}
