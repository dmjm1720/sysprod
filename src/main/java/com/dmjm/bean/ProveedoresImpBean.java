package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IProveedoresImportacionDao;
import com.dmjm.impl.ProveedoresImportacionDaoImpl;
import com.dmjm.model.ProveedoresImportacion;

@Named(value = "provImpBean")
@ViewScoped
public class ProveedoresImpBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProveedoresImportacion> listaProveedores;

	private ProveedoresImportacion proveedoresImportacion;

	@PostConstruct
	public void init() {
		listaProveedores = new ArrayList<>();
		proveedoresImportacion = new ProveedoresImportacion();
	}

	public ProveedoresImportacion getProveedoresImportacion() {
		return proveedoresImportacion;
	}

	public void setProveedoresImportacion(ProveedoresImportacion proveedoresImportacion) {
		this.proveedoresImportacion = proveedoresImportacion;
	}

	public List<ProveedoresImportacion> getListaProveedores() {

		IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
		listaProveedores = pDao.listaProvImp();
		return listaProveedores;
	}

	public void guardarProvImp() {
		IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
		proveedoresImportacion.setEstado(1);
		pDao.guardarProvImp(proveedoresImportacion);
		proveedoresImportacion = new ProveedoresImportacion();

	}

	public void actualizarProvImp() {
		IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
		pDao.actualizarProvImp(proveedoresImportacion);
		proveedoresImportacion = new ProveedoresImportacion();

	}
    public void bajaProveedores() {
    	IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
    	proveedoresImportacion.setEstado(0);
    	pDao.borrarProvImp(proveedoresImportacion);
    	proveedoresImportacion = new ProveedoresImportacion();
   }

}
