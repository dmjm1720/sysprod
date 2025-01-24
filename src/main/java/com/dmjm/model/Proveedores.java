package com.dmjm.model;

import java.util.HashSet;
import java.util.Set;

public class Proveedores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idProveedor;
	private String nombre;
	private String razonSocial;
	private String origen;
	private Integer descuentoHumedadTablaA;
	private Integer descuentoHumedadTablaB;
	private Integer estado;
	private String nacionalImportacion;

	public Proveedores() {
	}

	public Proveedores(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Proveedores(int idProveedor, String nombre, String razonSocial, String origen,
			Integer descuentoHumedadTablaA, Integer descuentoHumedadTablaB, Integer estado, String nacionalImportacion) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.origen = origen;
		this.descuentoHumedadTablaA = descuentoHumedadTablaA;
		this.descuentoHumedadTablaB = descuentoHumedadTablaB;
		this.estado = estado;
		this.nacionalImportacion = nacionalImportacion;

	}

	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Integer getDescuentoHumedadTablaA() {
		return this.descuentoHumedadTablaA;
	}

	public void setDescuentoHumedadTablaA(Integer descuentoHumedadTablaA) {
		this.descuentoHumedadTablaA = descuentoHumedadTablaA;
	}

	public Integer getDescuentoHumedadTablaB() {
		return this.descuentoHumedadTablaB;
	}

	public void setDescuentoHumedadTablaB(Integer descuentoHumedadTablaB) {
		this.descuentoHumedadTablaB = descuentoHumedadTablaB;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getNacionalImportacion() {
		return nacionalImportacion;
	}

	public void setNacionalImportacion(String nacionalImportacion) {
		this.nacionalImportacion = nacionalImportacion;
	}


}
