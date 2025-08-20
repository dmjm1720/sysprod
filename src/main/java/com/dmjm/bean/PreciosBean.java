package com.dmjm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IBitacoraPreciosDao;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IPreciosDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.impl.BitacoraPreciosDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.PreciosDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.BitacoraPrecios;
import com.dmjm.model.Materia;
import com.dmjm.model.Precios;
import com.dmjm.model.Proveedores;
import com.dmjm.model.Usuarios;

@Named(value = "preciosBean")
@ViewScoped
public class PreciosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(PreciosBean.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private List<Precios> listarPrecios;
	private Precios precios;
	private Precios precioAnterior;

	private String filterProveedor;
	private String filterMateria;

	private Proveedores proveedores;
	private Materia materia;

	@PostConstruct
	public void init() {
		listarPrecios = new ArrayList<>();
		precios = new Precios();
		precioAnterior = new Precios();
		proveedores = new Proveedores();
		materia = new Materia();
		precios.setFechaActualizacion(new Date());
	}

	public List<Precios> getListarPrecios() {
		IPreciosDao pDao = new PreciosDaoImpl();
		listarPrecios = pDao.listarPrecios();
		return listarPrecios;
	}

	public Precios getPrecioAnterior() {
		return precioAnterior;
	}

	public void setPrecioAnterior(Precios precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public Precios getPrecios() {
		return precios;
	}

	public void setPrecios(Precios precios) {
		this.precios = precios;
	}

	public String getFilterProveedor() {
		return filterProveedor;
	}

	public void setFilterProveedor(String filterProveedor) {
		this.filterProveedor = filterProveedor;
	}

	public String getFilterMateria() {
		return filterMateria;
	}

	public void setFilterMateria(String filterMateria) {
		this.filterMateria = filterMateria;
	}

	public Proveedores getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void guardar() throws SQLException {
		IPreciosDao pDao = new PreciosDaoImpl();
		proveedores.setIdProveedor(buscarProveedor(filterProveedor));
		materia.setIdMateria(buscarMateria(filterMateria));

		precios.setProveedores(proveedores);
		precios.setMateria(materia);
		
		

		pDao.guardarPrecios(precios);
		
		IBitacoraPreciosDao bDao = new BitacoraPreciosDaoImpl();
		BitacoraPrecios b = new BitacoraPrecios();
		b.setIdMateria(precios.getMateria().getIdMateria());
		b.setIdProveedor(precios.getProveedores().getIdProveedor());
		b.setFechaActualizacion(new Date());
		b.setActualizadoPor(us.getIdUsuario());
		b.setFechaSistema(new Date());
		b.setNuevoPrecio(new BigDecimal(precios.getPrecioActual()));
		b.setTipo("NUEVO");

		bDao.guardarBitacoraPrecios(b);

		LOGGER.warn("============================================");
		LOGGER.warn("SE HA REGISTRADO UN NUEVO PRECIO");
		LOGGER.warn("MATERIA: " + buscarNombreMateria(filterMateria) + "->PROVEEDOR: "
				+ buscarNombreProveedor(filterProveedor) + "->NUEVO PRECIO: "
				+ new BigDecimal(precios.getPrecioActual()) + "->FECHA DE CREACIÓN: "
				+ precios.getFechaActualizacion() + " ->FECHA DE SISTEMA: " + new Date()
				+ " ->CREADO POR: " + us.getNombre());

		LOGGER.warn("============================================");


		precios = new Precios();
		proveedores = new Proveedores();
		materia = new Materia();
		filterMateria = null;
		filterProveedor = null;

		String info = "Se ha actualizado el precio correctamente";
		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");
	}

	// **DATOS DEL PROVEEDOR, NOMBRE**//
	public List<String> buscarNombreProveedor(String nombre) throws SQLException {
		IProveedoresDao pDao = new ProveedoresDaoImpl();
		return pDao.completeProveedor(nombre);
	}

	// **DATOS DEL PROVEEDOR, ID**//
	public int buscarProveedor(String nombre) throws SQLException {
		IProveedoresDao pDao = new ProveedoresDaoImpl();
		return pDao.buscarProveedor(nombre);
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

	public void preUpdate(String preAnterior) {
		LOGGER.info("PRECIO ANTERIOR: " + preAnterior);
		precioAnterior.setPrecioActual(preAnterior);
	}

	public void actualizarPrecio() {
		IPreciosDao pDao = new PreciosDaoImpl();
		pDao.actualizarPrecios(precios);

		IBitacoraPreciosDao bDao = new BitacoraPreciosDaoImpl();
		BitacoraPrecios b = new BitacoraPrecios();
		b.setIdMateria(precios.getMateria().getIdMateria());
		b.setIdProveedor(precios.getProveedores().getIdProveedor());
		b.setPrecioAnterior(new BigDecimal(precioAnterior.getPrecioActual()));
		b.setFechaActualizacion(precios.getFechaActualizacion());
		b.setActualizadoPor(us.getIdUsuario());
		b.setFechaSistema(new Date());
		b.setNuevoPrecio(new BigDecimal(precios.getPrecioActual()));
		b.setTipo("ACTUALIZACIÓN");

		bDao.guardarBitacoraPrecios(b);

		LOGGER.warn("============================================");
		LOGGER.warn("SE HA REALIZADO UNA ACTUALIZACIÓN DE PRECIOS");
		LOGGER.warn("MATERIA: " + precios.getMateria().getTipo() + "->PROVEEDOR: "
				+ precios.getProveedores().getNombre() + "->NUEVO PRECIO: "
				+ new BigDecimal(precios.getPrecioActual()) + "->FECHA DE ACTUALIZACIÓN: "
				+ precios.getFechaActualizacion() + " ->FECHA DE SISTEMA: " + b.getFechaSistema()
				+ " ->ACTUALIZADOR POR: " + us.getNombre());

		LOGGER.info("PRECIO ANTERIOR: " + precioAnterior.getPrecioActual());
		LOGGER.info("ACTUALIZACIÓN DE PRECIO:" + b.getNuevoPrecio() + " ID: " + precios.getIdPrecios()
				+ " POR EL USUARIO: " + us.getUsuario() + " ->" + us.getIniciales() + " ->" + us.getNombre());

		LOGGER.warn("============================================");
		String info = "SE HA ACTUALIZADO EL PRECIO CORRECTAMENTE";
		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");
		precios = new Precios();
		precioAnterior = new Precios();
	}

}
