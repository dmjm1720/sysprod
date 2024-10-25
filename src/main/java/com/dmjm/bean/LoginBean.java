package com.dmjm.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Usuarios;

import java.io.Serializable;

@Named("login")
@SessionScoped

public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(LoginBean.class);
    
    private Usuarios usuario;
    
    public Usuarios getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public LoginBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.usuario = new Usuarios();
    }
    
    public void login(ActionEvent event) {
        
        boolean loggedIn = false;
        String ruta = "";
        IUsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuario = usuarioDao.login(this.usuario);
        
        if (this.usuario != null) {
        	LOGGER.info("SESIÓN INICIADA: "+ usuario.getNombre());
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", this.usuario.getNombre()));
            loggedIn = true;
//            if (this.usuario.getPerfiles().getIdPerfil()==2) {
                ruta = "/sysprod/views/Entradas.html";
//            }            
        } else {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR DE ACCESO!", "¡VERIFIQUE SUS CREDENCIALES!"));
            this.usuario = new Usuarios();
        }
        
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        PrimeFaces.current().ajax().addCallbackParam("ruta", ruta);
    }
    
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
}
