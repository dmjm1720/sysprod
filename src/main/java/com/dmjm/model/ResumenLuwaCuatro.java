package com.dmjm.model;

import java.util.Date;

public class ResumenLuwaCuatro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idResumen;
	private FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro;
	private String noOperacion;
	private Date fechaInicio;
	private Date horaInicio;
	private Date fechaFinal;
	private Date horaFinal;
	private Integer tiempo;

	public ResumenLuwaCuatro() {
	}

	public ResumenLuwaCuatro(int idResumen) {
		this.idResumen = idResumen;
	}

	public ResumenLuwaCuatro(int idResumen, FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro, String noOperacion,
			Date fechaInicio, Date horaInicio, Date fechaFinal, Date horaFinal, Integer tiempo) {
		this.idResumen = idResumen;
		this.folioPreparacionLuwaCuatro = folioPreparacionLuwaCuatro;
		this.noOperacion = noOperacion;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.fechaFinal = fechaFinal;
		this.horaFinal = horaFinal;
		this.tiempo = tiempo;
	}

	public int getIdResumen() {
		return this.idResumen;
	}

	public void setIdResumen(int idResumen) {
		this.idResumen = idResumen;
	}

	public FolioPreparacionLuwaCuatro getFolioPreparacionLuwaCuatro() {
		return this.folioPreparacionLuwaCuatro;
	}

	public void setFolioPreparacionLuwaCuatro(FolioPreparacionLuwaCuatro folioPreparacionLuwaCuatro) {
		this.folioPreparacionLuwaCuatro = folioPreparacionLuwaCuatro;
	}

	public String getNoOperacion() {
		return this.noOperacion;
	}

	public void setNoOperacion(String noOperacion) {
		this.noOperacion = noOperacion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

}
