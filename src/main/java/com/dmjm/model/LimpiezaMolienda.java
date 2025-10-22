package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaMolienda implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaMolienda;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private Integer noLimpieza;
	private String proceso;
	private String tolvasPullman;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private Integer tempAgua;
	private String vobo;
	private Integer idUsuario;
	private Integer turno;

	public LimpiezaMolienda() {
	}

	public LimpiezaMolienda(int idLimpiezaMolienda) {
		this.idLimpiezaMolienda = idLimpiezaMolienda;
	}

	public LimpiezaMolienda(int idLimpiezaMolienda, FolioPreparacionMolienda folioPreparacionMolienda,
			Integer noLimpieza, String proceso, String tolvasPullman, Date horaInicial, Date horaFinal, String quimico,
			BigDecimal litrosUsados, String lote, Integer tempAgua, String vobo, Integer idUsuario, Integer turno) {
		this.idLimpiezaMolienda = idLimpiezaMolienda;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.noLimpieza = noLimpieza;
		this.proceso = proceso;
		this.tolvasPullman = tolvasPullman;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.tempAgua = tempAgua;
		this.vobo = vobo;
		this.idUsuario = idUsuario;
		this.turno = turno;
	}

	public int getIdLimpiezaMolienda() {
		return this.idLimpiezaMolienda;
	}

	public void setIdLimpiezaMolienda(int idLimpiezaMolienda) {
		this.idLimpiezaMolienda = idLimpiezaMolienda;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return this.folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public Integer getNoLimpieza() {
		return this.noLimpieza;
	}

	public void setNoLimpieza(Integer noLimpieza) {
		this.noLimpieza = noLimpieza;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getTolvasPullman() {
		return this.tolvasPullman;
	}

	public void setTolvasPullman(String tolvasPullman) {
		this.tolvasPullman = tolvasPullman;
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

	public Integer getTempAgua() {
		return this.tempAgua;
	}

	public void setTempAgua(Integer tempAgua) {
		this.tempAgua = tempAgua;
	}

	public String getVobo() {
		return this.vobo;
	}

	public void setVobo(String vobo) {
		this.vobo = vobo;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

}
