package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaUltraDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaUltraDos;
	private FolioPreparacionUltraDos folioPreparacionUltraDos;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private String proceso;
	private Integer noLimpieza;
	private String voBo;
	private String noCocedor;

	public LimpiezaUltraDos() {
	}

	public LimpiezaUltraDos(int idLimpiezaUltraDos) {
		this.idLimpiezaUltraDos = idLimpiezaUltraDos;
	}

	public LimpiezaUltraDos(int idLimpiezaUltraDos, FolioPreparacionUltraDos folioPreparacionUltraDos, Date horaInicial,
			Date horaFinal, String quimico, BigDecimal litrosUsados, String lote, String proceso, Integer noLimpieza,
			String voBo, String noCocedor) {
		this.idLimpiezaUltraDos = idLimpiezaUltraDos;
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
		this.noLimpieza = noLimpieza;
		this.voBo = voBo;
		this.noCocedor = noCocedor;
	}

	public int getIdLimpiezaUltraDos() {
		return this.idLimpiezaUltraDos;
	}

	public void setIdLimpiezaUltraDos(int idLimpiezaUltraDos) {
		this.idLimpiezaUltraDos = idLimpiezaUltraDos;
	}

	public FolioPreparacionUltraDos getFolioPreparacionUltraDos() {
		return this.folioPreparacionUltraDos;
	}

	public void setFolioPreparacionUltraDos(FolioPreparacionUltraDos folioPreparacionUltraDos) {
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
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

	public String getNoCocedor() {
		return noCocedor;
	}

	public void setNoCocedor(String noCocedor) {
		this.noCocedor = noCocedor;
	}

}
