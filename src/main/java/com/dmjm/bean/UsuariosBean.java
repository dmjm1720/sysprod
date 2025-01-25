package com.dmjm.bean;

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Perfiles;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Password;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
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
		usuarios.setPassword(Password.sha512(usuarios.getPassword()));
		eDao.guardarUsuario(usuarios);
		usuarios = new Usuarios();
	}

	public void actualizar() {
		IUsuarioDao uDao = new UsuarioDaoImpl();
		Perfiles p = new Perfiles();
		p.setIdPerfil(idPerfil);
		usuarios.setPerfiles(p);
		usuarios.setPassword(Password.sha512(usuarios.getPassword()));
		uDao.actualizarUsuario(usuarios);
		String info = "Usuario agregado correctamente";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String viewId = facesContext.getViewRoot().getViewId();
		System.out.println("Vista actual: " + viewId);
		

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");
		usuarios = new Usuarios();

	}

}
