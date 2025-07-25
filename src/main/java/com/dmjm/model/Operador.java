package com.dmjm.model;

import java.io.Serializable;

public class Operador implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOperador;
	private String nombre;
	private String proceso;
	private String estado;

	public Operador() {
	}

	public Operador(int idOperador) {
		this.idOperador = idOperador;
	}

	public Operador(int idOperador, String nombre, String proceso, String estado) {
		this.idOperador = idOperador;
		this.nombre = nombre;
		this.proceso = proceso;
		this.estado = estado;
	}

	public int getIdOperador() {
		return this.idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
