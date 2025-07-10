package com.dmjm.model;

public class Quimicos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idQuimico;
	private String nombre;
	private String descripcion;

	public Quimicos() {
	}

	public Quimicos(int idQuimico) {
		this.idQuimico = idQuimico;
	}

	public Quimicos(int idQuimico, String nombre, String descripcion) {
		this.idQuimico = idQuimico;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdQuimico() {
		return this.idQuimico;
	}

	public void setIdQuimico(int idQuimico) {
		this.idQuimico = idQuimico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
