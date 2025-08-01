package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IMateriaDao;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.model.Materia;

@Named(value = "materiaBean")
@ViewScoped
public class MateriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private List<Materia> listarMateria;
    private Materia materia;

    @PostConstruct
    public void init() {
        listarMateria = new ArrayList<>();
        materia = new Materia();

    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

 
    public List<Materia> getListarMateria() {
        IMateriaDao eDao = new MateriaDaoImpl();
        listarMateria = eDao.listarMateria();
        return listarMateria;
    }

    public void guardar() {
        IMateriaDao eDao = new MateriaDaoImpl();
        eDao.guardarMateria(materia);
        materia = new Materia();
    }

    public void actualizar() {
        IMateriaDao eDao = new MateriaDaoImpl();
        eDao.actualizarMateria(materia);
        materia = new Materia();
    }
    
}
