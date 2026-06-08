package com.dmjm.model;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.dao.IMateriaDao;
import com.dmjm.impl.MateriaDaoImpl;

public class Categorias implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idCategoria;
	private String categoriaSistema;
	private String subCategoria;
	private String nota;
	private Materia materia;

	public Categorias() {
	}

	public Categorias(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categorias(int idCategoria, Materia materia, String categoriaSistema, String subCategoria, String nota) {
		this.idCategoria = idCategoria;
		this.materia = materia;
		this.categoriaSistema = categoriaSistema;
		this.subCategoria = subCategoria;
		this.nota = nota;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoriaSistema() {
		return this.categoriaSistema;
	}

	public void setCategoriaSistema(String categoriaSistema) {
		this.categoriaSistema = categoriaSistema;
	}

	public String getSubCategoria() {
		return this.subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}
