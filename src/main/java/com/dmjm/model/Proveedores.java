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
	private Set entradases = new HashSet(0);
	private Set precioses = new HashSet(0);

	public Proveedores() {
	}

	public Proveedores(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Proveedores(int idProveedor, String nombre, String razonSocial, String origen,
			Integer descuentoHumedadTablaA, Integer descuentoHumedadTablaB, Set entradases, Set precioses) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.origen = origen;
		this.descuentoHumedadTablaA = descuentoHumedadTablaA;
		this.descuentoHumedadTablaB = descuentoHumedadTablaB;
		this.entradases = entradases;
		this.precioses = precioses;
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

	public Set getEntradases() {
		return this.entradases;
	}

	public void setEntradases(Set entradases) {
		this.entradases = entradases;
	}

	public Set getPrecioses() {
		return this.precioses;
	}

	public void setPrecioses(Set precioses) {
		this.precioses = precioses;
	}

}
