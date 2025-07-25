package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class Precios implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPrecios;
	private Materia materia;
	private Proveedores proveedores;
	private String precioActual;
	private Date fechaActualizacion;

	public Precios() {
	}

	public Precios(int idPrecios) {
		this.idPrecios = idPrecios;
	}

	public Precios(int idPrecios, Materia materia, Proveedores proveedores, String precioActual,
			Date fechaActualizacion) {
		this.idPrecios = idPrecios;
		this.materia = materia;
		this.proveedores = proveedores;
		this.precioActual = precioActual;
		this.fechaActualizacion = fechaActualizacion;
	}

	public int getIdPrecios() {
		return this.idPrecios;
	}

	public void setIdPrecios(int idPrecios) {
		this.idPrecios = idPrecios;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Proveedores getProveedores() {
		return this.proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public String getPrecioActual() {
		return this.precioActual;
	}

	public void setPrecioActual(String precioActual) {
		this.precioActual = precioActual;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
