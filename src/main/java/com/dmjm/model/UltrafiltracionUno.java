package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UltrafiltracionUno implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idUltrafiltracion;
	private FolioPreparacionUltraUno folioPreparacionUltraUno;
	private String hora;
	private Integer flujoEntrada;
	private BigDecimal temp01;
	private BigDecimal concEntrada;
	private BigDecimal presionPsi;
	private Integer flujoPerm;
	private BigDecimal concSalida;
	private Integer flujoSalida;
	private BigDecimal concPermeado;
	private String estadoA;
	private String estadoR;
	private Boolean estadoAR;
	private BigDecimal temp02;
	private Integer folioUltra;
	private Date fecha;
	private String operacion;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;

	public UltrafiltracionUno() {
	}

	public UltrafiltracionUno(int idUltrafiltracion) {
		this.idUltrafiltracion = idUltrafiltracion;
	}

	public UltrafiltracionUno(int idUltrafiltracion, FolioPreparacionUltraUno folioPreparacionUltraUno, String hora,
			Integer flujoEntrada, BigDecimal temp01, BigDecimal concEntrada, BigDecimal presionPsi, Integer flujoPerm,
			BigDecimal concSalida, Integer flujoSalida, BigDecimal concPermeado, String estadoA, String estadoR,
			Boolean estadoAR, BigDecimal temp02, Integer folioUltra, Date fecha, String operacion, Boolean estadoManto,
			Boolean estadoLimpieza) {
		this.idUltrafiltracion = idUltrafiltracion;
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
		this.hora = hora;
		this.flujoEntrada = flujoEntrada;
		this.temp01 = temp01;
		this.concEntrada = concEntrada;
		this.presionPsi = presionPsi;
		this.flujoPerm = flujoPerm;
		this.concSalida = concSalida;
		this.flujoSalida = flujoSalida;
		this.concPermeado = concPermeado;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.estadoAR = estadoAR;
		this.temp02 = temp02;
		this.folioUltra = folioUltra;
		this.fecha = fecha;
		this.operacion = operacion;
		this.folioUltra = folioUltra;
		this.fecha = fecha;
	}

	public int getIdUltrafiltracion() {
		return this.idUltrafiltracion;
	}

	public void setIdUltrafiltracion(int idUltrafiltracion) {
		this.idUltrafiltracion = idUltrafiltracion;
	}

	public FolioPreparacionUltraUno getFolioPreparacionUltraUno() {
		return this.folioPreparacionUltraUno;
	}

	public void setFolioPreparacionUltraUno(FolioPreparacionUltraUno folioPreparacionUltraUno) {
		this.folioPreparacionUltraUno = folioPreparacionUltraUno;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getFlujoEntrada() {
		return this.flujoEntrada;
	}

	public void setFlujoEntrada(Integer flujoEntrada) {
		this.flujoEntrada = flujoEntrada;
	}

	public BigDecimal getTemp01() {
		return this.temp01;
	}

	public void setTemp01(BigDecimal temp01) {
		this.temp01 = temp01;
	}

	public BigDecimal getConcEntrada() {
		return this.concEntrada;
	}

	public void setConcEntrada(BigDecimal concEntrada) {
		this.concEntrada = concEntrada;
	}

	public BigDecimal getPresionPsi() {
		return this.presionPsi;
	}

	public void setPresionPsi(BigDecimal presionPsi) {
		this.presionPsi = presionPsi;
	}

	public Integer getFlujoPerm() {
		return this.flujoPerm;
	}

	public void setFlujoPerm(Integer flujoPerm) {
		this.flujoPerm = flujoPerm;
	}

	public BigDecimal getConcSalida() {
		return this.concSalida;
	}

	public void setConcSalida(BigDecimal concSalida) {
		this.concSalida = concSalida;
	}

	public Integer getFlujoSalida() {
		return this.flujoSalida;
	}

	public void setFlujoSalida(Integer flujoSalida) {
		this.flujoSalida = flujoSalida;
	}

	public BigDecimal getConcPermeado() {
		return this.concPermeado;
	}

	public void setConcPermeado(BigDecimal concPermeado) {
		this.concPermeado = concPermeado;
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

	public Boolean getEstadoAR() {
		return this.estadoAR;
	}

	public void setEstadoAR(Boolean estadoAR) {
		this.estadoAR = estadoAR;
	}

	public BigDecimal getTemp02() {
		return this.temp02;
	}

	public void setTemp02(BigDecimal temp02) {
		this.temp02 = temp02;
	}

	public Integer getFolioUltra() {
		return this.folioUltra;
	}

	public void setFolioUltra(Integer folioUltra) {
		this.folioUltra = folioUltra;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Boolean getEstadoManto() {
		return estadoManto;
	}

	public void setEstadoManto(Boolean estadoManto) {
		this.estadoManto = estadoManto;
	}

	public Boolean getEstadoLimpieza() {
		return estadoLimpieza;
	}

	public void setEstadoLimpieza(Boolean estadoLimpieza) {
		this.estadoLimpieza = estadoLimpieza;
	}

}
