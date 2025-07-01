package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaEstA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaEstA;
	private FolioPreparacionEstA folioPreparacionEstA;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private String proceso;
	private Integer noLimpieza;
	private String voBo;

	public LimpiezaEstA() {
	}

	public LimpiezaEstA(int idLimpiezaEstA) {
		this.idLimpiezaEstA = idLimpiezaEstA;
	}

	public LimpiezaEstA(int idLimpiezaEstA, FolioPreparacionEstA folioPreparacionEstA, Date horaInicial, Date horaFinal,
			String quimico, BigDecimal litrosUsados, String lote, String proceso, Integer noLimpieza, String voBo) {
		this.idLimpiezaEstA = idLimpiezaEstA;
		this.folioPreparacionEstA = folioPreparacionEstA;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
		this.noLimpieza = noLimpieza;
		this.voBo = voBo;
	}

	public int getIdLimpiezaEstA() {
		return this.idLimpiezaEstA;
	}

	public void setIdLimpiezaEstA(int idLimpiezaEstA) {
		this.idLimpiezaEstA = idLimpiezaEstA;
	}

	public FolioPreparacionEstA getFolioPreparacionEstA() {
		return this.folioPreparacionEstA;
	}

	public void setFolioPreparacionEstA(FolioPreparacionEstA folioPreparacionEstA) {
		this.folioPreparacionEstA = folioPreparacionEstA;
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
		return noLimpieza;
	}

	public void setNoLimpieza(Integer noLimpieza) {
		this.noLimpieza = noLimpieza;
	}

	public String getVoBo() {
		return voBo;
	}

	public void setVoBo(String voBo) {
		this.voBo = voBo;
	}
	
	

}
