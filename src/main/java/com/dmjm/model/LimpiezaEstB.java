package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaEstB implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaEstB;
	private FolioPreparacionEstB folioPreparacionEstB;
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

	public LimpiezaEstB() {
	}

	public LimpiezaEstB(int idLimpiezaEstB) {
		this.idLimpiezaEstB = idLimpiezaEstB;
	}

	public LimpiezaEstB(int idLimpiezaEstB, FolioPreparacionEstB folioPreparacionEstB, Date horaInicial, Date horaFinal,
			String quimico, BigDecimal litrosUsados, String lote, String proceso, Integer noLimpieza, String voBo,
			String noCocedor, Integer idUsuario) {
		this.idLimpiezaEstB = idLimpiezaEstB;
		this.folioPreparacionEstB = folioPreparacionEstB;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
		this.noLimpieza = noLimpieza;
		this.voBo = voBo;
		this.noCocedor = noCocedor;
		this.idUsuario = idUsuario;
	}

	public int getIdLimpiezaEstB() {
		return this.idLimpiezaEstB;
	}

	public void setIdLimpiezaEstB(int idLimpiezaEstB) {
		this.idLimpiezaEstB = idLimpiezaEstB;
	}

	public FolioPreparacionEstB getFolioPreparacionEstB() {
		return this.folioPreparacionEstB;
	}

	public void setFolioPreparacionEstB(FolioPreparacionEstB folioPreparacionEstB) {
		this.folioPreparacionEstB = folioPreparacionEstB;
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
