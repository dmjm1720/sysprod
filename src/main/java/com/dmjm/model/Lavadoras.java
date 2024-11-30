package com.dmjm.model;

public class Lavadoras implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLavadora;
	private String nombre;
	private String descripcion;
	private Integer estado;
	private String etapa;

	public Lavadoras() {
	}

	public Lavadoras(int idLavadora) {
		this.idLavadora = idLavadora;
	}

	public Lavadoras(int idLavadora, String nombre, String descripcion, Integer estado, String etapa) {
		this.idLavadora = idLavadora;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.etapa = etapa;

	}

	public int getIdLavadora() {
		return this.idLavadora;
	}

	public void setIdLavadora(int idLavadora) {
		this.idLavadora = idLavadora;
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

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

}
