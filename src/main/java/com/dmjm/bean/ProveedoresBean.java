package com.dmjm.bean;

import com.dmjm.dao.IProveedoresDao;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.Proveedores;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "proveedoresBean")
@ViewScoped
public class ProveedoresBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private List<Proveedores> listarProveedores;
    private Proveedores proveedores;

    @PostConstruct
    public void init() {
        listarProveedores = new ArrayList<>();
        proveedores = new Proveedores();

    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public List<Proveedores> getListarProveedores() {
        IProveedoresDao eDao = new ProveedoresDaoImpl();
        listarProveedores = eDao.listarProveedores();
        return listarProveedores;
    }

    public void guardar() {
        IProveedoresDao eDao = new ProveedoresDaoImpl();
        proveedores.setEstado(1);
        eDao.guardarProveedores(proveedores);
        proveedores = new Proveedores();
    }
    
    public void actualizar() {
    	 IProveedoresDao eDao = new ProveedoresDaoImpl();
         eDao.actualizarProveedores(proveedores);
         proveedores = new Proveedores();
    }
    
    public void bajaProveedores() {
    	 IProveedoresDao eDao = new ProveedoresDaoImpl();
    	 proveedores.setEstado(0);
         eDao.borrarProveedores(proveedores);
         proveedores = new Proveedores();
    }

}
