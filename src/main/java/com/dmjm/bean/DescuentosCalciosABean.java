package com.dmjm.bean;

import com.dmjm.dao.IDescuentoCalciosADao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.impl.DescuentosCalciosADaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.DescuentoCalciosTablaA;
import com.dmjm.model.Proveedores;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "descuentoCA")
@ViewScoped
public class DescuentosCalciosABean implements Serializable {

    private static final long serialVersionUID = 1L;
	private List<DescuentoCalciosTablaA> listarDescuentosCalcioA;
    private DescuentoCalciosTablaA descuentosCalciosA;

    @PostConstruct
    public void init() {
    	listarDescuentosCalcioA = new ArrayList<>();
    	descuentosCalciosA = new DescuentoCalciosTablaA();

    }

  

    public DescuentoCalciosTablaA getDescuentosCalciosA() {
		return descuentosCalciosA;
	}



	public void setDescuentosCalciosA(DescuentoCalciosTablaA descuentosCalciosA) {
		this.descuentosCalciosA = descuentosCalciosA;
	}



	public List<DescuentoCalciosTablaA> getlistarDescuentosCalcioA() {
        IDescuentoCalciosADao eDao = new DescuentosCalciosADaoImpl();
        listarDescuentosCalcioA = eDao.listarDescuentos();
        return listarDescuentosCalcioA;
    }

    public void guardar() {
    	 IDescuentoCalciosADao eDao = new DescuentosCalciosADaoImpl();
        eDao.guardarDescuentosCalciosA(descuentosCalciosA);
        descuentosCalciosA = new DescuentoCalciosTablaA();
    }
    
    public void actualizar() {
    	 IDescuentoCalciosADao eDao = new DescuentosCalciosADaoImpl();
    	 eDao.actualziarDescuentosCalciosA(descuentosCalciosA);
         descuentosCalciosA = new DescuentoCalciosTablaA();
    }

}
