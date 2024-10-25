package com.dmjm.model;

public class DescuentoHumedadTablaB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idHumedad;
	private Integer humedad;
	private String descuento;

	public DescuentoHumedadTablaB() {
	}

	public DescuentoHumedadTablaB(int idHumedad) {
		this.idHumedad = idHumedad;
	}

	public DescuentoHumedadTablaB(int idHumedad, Integer humedad, String descuento) {
		this.idHumedad = idHumedad;
		this.humedad = humedad;
		this.descuento = descuento;
	}

	public int getIdHumedad() {
		return this.idHumedad;
	}

	public void setIdHumedad(int idHumedad) {
		this.idHumedad = idHumedad;
	}

	public Integer getHumedad() {
		return this.humedad;
	}

	public void setHumedad(Integer humedad) {
		this.humedad = humedad;
	}

	public String getDescuento() {
		return this.descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

}
