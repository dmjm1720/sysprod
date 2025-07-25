package com.dmjm.model;

import java.io.Serializable;

public class Perfiles implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPerfil;
	private String nombrePerfil;

	public Perfiles() {
	}

	public Perfiles(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Perfiles(int idPerfil, String nombrePerfil) {
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;

	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

}
