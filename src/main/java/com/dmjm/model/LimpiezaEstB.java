package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LimpiezaEstB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimpiezaEstB;
	private FolioPreparacionEstB folioPreparacionEstB;
	private Date horaInicial;
	private Date horaFinal;
	private String quimico;
	private BigDecimal litrosUsados;
	private String lote;
	private String proceso;

	public LimpiezaEstB() {
	}

	public LimpiezaEstB(int idLimpiezaEstB) {
		this.idLimpiezaEstB = idLimpiezaEstB;
	}

	public LimpiezaEstB(int idLimpiezaEstB, FolioPreparacionEstB folioPreparacionEstB, Date horaInicial, Date horaFinal,
			String quimico, BigDecimal litrosUsados, String lote, String proceso) {
		this.idLimpiezaEstB = idLimpiezaEstB;
		this.folioPreparacionEstB = folioPreparacionEstB;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.quimico = quimico;
		this.litrosUsados = litrosUsados;
		this.lote = lote;
		this.proceso = proceso;
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

}
