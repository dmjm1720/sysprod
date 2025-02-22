package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;


public class EntradasMaquinariaEquipo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idMaqEq;
	private CuentasContables cuentasContables;
	private ProveedoresImportacion proveedoresImportacion;
	private String factura;
	private Date fechaEntrega;
	private Long folioImportacion;
	private String noReqExtra;
	private String oc;
	private String pedimento;
	private BigDecimal importe;
	private String identificacionMaqEq;

	public EntradasMaquinariaEquipo() {
	}

	public EntradasMaquinariaEquipo(int idMaqEq) {
		this.idMaqEq = idMaqEq;
	}

	public EntradasMaquinariaEquipo(int idMaqEq, CuentasContables cuentasContables,
			ProveedoresImportacion proveedoresImportacion, String factura, Date fechaEntrega, Long folioImportacion,
			String noReqExtra, String oc, String pedimento, BigDecimal importe, String identificacionMaqEq) {
		this.idMaqEq = idMaqEq;
		this.cuentasContables = cuentasContables;
		this.proveedoresImportacion = proveedoresImportacion;
		this.factura = factura;
		this.fechaEntrega = fechaEntrega;
		this.folioImportacion = folioImportacion;
		this.noReqExtra = noReqExtra;
		this.oc = oc;
		this.pedimento = pedimento;
		this.importe = importe;
		this.identificacionMaqEq = identificacionMaqEq;
	}

	public int getIdMaqEq() {
		return this.idMaqEq;
	}

	public void setIdMaqEq(int idMaqEq) {
		this.idMaqEq = idMaqEq;
	}

	public CuentasContables getCuentasContables() {
		return this.cuentasContables;
	}

	public void setCuentasContables(CuentasContables cuentasContables) {
		this.cuentasContables = cuentasContables;
	}

	public ProveedoresImportacion getProveedoresImportacion() {
		return this.proveedoresImportacion;
	}

	public void setProveedoresImportacion(ProveedoresImportacion proveedoresImportacion) {
		this.proveedoresImportacion = proveedoresImportacion;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Long getFolioImportacion() {
		return this.folioImportacion;
	}

	public void setFolioImportacion(Long folioImportacion) {
		this.folioImportacion = folioImportacion;
	}

	public String getNoReqExtra() {
		return this.noReqExtra;
	}

	public void setNoReqExtra(String noReqExtra) {
		this.noReqExtra = noReqExtra;
	}

	public String getOc() {
		return this.oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	public String getPedimento() {
		return this.pedimento;
	}

	public void setPedimento(String pedimento) {
		this.pedimento = pedimento;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getIdentificacionMaqEq() {
		return this.identificacionMaqEq;
	}

	public void setIdentificacionMaqEq(String identificacionMaqEq) {
		this.identificacionMaqEq = identificacionMaqEq;
	}

}
