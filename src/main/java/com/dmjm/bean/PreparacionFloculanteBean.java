package com.dmjm.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IDafUnoPrepFlolucolanteDao;
import com.dmjm.impl.DafUnoPrepFlolucolanteDaoImpl;
import com.dmjm.model.DafUnoPrepFlolucolante;

@Named("floculanteBean")
@ViewScoped
public class PreparacionFloculanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DafUnoPrepFlolucolante> listaPreparacionFlolucolante;
	private DafUnoPrepFlolucolante dafUnoPrep;
	private DafUnoPrepFlolucolante dafUnoPrepEditar;
	private String bandera;

	public PreparacionFloculanteBean() {

	}

	@PostConstruct
	public void init() {
		listaPreparacionFlolucolante = new ArrayList<>();
		dafUnoPrep = new DafUnoPrepFlolucolante();
		dafUnoPrepEditar = new DafUnoPrepFlolucolante();
	}

	public DafUnoPrepFlolucolante getDafUnoPrep() {
		return dafUnoPrep;
	}

	public void setDafUnoPrep(DafUnoPrepFlolucolante dafUnoPrep) {
		this.dafUnoPrep = dafUnoPrep;
	}

	public DafUnoPrepFlolucolante getDafUnoPrepEditar() {
		return dafUnoPrepEditar;
	}

	public void setDafUnoPrepEditar(DafUnoPrepFlolucolante dafUnoPrepEditar) {
		this.dafUnoPrepEditar = dafUnoPrepEditar;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	public List<DafUnoPrepFlolucolante> getListaPreparacionFlolucolante() {
		IDafUnoPrepFlolucolanteDao pDao = new DafUnoPrepFlolucolanteDaoImpl();
		listaPreparacionFlolucolante = pDao.listaDafUnoPrepFlolucolante();
		return listaPreparacionFlolucolante;
	}

	public void guardarDafPrepFloculante() {
		IDafUnoPrepFlolucolanteDao fDao = new DafUnoPrepFlolucolanteDaoImpl();
		int folio = folioMax();
		dafUnoPrep.setFolioDaf(folio);
		fDao.guardarDafUnoPrepFlolucolante(dafUnoPrep);
		IDafUnoPrepFlolucolanteDao fol = new DafUnoPrepFlolucolanteDaoImpl();
		int year = LocalDate.now().getYear();
		fol.actualizarFolio(year, folio);
		dafUnoPrep = new DafUnoPrepFlolucolante();
		bandera="";
	}

	public void actualizarDafPrepFloculante() {
		IDafUnoPrepFlolucolanteDao fDao = new DafUnoPrepFlolucolanteDaoImpl();
		fDao.actualizarDafUnoPrepFlolucolante(dafUnoPrep);
		dafUnoPrep = new DafUnoPrepFlolucolante();
		bandera="";
	}

	public int folioMax() {
		IDafUnoPrepFlolucolanteDao fDao = new DafUnoPrepFlolucolanteDaoImpl();
		int year = LocalDate.now().getYear();
		return fDao.buscarFolio(year);
	}

	public void dafPrep(String dato) {
		this.bandera = dato;
	}

}
