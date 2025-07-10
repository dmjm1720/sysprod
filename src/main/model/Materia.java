package com.dmjm.model;

public class Materia implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idMateria;
	private String tipo;
	private Integer descuentoCalcioTablaA;
	private Integer descuentoCalcioTablaB;

	public Materia() {
	}

	public Materia(int idMateria) {
		this.idMateria = idMateria;
	}

	public Materia(int idMateria, String tipo, Integer descuentoCalcioTablaA, Integer descuentoCalcioTablaB) {
		this.idMateria = idMateria;
		this.tipo = tipo;
		this.descuentoCalcioTablaA = descuentoCalcioTablaA;
		this.descuentoCalcioTablaB = descuentoCalcioTablaB;

	}

	public int getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getDescuentoCalcioTablaA() {
		return this.descuentoCalcioTablaA;
	}

	public void setDescuentoCalcioTablaA(Integer descuentoCalcioTablaA) {
		this.descuentoCalcioTablaA = descuentoCalcioTablaA;
	}

	public Integer getDescuentoCalcioTablaB() {
		return this.descuentoCalcioTablaB;
	}

	public void setDescuentoCalcioTablaB(Integer descuentoCalcioTablaB) {
		this.descuentoCalcioTablaB = descuentoCalcioTablaB;
	}

}
