package com.dmjm.model;

import java.io.Serializable;

public class DescuentoHumedadTablaB implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idHumedad;
	private String descuento;
	private String de;
	private String hasta;

	public DescuentoHumedadTablaB() {
	}

	public DescuentoHumedadTablaB(int idHumedad) {
		this.idHumedad = idHumedad;
	}

	public DescuentoHumedadTablaB(int idHumedad, String descuento, String de, String hasta) {
		this.idHumedad = idHumedad;
		this.descuento = descuento;
		this.de = de;
		this.hasta = hasta;
	}

	public int getIdHumedad() {
		return this.idHumedad;
	}

	public void setIdHumedad(int idHumedad) {
		this.idHumedad = idHumedad;
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
