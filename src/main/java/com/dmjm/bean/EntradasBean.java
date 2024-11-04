package com.dmjm.bean;

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
import com.dmjm.util.ReporteLiberacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

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

	private double ceros = 0.0;

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

		if (entradas.getSucursal() == null || entradas.getTicketBasculaToluca()=="" || entradas.getFactura()=="" || entradas.getCertificado() == ""
				|| transportista.getIdTransportista()==0 || proveedores.getIdProveedor()==0
				|| materia.getIdMateria()==0 || preservacion.getIdPreservacion()==0) {

			String suc = "";
			String fac = "";
			String cer = "";
			String trans = "";
			String pro = "";
			String mat = "";
			String pre = "";
			String ticket="";

			if (entradas.getSucursal() == null) {
				suc = "Sucursal, ";
			}

			if (entradas.getFactura() =="") {
				fac = "Factura, ";
			}

			if (entradas.getCertificado() == "") {
				cer = "Certificado, ";
			}
			if(materia.getIdMateria()==0) {
				mat="Identificación de la materia, ";
			}

			if (transportista.getIdTransportista()==0) {
				trans = "Transportista, ";
			}
			if (proveedores.getIdProveedor()==0) {
				pro = "Proveedor, ";
			}
			if (preservacion.getIdPreservacion()==0) {
				pre = "Método de preservación, ";
			}
			if(entradas.getTicketBasculaToluca()=="") {
				ticket="Ticket Toluca, ";
			}

			String mensaje = "Te faltan campos: " + suc  + ticket +  fac + cer  + trans  + pro  + mat  + pre; 
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
			case "Control de calidad" -> entradas.setControlCalidad(us.getIniciales());
			case "Gerencia" -> entradas.setGerenciaProduccion(us.getIniciales());
			default -> {
			}
			}

			if (us.getPerfiles().getNombrePerfil().equals("Coordinador")) {
				entradas.setEstado(0);
			}

			double porcentaje15 = 1.5;
			if (Double.parseDouble(entradas.getPorcentajeMerma().toString()) < porcentaje15) {
				porcentaje15 = 0.0;
			}

			entradas.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

			eDao.guardarEntradas(entradas);

			IFoliosDao fDao = new FoliosDaoImpl();
			Month mes = LocalDate.now().getMonth();
			int year = LocalDate.now().getYear();
			String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

			fDao.actualizarFolio(year, nombre.toUpperCase(), entradas.getTolvas());

			entradas = new Entradas();
			preservacion = new Preservacion();
			transportista = new Transportista();
			proveedores = new Proveedores();
			materia = new Materia();

			String info = "Se ha registrado una nueva captura de materia prima";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
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
		Month mes = LocalDate.now().getMonth();
		int year = LocalDate.now().getYear();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		IFoliosDao fDao = new FoliosDaoImpl();
		return fDao.folioMax(year, nombre.toUpperCase());
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

		if (us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
			entradasEditar.setBloqueoEditar(1);
			entradasEditar.setEstado(2);

		} else if (us.getPerfiles().getNombrePerfil().equals("Control de calidad")) {
			entradasEditar.setEstado(1);

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

			// **FINALIZA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
		}
		eDao.actualizarEntradas(entradasEditar);

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

	public void totalKilos() {
		double resta = 0.0;
		resta = (kilosEmbarcados - kilosRecibidos);
		entradas.setMerma(BigDecimal.valueOf(resta));

	}

	public void porcentajeTotal() {
		double porcentaje = 0.0;
		porcentaje = (Double.parseDouble(entradas.getMerma().toString()) * 100) / (kilosEmbarcados);
		String.format("%.2f", porcentaje);
		entradas.setPorcentajeMerma(BigDecimal.valueOf(Double.parseDouble(String.format("%.2f", porcentaje))));
	}

	public double descuentoHumedadTabla_AB(String porcentaje, String tabla) {
		double resultado = 0.0;
		String sql = "";
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_A " + "WHERE HUMEDAD >= " + porcentaje
					+ " ORDER BY ID_HUMEDAD ASC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_B " + "WHERE HUMEDAD >= " + porcentaje
					+ " ORDER BY ID_HUMEDAD ASC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {

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

	public double descuentoCalciosTabla_AB(String porcentaje, String tabla) {
		double resultado = 0.0;
		String sql = "";
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_A " + "WHERE ALCALINIDAD >= " + porcentaje
					+ " ORDER BY ID_CALCIOS ASC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_B " + "WHERE ALCALINIDAD >= " + porcentaje
					+ " ORDER BY ID_CALCIOS ASC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {

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

	public void visualizarReporte(String idEntrada, int tolva) {
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

		ruta = servletContext.getRealPath("/REP/liberacion.jasper");

		reporte.getReporte(ruta, idEntrada);

		FacesContext.getCurrentInstance().responseComplete();

	}

}
