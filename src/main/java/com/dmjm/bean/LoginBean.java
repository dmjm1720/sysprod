package com.dmjm.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Usuarios;

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
			LOGGER.info("SESIÓN INICIADA: " + usuario.getNombre());

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", usuario);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol",
					usuario.getPerfiles().getNombrePerfil());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", this.usuario.getNombre()));
			loggedIn = true;
			if (this.usuario.getPerfiles().getNombrePerfil().equals("Administrador")) {
				ruta = "/sysprod/admin/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Coordinador")) {
				ruta = "/sysprod/coordinador/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Control de calidad")) {
				ruta = "/sysprod/calidad/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Gerencia")) {
				ruta = "/sysprod/gerencia/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Contralor")) {
				ruta = "/sysprod/contralor/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Presidencia")) {
				ruta = "/sysprod/presidencia/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("R. Industriales")) {
				ruta = "/sysprod/industriales/EntradasImportacion.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Aux. Contable")) {
				ruta = "/sysprod/auxiliar/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Operador")) {
				ruta = "/sysprod/operador/Entradas.html";
			} else if (this.usuario.getPerfiles().getNombrePerfil().equals("Cocedores")) {
				ruta = "/sysprod/cocedores/Cocedores.html";
			}else if (this.usuario.getPerfiles().getNombrePerfil().equals("Esterilizador Planta A")) {
				ruta = "/sysprod/esterilizadorA/EsterilizadorPlantaA.html";
			}else if (this.usuario.getPerfiles().getNombrePerfil().equals("Esterilizador Planta B")) {
				ruta = "/sysprod/esterilizadorB/EsterilizadorPlantaB.html";
			}else if (this.usuario.getPerfiles().getNombrePerfil().equals("Ultra Filtración I")) {
				ruta = "/sysprod/ultra1/UltraFiltracionUno.html";
			}else if (this.usuario.getPerfiles().getNombrePerfil().equals("Ultra Filtración II")) {
				ruta = "/sysprod/ultra2/UltraFiltracionDos.html";
			}else if (this.usuario.getPerfiles().getNombrePerfil().equals("Inocuidad")) {
				ruta = "/sysprod/inocuidad/Cocedores.html";
			}
			

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
