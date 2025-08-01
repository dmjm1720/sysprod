package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaVotatorA implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaVotatorA;
	private FolioPreparacionVotatorA folioPreparacionVotatorA;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String um;
	private Integer temperatura;
	private String lote;
	private BigDecimal phFinal;
	private String proceso;
	private Integer noLimpieza;
	private String vobo;
	private String noVotator;
	private Integer idUsuario;

	public LimpiezaVotatorA() {
	}

	public LimpiezaVotatorA(int idLimpiezaVotatorA) {
		this.idLimpiezaVotatorA = idLimpiezaVotatorA;
	}

	public LimpiezaVotatorA(int idLimpiezaVotatorA, FolioPreparacionVotatorA folioPreparacionVotatorA, Date horaInicial,
			Date horaFinal, String quimico, BigDecimal litrosUsados, String um, Integer temperatura, String lote,
			BigDecimal phFinal, String proceso, Integer noLimpieza, String vobo, String noVotator, Integer idUsuario) {
		this.idLimpiezaVotatorA = idLimpiezaVotatorA;
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.um = um;
		this.temperatura = temperatura;
		this.lote = lote;
		this.phFinal = phFinal;
		this.proceso = proceso;
		this.noLimpieza = noLimpieza;
		this.vobo = vobo;
		this.noVotator = noVotator;
		this.idUsuario = idUsuario;
	}

	public int getIdLimpiezaVotatorA() {
		return this.idLimpiezaVotatorA;
	}

	public void setIdLimpiezaVotatorA(int idLimpiezaVotatorA) {
		this.idLimpiezaVotatorA = idLimpiezaVotatorA;
	}

	public FolioPreparacionVotatorA getFolioPreparacionVotatorA() {
		return this.folioPreparacionVotatorA;
	}

	public void setFolioPreparacionVotatorA(FolioPreparacionVotatorA folioPreparacionVotatorA) {
		this.folioPreparacionVotatorA = folioPreparacionVotatorA;
	}

	public Date getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getQuimico() {
		return this.quimico;
	}

	public void setQuimico(String quimico) {
		this.quimico = quimico;
	}

	public BigDecimal getLitrosUsados() {
		return this.litrosUsados;
	}

	public void setLitrosUsados(BigDecimal litrosUsados) {
		this.litrosUsados = litrosUsados;
	}

	public String getUm() {
		return this.um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Integer getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public BigDecimal getPhFinal() {
		return this.phFinal;
	}

	public void setPhFinal(BigDecimal phFinal) {
		this.phFinal = phFinal;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Integer getNoLimpieza() {
		return this.noLimpieza;
	}

	public void setNoLimpieza(Integer noLimpieza) {
		this.noLimpieza = noLimpieza;
	}

	public String getVobo() {
		return this.vobo;
	}

	public void setVobo(String vobo) {
		this.vobo = vobo;
	}

	public String getNoVotator() {
		return this.noVotator;
	}

	public void setNoVotator(String noVotator) {
		this.noVotator = noVotator;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
