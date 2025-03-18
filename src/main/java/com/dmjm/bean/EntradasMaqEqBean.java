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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICuentasContablesDao;
import com.dmjm.dao.IEntradasMaqEqDao;
import com.dmjm.dao.IFolioDeImportacionDao;
import com.dmjm.dao.IFoliosImportacion;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.dao.IProveedoresImportacionDao;
import com.dmjm.impl.CuentasContablesDaoImpl;
import com.dmjm.impl.EntradasMaqEqImpl;
import com.dmjm.impl.FolioDeImportacionDaoImpl;
import com.dmjm.impl.FoliosImportacionDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.impl.ProveedoresImportacionDaoImpl;
import com.dmjm.model.CuentasContables;
import com.dmjm.model.EntradasMaquinariaEquipo;
import com.dmjm.model.Proveedores;
import com.dmjm.model.ProveedoresImportacion;
import com.dmjm.util.ReporteImportacion;
import com.dmjm.util.ReporteImportacionMaq;

@Named(value = "entMaqEqBean")
@ViewScoped
public class EntradasMaqEqBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EntradasMaquinariaEquipo> listaEntradasImp;
	private EntradasMaquinariaEquipo entradasImportacionMaq;
	private ProveedoresImportacion proveedoresImportacion;
	private CuentasContables cuentasContables;

	private String filterProveedor;
	private String filterMateria;
	private String filterCuenta;
	private Proveedores proveedores;
	private String factura;
	private String proveedor;

	@PostConstruct
	public void init() {
		listaEntradasImp = new ArrayList<>();
		entradasImportacionMaq = new EntradasMaquinariaEquipo();
		proveedoresImportacion = new ProveedoresImportacion();
		proveedores = new Proveedores();
		cuentasContables = new CuentasContables();
		entradasImportacionMaq.setFolioImportacion(buscarFolioImportacion());

	}

	public EntradasMaquinariaEquipo getEntradasImportacionMaq() {
		return entradasImportacionMaq;
	}

	public void setEntradasImportacionMaq(EntradasMaquinariaEquipo entradasImportacionMaq) {
		this.entradasImportacionMaq = entradasImportacionMaq;
	}

	public ProveedoresImportacion getProveedoresImportacion() {
		return proveedoresImportacion;
	}

	public void setProveedoresImportacion(ProveedoresImportacion proveedoresImportacion) {
		this.proveedoresImportacion = proveedoresImportacion;
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

	public List<EntradasMaquinariaEquipo> getListaEntradasImp() {

		IEntradasMaqEqDao eDao = new EntradasMaqEqImpl();
		listaEntradasImp = eDao.listarEntradas();
		return listaEntradasImp;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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

	public void actualizarFolioDeImportacion(long folioImportacion) {
		IFolioDeImportacionDao fDao = new FolioDeImportacionDaoImpl();
		fDao.actualizarFolio(folioImportacion);
	}

	public long buscarFolioImportacion() {
		IFolioDeImportacionDao fDao = new FolioDeImportacionDaoImpl();
		return fDao.buscarFolio();
	}

	// *****************GUARDAR*********************//

	public void guardar() throws SQLException {

		if (filterCuenta != null) {
			IEntradasMaqEqDao eDao = new EntradasMaqEqImpl();

			proveedoresImportacion.setIdProveedor(buscarProveedor(filterProveedor));
			cuentasContables.setIdCuentaContable(buscarCuenta(filterCuenta));

			entradasImportacionMaq.setProveedoresImportacion(proveedoresImportacion);
			entradasImportacionMaq.setCuentasContables(cuentasContables);

			actualizarFolioDeImportacion(entradasImportacionMaq.getFolioImportacion());
			entradasImportacionMaq.setEstadoImpresion(0);

			eDao.guardarEntradasImportacion(entradasImportacionMaq);

			String info = "Se ha registrado correctamente la factura de importación no.: " + factura + " Folio EA: "
					+ entradasImportacionMaq.getFolioImportacion();

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
			entradasImportacionMaq = new EntradasMaquinariaEquipo();

			String script = "setTimeout(function() { window.location.href='EntradasMaquinariaEquipo.html'; }, 5000);";
			PrimeFaces.current().executeScript(script);

		} else {
			String info = "Cuenta contable requerida";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		}

	}

	// **DATOS DEL PROVEEDOR, NOMBRE**//
	public List<String> buscarNombreProveedor(String nombre) throws SQLException {
		IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
		return pDao.completeProveedorImp(nombre);
	}

	// **DATOS DEL PROVEEDOR, ID**//
	public int buscarProveedor(String nombre) throws SQLException {
		IProveedoresImportacionDao pDao = new ProveedoresImportacionDaoImpl();
		return pDao.buscarProvImp(nombre);
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
	public List<String> buscarNombreCuenta(String cuenta) throws SQLException {
		ICuentasContablesDao cDao = new CuentasContablesDaoImpl();
		return cDao.completeCuentasContablesImp(cuenta);
	}

	// **DATOS DE LA CUENTA CONTABLE, ID**//
	public int buscarCuenta(String cuenta) throws SQLException {
		ICuentasContablesDao cDao = new CuentasContablesDaoImpl();
		return cDao.buscarCuentaContable(cuenta);
	}

	public void visualizarReporte(String idEntrada, int estado) throws SQLException {

		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ReporteImportacionMaq reporte = new ReporteImportacionMaq();
		if (estado == 0) {
			ruta = servletContext.getRealPath("/REP/reporteImportacionMaquinariaSF.jasper");
		} else if (estado == 1) {
			ruta = servletContext.getRealPath("/REP/reporteImportacionMaquinaria.jasper");
		}

		reporte.getReporte(ruta, idEntrada, "3");

		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void liberar(int id) {
		IEntradasMaqEqDao eDao = new EntradasMaqEqImpl();
		eDao.actualizarEstadoImpresion(id);
	}

}
