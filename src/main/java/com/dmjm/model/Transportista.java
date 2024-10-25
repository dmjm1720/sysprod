package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Transportista implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idTransportista;
	private String nombre;
	private Set entradases = new HashSet(0);

	public Transportista() {
	}

	public Transportista(int idTransportista) {
		this.idTransportista = idTransportista;
	}

	public Transportista(int idTransportista, String nombre, Set entradases) {
		this.idTransportista = idTransportista;
		this.nombre = nombre;
		this.entradases = entradases;
	}

	public int getIdTransportista() {
		return this.idTransportista;
	}

	public void setIdTransportista(int idTransportista) {
		this.idTransportista = idTransportista;
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
