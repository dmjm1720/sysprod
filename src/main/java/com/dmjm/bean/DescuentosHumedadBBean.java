package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IDescuentoHumedadBDao;
import com.dmjm.impl.DescuentosHumedadBDaoImpl;
import com.dmjm.model.DescuentoHumedadTablaB;

@Named(value = "descuentoHB")
@ViewScoped
public class DescuentosHumedadBBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DescuentoHumedadTablaB> listarDescuentosHumedadB;
	private DescuentoHumedadTablaB descuentosHumedadB;

	@PostConstruct
	public void init() {
		listarDescuentosHumedadB = new ArrayList<>();
		descuentosHumedadB = new DescuentoHumedadTablaB();

	}

	public DescuentoHumedadTablaB getDescuentosHumedadB() {
		return descuentosHumedadB;
	}

	public void setDescuentosHumedadB(DescuentoHumedadTablaB descuentosHumedadB) {
		this.descuentosHumedadB = descuentosHumedadB;
	}

	public List<DescuentoHumedadTablaB> listarDescuentosHumedadB() {
		IDescuentoHumedadBDao eDao = new DescuentosHumedadBDaoImpl();
		listarDescuentosHumedadB = eDao.listarDescuentos();
		return listarDescuentosHumedadB;
	}

	public void guardar() {
		IDescuentoHumedadBDao eDao = new DescuentosHumedadBDaoImpl();
		eDao.guardarHumedadB(descuentosHumedadB);
		descuentosHumedadB = new DescuentoHumedadTablaB();
	}

	public void actualizar() {
		IDescuentoHumedadBDao eDao = new DescuentosHumedadBDaoImpl();
		eDao.actualizarHumedadB(descuentosHumedadB);
		descuentosHumedadB = new DescuentoHumedadTablaB();
	}
	
	public void eliminar() {
		IDescuentoHumedadBDao eDao = new DescuentosHumedadBDaoImpl();
		eDao.eliminarHumedadB(descuentosHumedadB);
		descuentosHumedadB = new DescuentoHumedadTablaB();
	}

}
