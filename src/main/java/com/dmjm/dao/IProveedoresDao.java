package com.dmjm.dao;

import com.dmjm.model.Proveedores;
import java.sql.SQLException;
import java.util.List;

public interface IProveedoresDao {

    List<Proveedores> listarProveedores();

    void guardarProveedores(Proveedores proveedores);

    void actualizarProveedores(Proveedores proveedores);
    
    List<String> completeProveedor(String nombre) throws SQLException;
    
    int buscarProveedor(String nombre) throws SQLException;
    
    void borrarProveedores(Proveedores proveedores);
    
    String buscarTipoMonedaProveedor(String nombre)throws SQLException;
}
