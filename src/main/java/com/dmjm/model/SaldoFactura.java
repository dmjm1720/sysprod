package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class SaldoFactura implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idSaldo;
	private String factura;
	private BigDecimal nuevoSaldo;
	private BigDecimal totalFactura;
	private Integer operacion;
	private String lavadora;
	private Date fecha;

	public SaldoFactura() {
	}

	public SaldoFactura(int idSaldo) {
		this.idSaldo = idSaldo;
	}

	public SaldoFactura(int idSaldo, String factura, BigDecimal nuevoSaldo, BigDecimal totalFactura, Integer operacion,
			String lavadora, Date fecha) {
		this.idSaldo = idSaldo;
		this.factura = factura;
		this.nuevoSaldo = nuevoSaldo;
		this.totalFactura = totalFactura;
		this.operacion = operacion;
		this.lavadora = lavadora;
		this.fecha = fecha;
	}

	public int getIdSaldo() {
		return this.idSaldo;
	}

	public void setIdSaldo(int idSaldo) {
		this.idSaldo = idSaldo;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public BigDecimal getNuevoSaldo() {
		return this.nuevoSaldo;
	}

	public void setNuevoSaldo(BigDecimal nuevoSaldo) {
		this.nuevoSaldo = nuevoSaldo;
	}

	public BigDecimal getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(BigDecimal totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Integer getOperacion() {
		return operacion;
	}

	public void setOperacion(Integer operacion) {
		this.operacion = operacion;
	}

	public String getLavadora() {
		return lavadora;
	}

	public void setLavadora(String lavadora) {
		this.lavadora = lavadora;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
