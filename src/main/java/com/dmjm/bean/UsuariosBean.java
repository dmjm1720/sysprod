package com.dmjm.bean;

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Perfiles;
import com.dmjm.model.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {
    
    private List<Usuarios> listarUsuarios;
    private Usuarios usuarios;
    private Perfiles perfiles;
    private int idPerfil;
    
    @PostConstruct
    public void init() {
        listarUsuarios = new ArrayList<>();
        usuarios = new Usuarios();
        perfiles = new Perfiles();
        
    }
    
    public Usuarios getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    public int getIdPerfil() {
        return idPerfil;
    }
    
    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    public Perfiles getPerfiles() {
        return perfiles;
    }
    
    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }
    
    public List<Usuarios> getListarUsuarios() {
        IUsuarioDao eDao = new UsuarioDaoImpl();
        listarUsuarios = eDao.listarUsuarios();
        return listarUsuarios;
    }
    
    public void guardar() {
        IUsuarioDao eDao = new UsuarioDaoImpl();
        perfiles.setIdPerfil(idPerfil);
        usuarios.setPerfiles(perfiles);
        usuarios.setEstatus(1);
        eDao.guardarUsuario(usuarios);
    }
    
}
