package com.dmjm.model;

public class Mallas implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idMalla;
	private Integer malla;
	private String descripcion;
	private Integer kgSaco;

	public Mallas() {
	}

	public Mallas(int idMalla) {
		this.idMalla = idMalla;
	}

	public Mallas(int idMalla, Integer malla, String descripcion, Integer kgSaco) {
		this.idMalla = idMalla;
		this.malla = malla;
		this.descripcion = descripcion;
		this.kgSaco = kgSaco;
	}

	public int getIdMalla() {
		return this.idMalla;
	}

	public void setIdMalla(int idMalla) {
		this.idMalla = idMalla;
	}

	public Integer getMalla() {
		return this.malla;
	}

	public void setMalla(Integer malla) {
		this.malla = malla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getKgSaco() {
		return this.kgSaco;
	}

	public void setKgSaco(Integer kgSaco) {
		this.kgSaco = kgSaco;
	}

}
