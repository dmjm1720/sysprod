package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IEntradasImportacionDao;
import com.dmjm.impl.EntradasImportacionDaoImpl;
import com.dmjm.model.CuentasContables;
import com.dmjm.model.EntradasImportacion;
import com.dmjm.model.Materia;
import com.dmjm.model.ProveedoresImportacion;

@Named(value = "entImportBean")
@ViewScoped
public class EntradasImportacionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EntradasImportacion> listaEntradasImp;
	private EntradasImportacion entradasImportacion;
	private ProveedoresImportacion proveedoresImportacion;
	private Materia materia;
	private CuentasContables cuentasContables;

	@PostConstruct
	public void init() {
		listaEntradasImp = new ArrayList<>();
		entradasImportacion = new EntradasImportacion();
		proveedoresImportacion = new ProveedoresImportacion();
		materia = new Materia();
		cuentasContables = new CuentasContables();
	}

	public void setEntradasImportacion(EntradasImportacion entradasImportacion) {
		this.entradasImportacion = entradasImportacion;
	}

	public EntradasImportacion getEntradasImportacion() {

		return entradasImportacion;
	}

	public ProveedoresImportacion getProveedoresImportacion() {
		return proveedoresImportacion;
	}

	public void setProveedoresImportacion(ProveedoresImportacion proveedoresImportacion) {
		this.proveedoresImportacion = proveedoresImportacion;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CuentasContables getCuentasContables() {
		return cuentasContables;
	}

	public void setCuentasContables(CuentasContables cuentasContables) {
		this.cuentasContables = cuentasContables;
	}

	public List<EntradasImportacion> getListaEntradasImp() {

		IEntradasImportacionDao eDao = new EntradasImportacionDaoImpl();
		listaEntradasImp = eDao.listarEntradas();
		return listaEntradasImp;
	}

}
