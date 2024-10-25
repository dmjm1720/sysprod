package com.dmjm.model;

import java.util.Date;

public class Usuarios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private Perfiles perfiles;
	private String nombre;
	private String correo;
	private Integer estatus;
	private Date fechaAlta;
	private String password;
	private String iniciales;
	private String usuario;

	public Usuarios() {
	}

	public Usuarios(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuarios(int idUsuario, Perfiles perfiles, String nombre, String correo, Integer estatus, Date fechaAlta,
			String password, String iniciales, String usuario) {
		this.idUsuario = idUsuario;
		this.perfiles = perfiles;
		this.nombre = nombre;
		this.correo = correo;
		this.estatus = estatus;
		this.fechaAlta = fechaAlta;
		this.password = password;
		this.iniciales = iniciales;
		this.usuario = usuario;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Perfiles getPerfiles() {
		return this.perfiles;
	}

	public void setPerfiles(Perfiles perfiles) {
		this.perfiles = perfiles;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIniciales() {
		return this.iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
