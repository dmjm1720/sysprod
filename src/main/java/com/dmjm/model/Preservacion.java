package com.dmjm.model;

import java.io.Serializable;

public class Preservacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPreservacion;
	private String nombre;

	public Preservacion() {
	}

	public Preservacion(int idPreservacion) {
		this.idPreservacion = idPreservacion;
	}

	public Preservacion(int idPreservacion, String nombre) {
		this.idPreservacion = idPreservacion;
		this.nombre = nombre;

	}

	public int getIdPreservacion() {
		return this.idPreservacion;
	}

	public void setIdPreservacion(int idPreservacion) {
		this.idPreservacion = idPreservacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
