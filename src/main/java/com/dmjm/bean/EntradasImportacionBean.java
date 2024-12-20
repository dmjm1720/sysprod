package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.ICuentasContablesDao;
import com.dmjm.dao.IEntradasImportacionDao;
import com.dmjm.dao.IFoliosImportacion;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.impl.CuentasContablesDaoImpl;
import com.dmjm.impl.EntradasImportacionDaoImpl;
import com.dmjm.impl.FoliosImportacionDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.CuentasContables;
import com.dmjm.model.EntradasImportacion;
import com.dmjm.model.Materia;
import com.dmjm.model.Proveedores;
import com.dmjm.model.ProveedoresImportacion;

@Named(value = "entImportBean")
@ViewScoped
public class EntradasImportacionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EntradasImportacion> listaEntradasImp;
	private EntradasImportacion entradasImportacion;
	private ProveedoresImportacion proveedoresImportacion;
	private Materia materia;
	private CuentasContables cuentasContables;

	private String filterProveedor;
	private String filterMateria;
	private String filterCuenta;
	private Proveedores proveedores;

	@PostConstruct
	public void init() {
		listaEntradasImp = new ArrayList<>();
		entradasImportacion = new EntradasImportacion();
		proveedoresImportacion = new ProveedoresImportacion();
		proveedores = new Proveedores();
		materia = new Materia();
		cuentasContables = new CuentasContables();
		entradasImportacion.setTolva(buscarFolio());
	}

	public void setEntradasImportacion(EntradasImportacion entradasImportacion) {
		this.entradasImportacion = entradasImportacion;
	}

	public EntradasImportacion getEntradasImportacion() {

		return entradasImportacion;
	}

	public ProveedoresImportacion getProveedoresImportacion() {
		return proveedoresImportacion;
	}

	public void setProveedoresImportacion(ProveedoresImportacion proveedoresImportacion) {
		this.proveedoresImportacion = proveedoresImportacion;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CuentasContables getCuentasContables() {
		return cuentasContables;
	}

	public void setCuentasContables(CuentasContables cuentasContables) {
		this.cuentasContables = cuentasContables;
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
	
	

	public String getFilterCuenta() {
		return filterCuenta;
	}

	public void setFilterCuenta(String filterCuenta) {
		this.filterCuenta = filterCuenta;
	}

	public List<EntradasImportacion> getListaEntradasImp() {

		IEntradasImportacionDao eDao = new EntradasImportacionDaoImpl();
		listaEntradasImp = eDao.listarEntradas();
		return listaEntradasImp;
	}

	public int buscarFolio() {
		int folio = 0;
		IFoliosImportacion fDao = new FoliosImportacionDaoImpl();
		Month mes = LocalDate.now().getMonth();
		int year = LocalDate.now().getYear();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		folio = fDao.folioMax(year, nombre.toUpperCase());
		return folio;
	}

	// *****************GUARDAR*********************//

	public void guardar() throws SQLException {
		IEntradasImportacionDao eDao = new EntradasImportacionDaoImpl();

		proveedores.setIdProveedor(buscarProveedor(filterProveedor));
		materia.setIdMateria(buscarMateria(filterMateria));
		cuentasContables.setIdCuentaContable(buscarCuenta(filterCuenta));
		
		proveedoresImportacion.setIdProveedor(1);
		entradasImportacion.setMateria(materia);
		entradasImportacion.setProveedoresImportacion(proveedoresImportacion);
		entradasImportacion.setCuentasContables(cuentasContables);
		
		IFoliosImportacion fDao = new FoliosImportacionDaoImpl();
		Month mes = LocalDate.now().getMonth();
		int year = LocalDate.now().getYear();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		fDao.actualizarFolio(year, nombre.toUpperCase(), entradasImportacion.getTolva());
		
		eDao.guardarEntradasImportacion(entradasImportacion);
		entradasImportacion = new EntradasImportacion();
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
	
	// **DATOS DE LA CUENTA CONTABLE, CUENTA**//
	public List<String> buscarNombreCuenta(String cuenta) throws SQLException{
		ICuentasContablesDao cDao = new CuentasContablesDaoImpl();
		return cDao.completeCuentasContablesImp(cuenta);
	}
	
	// **DATOS DE LA CUENTA CONTABLE, ID**//
	public int buscarCuenta(String cuenta) throws SQLException {
		ICuentasContablesDao cDao = new CuentasContablesDaoImpl();
		return cDao.buscarCuentaContable(cuenta);
	}
}
