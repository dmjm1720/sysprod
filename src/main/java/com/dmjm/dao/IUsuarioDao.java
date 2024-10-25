package com.dmjm.dao;

import com.dmjm.model.Usuarios;
import java.util.List;

public interface IUsuarioDao {

    public Usuarios obtenerDatosUsuario(Usuarios usuarios);

    public Usuarios login(Usuarios usuarios);

    public void guardarUsuario(Usuarios usuarios);

    public void actualizarUsuario(Usuarios usuarios);

    public void borrarUsuario(Usuarios usuarios);
    
     List<Usuarios> listarUsuarios();
}
