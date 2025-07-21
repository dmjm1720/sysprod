package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Usuarios;

public interface IUsuarioDao {

    public Usuarios obtenerDatosUsuario(Usuarios usuarios);

    public Usuarios login(Usuarios usuarios);

    public void guardarUsuario(Usuarios usuarios);

    public void actualizarUsuario(Usuarios usuarios);

    public void borrarUsuario(Usuarios usuarios);
    
     List<Usuarios> listarUsuarios();
     
     
 	List<String> completeUsuario(String nombre) throws SQLException;

 	int buscarUsuario(String nombre) throws SQLException;
}
