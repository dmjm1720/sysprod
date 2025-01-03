package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Entradas;

public interface IEntradasDao {

    List<Entradas> listarEntradas();

    void guardarEntradas(Entradas entradas);

    void actualizarEntradas(Entradas entradas);
    
    void actualizarFechaImpresion(int idEntrada, String fecha, int tolva);
    
    Entradas validarFechaImpresion(int idEntrada);
    
    void actualizarPerfilCoord(Entradas entradas);
}
