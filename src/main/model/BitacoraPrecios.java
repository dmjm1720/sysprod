package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class BitacoraPrecios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idPrecioBitacora;
	private Integer idMateria;
	private Integer idProveedor;
	private BigDecimal precioActual;
	private Date fechaActualizacion;
	private Integer actualizadoPor;

	public BitacoraPrecios() {
	}

	public BitacoraPrecios(int idPrecioBitacora) {
		this.idPrecioBitacora = idPrecioBitacora;
	}

	public BitacoraPrecios(int idPrecioBitacora, Integer idMateria, Integer idProveedor, BigDecimal precioActual,
			Date fechaActualizacion, Integer actualizadoPor) {
		this.idPrecioBitacora = idPrecioBitacora;
		this.idMateria = idMateria;
		this.idProveedor = idProveedor;
		this.precioActual = precioActual;
		this.fechaActualizacion = fechaActualizacion;
		this.actualizadoPor = actualizadoPor;
	}

	public BitacoraPrecios(Integer idMateria, Integer idProveedor, BigDecimal precioActual, Date fechaActualizacion,
			Integer actualizadoPor) {
		this.idMateria = idMateria;
		this.idProveedor = idProveedor;
		this.precioActual = precioActual;
		this.fechaActualizacion = fechaActualizacion;
		this.actualizadoPor = actualizadoPor;
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

	public BigDecimal getPrecioActual() {
		return this.precioActual;
	}

	public void setPrecioActual(BigDecimal precioActual) {
		this.precioActual = precioActual;
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

}
