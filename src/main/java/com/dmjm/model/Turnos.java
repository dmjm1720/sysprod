package com.dmjm.model;

public class Turnos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idTurno;
	private String nombreTurno;

	public Turnos() {
	}

	public Turnos(int idTurno) {
		this.idTurno = idTurno;
	}

	public Turnos(int idTurno, String nombreTurno) {
		this.idTurno = idTurno;
		this.nombreTurno = nombreTurno;

	}

	public int getIdTurno() {
		return this.idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public String getNombreTurno() {
		return this.nombreTurno;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

}
