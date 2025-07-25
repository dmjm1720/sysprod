package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Lavadoras implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLavadora;
	private String nombre;
	private String descripcion;
	private Integer estado;
	private String etapa;
	private BigDecimal capacidadKilos;
	public Lavadoras() {
	}

	public Lavadoras(int idLavadora) {
		this.idLavadora = idLavadora;
	}

	public Lavadoras(int idLavadora, String nombre, String descripcion, Integer estado, String etapa, BigDecimal capacidadKilos) {
		this.idLavadora = idLavadora;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.etapa = etapa;
		this.capacidadKilos = capacidadKilos;

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

	public BigDecimal getCapacidadKilos() {
		return capacidadKilos;
	}

	public void setCapacidadKilos(BigDecimal capacidadKilos) {
		this.capacidadKilos = capacidadKilos;
	}
	
	

}
