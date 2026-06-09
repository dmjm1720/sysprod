package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICategoriasDao;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.impl.CategoriasDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.model.Categorias;
import com.dmjm.model.Materia;
import com.dmjm.model.Usuarios;

@Named("categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CategoriaBean.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private List<Categorias> listarCategorias;
	private Categorias categorias;
	private Categorias categoriasEditar;
	private String validarCategoria;
	private String filtroMateria;
	private Materia materia;

	private List<SelectItem> tiposMateria;

	@PostConstruct
	public void init() {
		listarCategorias = new ArrayList<>();
		categorias = new Categorias();
		categoriasEditar = new Categorias();
		materia = new Materia();
		tiposMateria = new ArrayList<>();
	}

	public CategoriaBean() {
		// TODO Auto-generated constructor stub
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public String getValidarCategoria() {

		return validarCategoria;
	}

	public void setValidarCategoria(String validarCategoria) {
		this.validarCategoria = validarCategoria;
	}

	public String getFiltroMateria() {
		return filtroMateria;
	}

	public void setFiltroMateria(String filtroMateria) {
		this.filtroMateria = filtroMateria;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void setListarCategorias(List<Categorias> listarCategorias) {
		this.listarCategorias = listarCategorias;
	}

	public List<SelectItem> getTiposMateria() {
		return tiposMateria;
	}

	public void setTiposMateria(List<SelectItem> tiposMateria) {
		this.tiposMateria = tiposMateria;
	}

	public Categorias getCategoriasEditar() {
		return categoriasEditar;
	}

	public void setCategoriasEditar(Categorias categoriasEditar) {
		this.categoriasEditar = categoriasEditar;
	}

	public List<Categorias> getListarCategorias() {
		ICategoriasDao catDao = new CategoriasDaoImpl();
		listarCategorias = catDao.listaCategorias();
		return listarCategorias;
	}

	public void guardarCategoria() throws SQLException {
		ICategoriasDao catDao = new CategoriasDaoImpl();
		int validarCat = validarCategoria();

		if (validarCat == 0) {
			materia.setIdMateria(buscarMateria(filtroMateria));
			categorias.setMateria(materia);
			catDao.guardarCategoria(categorias);
		} else {
			String info = "Categoría duplicada, no es posible guardar";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
		}
		categorias = new Categorias();
	}

	public int validarCategoria() throws SQLException {
		ICategoriasDao catDao = new CategoriasDaoImpl();
		return catDao.validarCategoriaExistente(buscarMateria(filtroMateria));
	}

	public void actualizarCategoria() throws SQLException {
		ICategoriasDao catDao = new CategoriasDaoImpl();
//		String validarCat = validarCategoria();
//		if (validarCat.equals("CATEGORÍA NO ENCONTRADA")) {
		materia.setIdMateria(buscarMateria(categoriasEditar.getMateria().getTipo()));
		categoriasEditar.setMateria(materia);
		catDao.actualizarCategoria(categoriasEditar);
		LOGGER.warn("ACTUALIZACIÓN DE CATEGORÍA: " + categoriasEditar.getMateria().getTipo());
//		} else {
//			String info = "Categoría duplicada, no es posible actualizar";
//
//			PrimeFaces.current()
//					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
//							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
//							+ "  timer: 8000\n" + "})");
//		}
		categorias = new Categorias();
	}

	public void abrirPopup(String categoria) {

		PrimeFaces.current()
				.executeScript("Swal.fire({ " + "title: 'Confirmar eliminación', "
						+ "text: '¿Estás seguro que deseas eliminar la categoría de: " + categoria + "?', "
						+ "icon: 'warning', " + "showCancelButton: true, " + "confirmButtonText: 'Sí, borrar', "
						+ "cancelButtonText: 'No, cancelar', " + "confirmButtonColor: '#d33', "
						+ "cancelButtonColor: '#3085d6', " + "customClass: { popup: 'my-small-popup' } " + // clase
																											// personalizada
						"}).then((result) => { " + "if (result.isConfirmed) { " + "   borrarCategoria(); " + // invoca
																												// el
																												// método
																												// del
																												// bean
						"} " + "});");
	}

	public void borrarCategoria() {
		ICategoriasDao catDao = new CategoriasDaoImpl();
		catDao.borrarCategoria(categorias);
		categorias = new Categorias();
	}

	// **DATOS DE LA MATERIA PRIMA, NOMBRE**//
	public List<String> buscarNombreMateria(String nombre) throws SQLException {
		IMateriaDao mDao = new MateriaDaoImpl();
		return mDao.completeMateria(nombre);
	}

	// **DATOS DE LA MATERIA PRIMA, ID**//
	public int buscarMateria(String nombre) throws SQLException {
		IMateriaDao mDao = new MateriaDaoImpl();
		return mDao.buscarMateria(nombre);
	}

	public List<SelectItem> llenarFiltro() throws SQLException {
		materia.setTipo(categoriasEditar.getMateria().getTipo());
		
		categoriasEditar.setMateria(materia);

		IMateriaDao mDao = new MateriaDaoImpl();

		List<String> lista = mDao.listaMaterial();

		tiposMateria.add(new SelectItem("", "-- Seleccione --"));
		for (String dato : lista) {
			tiposMateria.add(new SelectItem(dato, dato));
		}
		return tiposMateria;
	}

}
