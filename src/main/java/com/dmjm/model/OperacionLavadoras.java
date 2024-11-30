package com.dmjm.model;

public class OperacionLavadoras implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idOperacionLavadoras;
	private Lavadoras lavadoras;
	private PreparacionPieles preparacionPieles;

	public OperacionLavadoras() {
	}

	public OperacionLavadoras(int idOperacionLavadoras) {
		this.idOperacionLavadoras = idOperacionLavadoras;
	}

	public OperacionLavadoras(int idOperacionLavadoras, Lavadoras lavadoras, PreparacionPieles preparacionPieles) {
		this.idOperacionLavadoras = idOperacionLavadoras;
		this.lavadoras = lavadoras;
		this.preparacionPieles = preparacionPieles;
	}

	public int getIdOperacionLavadoras() {
		return this.idOperacionLavadoras;
	}

	public void setIdOperacionLavadoras(int idOperacionLavadoras) {
		this.idOperacionLavadoras = idOperacionLavadoras;
	}

	public Lavadoras getLavadoras() {
		return this.lavadoras;
	}

	public void setLavadoras(Lavadoras lavadoras) {
		this.lavadoras = lavadoras;
	}

	public PreparacionPieles getPreparacionPieles() {
		return this.preparacionPieles;
	}

	public void setPreparacionPieles(PreparacionPieles preparacionPieles) {
		this.preparacionPieles = preparacionPieles;
	}

}
