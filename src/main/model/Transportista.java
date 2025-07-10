package com.dmjm.model;

public class Transportista implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idTransportista;
	private String nombre;
	private int estado;

	public Transportista() {
	}

	public Transportista(int idTransportista) {
		this.idTransportista = idTransportista;
	}

	public Transportista(int idTransportista, String nombre, int estado) {
		this.idTransportista = idTransportista;
		this.nombre = nombre;
		this.estado = estado;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
