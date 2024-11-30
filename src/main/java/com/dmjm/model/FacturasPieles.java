package com.dmjm.model;

import java.math.BigDecimal;

public class FacturasPieles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idFacturaPiel;
	private PreparacionPieles preparacionPieles;
	private String factura;
	private BigDecimal cz;
	private BigDecimal dc;
	private BigDecimal ds;
	private BigDecimal cn;
	private BigDecimal rp;

	public FacturasPieles() {
	}

	public FacturasPieles(int idFacturaPiel) {
		this.idFacturaPiel = idFacturaPiel;
	}

	public FacturasPieles(int idFacturaPiel, PreparacionPieles preparacionPieles, String factura, BigDecimal cz,
			BigDecimal dc, BigDecimal ds, BigDecimal cn, BigDecimal rp) {
		this.idFacturaPiel = idFacturaPiel;
		this.preparacionPieles = preparacionPieles;
		this.factura = factura;
		this.cz = cz;
		this.dc = dc;
		this.ds = ds;
		this.cn = cn;
		this.rp = rp;
	}

	public int getIdFacturaPiel() {
		return this.idFacturaPiel;
	}

	public void setIdFacturaPiel(int idFacturaPiel) {
		this.idFacturaPiel = idFacturaPiel;
	}

	public PreparacionPieles getPreparacionPieles() {
		return this.preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public BigDecimal getCz() {
		return this.cz;
	}

	public void setCz(BigDecimal cz) {
		this.cz = cz;
	}

	public BigDecimal getDc() {
		return this.dc;
	}

	public void setDc(BigDecimal dc) {
		this.dc = dc;
	}

	public BigDecimal getDs() {
		return this.ds;
	}

	public void setDs(BigDecimal ds) {
		this.ds = ds;
	}

	public BigDecimal getCn() {
		return this.cn;
	}

	public void setCn(BigDecimal cn) {
		this.cn = cn;
	}

	public BigDecimal getRp() {
		return this.rp;
	}

	public void setRp(BigDecimal rp) {
		this.rp = rp;
	}

}
