package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Precios;

public interface IPreciosDao {

    List<Precios> listarPrecios();

    void guardarPrecios(Precios precios);

    void actualizarPrecios(Precios precios);

    double buscarPrecios(int idProveedor, int idMateria);

}
