package com.dmjm.model;

import java.util.Date;

public class OrdenMantenimiento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOrdenManto;
	private FolioPreparacionCocedores folioPreparacionCocedores;
	private String si;
	private String no;
	private Integer noOrden;
	private Date horaInicio;
	private Date horaFin;

	public OrdenMantenimiento() {
	}

	public OrdenMantenimiento(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public OrdenMantenimiento(int idOrdenManto, FolioPreparacionCocedores folioPreparacionCocedores, String si,
			String no, Integer noOrden, Date horaInicio, Date horaFin) {
		this.idOrdenManto = idOrdenManto;
		this.folioPreparacionCocedores = folioPreparacionCocedores;
		this.si = si;
		this.no = no;
		this.noOrden = noOrden;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public int getIdOrdenManto() {
		return this.idOrdenManto;
	}

	public void setIdOrdenManto(int idOrdenManto) {
		this.idOrdenManto = idOrdenManto;
	}

	public FolioPreparacionCocedores getFolioPreparacionCocedores() {
		return this.folioPreparacionCocedores;
	}

	public void setFolioPreparacionCocedores(FolioPreparacionCocedores folioPreparacionCocedores) {
		this.folioPreparacionCocedores = folioPreparacionCocedores;
	}

	public String getSi() {
		return this.si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
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

}
