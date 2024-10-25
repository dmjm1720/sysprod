package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Preservacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idPreservacion;
	private String nombre;
	private Set entradases = new HashSet(0);

	public Preservacion() {
	}

	public Preservacion(int idPreservacion) {
		this.idPreservacion = idPreservacion;
	}

	public Preservacion(int idPreservacion, String nombre, Set entradases) {
		this.idPreservacion = idPreservacion;
		this.nombre = nombre;
		this.entradases = entradases;
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

	public Set getEntradases() {
		return this.entradases;
	}

	public void setEntradases(Set entradases) {
		this.entradases = entradases;
	}

}
