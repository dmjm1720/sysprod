package com.dmjm.model;

import java.util.Date;

public class ResumenLuwaUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idResumen;
	private FolioPreparacionLuwaUno folioPreparacionLuwaUno;
	private String noOperacion;
	private Date fechaInicio;
	private Date horaInicio;
	private Date fechaFinal;
	private Date horaFinal;
	private Integer tiempo;

	public ResumenLuwaUno() {
	}

	public ResumenLuwaUno(int idResumen) {
		this.idResumen = idResumen;
	}

	public ResumenLuwaUno(int idResumen, FolioPreparacionLuwaUno folioPreparacionLuwaUno, String noOperacion,
			Date fechaInicio, Date horaInicio, Date fechaFinal, Date horaFinal, Integer tiempo) {
		this.idResumen = idResumen;
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
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

	public FolioPreparacionLuwaUno getFolioPreparacionLuwaUno() {
		return this.folioPreparacionLuwaUno;
	}

	public void setFolioPreparacionLuwaUno(FolioPreparacionLuwaUno folioPreparacionLuwaUno) {
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
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
