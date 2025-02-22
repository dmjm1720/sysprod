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
    
    Entradas obtenerIdEntrada(String factura);
    
    void actualizarNombreArchivoCert(int id, String nombre);
    
    String nombreArchivoCert(int id);
    
    List<Entradas> listarEntradasImportacion();
    
    Entradas buscarEntradaPorFactura(String factura);
    
    void actualizarFacturaImportacion (String factura);
    
    List<Entradas> listarEntradasFactura();
}
