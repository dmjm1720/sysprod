package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoUltraUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoUltraUno;
	private FolioPreparacionUltraUno folioPreparacionUltraUno;
	private String siNo;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoUltraUno() {
	}

	public OrdenMantenimientoUltraUno(int idOrdenMantoUltraUno) {
		this.idOrdenMantoUltraUno = idOrdenMantoUltraUno;
	}

	public OrdenMantenimientoUltraUno(int idOrdenMantoUltraUno, FolioPreparacionUltraUno folioPreparacionUltraUno,
			String siNo, Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoUltraUno = idOrdenMantoUltraUno;
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
		this.siNo = siNo;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenMantoUltraUno() {
		return this.idOrdenMantoUltraUno;
	}

	public void setIdOrdenMantoUltraUno(int idOrdenMantoUltraUno) {
		this.idOrdenMantoUltraUno = idOrdenMantoUltraUno;
	}

	public FolioPreparacionUltraUno getFolioPreparacionUltraUno() {
		return this.folioPreparacionUltraUno;
	}

	public void setFolioPreparacionUltraUno(FolioPreparacionUltraUno folioPreparacionUltraUno) {
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
	}

	public String getSiNo() {
		return this.siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	public Integer getNoOrden() {
		return this.noOrden;
	}

	public void setNoOrden(Integer noOrden) {
		this.noOrden = noOrden;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
