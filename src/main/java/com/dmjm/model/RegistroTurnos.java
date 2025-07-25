package com.dmjm.model;

import java.io.Serializable;
import java.util.Date;

public class RegistroTurnos implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idRegistroTurno;
	private Usuarios usuarios;
	private Operador operador;
	private Turnos turnos;
	private Integer folio;
	private String descProceso;
	private Date fecha;

	public RegistroTurnos() {
	}

	public RegistroTurnos(int idRegistroTurno) {
		this.idRegistroTurno = idRegistroTurno;
	}

	public RegistroTurnos(int idRegistroTurno, Usuarios usuarios, Operador operador, Turnos turnos, Integer folio,
			String descProceso, Date fecha) {
		this.idRegistroTurno = idRegistroTurno;
		this.usuarios = usuarios;
		this.operador = operador;
		this.turnos = turnos;
		this.folio = folio;
		this.descProceso = descProceso;
		this.fecha = fecha;
	}

	public int getIdRegistroTurno() {
		return this.idRegistroTurno;
	}

	public void setIdRegistroTurno(int idRegistroTurno) {
		this.idRegistroTurno = idRegistroTurno;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Turnos getTurnos() {
		return this.turnos;
	}

	public void setTurnos(Turnos turnos) {
		this.turnos = turnos;
	}

	public Integer getFolio() {
		return this.folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getDescProceso() {
		return this.descProceso;
	}

	public void setDescProceso(String descProceso) {
		this.descProceso = descProceso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
