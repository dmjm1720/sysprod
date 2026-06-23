package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class FacturasPieles implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFacturaPiel;
	private PreparacionPieles preparacionPieles;
	private String factura;
	private BigDecimal total;
	private BigDecimal categoria1;
	private BigDecimal categoria2;
	private BigDecimal categoria3;
	private BigDecimal categoria4;
	private BigDecimal categoria5;
	private BigDecimal categoria6;
	private BigDecimal categoria7;
	private BigDecimal categoria8;
	private BigDecimal categoria9;
	private BigDecimal categoria10;
	private BigDecimal categoria11;
	private BigDecimal categoria12;

	public FacturasPieles() {
	}

	public FacturasPieles(int idFacturaPiel) {
		this.idFacturaPiel = idFacturaPiel;
	}

	public FacturasPieles(int idFacturaPiel, PreparacionPieles preparacionPieles, String factura, BigDecimal total,
			BigDecimal categoria1, BigDecimal categoria2, BigDecimal categoria3, BigDecimal categoria4,
			BigDecimal categoria5, BigDecimal categoria6, BigDecimal categoria7, BigDecimal categoria8,
			BigDecimal categoria9, BigDecimal categoria10, BigDecimal categoria11, BigDecimal categoria12) {

		this.idFacturaPiel = idFacturaPiel;
		this.preparacionPieles = preparacionPieles;
		this.factura = factura;
		this.total = total;
		this.categoria1 = categoria1;
		this.categoria2 = categoria2;
		this.categoria3 = categoria3;
		this.categoria4 = categoria4;
		this.categoria5 = categoria5;
		this.categoria6 = categoria6;
		this.categoria7 = categoria7;
		this.categoria8 = categoria8;
		this.categoria9 = categoria9;
		this.categoria10 = categoria10;
		this.categoria11 = categoria11;
		this.categoria12 = categoria12;
	}

	public int getIdFacturaPiel() {
		return idFacturaPiel;
	}

	public void setIdFacturaPiel(int idFacturaPiel) {
		this.idFacturaPiel = idFacturaPiel;
	}

	public PreparacionPieles getPreparacionPieles() {
		return preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(BigDecimal categoria1) {
		this.categoria1 = categoria1;
	}

	public BigDecimal getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(BigDecimal categoria2) {
		this.categoria2 = categoria2;
	}

	public BigDecimal getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(BigDecimal categoria3) {
		this.categoria3 = categoria3;
	}

	public BigDecimal getCategoria4() {
		return categoria4;
	}

	public void setCategoria4(BigDecimal categoria4) {
		this.categoria4 = categoria4;
	}

	public BigDecimal getCategoria5() {
		return categoria5;
	}

	public void setCategoria5(BigDecimal categoria5) {
		this.categoria5 = categoria5;
	}

	public BigDecimal getCategoria6() {
		return categoria6;
	}

	public void setCategoria6(BigDecimal categoria6) {
		this.categoria6 = categoria6;
	}

	public BigDecimal getCategoria7() {
		return categoria7;
	}

	public void setCategoria7(BigDecimal categoria7) {
		this.categoria7 = categoria7;
	}

	public BigDecimal getCategoria8() {
		return categoria8;
	}

	public void setCategoria8(BigDecimal categoria8) {
		this.categoria8 = categoria8;
	}

	public BigDecimal getCategoria9() {
		return categoria9;
	}

	public void setCategoria9(BigDecimal categoria9) {
		this.categoria9 = categoria9;
	}

	public BigDecimal getCategoria10() {
		return categoria10;
	}

	public void setCategoria10(BigDecimal categoria10) {
		this.categoria10 = categoria10;
	}

	public BigDecimal getCategoria11() {
		return categoria11;
	}

	public void setCategoria11(BigDecimal categoria11) {
		this.categoria11 = categoria11;
	}

	public BigDecimal getCategoria12() {
		return categoria12;
	}

	public void setCategoria12(BigDecimal categoria12) {
		this.categoria12 = categoria12;
	}

}
