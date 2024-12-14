package com.dmjm.model;


public class ProveedoresImportacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idProveedor;
	private String nombre;
	private Integer estado;

	public ProveedoresImportacion() {
	}

	public ProveedoresImportacion(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public ProveedoresImportacion(int idProveedor, String nombre, Integer estado) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.estado = estado;

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

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
