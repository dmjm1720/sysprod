package com.dmjm.dao;

import com.dmjm.model.Precios;
import java.util.List;

public interface IPreciosDao {

    List<Precios> listarPrecios();

    void guardarPrecios(Precios precios);

    void actualizarPrecios(Precios precios);

    double buscarPrecios(int idProveedor, int idMateria);

}
