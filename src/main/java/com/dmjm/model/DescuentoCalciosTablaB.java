package com.dmjm.model;

import java.io.Serializable;

public class DescuentoCalciosTablaB implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCalcios;
	private String descuento;
	private String de;
	private String hasta;

	public DescuentoCalciosTablaB() {
	}

	public DescuentoCalciosTablaB(int idCalcios) {
		this.idCalcios = idCalcios;
	}

	public DescuentoCalciosTablaB(int idCalcios, String descuento, String de, String hasta) {
		this.idCalcios = idCalcios;
		this.descuento = descuento;
		this.de = de;
		this.hasta = hasta;
	}

	public int getIdCalcios() {
		return this.idCalcios;
	}

	public void setIdCalcios(int idCalcios) {
		this.idCalcios = idCalcios;
	}

	public String getDescuento() {
		return this.descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

}
