package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Proveedores;

public interface IProveedoresDao {

    List<Proveedores> listarProveedores();

    void guardarProveedores(Proveedores proveedores);

    void actualizarProveedores(Proveedores proveedores);
    
    List<String> completeProveedor(String nombre) throws SQLException;
    
    int buscarProveedor(String nombre) throws SQLException;
    
    void borrarProveedores(Proveedores proveedores);
    
    String buscarTipoMonedaProveedor(String nombre)throws SQLException;
    
    Proveedores buscarMerma (int id);
    

}
