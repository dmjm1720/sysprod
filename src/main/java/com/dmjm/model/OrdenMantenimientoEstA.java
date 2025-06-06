package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoEstA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
	private FolioPreparacionEstA folioPreparacionEstA;
	private String siNo;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoEstA() {
	}

	public OrdenMantenimientoEstA(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public OrdenMantenimientoEstA(int idOrdenManto, FolioPreparacionEstA folioPreparacionEstA, String siNo,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenManto = idOrdenManto;
		this.folioPreparacionEstA = folioPreparacionEstA;
		this.siNo = siNo;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
	}

	public int getIdOrdenManto() {
		return this.idOrdenManto;
	}

	public void setIdOrdenManto(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public FolioPreparacionEstA getFolioPreparacionEstA() {
		return this.folioPreparacionEstA;
	}

	public void setFolioPreparacionEstA(FolioPreparacionEstA folioPreparacionEstA) {
		this.folioPreparacionEstA = folioPreparacionEstA;
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
