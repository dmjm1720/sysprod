package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Preservacion;

public interface IPreservacionDao {

    List<Preservacion> listarPreservacion();

    void guardarPreservacion(Preservacion preservacion);

    void actualizarPreservacion(Preservacion preservacion);
    
    List<String> completePreservacion(String nombre) throws SQLException;
    
    int buscarPreservacion(String nombre) throws SQLException;

}
