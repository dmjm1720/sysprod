package com.dmjm.util;

public class PrecioMateria {

	private String precio;
	private String materiaNombre;

	public PrecioMateria(String precio, String materiaNombre) {
		this.precio = precio;
		this.materiaNombre = materiaNombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getMateriaNombre() {
		return materiaNombre;
	}

	public void setMateriaNombre(String materiaNombre) {
		this.materiaNombre = materiaNombre;
	}

}
