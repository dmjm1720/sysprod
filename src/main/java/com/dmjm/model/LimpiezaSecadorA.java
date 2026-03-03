package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaSecadorA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaSecadorA;
	private FolioPreparacionSecadorA folioPreparacionSecadorA;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private String proceso;
	private Integer noLimpieza;
	private String vobo;
	private String noSecador;
	private Integer idUsuario;
	private String um;
	private Integer temperatura;
	private BigDecimal phFinal;

	public LimpiezaSecadorA() {
	}

	public LimpiezaSecadorA(int idLimpiezaSecadorA) {
		this.idLimpiezaSecadorA = idLimpiezaSecadorA;
	}

	public LimpiezaSecadorA(int idLimpiezaSecadorA, FolioPreparacionSecadorA folioPreparacionSecadorA, Date horaInicial,
			Date horaFinal, String quimico, BigDecimal litrosUsados, String lote, String proceso, Integer noLimpieza,
			String vobo, String noSecador, Integer idUsuario, String um, Integer temperatura, BigDecimal phFinal) {
		this.idLimpiezaSecadorA = idLimpiezaSecadorA;
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
		this.noLimpieza = noLimpieza;
		this.vobo = vobo;
		this.noSecador = noSecador;
		this.idUsuario = idUsuario;
		this.um = um;
		this.temperatura = temperatura;
		this.phFinal = phFinal;
	}

	public int getIdLimpiezaSecadorA() {
		return this.idLimpiezaSecadorA;
	}

	public void setIdLimpiezaSecadorA(int idLimpiezaSecadorA) {
		this.idLimpiezaSecadorA = idLimpiezaSecadorA;
	}

	public FolioPreparacionSecadorA getFolioPreparacionSecadorA() {
		return this.folioPreparacionSecadorA;
	}

	public void setFolioPreparacionSecadorA(FolioPreparacionSecadorA folioPreparacionSecadorA) {
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
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

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
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

	public String getNoSecador() {
		return this.noSecador;
	}

	public void setNoSecador(String noSecador) {
		this.noSecador = noSecador;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Integer getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}

	public BigDecimal getPhFinal() {
		return phFinal;
	}

	public void setPhFinal(BigDecimal phFinal) {
		this.phFinal = phFinal;
	}

}
