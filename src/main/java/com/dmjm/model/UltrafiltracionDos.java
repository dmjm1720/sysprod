package com.dmjm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UltrafiltracionDos implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idUltrafiltracion;
	private FolioPreparacionUltraDos folioPreparacionUltraDos;
	private String hora;
	private String operacion;
	private BigDecimal presionEntrada;
	private BigDecimal presionSalida;
	private Integer flujoEnt;
	private BigDecimal temp01;
	private BigDecimal concEntrada;
	private BigDecimal ph;
	private BigDecimal presionBar;
	private BigDecimal porcentajeBomba;
	private BigDecimal temp02;
	private BigDecimal presionPsi01;
	private Integer flujoPerm01;
	private BigDecimal temp03;
	private BigDecimal presionPsi02;
	private Integer flujoPerm02;
	private BigDecimal temp04;
	private BigDecimal presionPsi03;
	private Integer flujoPerm03;
	private BigDecimal temp05;
	private BigDecimal presionPsi04;
	private Integer flujoPerm04;
	private BigDecimal concSalida;
	private Integer ratio;
	private Integer flujoSalida;
	private BigDecimal concPermeado;
	private String estadoA;
	private String estadoR;
	private Boolean estadoAR;
	private Integer folioUltra;
	private Date fecha;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;


	public UltrafiltracionDos() {
	}

	public UltrafiltracionDos(int idUltrafiltracion) {
		this.idUltrafiltracion = idUltrafiltracion;
	}

	public UltrafiltracionDos(int idUltrafiltracion, FolioPreparacionUltraDos folioPreparacionUltraDos, String hora,
			String operacion, BigDecimal presionEntrada, BigDecimal presionSalida, Integer flujoEnt, BigDecimal temp01,
			BigDecimal concEntrada, BigDecimal ph, BigDecimal presionBar, BigDecimal porcentajeBomba, BigDecimal temp02,
			BigDecimal presionPsi01, Integer flujoPerm01, BigDecimal temp03, BigDecimal presionPsi02,
			Integer flujoPerm02, BigDecimal temp04, BigDecimal presionPsi03, Integer flujoPerm03, BigDecimal temp05,
			BigDecimal presionPsi04, Integer flujoPerm04, BigDecimal concSalida, Integer ratio, Integer flujoSalida,
			BigDecimal concPermeado, String estadoA, String estadoR, Boolean estadoAR, Integer folioUltra, Date fecha,Boolean estadoManto,
			Boolean estadoLimpieza) {
		this.idUltrafiltracion = idUltrafiltracion;
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
		this.hora = hora;
		this.operacion = operacion;
		this.presionEntrada = presionEntrada;
		this.presionSalida = presionSalida;
		this.flujoEnt = flujoEnt;
		this.temp01 = temp01;
		this.concEntrada = concEntrada;
		this.ph = ph;
		this.presionBar = presionBar;
		this.porcentajeBomba = porcentajeBomba;
		this.temp02 = temp02;
		this.presionPsi01 = presionPsi01;
		this.flujoPerm01 = flujoPerm01;
		this.temp03 = temp03;
		this.presionPsi02 = presionPsi02;
		this.flujoPerm02 = flujoPerm02;
		this.temp04 = temp04;
		this.presionPsi03 = presionPsi03;
		this.flujoPerm03 = flujoPerm03;
		this.temp05 = temp05;
		this.presionPsi04 = presionPsi04;
		this.flujoPerm04 = flujoPerm04;
		this.concSalida = concSalida;
		this.ratio = ratio;
		this.flujoSalida = flujoSalida;
		this.concPermeado = concPermeado;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.estadoAR = estadoAR;
		this.folioUltra = folioUltra;
		this.fecha = fecha;
	}

	public int getIdUltrafiltracion() {
		return this.idUltrafiltracion;
	}

	public void setIdUltrafiltracion(int idUltrafiltracion) {
		this.idUltrafiltracion = idUltrafiltracion;
	}

	public FolioPreparacionUltraDos getFolioPreparacionUltraDos() {
		return this.folioPreparacionUltraDos;
	}

	public void setFolioPreparacionUltraDos(FolioPreparacionUltraDos folioPreparacionUltraDos) {
		this.folioPreparacionUltraDos = folioPreparacionUltraDos;
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

	public BigDecimal getPresionEntrada() {
		return this.presionEntrada;
	}

	public void setPresionEntrada(BigDecimal presionEntrada) {
		this.presionEntrada = presionEntrada;
	}

	public BigDecimal getPresionSalida() {
		return this.presionSalida;
	}

	public void setPresionSalida(BigDecimal presionSalida) {
		this.presionSalida = presionSalida;
	}

	public Integer getFlujoEnt() {
		return this.flujoEnt;
	}

	public void setFlujoEnt(Integer flujoEnt) {
		this.flujoEnt = flujoEnt;
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

	public BigDecimal getPh() {
		return this.ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public BigDecimal getPresionBar() {
		return this.presionBar;
	}

	public void setPresionBar(BigDecimal presionBar) {
		this.presionBar = presionBar;
	}

	public BigDecimal getPorcentajeBomba() {
		return this.porcentajeBomba;
	}

	public void setPorcentajeBomba(BigDecimal porcentajeBomba) {
		this.porcentajeBomba = porcentajeBomba;
	}

	public BigDecimal getTemp02() {
		return this.temp02;
	}

	public void setTemp02(BigDecimal temp02) {
		this.temp02 = temp02;
	}

	public BigDecimal getPresionPsi01() {
		return this.presionPsi01;
	}

	public void setPresionPsi01(BigDecimal presionPsi01) {
		this.presionPsi01 = presionPsi01;
	}

	public Integer getFlujoPerm01() {
		return this.flujoPerm01;
	}

	public void setFlujoPerm01(Integer flujoPerm01) {
		this.flujoPerm01 = flujoPerm01;
	}

	public BigDecimal getTemp03() {
		return this.temp03;
	}

	public void setTemp03(BigDecimal temp03) {
		this.temp03 = temp03;
	}

	public BigDecimal getPresionPsi02() {
		return this.presionPsi02;
	}

	public void setPresionPsi02(BigDecimal presionPsi02) {
		this.presionPsi02 = presionPsi02;
	}

	public Integer getFlujoPerm02() {
		return this.flujoPerm02;
	}

	public void setFlujoPerm02(Integer flujoPerm02) {
		this.flujoPerm02 = flujoPerm02;
	}

	public BigDecimal getTemp04() {
		return this.temp04;
	}

	public void setTemp04(BigDecimal temp04) {
		this.temp04 = temp04;
	}

	public BigDecimal getPresionPsi03() {
		return this.presionPsi03;
	}

	public void setPresionPsi03(BigDecimal presionPsi03) {
		this.presionPsi03 = presionPsi03;
	}

	public Integer getFlujoPerm03() {
		return this.flujoPerm03;
	}

	public void setFlujoPerm03(Integer flujoPerm03) {
		this.flujoPerm03 = flujoPerm03;
	}

	public BigDecimal getTemp05() {
		return this.temp05;
	}

	public void setTemp05(BigDecimal temp05) {
		this.temp05 = temp05;
	}

	public BigDecimal getPresionPsi04() {
		return this.presionPsi04;
	}

	public void setPresionPsi04(BigDecimal presionPsi04) {
		this.presionPsi04 = presionPsi04;
	}

	public Integer getFlujoPerm04() {
		return this.flujoPerm04;
	}

	public void setFlujoPerm04(Integer flujoPerm04) {
		this.flujoPerm04 = flujoPerm04;
	}

	public BigDecimal getConcSalida() {
		return this.concSalida;
	}

	public void setConcSalida(BigDecimal concSalida) {
		this.concSalida = concSalida;
	}

	public Integer getRatio() {
		return this.ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
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
