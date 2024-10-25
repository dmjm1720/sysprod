package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Materia implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idMateria;
	private String tipo;
	private Integer descuentoCalcioTablaA;
	private Integer descuentoCalcioTablaB;
	private Set entradases = new HashSet(0);
	private Set precioses = new HashSet(0);

	public Materia() {
	}

	public Materia(int idMateria) {
		this.idMateria = idMateria;
	}

	public Materia(int idMateria, String tipo, Integer descuentoCalcioTablaA, Integer descuentoCalcioTablaB,
			Set entradases, Set precioses) {
		this.idMateria = idMateria;
		this.tipo = tipo;
		this.descuentoCalcioTablaA = descuentoCalcioTablaA;
		this.descuentoCalcioTablaB = descuentoCalcioTablaB;
		this.entradases = entradases;
		this.precioses = precioses;
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

	public Set getEntradases() {
		return this.entradases;
	}

	public void setEntradases(Set entradases) {
		this.entradases = entradases;
	}

	public Set getPrecioses() {
		return this.precioses;
	}

	public void setPrecioses(Set precioses) {
		this.precioses = precioses;
	}

}
