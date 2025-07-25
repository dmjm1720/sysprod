package com.dmjm.model;

import java.io.Serializable;

public class CuentasContables implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCuentaContable;
	private String cuenta;
	private String descripcion;

	public CuentasContables() {
	}

	public CuentasContables(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public CuentasContables(int idCuentaContable, String cuenta, String descripcion) {
		this.idCuentaContable = idCuentaContable;
		this.cuenta = cuenta;
		this.descripcion = descripcion;

	}

	public int getIdCuentaContable() {
		return this.idCuentaContable;
	}

	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
