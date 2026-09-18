package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.ILavadorasEstatusOperacionDao;
import com.dmjm.impl.LavadorasEstatusOperacionDaoImpl;
import com.dmjm.model.LavadorasEstatusOperacion;

@Named("lavBean")
@ViewScoped
public class EstatusLavadoraOperacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LavadorasEstatusOperacion> listarLavadoras;
	private LavadorasEstatusOperacion estatusOperacion;

	public EstatusLavadoraOperacion() {

	}

	@PostConstruct
	public void init() {
		listarLavadoras = new ArrayList<>();
		getListarLavadoras();

		estatusOperacion = new LavadorasEstatusOperacion();
	}

	public List<LavadorasEstatusOperacion> getListarLavadoras() {
		ILavadorasEstatusOperacionDao lDao = new LavadorasEstatusOperacionDaoImpl();
		listarLavadoras = lDao.listarLavadorasEstatusOperacion();
		return listarLavadoras;
	}

	public LavadorasEstatusOperacion getEstatusOperacion() {
		return estatusOperacion;
	}

	public void setEstatusOperacion(LavadorasEstatusOperacion estatusOperacion) {
		this.estatusOperacion = estatusOperacion;
	}

}
