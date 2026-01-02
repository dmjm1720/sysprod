package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class DafDos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idDaf;
	private FolioPreparacionDafDos folioPreparacionDafDos;
	private String hora;
	private String operacion;
	private BigDecimal concPorcentaje;
	private BigDecimal flujoGrenetina;
	private BigDecimal ph;
	private Integer ntuEntrada;
	private Integer ntuSalida;
	private BigDecimal presionAire;
	private BigDecimal frecBombaFlolucolante;
	private BigDecimal valvulaRegPresion;
	private String estadoA;
	private String estadoR;
	private Integer folioDaf;
	private Date fecha;
	private Boolean estadoAR;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;

	public DafDos() {
	}

	public DafDos(int idDaf) {
		this.idDaf = idDaf;
	}

	public DafDos(int idDaf, FolioPreparacionDafDos folioPreparacionDafDos, String hora, String operacion,
			BigDecimal concPorcentaje, BigDecimal flujoGrenetina, BigDecimal ph, Integer ntuEntrada, Integer ntuSalida,
			BigDecimal presionAire, BigDecimal frecBombaFlolucolante, BigDecimal valvulaRegPresion, String estadoA,
			String estadoR, Integer folioDaf, Date fecha, Boolean estadoAR, Boolean estadoManto,
			Boolean estadoLimpieza) {
		this.idDaf = idDaf;
		this.folioPreparacionDafDos = folioPreparacionDafDos;
		this.hora = hora;
		this.operacion = operacion;
		this.concPorcentaje = concPorcentaje;
		this.flujoGrenetina = flujoGrenetina;
		this.ph = ph;
		this.ntuEntrada = ntuEntrada;
		this.ntuSalida = ntuSalida;
		this.presionAire = presionAire;
		this.frecBombaFlolucolante = frecBombaFlolucolante;
		this.valvulaRegPresion = valvulaRegPresion;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.folioDaf = folioDaf;
		this.fecha = fecha;
		this.estadoAR = estadoAR;
		this.estadoManto = estadoManto;
		this.estadoLimpieza = estadoLimpieza;
	}

	public int getIdDaf() {
		return this.idDaf;
	}

	public void setIdDaf(int idDaf) {
		this.idDaf = idDaf;
	}

	public FolioPreparacionDafDos getFolioPreparacionDafDos() {
		return this.folioPreparacionDafDos;
	}

	public void setFolioPreparacionDafDos(FolioPreparacionDafDos folioPreparacionDafDos) {
		this.folioPreparacionDafDos = folioPreparacionDafDos;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getConcPorcentaje() {
		return this.concPorcentaje;
	}

	public void setConcPorcentaje(BigDecimal concPorcentaje) {
		this.concPorcentaje = concPorcentaje;
	}

	public BigDecimal getFlujoGrenetina() {
		return this.flujoGrenetina;
	}

	public void setFlujoGrenetina(BigDecimal flujoGrenetina) {
		this.flujoGrenetina = flujoGrenetina;
	}

	public BigDecimal getPh() {
		return this.ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public Integer getNtuEntrada() {
		return this.ntuEntrada;
	}

	public void setNtuEntrada(Integer ntuEntrada) {
		this.ntuEntrada = ntuEntrada;
	}

	public Integer getNtuSalida() {
		return this.ntuSalida;
	}

	public void setNtuSalida(Integer ntuSalida) {
		this.ntuSalida = ntuSalida;
	}

	public BigDecimal getPresionAire() {
		return this.presionAire;
	}

	public void setPresionAire(BigDecimal presionAire) {
		this.presionAire = presionAire;
	}

	public BigDecimal getFrecBombaFlolucolante() {
		return this.frecBombaFlolucolante;
	}

	public void setFrecBombaFlolucolante(BigDecimal frecBombaFlolucolante) {
		this.frecBombaFlolucolante = frecBombaFlolucolante;
	}

	public BigDecimal getValvulaRegPresion() {
		return this.valvulaRegPresion;
	}

	public void setValvulaRegPresion(BigDecimal valvulaRegPresion) {
		this.valvulaRegPresion = valvulaRegPresion;
	}

	public String getEstadoA() {
		return this.estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoR() {
		return this.estadoR;
	}

	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}

	public Integer getFolioDaf() {
		return this.folioDaf;
	}

	public void setFolioDaf(Integer folioDaf) {
		this.folioDaf = folioDaf;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstadoAR() {
		return this.estadoAR;
	}

	public void setEstadoAR(Boolean estadoAR) {
		this.estadoAR = estadoAR;
	}

	public Boolean getEstadoManto() {
		return this.estadoManto;
	}

	public void setEstadoManto(Boolean estadoManto) {
		this.estadoManto = estadoManto;
	}

	public Boolean getEstadoLimpieza() {
		return this.estadoLimpieza;
	}

	public void setEstadoLimpieza(Boolean estadoLimpieza) {
		this.estadoLimpieza = estadoLimpieza;
	}

}
