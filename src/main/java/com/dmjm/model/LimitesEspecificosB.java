package com.dmjm.model;

import java.util.Date;

public class LimitesEspecificosB implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLimite;
	private Integer folioLm;
	private Date fecha;
	private String limiteUno;
	private String limiteDos;
	private String limiteTres;
	private String limiteCuatro;
	private String limiteCinco;
	private String limiteSeis;
	private String limiteSiete;
	private String limiteOcho;
	private String limiteNueve;

	public LimitesEspecificosB() {
	}

	public LimitesEspecificosB(int idLimite) {
		this.idLimite = idLimite;
	}

	public LimitesEspecificosB(int idLimite, Integer folioLm, Date fecha, String limiteUno, String limiteDos,
			String limiteTres, String limiteCuatro, String limiteCinco, String limiteSeis, String limiteSiete,
			String limiteOcho, String limiteNueve) {
		this.idLimite = idLimite;
		this.folioLm = folioLm;
		this.fecha = fecha;
		this.limiteUno = limiteUno;
		this.limiteDos = limiteDos;
		this.limiteTres = limiteTres;
		this.limiteCuatro = limiteCuatro;
		this.limiteCinco = limiteCinco;
		this.limiteSeis = limiteSeis;
		this.limiteSiete = limiteSiete;
		this.limiteOcho = limiteOcho;
		this.limiteNueve = limiteNueve;
	}

	public int getIdLimite() {
		return this.idLimite;
	}

	public void setIdLimite(int idLimite) {
		this.idLimite = idLimite;
	}

	public Integer getFolioLm() {
		return this.folioLm;
	}

	public void setFolioLm(Integer folioLm) {
		this.folioLm = folioLm;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLimiteUno() {
		return this.limiteUno;
	}

	public void setLimiteUno(String limiteUno) {
		this.limiteUno = limiteUno;
	}

	public String getLimiteDos() {
		return this.limiteDos;
	}

	public void setLimiteDos(String limiteDos) {
		this.limiteDos = limiteDos;
	}

	public String getLimiteTres() {
		return this.limiteTres;
	}

	public void setLimiteTres(String limiteTres) {
		this.limiteTres = limiteTres;
	}

	public String getLimiteCuatro() {
		return this.limiteCuatro;
	}

	public void setLimiteCuatro(String limiteCuatro) {
		this.limiteCuatro = limiteCuatro;
	}

	public String getLimiteCinco() {
		return this.limiteCinco;
	}

	public void setLimiteCinco(String limiteCinco) {
		this.limiteCinco = limiteCinco;
	}

	public String getLimiteSeis() {
		return this.limiteSeis;
	}

	public void setLimiteSeis(String limiteSeis) {
		this.limiteSeis = limiteSeis;
	}

	public String getLimiteSiete() {
		return this.limiteSiete;
	}

	public void setLimiteSiete(String limiteSiete) {
		this.limiteSiete = limiteSiete;
	}

	public String getLimiteOcho() {
		return this.limiteOcho;
	}

	public void setLimiteOcho(String limiteOcho) {
		this.limiteOcho = limiteOcho;
	}

	public String getLimiteNueve() {
		return this.limiteNueve;
	}

	public void setLimiteNueve(String limiteNueve) {
		this.limiteNueve = limiteNueve;
	}

}
