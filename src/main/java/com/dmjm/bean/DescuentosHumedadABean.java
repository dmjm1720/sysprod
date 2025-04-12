package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IDescuentoHumedadADao;
import com.dmjm.impl.DescuentosHumedadADaoImpl;
import com.dmjm.model.DescuentoHumedadTablaA;

@Named(value = "descuentoHA")
@ViewScoped
public class DescuentosHumedadABean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DescuentoHumedadTablaA> listarDescuentosHumedadA;
	private DescuentoHumedadTablaA descuentosHumedadA;

	@PostConstruct
	public void init() {
		listarDescuentosHumedadA = new ArrayList<>();
		descuentosHumedadA = new DescuentoHumedadTablaA();

	}

	public DescuentoHumedadTablaA getDescuentosHumedadA() {
		return descuentosHumedadA;
	}

	public void setDescuentosHumedadA(DescuentoHumedadTablaA descuentosHumedadA) {
		this.descuentosHumedadA = descuentosHumedadA;
	}

	public List<DescuentoHumedadTablaA> listarDescuentosHumedadA() {
		IDescuentoHumedadADao eDao = new DescuentosHumedadADaoImpl();
		listarDescuentosHumedadA = eDao.listarDescuentos();
		return listarDescuentosHumedadA;
	}

	public void guardar() {
		IDescuentoHumedadADao eDao = new DescuentosHumedadADaoImpl();
		eDao.guardarHumedadA(descuentosHumedadA);
		descuentosHumedadA = new DescuentoHumedadTablaA();
	}

	public void actualizar() {
		IDescuentoHumedadADao eDao = new DescuentosHumedadADaoImpl();
		eDao.actualizarHumedadA(descuentosHumedadA);
		descuentosHumedadA = new DescuentoHumedadTablaA();
	}
	
	public void eliminar() {
		IDescuentoHumedadADao eDao = new DescuentosHumedadADaoImpl();
		eDao.eliminarHumedadA(descuentosHumedadA);
		descuentosHumedadA = new DescuentoHumedadTablaA();
	}

}
