package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Perfiles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idPerfil;
	private String nombrePerfil;
	private Set usuarioses = new HashSet(0);

	public Perfiles() {
	}

	public Perfiles(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Perfiles(int idPerfil, String nombrePerfil, Set usuarioses) {
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
		this.usuarioses = usuarioses;
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

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

}
