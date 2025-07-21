package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IDescuentoCalciosBDao;
import com.dmjm.impl.DescuentosCalciosBDaoImpl;
import com.dmjm.model.DescuentoCalciosTablaB;

@Named(value = "descuentoCB")
@ViewScoped
public class DescuentosCalciosBBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private List<DescuentoCalciosTablaB> listarDescuentosCalcioB;
    private DescuentoCalciosTablaB descuentosCalciosB;

    @PostConstruct
    public void init() {
    	listarDescuentosCalcioB = new ArrayList<>();
    	descuentosCalciosB = new DescuentoCalciosTablaB();

    }

  

    public DescuentoCalciosTablaB getDescuentosCalciosB() {
		return descuentosCalciosB;
	}



	public void setDescuentosCalciosB(DescuentoCalciosTablaB descuentosCalciosB) {
		this.descuentosCalciosB = descuentosCalciosB;
	}



	public List<DescuentoCalciosTablaB> getlistarDescuentosCalcioB() {
        IDescuentoCalciosBDao eDao = new DescuentosCalciosBDaoImpl();
        listarDescuentosCalcioB = eDao.listarDescuentos();
        return listarDescuentosCalcioB;
    }

    public void guardar() {
    	 IDescuentoCalciosBDao eDao = new DescuentosCalciosBDaoImpl();
        eDao.guardarDescuentosCalciosA(descuentosCalciosB);
        descuentosCalciosB = new DescuentoCalciosTablaB();
    }
    
    public void actualizar() {
    	 IDescuentoCalciosBDao eDao = new DescuentosCalciosBDaoImpl();
    	 eDao.actualizarDescuentosCalciosB(descuentosCalciosB);
         descuentosCalciosB = new DescuentoCalciosTablaB();
    }
    
    public void eliminar() {
   	 IDescuentoCalciosBDao eDao = new DescuentosCalciosBDaoImpl();
   	 eDao.eliminarDescuentosCalciosB(descuentosCalciosB);
        descuentosCalciosB = new DescuentoCalciosTablaB();
   }

}
