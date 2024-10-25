package com.dmjm.model;

public class DescuentoCalciosTablaB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idCalcios;
	private Integer alcalinidad;
	private String descuento;

	public DescuentoCalciosTablaB() {
	}

	public DescuentoCalciosTablaB(int idCalcios) {
		this.idCalcios = idCalcios;
	}

	public DescuentoCalciosTablaB(int idCalcios, Integer alcalinidad, String descuento) {
		this.idCalcios = idCalcios;
		this.alcalinidad = alcalinidad;
		this.descuento = descuento;
	}

	public int getIdCalcios() {
		return this.idCalcios;
	}

	public void setIdCalcios(int idCalcios) {
		this.idCalcios = idCalcios;
	}

	public Integer getAlcalinidad() {
		return this.alcalinidad;
	}

	public void setAlcalinidad(Integer alcalinidad) {
		this.alcalinidad = alcalinidad;
	}

	public String getDescuento() {
		return this.descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

}
