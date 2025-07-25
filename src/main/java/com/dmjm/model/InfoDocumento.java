package com.dmjm.model;

import java.io.Serializable;

public class InfoDocumento implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idDocumeno;
	private String rutaImagenFirma;
	private String notaUno;
	private String notaDos;
	private String notaTres;
	private String nombreGerenciaProduccion;

	public InfoDocumento() {
	}

	public InfoDocumento(int idDocumeno) {
		this.idDocumeno = idDocumeno;
	}

	public InfoDocumento(int idDocumeno, String rutaImagenFirma, String notaUno, String notaDos, String notaTres,
			String nombreGerenciaProduccion) {
		this.idDocumeno = idDocumeno;
		this.rutaImagenFirma = rutaImagenFirma;
		this.notaUno = notaUno;
		this.notaDos = notaDos;
		this.notaTres = notaTres;
		this.nombreGerenciaProduccion = nombreGerenciaProduccion;
	}

	public int getIdDocumeno() {
		return this.idDocumeno;
	}

	public void setIdDocumeno(int idDocumeno) {
		this.idDocumeno = idDocumeno;
	}

	public String getRutaImagenFirma() {
		return this.rutaImagenFirma;
	}

	public void setRutaImagenFirma(String rutaImagenFirma) {
		this.rutaImagenFirma = rutaImagenFirma;
	}

	public String getNotaUno() {
		return this.notaUno;
	}

	public void setNotaUno(String notaUno) {
		this.notaUno = notaUno;
	}

	public String getNotaDos() {
		return this.notaDos;
	}

	public void setNotaDos(String notaDos) {
		this.notaDos = notaDos;
	}

	public String getNotaTres() {
		return this.notaTres;
	}

	public void setNotaTres(String notaTres) {
		this.notaTres = notaTres;
	}

	public String getNombreGerenciaProduccion() {
		return this.nombreGerenciaProduccion;
	}

	public void setNombreGerenciaProduccion(String nombreGerenciaProduccion) {
		this.nombreGerenciaProduccion = nombreGerenciaProduccion;
	}

}
