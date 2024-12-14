package com.dmjm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IEntradasDao;
import com.dmjm.dao.IFoliosDao;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IPreciosDao;
import com.dmjm.dao.IPreservacionDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.dao.ITransportistaDao;
import com.dmjm.impl.EntradasDaoImpl;
import com.dmjm.impl.FoliosDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.PreciosDaoImpl;
import com.dmjm.impl.PreservacionDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.impl.TransportistaDaoImpl;
import com.dmjm.model.Entradas;
import com.dmjm.model.Materia;
import com.dmjm.model.Preservacion;
import com.dmjm.model.Proveedores;
import com.dmjm.model.Transportista;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.Correo;
import com.dmjm.util.ReporteLiberacion;
import com.dmjm.util.ReporteLiberacionSF;

@Named(value = "entradasBean")
@ViewScoped
public class EntradasBean extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(EntradasBean.class.getName());

	private List<Entradas> listarEntradas;
	private Entradas entradas;
	private Materia materia;
	private Preservacion preservacion;
	private Proveedores proveedores;
	private Transportista transportista;
	private int idMat;
	private int idPre;
	private int idPro;
	private int idTrans;
	private String inicialesCoordinador;
	private String inicialesControlCalidad;
	private String inicialesGerencia;
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private String filterTransportista;
	private String filterProveedor;
	private String filterMateria;
	private String filterPreservacion;
	private Entradas entradasEditar;
	private Entradas entradasDesbloquear;
	private Entradas entradasDetalle;

	private double dato1 = 0.0;
	private double dato2 = 0.0;
	private double dato3 = 0.0;
	private double dato4 = 0.0;
	private double dato5 = 0.0;
	private double dato6 = 0.0;
	private double dato7 = 0.0;
	private double dato8 = 0.0;
	private double dato9 = 0.0;
	private double dato10 = 0.0;
	private double dato11 = 0.0;
	private double dato12 = 0.0;
	private double dato13 = 0.0;
	private double dato14 = 0.0;
	private double kilosEmbarcados = 0.0;
	private double kilosRecibidos = 0.0;
	private double kilosNetos = 0.0;
	private double kilosBascula = 0.0;
	private double kilosBasculaMerma = 0.0;

	private double ceros = 0.0;
	private String mensajeMes;

	@PostConstruct
	public void init() {
		listarEntradas = new ArrayList<>();
		entradas = new Entradas();
		materia = new Materia();
		preservacion = new Preservacion();
		proveedores = new Proveedores();
		transportista = new Transportista();
		if (us.getPerfiles().getNombrePerfil().equals("Coordinador")) {
			inicialesCoordinador = us.getIniciales() + "|" + us.getNombre();
		} else if (us.getPerfiles().getNombrePerfil().equals("Control de calidad")) {
			inicialesControlCalidad = us.getIniciales() + "|" + us.getNombre();

		} else if (us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
			inicialesGerencia = us.getIniciales() + "|" + us.getNombre();
			entradas.setFechaLiberacion(new Date());

		}
		entradas.setTolvas(buscarFolio());
		entradas.setFechaRecepcion(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // tuFechaBase es un Date;
		calendar.add(Calendar.HOUR, 1); // horasASumar es int.
		// lo que más quieras sumar
		Date fechaSalida = calendar.getTime(); // Y ya tienes la fecha sumada.

		entradas.setFechaRecepcionToluca(fechaSalida);
		entradasEditar = new Entradas();
		entradasDesbloquear = new Entradas();
		entradasDetalle = new Entradas();

	}

	public Entradas getEntradas() {
		return entradas;
	}

	public void setEntradas(Entradas entradas) {
		this.entradas = entradas;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Preservacion getPreservacion() {
		return preservacion;
	}

	public void setPreservacion(Preservacion preservacion) {
		this.preservacion = preservacion;
	}

	public Proveedores getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public int getIdMat() {
		return idMat;
	}

	public void setIdMat(int idMat) {
		this.idMat = idMat;
	}

	public int getIdPre() {
		return idPre;
	}

	public void setIdPre(int idPre) {
		this.idPre = idPre;
	}

	public int getIdPro() {
		return idPro;
	}

	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	public int getIdTrans() {
		return idTrans;
	}

	public void setIdTrans(int idTrans) {
		this.idTrans = idTrans;
	}

	public String getInicialesCoordinador() {
		return inicialesCoordinador;
	}

	public void setInicialesCoordinador(String inicialesCoordinador) {
		this.inicialesCoordinador = inicialesCoordinador;
	}

	public String getInicialesControlCalidad() {
		return inicialesControlCalidad;
	}

	public void setInicialesControlCalidad(String inicialesControlCalidad) {
		this.inicialesControlCalidad = inicialesControlCalidad;
	}

	public String getInicialesGerencia() {
		return inicialesGerencia;
	}

	public void setInicialesGerencia(String inicialesGerencia) {
		this.inicialesGerencia = inicialesGerencia;
	}

	public String getFilterTransportista() {
		return filterTransportista;
	}

	public void setFilterTransportista(String filterTransportista) {
		this.filterTransportista = filterTransportista;
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

	public String getFilterPreservacion() {
		return filterPreservacion;
	}

	public void setFilterPreservacion(String filterPreservacion) {
		this.filterPreservacion = filterPreservacion;
	}

	public List<Entradas> getListarEntradas() {
		IEntradasDao eDao = new EntradasDaoImpl();
		listarEntradas = eDao.listarEntradas();
		return listarEntradas;
	}

	public Entradas getEntradasEditar() {
		return entradasEditar;
	}

	public void setEntradasEditar(Entradas entradasEditar) {
		this.entradasEditar = entradasEditar;
	}

	public Entradas getEntradasDesbloquear() {
		return entradasDesbloquear;
	}

	public void setEntradasDesbloquear(Entradas entradasDesbloquear) {
		this.entradasDesbloquear = entradasDesbloquear;
	}

	public double getDato1() {
		return dato1;
	}

	public void setDato1(double dato1) {
		this.dato1 = dato1;
	}

	public double getDato2() {
		return dato2;
	}

	public void setDato2(double dato2) {
		this.dato2 = dato2;
	}

	public double getDato3() {
		return dato3;
	}

	public void setDato3(double dato3) {
		this.dato3 = dato3;
	}

	public double getDato4() {
		return dato4;
	}

	public void setDato4(double dato4) {
		this.dato4 = dato4;
	}

	public double getDato5() {
		return dato5;
	}

	public void setDato5(double dato5) {
		this.dato5 = dato5;
	}

	public double getDato6() {
		return dato6;
	}

	public void setDato6(double dato6) {
		this.dato6 = dato6;
	}

	public double getDato7() {
		return dato7;
	}

	public void setDato7(double dato7) {
		this.dato7 = dato7;
	}

	public double getDato8() {
		return dato8;
	}

	public void setDato8(double dato8) {
		this.dato8 = dato8;
	}

	public double getDato9() {
		return dato9;
	}

	public void setDato9(double dato9) {
		this.dato9 = dato9;
	}

	public double getDato10() {
		return dato10;
	}

	public void setDato10(double dato10) {
		this.dato10 = dato10;
	}

	public double getDato11() {
		return dato11;
	}

	public void setDato11(double dato11) {
		this.dato11 = dato11;
	}

	public double getDato12() {
		return dato12;
	}

	public void setDato12(double dato12) {
		this.dato12 = dato12;
	}

	public double getDato13() {
		return dato13;
	}

	public void setDato13(double dato13) {
		this.dato13 = dato13;
	}

	public double getDato14() {
		return dato14;
	}

	public void setDato14(double dato14) {
		this.dato14 = dato14;
	}

	public void setDato14(int dato14) {
		this.dato14 = dato14;
	}

	public double getKilosEmbarcados() {
		return kilosEmbarcados;
	}

	public void setKilosEmbarcados(double kilosEmbarcados) {
		this.kilosEmbarcados = kilosEmbarcados;
	}

	public double getKilosRecibidos() {
		return kilosRecibidos;
	}

	public void setKilosRecibidos(double kilosRecibidos) {
		this.kilosRecibidos = kilosRecibidos;
	}

	public Entradas getEntradasDetalle() {
		return entradasDetalle;
	}

	public void setEntradasDetalle(Entradas entradasDetalle) {
		this.entradasDetalle = entradasDetalle;
	}

	public double getCeros() {
		return ceros;
	}

	public void setCeros(double ceros) {
		this.ceros = ceros;
	}

	public double getKilosNetos() {
		return kilosNetos;
	}

	public void setKilosNetos(double kilosNetos) {
		this.kilosNetos = kilosNetos;
	}

	public String getMensajeMes() {
		return mensajeMes;
	}

	public void setMensajeMes(String mensajeMes) {
		this.mensajeMes = mensajeMes;
	}

	public double getKilosBascula() {
		return kilosBascula;
	}

	public void setKilosBascula(double kilosBascula) {
		this.kilosBascula = kilosBascula;
	}

	public double getKilosBasculaMerma() {
		return kilosBasculaMerma;
	}

	public void setKilosBasculaMerma(double kilosBasculaMerma) {
		this.kilosBasculaMerma = kilosBasculaMerma;
	}

	// *****************GUARDAR*********************//
	public void guardar() throws SQLException {
		IEntradasDao eDao = new EntradasDaoImpl();

		preservacion.setIdPreservacion(buscarPreservacion(filterPreservacion));
		transportista.setIdTransportista(buscarTransportista(filterTransportista));
		proveedores.setIdProveedor(buscarProveedor(filterProveedor));
		materia.setIdMateria(buscarMateria(filterMateria));

		entradas.setMateria(materia);
		entradas.setPreservacion(preservacion);
		entradas.setTransportista(transportista);
		entradas.setProveedores(proveedores);

		entradas.setKgEmbarcados(BigDecimal.valueOf(kilosEmbarcados));
		entradas.setKgRecibidos(BigDecimal.valueOf(kilosRecibidos));
		entradas.setKgNetos(BigDecimal.valueOf(kilosNetos));
		entradas.setKgBascula(BigDecimal.valueOf(kilosBascula));
		entradas.setKgBasculaMerma(BigDecimal.valueOf(kilosBasculaMerma));

		// **PRECIO DE LA CARNAZA CON PELO**//

		entradas.setCarnazaConPelo(BigDecimal.valueOf(dato1));
		entradas.setCarnzaPrimera(BigDecimal.valueOf(dato2));
		entradas.setCarnzaSegunda(BigDecimal.valueOf(dato3));
		entradas.setCarnazaSalada(BigDecimal.valueOf(dato4));
		entradas.setDesbarbeRecorte(BigDecimal.valueOf(dato5));
		entradas.setCerdoMexicano(BigDecimal.valueOf(dato6));
		entradas.setOrejaCachete(BigDecimal.valueOf(dato7));
		entradas.setPedaceriaConPelo(BigDecimal.valueOf(dato8));
		entradas.setPedaceria(BigDecimal.valueOf(dato9));
		entradas.setDescarneAdherido(BigDecimal.valueOf(dato10));
		entradas.setDescarneSeparado(BigDecimal.valueOf(dato11));
		entradas.setCueroDepiladoIntegral(BigDecimal.valueOf(dato12));
		entradas.setGarra(BigDecimal.valueOf(dato13));
		entradas.setCueroEnSangre(BigDecimal.valueOf(dato14));

		// VALIDAR DATOS

		if (entradas.getSucursal() == null || entradas.getFactura() == "" || entradas.getCertificado() == ""
				|| transportista.getIdTransportista() == 0 || proveedores.getIdProveedor() == 0
				|| materia.getIdMateria() == 0 || preservacion.getIdPreservacion() == 0) {

			String suc = "";
			String fac = "";
			String cer = "";
			String trans = "";
			String pro = "";
			String mat = "";
			String pre = "";
			String ticket = "";

			if (entradas.getSucursal() == null) {
				suc = "Sucursal, ";
			}

			if (entradas.getFactura() == "") {
				fac = "Factura, ";
			}

			if (entradas.getCertificado() == "") {
				cer = "Certificado, ";
			}
			if (materia.getIdMateria() == 0) {
				mat = "Identificación de la materia, ";
			}

			if (transportista.getIdTransportista() == 0) {
				trans = "Transportista, ";
			}
			if (proveedores.getIdProveedor() == 0) {
				pro = "Proveedor, ";
			}
			if (preservacion.getIdPreservacion() == 0) {
				pre = "Método de preservación, ";
			}
//			if (entradas.getTicketBasculaToluca() == "") {
//				ticket = "Ticket Toluca, ";
//			}

			String mensaje = "Te faltan campos: " + suc + ticket + fac + cer + trans + pro + mat + pre;
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + mensaje + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");

		} else {

			LOGGER.info("INFO DATOS: " + dato1 + dato2 + dato3 + dato4 + dato5 + dato6 + dato7 + dato8 + dato9 + dato10
					+ dato11 + dato12 + dato13 + dato14);

			if (dato1 != ceros) {
				entradas.setPrecioCcp(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 1)));
			} else {
				entradas.setPrecioCcp(BigDecimal.valueOf(ceros));
			}

			if (dato2 != ceros) {
				entradas.setPrecioC1(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 2)));
			} else {
				entradas.setPrecioC1(BigDecimal.valueOf(ceros));
			}

			if (dato3 != ceros) {
				entradas.setPrecioC2(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 3)));
			} else {
				entradas.setPrecioC2(BigDecimal.valueOf(ceros));
			}

			if (dato4 != ceros) {
				entradas.setPrecioCs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 4)));
			} else {
				entradas.setPrecioCs(BigDecimal.valueOf(ceros));
			}

			if (dato5 != ceros) {
				entradas.setPrecioDr(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 5)));
			} else {
				entradas.setPrecioDr(BigDecimal.valueOf(ceros));
			}

			if (dato6 != ceros) {
				entradas.setPrecioCm(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 6)));
			} else {
				entradas.setPrecioCm(BigDecimal.valueOf(ceros));
			}

			if (dato7 != ceros) {
				entradas.setPrecioCo(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 7)));
			} else {
				entradas.setPrecioCo(BigDecimal.valueOf(ceros));
			}

			if (dato8 != ceros) {
				entradas.setPrecioPc(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 8)));
			} else {
				entradas.setPrecioPc(BigDecimal.valueOf(ceros));
			}

			if (dato9 != ceros) {
				entradas.setPrecioP(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 9)));
			} else {
				entradas.setPrecioP(BigDecimal.valueOf(ceros));
			}

			if (dato10 != ceros) {
				entradas.setPrecioDa(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 10)));
			} else {
				entradas.setPrecioDa(BigDecimal.valueOf(ceros));
			}

			if (dato11 != ceros) {
				entradas.setPrecioDs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 11)));
			} else {
				entradas.setPrecioDs(BigDecimal.valueOf(ceros));
			}

			if (dato12 != ceros) {
				entradas.setPrecioCdi(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 12)));
			} else {
				entradas.setPrecioCdi(BigDecimal.valueOf(ceros));
			}

			if (dato13 != ceros) {
				entradas.setPrecioG(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 13)));
			} else {
				entradas.setPrecioG(BigDecimal.valueOf(ceros));
			}

			if (dato14 != ceros) {
				entradas.setPrecioCe(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 14)));
			} else {

				entradas.setPrecioCe(BigDecimal.valueOf(ceros));
			}

			switch (us.getPerfiles().getNombrePerfil()) {
			case "Coordinador" -> entradas.setCoordinadorProduccion(us.getIniciales());
			case "Gerencia" -> entradas.setCoordinadorProduccion(us.getIniciales());
			default -> {
			}
			}

			if (us.getPerfiles().getNombrePerfil().equals("Coordinador")
					|| us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
				entradas.setEstado(0);

				entradas.setGerenciaProduccion(null);
				entradas.setFechaLiberacion(null);

			}

			double porcentaje15 = 1.5;
			if (Double.parseDouble(entradas.getPorcentajeMerma().toString()) < porcentaje15) {
				porcentaje15 = 0.0;
			}

			entradas.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

			eDao.guardarEntradas(entradas);

			IFoliosDao fDao = new FoliosDaoImpl();
			if (mensajeMes != null) {

				int year = 0;
				Month mes = LocalDate.now().getMonth().minus(1);
				String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
				if (nombre.toUpperCase().contains("DICIEMBRE")) {
					year = LocalDate.now().getYear();
					year = year - 1;
				} else {
					year = LocalDate.now().getYear();
				}
				fDao.actualizarFolio(year, nombre.toUpperCase(), entradas.getTolvas());
				LOGGER.info("SE CAPTURA CON MES ANTERIOR-> " + nombre.toUpperCase() + " AÑO-> " + year + " TOLVA->"
						+ entradas.getTolvas());
			} else {
				Month mes = LocalDate.now().getMonth();
				int year = LocalDate.now().getYear();
				String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
				fDao.actualizarFolio(year, nombre.toUpperCase(), entradas.getTolvas());
				LOGGER.info("SE CAPTURA CON MES ACTUAL-> " + nombre.toUpperCase() + " AÑO-> " + year + " TOLVA->"
						+ entradas.getTolvas());
			}

			entradas = null;
			preservacion = null;
			transportista = null;
			proveedores = null;
			materia = null;

			String info = "Se ha registrado una nueva captura de materia prima";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");

			// PrimeFaces.current().ajax().update("frmPrincipal:acco:panel,
			// frmPrincipal:acco:panel2, frmPrincipal:acco:panel3");
			PrimeFaces.current().executeScript("PF('frmPrincipal').reset();");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel1");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel2");
		}
	}

	// **DATOS DEL TRANSPORTISTA, NOMBRE**//
	public List<String> buscarNombreTransportista(String nombre) throws SQLException {
		ITransportistaDao tDao = new TransportistaDaoImpl();
		return tDao.completeTransportista(nombre);
	}

	// **DATOS DEL TRANSPORTISTA, ID**//
	public int buscarTransportista(String nombre) throws SQLException {
		ITransportistaDao tDao = new TransportistaDaoImpl();
		return tDao.buscarTransportista(nombre);
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

	// **DATOS DE LA PRESERVACION, NOMBRE**//
	public List<String> buscarNombrePreservacion(String nombre) throws SQLException {
		IPreservacionDao pDao = new PreservacionDaoImpl();
		return pDao.completePreservacion(nombre);
	}

	// **DATOS DE LA PRESERVACION, ID**//
	public int buscarPreservacion(String nombre) throws SQLException {
		IPreservacionDao pDao = new PreservacionDaoImpl();
		return pDao.buscarPreservacion(nombre);
	}

	// **BUSCAR FOLIO DEL MES+**//
	public int buscarFolio() {
		int folio = 0;
		// **VALIDAR SI ESTA ACTIVA LA CAPTURA DEL MES ANTERIOR**//
		IFoliosDao fmAnt = new FoliosDaoImpl();
		IFoliosDao fDao = new FoliosDaoImpl();
		if (fmAnt.validarFolioMesAnt() == 1) {
			Month mes = LocalDate.now().getMonth().minus(1);
			String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			int year=0;
			if(nombre.equals("diciembre")) {
				LocalDate lastYearDate = LocalDate.now().minusYears(1);
				year = lastYearDate.getYear();
			}else {
				year = LocalDate.now().getYear();
			}
			mensajeMes = " ESTA HABILITADA LA CAPTURA PARA EL MES DE " + nombre.toUpperCase() + ", AÑO " + year +" ";
			LOGGER.info(mensajeMes);
			folio =  fDao.folioMax(year, nombre.toUpperCase());
			
		} else {

			Month mes = LocalDate.now().getMonth();
			int year = LocalDate.now().getYear();
			String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			folio =  fDao.folioMax(year, nombre.toUpperCase());
		}

		return folio;
	}

	public void activar() {

		IFoliosDao fDao = new FoliosDaoImpl();

		fDao.habilitarFolio();
		String info = "SE HA HABILITADO LA CAPTURA CON EL MES ANTERIOR";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");
	}

	public void desactivar() {
		IFoliosDao fDao = new FoliosDaoImpl();

		fDao.deshabilitarFolio();
		String info = "SE HA DESHABILITADO LA CAPTURA CON EL MES ANTERIOR";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");
	}

	// **BUSCAR PRECIO DE LA MATERIA PRIMA**//
	public Double buscarPrecio(int idProveedor, int idMateria) {
		IPreciosDao pDao = new PreciosDaoImpl();
		return pDao.buscarPrecios(idProveedor, idMateria);
	}

	public void actualizar() {
		IEntradasDao eDao = new EntradasDaoImpl();
		switch (us.getPerfiles().getNombrePerfil()) {
		case "Coordinador" -> entradasEditar.setCoordinadorProduccion(us.getIniciales());
		case "Control de calidad" -> entradasEditar.setControlCalidad(us.getIniciales());
		case "Gerencia" -> entradasEditar.setGerenciaProduccion(us.getIniciales());
		default -> {
		}
		}

		int banderaGerencia = 0;
		int banderaControlCalidad = 0;
		if (us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
			entradasEditar.setBloqueoEditar(1);
			entradasEditar.setEstado(2);
			banderaGerencia = 1;
		} else if (us.getPerfiles().getNombrePerfil().equals("Control de calidad")) {

			double humedad = Optional.of(Double.valueOf(entradasEditar.getHumedad().toString())).orElse(0.0);
			double calcios = Optional.of(Double.valueOf(entradasEditar.getAlcalinidad().toString())).orElse(0.0);
			if (calcios != 0.0 && humedad == 0.0) {
				entradasEditar.setEstado(3);
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES: " + humedad);
				LOGGER.info("VALIDACIÓN DE ALCALINIDAD ES: " + calcios);
			} else if (calcios != 0.0 && humedad > 0.0) {
				entradasEditar.setEstado(1);
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES >: " + humedad);
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES >: " + calcios);
			}

			banderaControlCalidad = 1;

			// **INICIA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
			if (entradasEditar.getMateria().getDescuentoCalcioTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoCalcioTa(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "A")));
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTa());
			}

			if (entradasEditar.getMateria().getDescuentoCalcioTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA B**//
				entradasEditar.setDescuentoCalcioTb(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "B")));
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTa(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "A")));
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTa());
			}
			if (entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTb(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "B")));
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(0)
					&& entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(0)) {
				entradasEditar.setDescuentoHumedad(BigDecimal.valueOf(ceros));
				LOGGER.info("PROVEEDOR SIN DESCUENTOS EN HUMEDADES EN LAS TABLAS A y B->"
						+ entradasEditar.getProveedores().getNombre());
			}

			// **FINALIZA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
		}

		// **KG PORCENTAJE**//

		double kg_porcentaje = 0.0;

		// **VALIDAR SI ES NEGATIVO**//
		if (Double.valueOf(entradasEditar.getPorcentajeMerma().toString()) < 0.0) {
			kg_porcentaje = Double.valueOf(entradasEditar.getKgEmbarcados().toString());
			LOGGER.info("EL PORCENTAJE ES NEGATIVO SE CALCULA CON LOS KILOS EMBARCADOS: " + kg_porcentaje);
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		} else {
			kg_porcentaje = Double.valueOf(entradasEditar.getKgEmbarcados().toString())
					- (((Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
							+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
							+ Double.valueOf(entradasEditar.getPorcentajeMerma().toString())
							- Double.valueOf(entradasEditar.getCalculoKgMerma().toString()))
							* Double.valueOf(entradasEditar.getKgEmbarcados().toString())) / 100);
			LOGGER.info("EL PORCENTAJE ES POSITIVO SE CALCULA CON EL FLUJO NORMAL: " + kg_porcentaje);
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		}

		entradasEditar.setPrecioCalcCcp(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaConPelo().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCcp().toString())));
		entradasEditar.setPrecioCalcC1(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnzaPrimera().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioC1().toString())));
		entradasEditar.setPrecioCalcC2(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnzaSegunda().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioC2().toString())));
		entradasEditar.setPrecioCalcCs(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaSalada().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCs().toString())));
		entradasEditar.setPrecioCalcDr(BigDecimal
				.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getDesbarbeRecorte().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioDr().toString())));
		entradasEditar.setPrecioCalcCm(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCerdoMexicano().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCm().toString())));
		entradasEditar.setPrecioCalcCo(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getOrejaCachete().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCo().toString())));
		entradasEditar.setPrecioCalcPc(BigDecimal
				.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getPedaceriaConPelo().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioPc().toString())));
		entradasEditar.setPrecioCalcP(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getPedaceria().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioP().toString())));
		entradasEditar.setPrecioCalcDa(BigDecimal
				.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getDescarneAdherido().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioDa().toString())));
		entradasEditar.setPrecioCalcDs(BigDecimal
				.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getDescarneSeparado().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioDs().toString())));
		entradasEditar.setPrecioCalcCdi(BigDecimal
				.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCueroDepiladoIntegral().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCdi().toString())));
		entradasEditar.setPrecioCalcG(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getGarra().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioG().toString())));
		entradasEditar.setPrecioCalcCe(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCueroEnSangre().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCe().toString())));

		double sumaSubtotal = 0.0;
		sumaSubtotal = (Double.valueOf(entradasEditar.getPrecioCalcCcp().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcC1().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcC2().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCs().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcDr().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCm().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCo().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcPc().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcP().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcDa().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcDs().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCdi().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcG().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCe().toString()));

		double iva = 0.0;
		iva = sumaSubtotal * .16;

		double tCatura = 0.0;
		tCatura = iva + sumaSubtotal;

		entradasEditar.setSubtotal(BigDecimal.valueOf(sumaSubtotal));
		entradasEditar.setIva(BigDecimal.valueOf(iva));
		entradasEditar.setTotalCaptura(BigDecimal.valueOf(tCatura));
		// **CALCULOS DE LOS PRECIOS**//

		LOGGER.info(
				"->PRECIO: " + kg_porcentaje + " ->SUBTOTAL:" + sumaSubtotal + " ->IVA:" + iva + " ->TOTAL:" + tCatura);

		eDao.actualizarEntradas(entradasEditar);
		Correo c = new Correo();

		c.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getProveedores().getNombre(),
				entradasEditar.getFactura(), entradasEditar.getMateria().getTipo(), banderaGerencia,
				banderaControlCalidad);

	}

	public void desbloquear() {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS SET BLOQUEO_EDITAR=0 WHERE ID_ENTRADA='"
							+ entradasDesbloquear.getIdEntrada() + "'");
			ps.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!",
					"SE HA DESBLOQUEADO CORRECTAMENTE LA CAPTURA"));
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("Error al desbloquear: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	public void calcularTotal() {

		double suma = 0.0;
		suma = (dato1 + dato2 + dato3 + dato4 + dato5 + dato6 + dato7 + dato8 + dato9 + dato10 + dato11 + dato12
				+ dato13 + dato14);
		if (suma > 100) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡ERROR!", "EL TOTAL DEBE SER IGUAL A 100% O MENOR"));
			entradas.setTotal(new BigDecimal(0.0));

			String info = "EL TOTAL DEBE SER IGUAL A 100% O MENOR";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Ooops...!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
		} else {
			entradas.setTotal(BigDecimal.valueOf(suma));
		}

	}

	public void calcularTotalPC() {

		double suma = 0.0;
		suma = (Double.parseDouble(entradasEditar.getCarnazaConPelo().toString())
				+ Double.parseDouble(entradasEditar.getCarnzaPrimera().toString())
				+ Double.parseDouble(entradasEditar.getCarnzaSegunda().toString())
				+ Double.parseDouble(entradasEditar.getCarnazaSalada().toString())
				+ Double.parseDouble(entradasEditar.getDesbarbeRecorte().toString())
				+ Double.parseDouble(entradasEditar.getCerdoMexicano().toString())
				+ Double.parseDouble(entradasEditar.getOrejaCachete().toString())
				+ Double.parseDouble(entradasEditar.getPedaceriaConPelo().toString())
				+ Double.parseDouble(entradasEditar.getPedaceria().toString())
				+ Double.parseDouble(entradasEditar.getDescarneAdherido().toString())
				+ Double.parseDouble(entradasEditar.getDescarneSeparado().toString())
				+ Double.parseDouble(entradasEditar.getCueroDepiladoIntegral().toString())
				+ Double.parseDouble(entradasEditar.getGarra().toString())
				+ Double.parseDouble(entradasEditar.getCueroEnSangre().toString()));
		if (suma > 100) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡ERROR!", "EL TOTAL DEBE SER IGUAL A 100% O MENOR"));
			entradasEditar.setTotal(new BigDecimal(0.0));

			String info = "EL TOTAL DEBE SER IGUAL A 100% O MENOR";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Ooops...!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
		} else {
			entradasEditar.setTotal(BigDecimal.valueOf(suma));
		}

	}

	public void aguaMerma() {
		double resta = 0.0;
		resta = kilosBascula - kilosBasculaMerma;
		kilosRecibidos = resta;
	}

	// #################################//
	// **KG EMBARCADOS - KG RECIBIDOS | KG NETOS - KG RECIBIDOS**//
	public void totalKilos() {
		double resta = 0.0;
		if (kilosEmbarcados > 0.0) {
			resta = (kilosEmbarcados - kilosRecibidos);
		}
		if (kilosNetos > 0.0) {
			resta = (kilosNetos - kilosRecibidos);
		}
		entradas.setMerma(BigDecimal.valueOf(resta));

	}

	// **KG EMBARCADOS - KG RECIBIDOS | KG NETOS - KG RECIBIDOS**//
	public void porcentajeTotal() {
		double porcentaje = 0.0;
		if (kilosEmbarcados > 0.0) {
			porcentaje = (Double.parseDouble(entradas.getMerma().toString()) * 100) / (kilosEmbarcados);
		}
		if (kilosNetos > 0.0) {
			porcentaje = (Double.parseDouble(entradas.getMerma().toString()) * 100) / (kilosNetos);
		}

		entradas.setPorcentajeMerma(BigDecimal.valueOf(Double.parseDouble(String.format("%.2f", porcentaje))));
	}
	// #################################//

	// #################################//
	// **KG EMBARCADOS - KG RECIBIDOS | KG NETOS - KG RECIBIDOS**//
	public void totalKilosPC() {
		double resta = 0.0;
		if (Double.valueOf(entradasEditar.getKgEmbarcados().toString()) > 0.0) {
			resta = (Double.valueOf(entradasEditar.getKgEmbarcados().toString())
					- Double.valueOf(entradasEditar.getKgRecibidos().toString()));
		}
		if (Double.valueOf(entradasEditar.getKgNetos().toString()) > 0.0) {
			resta = (Double.valueOf(entradasEditar.getKgNetos().toString())
					- Double.valueOf(entradasEditar.getKgRecibidos().toString()));
		}
		entradasEditar.setMerma(BigDecimal.valueOf(resta));

	}

	// **KG EMBARCADOS - KG RECIBIDOS | KG NETOS - KG RECIBIDOS**//
	public void porcentajeTotalPC() {
		double porcentaje = 0.0;
		if (Double.valueOf(entradasEditar.getKgEmbarcados().toString()) > 0.0) {
			porcentaje = (Double.parseDouble(entradasEditar.getMerma().toString()) * 100)
					/ (Double.valueOf(entradasEditar.getKgEmbarcados().toString()));
		}
		if (Double.valueOf(entradasEditar.getKgNetos().toString()) > 0.0) {
			porcentaje = (Double.parseDouble(entradasEditar.getMerma().toString()) * 100)
					/ (Double.valueOf(entradasEditar.getKgNetos().toString()));
		}

		entradasEditar.setPorcentajeMerma(BigDecimal.valueOf(Double.parseDouble(String.format("%.2f", porcentaje))));
	}
	// #################################//

	// **PARA LOS DESCUENTOS EN LAS TABLASE DE LAS HUMEDADES**//
	public double descuentoHumedadTabla_AB(String porcentaje, String tabla) {
		double resultado = 0.0;
		String sql = "";
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_A WHERE HUMEDAD <= " + porcentaje
					+ " ORDER BY ID_HUMEDAD DESC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_B WHERE HUMEDAD <= " + porcentaje
					+ " ORDER BY ID_HUMEDAD DESC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				resultado = 0.0;
			} else {
				while (rs.next()) {
					resultado = rs.getDouble("DESCUENTO");
					LOGGER.info(entradasEditar.getProveedores().getNombre());
					LOGGER.info("DESCUENTO HUMEDAD DE LA TABLA: " + tabla + ":->" + resultado);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error(ex);

		}

		return resultado;
	}

	// **PARA LOS DESCUENTOS EN LAS TABLAS DE LOS CALCIOS**//
	public double descuentoCalciosTabla_AB(String porcentaje, String tabla) {
		double resultado = 0.0;
		String sql = "";
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_A WHERE ALCALINIDAD >= " + porcentaje
					+ " ORDER BY ID_CALCIOS ASC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_B WHERE ALCALINIDAD >= " + porcentaje
					+ " ORDER BY ID_CALCIOS ASC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				resultado = 0.0;
			} else {
				while (rs.next()) {
					resultado = rs.getDouble("DESCUENTO");
					LOGGER.info(entradasEditar.getMateria().getTipo());
					LOGGER.info("DESCUENTO DE CALCIOS DE LA TABLA: " + tabla + ":->" + resultado);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error(ex);
		}

		return resultado;
	}

	public void visualizarReporte(String idEntrada, int tolva, int estado, String iniciales) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		if (us.getPerfiles().getIdPerfil() == 5) {
			IEntradasDao fDao = new EntradasDaoImpl();
			Entradas e = new Entradas();
			e = fDao.validarFechaImpresion(Integer.parseInt(idEntrada));

			Optional<Date> validarFecha = Optional.ofNullable(e.getFechaImpresionContador());

			if (validarFecha.isEmpty()) {
				IEntradasDao eDao = new EntradasDaoImpl();
				SimpleDateFormat fec = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				eDao.actualizarFechaImpresion(Integer.parseInt(idEntrada), fec.format(new Date()), tolva);
			} else {
				LOGGER.info("YA CONTIENE LA FECHA DE IMPRESIÓN: " + e.getFechaImpresionContador() + " NO. TOLVA: "
						+ e.getTolvas());
			}

		}
		ReporteLiberacion reporte = new ReporteLiberacion();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		String idDoc = "";

		switch (iniciales) {
		case "JMNV":
			idDoc = "1";
			break;
		case "EEL":
			idDoc = "2";
			break;
		default:
			idDoc = "0";
		}

		switch (estado) {
		case 0:
		case 1:
			ReporteLiberacionSF reporteSF = new ReporteLiberacionSF();
			ruta = servletContext.getRealPath("/REP/liberacionSF.jasper");
			reporteSF.getReporte(ruta, idEntrada, idDoc);
			break;
		case 2:
			ruta = servletContext.getRealPath("/REP/liberacion.jasper");
			reporte.getReporte(ruta, idEntrada, idDoc);
			break;
		default:

			throw new IllegalArgumentException("Unexpected value: " + estado);
		}

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void actualizarPerfilCoord() throws SQLException {
		IEntradasDao eDao = new EntradasDaoImpl();

		preservacion.setIdPreservacion(buscarPreservacion(filterPreservacion));
		transportista.setIdTransportista(buscarTransportista(filterTransportista));
		proveedores.setIdProveedor(buscarProveedor(filterProveedor));
		materia.setIdMateria(buscarMateria(filterMateria));

		entradasEditar.setMateria(materia);
		entradasEditar.setPreservacion(preservacion);
		entradasEditar.setTransportista(transportista);
		entradasEditar.setProveedores(proveedores);

//		entradasEditar.setKgEmbarcados(BigDecimal.valueOf(kilosEmbarcados));
//		entradasEditar.setKgRecibidos(BigDecimal.valueOf(kilosRecibidos));
//		entradasEditar.setKgNetos(BigDecimal.valueOf(kilosNetos));
		// **PRECIO DE LA CARNAZA CON PELO**//

//		entradasEditar.setCarnazaConPelo(BigDecimal.valueOf(dato1));
//		entradasEditar.setCarnzaPrimera(BigDecimal.valueOf(dato2));
//		entradasEditar.setCarnzaSegunda(BigDecimal.valueOf(dato3));
//		entradasEditar.setCarnazaSalada(BigDecimal.valueOf(dato4));
//		entradasEditar.setDesbarbeRecorte(BigDecimal.valueOf(dato5));
//		entradasEditar.setCerdoMexicano(BigDecimal.valueOf(dato6));
//		entradasEditar.setOrejaCachete(BigDecimal.valueOf(dato7));
//		entradasEditar.setPedaceriaConPelo(BigDecimal.valueOf(dato8));
//		entradasEditar.setPedaceria(BigDecimal.valueOf(dato9));
//		entradasEditar.setDescarneAdherido(BigDecimal.valueOf(dato10));
//		entradasEditar.setDescarneSeparado(BigDecimal.valueOf(dato11));
//		entradasEditar.setCueroDepiladoIntegral(BigDecimal.valueOf(dato12));
//		entradasEditar.setGarra(BigDecimal.valueOf(dato13));
//		entradasEditar.setCueroEnSangre(BigDecimal.valueOf(dato14));

		// VALIDAR DATOS

		if (entradasEditar.getSucursal() == null || entradasEditar.getFactura() == ""
				|| entradasEditar.getCertificado() == "" || transportista.getIdTransportista() == 0
				|| proveedores.getIdProveedor() == 0 || materia.getIdMateria() == 0
				|| preservacion.getIdPreservacion() == 0) {

			String suc = "";
			String fac = "";
			String cer = "";
			String trans = "";
			String pro = "";
			String mat = "";
			String pre = "";
			String ticket = "";

			if (entradasEditar.getSucursal() == null) {
				suc = "Sucursal, ";
			}

			if (entradasEditar.getFactura() == "") {
				fac = "Factura, ";
			}

			if (entradasEditar.getCertificado() == "") {
				cer = "Certificado, ";
			}
			if (materia.getIdMateria() == 0) {
				mat = "Identificación de la materia, ";
			}

			if (transportista.getIdTransportista() == 0) {
				trans = "Transportista, ";
			}
			if (proveedores.getIdProveedor() == 0) {
				pro = "Proveedor, ";
			}
			if (preservacion.getIdPreservacion() == 0) {
				pre = "Método de preservación, ";
			}

			String mensaje = "Te faltan campos: " + suc + ticket + fac + cer + trans + pro + mat + pre;
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + mensaje + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");

		} else {

			LOGGER.info("INFO DATOS: " + dato1 + dato2 + dato3 + dato4 + dato5 + dato6 + dato7 + dato8 + dato9 + dato10
					+ dato11 + dato12 + dato13 + dato14);

			if (Double.parseDouble(entradasEditar.getCarnazaConPelo().toString()) != ceros) {
				entradasEditar.setPrecioCcp(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 1)));
			} else {
				entradasEditar.setPrecioCcp(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCarnzaPrimera().toString()) != ceros) {
				entradasEditar.setPrecioC1(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 2)));
			} else {
				entradasEditar.setPrecioC1(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCarnzaSegunda().toString()) != ceros) {
				entradasEditar.setPrecioC2(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 3)));
			} else {
				entradasEditar.setPrecioC2(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCarnazaSalada().toString()) != ceros) {
				entradasEditar.setPrecioCs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 4)));
			} else {
				entradasEditar.setPrecioCs(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getDesbarbeRecorte().toString()) != ceros) {
				entradasEditar.setPrecioDr(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 5)));
			} else {
				entradasEditar.setPrecioDr(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCerdoMexicano().toString()) != ceros) {
				entradasEditar.setPrecioCm(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 6)));
			} else {
				entradasEditar.setPrecioCm(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getOrejaCachete().toString()) != ceros) {
				entradasEditar.setPrecioCo(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 7)));
			} else {
				entradasEditar.setPrecioCo(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getPedaceriaConPelo().toString()) != ceros) {
				entradasEditar.setPrecioPc(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 8)));
			} else {
				entradasEditar.setPrecioPc(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getPedaceria().toString()) != ceros) {
				entradasEditar.setPrecioP(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 9)));
			} else {
				entradasEditar.setPrecioP(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getDescarneAdherido().toString()) != ceros) {
				entradasEditar.setPrecioDa(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 10)));
			} else {
				entradasEditar.setPrecioDa(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getDescarneSeparado().toString()) != ceros) {
				entradasEditar.setPrecioDs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 11)));
			} else {
				entradasEditar.setPrecioDs(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCueroDepiladoIntegral().toString()) != ceros) {
				entradasEditar.setPrecioCdi(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 12)));
			} else {
				entradasEditar.setPrecioCdi(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getGarra().toString()) != ceros) {
				entradasEditar.setPrecioG(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 13)));
			} else {
				entradasEditar.setPrecioG(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCueroEnSangre().toString()) != ceros) {
				entradasEditar.setPrecioCe(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 14)));
			} else {

				entradasEditar.setPrecioCe(BigDecimal.valueOf(ceros));
			}

			double porcentaje15 = 1.5;
			if (Double.parseDouble(entradasEditar.getPorcentajeMerma().toString()) < porcentaje15) {
				porcentaje15 = 0.0;
			}

			entradasEditar.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

			eDao.actualizarPerfilCoord(entradasEditar);

			entradasEditar = null;
			preservacion = null;
			transportista = null;
			proveedores = null;
			materia = null;

			String info = "Se ha actualizado la captura de materia prima";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");

			// PrimeFaces.current().ajax().update("frmPrincipal:acco:panel,
			// frmPrincipal:acco:panel2, frmPrincipal:acco:panel3");
			PrimeFaces.current().executeScript("PF('frmPrincipal').reset();");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel1");
			PrimeFaces.current().ajax().update("frmPrincipal:acco:panel2");
		}
	}

	public void llenarInfo() {

		kilosEmbarcados = Double.parseDouble(entradasEditar.getKgEmbarcados().toString());
//			entradas.setKgRecibidos(BigDecimal.valueOf(kilosRecibidos));
//			entradas.setKgNetos(BigDecimal.valueOf(kilosNetos));
		//
//			// **PRECIO DE LA CARNAZA CON PELO**//
		//
//			entradas.setCarnazaConPelo(BigDecimal.valueOf(dato1));
//			entradas.setCarnzaPrimera(BigDecimal.valueOf(dato2));
//			entradas.setCarnzaSegunda(BigDecimal.valueOf(dato3));
//			entradas.setCarnazaSalada(BigDecimal.valueOf(dato4));
//			entradas.setDesbarbeRecorte(BigDecimal.valueOf(dato5));
//			entradas.setCerdoMexicano(BigDecimal.valueOf(dato6));
//			entradas.setOrejaCachete(BigDecimal.valueOf(dato7));
//			entradas.setPedaceriaConPelo(BigDecimal.valueOf(dato8));
//			entradas.setPedaceria(BigDecimal.valueOf(dato9));
//			entradas.setDescarneAdherido(BigDecimal.valueOf(dato10));
//			entradas.setDescarneSeparado(BigDecimal.valueOf(dato11));
//			entradas.setCueroDepiladoIntegral(BigDecimal.valueOf(dato12));
//			entradas.setGarra(BigDecimal.valueOf(dato13));
//			entradas.setCueroEnSangre(BigDecimal.valueOf(dato14));

	}

}
