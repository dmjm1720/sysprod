package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimientoEstB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
	private FolioPreparacionEstB folioPreparacionEstB;
	private String siNo;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;

	public OrdenMantenimientoEstB() {
	}

	public OrdenMantenimientoEstB(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public OrdenMantenimientoEstB(int idOrdenManto, FolioPreparacionEstB folioPreparacionEstB, String siNo,
			Integer noOrden, Date horaInicio, Date horaFin, String descripcion) {
		this.idOrdenManto = idOrdenManto;
		this.folioPreparacionEstB = folioPreparacionEstB;
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

	public FolioPreparacionEstB getFolioPreparacionEstB() {
		return this.folioPreparacionEstB;
	}

	public void setFolioPreparacionEstB(FolioPreparacionEstB folioPreparacionEstB) {
		this.folioPreparacionEstB = folioPreparacionEstB;
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
