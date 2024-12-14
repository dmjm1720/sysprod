package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.ICuentasContablesDao;
import com.dmjm.impl.CuentasContablesDaoImpl;
import com.dmjm.model.CuentasContables;

@Named(value = "cuentasBean")
@ViewScoped
public class CuentasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CuentasContables> listaCuentas;
	private CuentasContables cuentasContables;

	@PostConstruct
	public void init() {
		listaCuentas = new ArrayList<>();
		cuentasContables = new CuentasContables();
	}

	public CuentasContables getCuentasContables() {
		return cuentasContables;
	}

	public void setCuentasContables(CuentasContables cuentasContables) {
		this.cuentasContables = cuentasContables;
	}

	public List<CuentasContables> getListaCuentas() {
		ICuentasContablesDao iDao = new CuentasContablesDaoImpl();
		listaCuentas = iDao.listaCuentasContables();
		return listaCuentas;
	}

	public void guardarCuentas() {
		ICuentasContablesDao iDao = new CuentasContablesDaoImpl();
		iDao.guardarCuentasContables(cuentasContables);
		cuentasContables = new CuentasContables();
	}
	
	public void actualizarCuentas() {
		ICuentasContablesDao iDao = new CuentasContablesDaoImpl();
		iDao.actualizarCuentasContables(cuentasContables);
		cuentasContables = new CuentasContables();
	}

}
