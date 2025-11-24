package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaLuwaUno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaLuwaUno;
	private FolioPreparacionLuwaUno folioPreparacionLuwaUno;
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
	private String noLuwa;
	private Integer idUsuario;

	public LimpiezaLuwaUno() {
	}

	public LimpiezaLuwaUno(int idLimpiezaLuwaUno) {
		this.idLimpiezaLuwaUno = idLimpiezaLuwaUno;
	}

	public LimpiezaLuwaUno(int idLimpiezaLuwaUno, FolioPreparacionLuwaUno folioPreparacionLuwaUno, Date horaInicial,
			Date horaFinal, String quimico, BigDecimal litrosUsados, String um, Integer temperatura, String lote,
			BigDecimal phFinal, String proceso, Integer noLimpieza, String vobo, String noLuwa, Integer idUsuario) {
		this.idLimpiezaLuwaUno = idLimpiezaLuwaUno;
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
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
		this.noLuwa = noLuwa;
		this.idUsuario = idUsuario;
	}

	public int getIdLimpiezaLuwaUno() {
		return this.idLimpiezaLuwaUno;
	}

	public void setIdLimpiezaLuwaUno(int idLimpiezaLuwaUno) {
		this.idLimpiezaLuwaUno = idLimpiezaLuwaUno;
	}

	public FolioPreparacionLuwaUno getFolioPreparacionLuwaUno() {
		return this.folioPreparacionLuwaUno;
	}

	public void setFolioPreparacionLuwaUno(FolioPreparacionLuwaUno folioPreparacionLuwaUno) {
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
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

	public String getNoLuwa() {
		return this.noLuwa;
	}

	public void setNoLuwa(String noLuwa) {
		this.noLuwa = noLuwa;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
