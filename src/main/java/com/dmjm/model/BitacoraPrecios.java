package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BitacoraPrecios implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idPrecioBitacora;
	private Integer idMateria;
	private Integer idProveedor;
	private BigDecimal precioAnterior;
	private Date fechaActualizacion;
	private Integer actualizadoPor;
	private Date fechaSistema;
	private BigDecimal nuevoPrecio;
	private String tipo;

	public BitacoraPrecios() {
	}

	public BitacoraPrecios(int idPrecioBitacora) {
		this.idPrecioBitacora = idPrecioBitacora;
	}

	public BitacoraPrecios(Integer idMateria, Integer idProveedor, BigDecimal precioAnterior, Date fechaActualizacion,
			Integer actualizadoPor, Date fechaSistema, BigDecimal nuevoPrecio, String tipo) {
		this.idMateria = idMateria;
		this.idProveedor = idProveedor;
		this.precioAnterior = precioAnterior;
		this.fechaActualizacion = fechaActualizacion;
		this.actualizadoPor = actualizadoPor;
		this.fechaSistema = fechaSistema;
		this.nuevoPrecio = nuevoPrecio;
		this.tipo = tipo;
	}

	public int getIdPrecioBitacora() {
		return this.idPrecioBitacora;
	}

	public void setIdPrecioBitacora(int idPrecioBitacora) {
		this.idPrecioBitacora = idPrecioBitacora;
	}

	public Integer getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public BigDecimal getPrecioAnterior() {
		return precioAnterior;
	}

	public void setPrecioAnterior(BigDecimal precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Integer getActualizadoPor() {
		return this.actualizadoPor;
	}

	public void setActualizadoPor(Integer actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public Date getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(Date fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public BigDecimal getNuevoPrecio() {
		return nuevoPrecio;
	}

	public void setNuevoPrecio(BigDecimal nuevoPrecio) {
		this.nuevoPrecio = nuevoPrecio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
