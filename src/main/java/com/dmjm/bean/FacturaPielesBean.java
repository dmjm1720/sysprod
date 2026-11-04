package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.impl.FacturaPielesDaoImpl;
import com.dmjm.model.FacturasPieles;

@Named("factPieles")
@ViewScoped
public class FacturaPielesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FacturasPieles> lista;

	public FacturaPielesBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		lista = new ArrayList<>();
		listar();
	}

	public List<FacturasPieles> getLista() {
		return lista;
	}

	public List<FacturasPieles> listar() {
		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
		lista = fDao.listaFacturaPieles();
		return lista;
	}
}
