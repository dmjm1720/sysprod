package com.dmjm.model;

import java.util.Date;

public class CribasImanes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idCribasImanes;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private Date fecha;
	private String cribasInspeccionadas;
	private Integer turno;
	private String apertura;
	private String integridad;
	private String hallazgosCriba;
	private String hallazgosImanes30Mesh;
	private String hallazgosImanes60Mesh;
	private Integer noLimpieza;
	private Integer idUsuario;
	private String vobo;

	public CribasImanes() {
	}

	public CribasImanes(int idCribasImanes) {
		this.idCribasImanes = idCribasImanes;
	}

	public CribasImanes(int idCribasImanes, FolioPreparacionMolienda folioPreparacionMolienda, Date fecha,
			String cribasInspeccionadas, Integer turno, String apertura, String integridad, String hallazgosCriba,
			String hallazgosImanes30Mesh, String hallazgosImanes60Mesh, Integer noLimpieza, Integer idUsuario,
			String vobo) {

		this.idCribasImanes = idCribasImanes;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.fecha = fecha;
		this.cribasInspeccionadas = cribasInspeccionadas;
		this.turno = turno;
		this.apertura = apertura;
		this.integridad = integridad;
		this.hallazgosCriba = hallazgosCriba;
		this.hallazgosImanes30Mesh = hallazgosImanes30Mesh;
		this.hallazgosImanes60Mesh = hallazgosImanes60Mesh;
		this.noLimpieza = noLimpieza;
		this.idUsuario = idUsuario;
		this.vobo = vobo;
	}

	public int getIdCribasImanes() {
		return this.idCribasImanes;
	}

	public void setIdCribasImanes(int idCribasImanes) {
		this.idCribasImanes = idCribasImanes;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return this.folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCribasInspeccionadas() {
		return this.cribasInspeccionadas;
	}

	public void setCribasInspeccionadas(String cribasInspeccionadas) {
		this.cribasInspeccionadas = cribasInspeccionadas;
	}

	public Integer getTurno() {
		return this.turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	public String getApertura() {
		return this.apertura;
	}

	public void setApertura(String apertura) {
		this.apertura = apertura;
	}

	public String getIntegridad() {
		return this.integridad;
	}

	public void setIntegridad(String integridad) {
		this.integridad = integridad;
	}

	public String getHallazgosCriba() {
		return this.hallazgosCriba;
	}

	public void setHallazgosCriba(String hallazgosCriba) {
		this.hallazgosCriba = hallazgosCriba;
	}

	public String getHallazgosImanes30Mesh() {
		return this.hallazgosImanes30Mesh;
	}

	public void setHallazgosImanes30Mesh(String hallazgosImanes30Mesh) {
		this.hallazgosImanes30Mesh = hallazgosImanes30Mesh;
	}

	public String getHallazgosImanes60Mesh() {
		return this.hallazgosImanes60Mesh;
	}

	public void setHallazgosImanes60Mesh(String hallazgosImanes60Mesh) {
		this.hallazgosImanes60Mesh = hallazgosImanes60Mesh;
	}

	public Integer getNoLimpieza() {
		return noLimpieza;
	}

	public void setNoLimpieza(Integer noLimpieza) {
		this.noLimpieza = noLimpieza;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getVobo() {
		return vobo;
	}

	public void setVobo(String vobo) {
		this.vobo = vobo;
	}

}
