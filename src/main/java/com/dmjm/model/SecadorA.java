package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class SecadorA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idSecador;
	private FolioPreparacionSecadorA folioPreparacionSecadorA;
	private String hora;
	private String operacion;
	private BigDecimal limiteUno;
	private BigDecimal limiteDos;
	private BigDecimal limiteTres;
	private BigDecimal limiteCuatro;
	private BigDecimal limiteCinco;
	private BigDecimal limiteSeis;
	private BigDecimal limiteSiete;
	private BigDecimal limiteOcho;
	private BigDecimal limiteNueve;
	private BigDecimal vapor;
	private BigDecimal velTapete;
	private Integer folioSecador;
	private Date fecha;
	private Integer folioLm;
	private Integer folioLr;
	private Boolean estadoAR;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;
	private String estadoA;
	private String estadoR;

	public SecadorA() {
	}

	public SecadorA(int idSecador) {
		this.idSecador = idSecador;
	}

	public SecadorA(int idSecador, FolioPreparacionSecadorA folioPreparacionSecadorA, String hora, String operacion,
			BigDecimal limiteUno, BigDecimal limiteDos, BigDecimal limiteTres, BigDecimal limiteCuatro,
			BigDecimal limiteCinco, BigDecimal limiteSeis, BigDecimal limiteSiete, BigDecimal limiteOcho,
			BigDecimal limiteNueve, BigDecimal vapor, BigDecimal velTapete, Integer folioSecador, Date fecha,
			Integer folioLm, Integer folioLr, Boolean estadoAR, Boolean estadoManto, Boolean estadoLimpieza,
			String estadoA, String estadoR) {
		this.idSecador = idSecador;
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
		this.hora = hora;
		this.operacion = operacion;
		this.limiteUno = limiteUno;
		this.limiteDos = limiteDos;
		this.limiteTres = limiteTres;
		this.limiteCuatro = limiteCuatro;
		this.limiteCinco = limiteCinco;
		this.limiteSeis = limiteSeis;
		this.limiteSiete = limiteSiete;
		this.limiteOcho = limiteOcho;
		this.limiteNueve = limiteNueve;
		this.vapor = vapor;
		this.velTapete = velTapete;
		this.folioSecador = folioSecador;
		this.fecha = fecha;
		this.folioLm = folioLm;
		this.folioLr = folioLr;
		this.estadoAR = estadoAR;
		this.estadoManto = estadoManto;
		this.estadoLimpieza = estadoLimpieza;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
	}

	public int getIdSecador() {
		return this.idSecador;
	}

	public void setIdSecador(int idSecador) {
		this.idSecador = idSecador;
	}

	public FolioPreparacionSecadorA getFolioPreparacionSecadorA() {
		return this.folioPreparacionSecadorA;
	}

	public void setFolioPreparacionSecadorA(FolioPreparacionSecadorA folioPreparacionSecadorA) {
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
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

	public BigDecimal getLimiteUno() {
		return this.limiteUno;
	}

	public void setLimiteUno(BigDecimal limiteUno) {
		this.limiteUno = limiteUno;
	}

	public BigDecimal getLimiteDos() {
		return this.limiteDos;
	}

	public void setLimiteDos(BigDecimal limiteDos) {
		this.limiteDos = limiteDos;
	}

	public BigDecimal getLimiteTres() {
		return this.limiteTres;
	}

	public void setLimiteTres(BigDecimal limiteTres) {
		this.limiteTres = limiteTres;
	}

	public BigDecimal getLimiteCuatro() {
		return this.limiteCuatro;
	}

	public void setLimiteCuatro(BigDecimal limiteCuatro) {
		this.limiteCuatro = limiteCuatro;
	}

	public BigDecimal getLimiteCinco() {
		return this.limiteCinco;
	}

	public void setLimiteCinco(BigDecimal limiteCinco) {
		this.limiteCinco = limiteCinco;
	}

	public BigDecimal getLimiteSeis() {
		return this.limiteSeis;
	}

	public void setLimiteSeis(BigDecimal limiteSeis) {
		this.limiteSeis = limiteSeis;
	}

	public BigDecimal getLimiteSiete() {
		return this.limiteSiete;
	}

	public void setLimiteSiete(BigDecimal limiteSiete) {
		this.limiteSiete = limiteSiete;
	}

	public BigDecimal getLimiteOcho() {
		return this.limiteOcho;
	}

	public void setLimiteOcho(BigDecimal limiteOcho) {
		this.limiteOcho = limiteOcho;
	}

	public BigDecimal getLimiteNueve() {
		return this.limiteNueve;
	}

	public void setLimiteNueve(BigDecimal limiteNueve) {
		this.limiteNueve = limiteNueve;
	}

	public BigDecimal getVapor() {
		return vapor;
	}

	public void setVapor(BigDecimal vapor) {
		this.vapor = vapor;
	}

	public BigDecimal getVelTapete() {
		return velTapete;
	}

	public void setVelTapete(BigDecimal velTapete) {
		this.velTapete = velTapete;
	}

	public Integer getFolioSecador() {
		return this.folioSecador;
	}

	public void setFolioSecador(Integer folioSecador) {
		this.folioSecador = folioSecador;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFolioLm() {
		return this.folioLm;
	}

	public void setFolioLm(Integer folioLm) {
		this.folioLm = folioLm;
	}

	public Integer getFolioLr() {
		return this.folioLr;
	}

	public void setFolioLr(Integer folioLr) {
		this.folioLr = folioLr;
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

	public String getEstadoA() {
		return estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoR() {
		return estadoR;
	}

	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}

}
