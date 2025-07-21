package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Materia;

public interface IMateriaDao {

    List<Materia> listarMateria();

    void guardarMateria(Materia materia);

    void actualizarMateria(Materia materia);
    
    List<String> completeMateria(String nombre) throws SQLException;

    int buscarMateria(String nombre) throws SQLException;
}
