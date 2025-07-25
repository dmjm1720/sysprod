package com.dmjm.model;

import java.io.Serializable;

public class Activacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idActivacion;
	private Integer estado;

	public Activacion() {
	}

	public Activacion(int idActivacion) {
		this.idActivacion = idActivacion;
	}

	public Activacion(int idActivacion, Integer estado) {
		this.idActivacion = idActivacion;
		this.estado = estado;
	}

	public int getIdActivacion() {
		return this.idActivacion;
	}

	public void setIdActivacion(int idActivacion) {
		this.idActivacion = idActivacion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
