package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Transportista;

public interface ITransportistaDao {

    List<Transportista> listarTransportista();

    void guardarTransportista(Transportista transportista);

    void actualizarTransportista(Transportista transportista);
    
    List<String> completeTransportista(String nombre) throws SQLException;
    
    int buscarTransportista(String nombre) throws SQLException;
    
    void borrarTransportista(Transportista transportista);

}
