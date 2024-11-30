package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Transportista implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idTransportista;
	private String nombre;

	public Transportista() {
	}

	public Transportista(int idTransportista) {
		this.idTransportista = idTransportista;
	}

	public Transportista(int idTransportista, String nombre) {
		this.idTransportista = idTransportista;
		this.nombre = nombre;
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

}
