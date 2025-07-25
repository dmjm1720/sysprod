package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class OrdenMantenimientoUltraUno implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenMantoUltraUno;
	private FolioPreparacionUltraUno folioPreparacionUltraUno;
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
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenMantoUltraUno = idOrdenMantoUltraUno;
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
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
