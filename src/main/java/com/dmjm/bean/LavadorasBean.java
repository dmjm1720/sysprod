package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.ILavadorasDao;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.model.Lavadoras;

@Named(value = "lavadorasBean")
@ViewScoped
public class LavadorasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Lavadoras> listarLavadoras;
	private Lavadoras lavadoras;

	@PostConstruct
	public void init() {
		listarLavadoras = new ArrayList<>();
		lavadoras = new Lavadoras();
	}

	public Lavadoras getLavadoras() {
		return lavadoras;
	}

	public void setLavadoras(Lavadoras lavadoras) {
		this.lavadoras = lavadoras;
	}

	public List<Lavadoras> getListarLavadoras() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		listarLavadoras = lDao.listaLavadoras();
		return listarLavadoras;
	}

	public void guardar() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.guardarLavadoras(lavadoras);
		lavadoras = new Lavadoras();
	}
	
	public void actualizar() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.actualizarLavadoras(lavadoras);
		lavadoras = new Lavadoras();
	}

}
