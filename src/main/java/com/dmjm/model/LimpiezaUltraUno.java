package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaUltraUno implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaUltraUno;
	private FolioPreparacionUltraUno folioPreparacionUltraUno;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private String proceso;
	private Integer noLimpieza;
	private String voBo;
	private String noCocedor;
	private Integer idUsuario;

	public LimpiezaUltraUno() {
	}

	public LimpiezaUltraUno(int idLimpiezaUltraUno) {
		this.idLimpiezaUltraUno = idLimpiezaUltraUno;
	}

	public LimpiezaUltraUno(int idLimpiezaUltraUno, FolioPreparacionUltraUno folioPreparacionUltraUno, Date horaInicial,
			Date horaFinal, String quimico, BigDecimal litrosUsados, String lote, String proceso, Integer noLimpieza,
			String voBo, String noCocedor, Integer idUsuario) {
		this.idLimpiezaUltraUno = idLimpiezaUltraUno;
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
		this.voBo = voBo;
		this.noCocedor = noCocedor;
		this.idUsuario = idUsuario;
	}

	public int getIdLimpiezaUltraUno() {
		return this.idLimpiezaUltraUno;
	}

	public void setIdLimpiezaUltraUno(int idLimpiezaUltraUno) {
		this.idLimpiezaUltraUno = idLimpiezaUltraUno;
	}

	public FolioPreparacionUltraUno getFolioPreparacionUltraUno() {
		return this.folioPreparacionUltraUno;
	}

	public void setFolioPreparacionUltraUno(FolioPreparacionUltraUno folioPreparacionUltraUno) {
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
