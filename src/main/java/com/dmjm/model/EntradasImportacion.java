package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class EntradasImportacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idEntradaImportacion;
	private CuentasContables cuentasContables;
	private Materia materia;
	private Proveedores proveedores;
	private int tolva;
	private Date fechaEntrada;
	private String ticketEmbarque;
	private String factura;
	private BigDecimal kgEmbarcado;
	private BigDecimal kgToluca;
	private BigDecimal kgNeto;
	private BigDecimal kgMerma;
	private BigDecimal kgPatio;
	private Integer folioEa;
	private Integer porcentajeSalado;
	private String certificadoZoosanitario;
	private Integer porcentaje;
	
	

	public EntradasImportacion() {
	}

	public EntradasImportacion(int idEntradaImportacion) {
		this.idEntradaImportacion = idEntradaImportacion;
	}

	public EntradasImportacion(int idEntradaImportacion, CuentasContables cuentasContables, Materia materia,
			Proveedores proveedores, int tolva, Date fechaEntrada, String ticketEmbarque,
			String factura, BigDecimal kgEmbarcado, BigDecimal kgToluca, BigDecimal kgNeto, BigDecimal kgMerma,
			BigDecimal kgPatio, Integer folioEa, Integer porcentajeSalado, String certificadoZoosanitario,
			Integer porcentaje) {
		this.idEntradaImportacion = idEntradaImportacion;
		this.cuentasContables = cuentasContables;
		this.materia = materia;
		this.proveedores = proveedores;
		this.tolva = tolva;
		this.fechaEntrada = fechaEntrada;
		this.ticketEmbarque = ticketEmbarque;
		this.factura = factura;
		this.kgEmbarcado = kgEmbarcado;
		this.kgToluca = kgToluca;
		this.kgNeto = kgNeto;
		this.kgMerma = kgMerma;
		this.kgPatio = kgPatio;
		this.folioEa = folioEa;
		this.porcentajeSalado = porcentajeSalado;
		this.certificadoZoosanitario = certificadoZoosanitario;
		this.porcentaje = porcentaje;
	}

	public int getIdEntradaImportacion() {
		return this.idEntradaImportacion;
	}

	public void setIdEntradaImportacion(int idEntradaImportacion) {
		this.idEntradaImportacion = idEntradaImportacion;
	}

	public CuentasContables getCuentasContables() {
		return this.cuentasContables;
	}

	public void setCuentasContables(CuentasContables cuentasContables) {
		this.cuentasContables = cuentasContables;
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

	public int getTolva() {
		return this.tolva;
	}

	public void setTolva(int tolva) {
		this.tolva = tolva;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getTicketEmbarque() {
		return this.ticketEmbarque;
	}

	public void setTicketEmbarque(String ticketEmbarque) {
		this.ticketEmbarque = ticketEmbarque;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public BigDecimal getKgEmbarcado() {
		return this.kgEmbarcado;
	}

	public void setKgEmbarcado(BigDecimal kgEmbarcado) {
		this.kgEmbarcado = kgEmbarcado;
	}

	public BigDecimal getKgToluca() {
		return this.kgToluca;
	}

	public void setKgToluca(BigDecimal kgToluca) {
		this.kgToluca = kgToluca;
	}

	public BigDecimal getKgNeto() {
		return this.kgNeto;
	}

	public void setKgNeto(BigDecimal kgNeto) {
		this.kgNeto = kgNeto;
	}

	public BigDecimal getKgMerma() {
		return this.kgMerma;
	}

	public void setKgMerma(BigDecimal kgMerma) {
		this.kgMerma = kgMerma;
	}

	public BigDecimal getKgPatio() {
		return this.kgPatio;
	}

	public void setKgPatio(BigDecimal kgPatio) {
		this.kgPatio = kgPatio;
	}

	public Integer getFolioEa() {
		return this.folioEa;
	}

	public void setFolioEa(Integer folioEa) {
		this.folioEa = folioEa;
	}

	public Integer getPorcentajeSalado() {
		return this.porcentajeSalado;
	}

	public void setPorcentajeSalado(Integer porcentajeSalado) {
		this.porcentajeSalado = porcentajeSalado;
	}

	public String getCertificadoZoosanitario() {
		return this.certificadoZoosanitario;
	}

	public void setCertificadoZoosanitario(String certificadoZoosanitario) {
		this.certificadoZoosanitario = certificadoZoosanitario;
	}

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

}
