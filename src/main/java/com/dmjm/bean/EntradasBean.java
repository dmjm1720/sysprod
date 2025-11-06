package com.dmjm.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

import com.dmjm.dao.IBitacoraPreciosDao;
import com.dmjm.dao.IEntradasDao;
import com.dmjm.dao.IFoliosDao;
import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IPreciosDao;
import com.dmjm.dao.IPreservacionDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.dao.ITransportistaDao;
import com.dmjm.impl.BitacoraPreciosDaoImpl;
import com.dmjm.impl.EntradasDaoImpl;
import com.dmjm.impl.FoliosDaoImpl;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.PreciosDaoImpl;
import com.dmjm.impl.PreservacionDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.impl.TransportistaDaoImpl;
import com.dmjm.model.BitacoraPrecios;
import com.dmjm.model.Entradas;
import com.dmjm.model.Materia;
import com.dmjm.model.Preservacion;
import com.dmjm.model.Proveedores;
import com.dmjm.model.Transportista;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.Correo;
import com.dmjm.util.CorreoPrecios;
import com.dmjm.util.CorreoRangos;
import com.dmjm.util.IvaMateriaPrima;
import com.dmjm.util.PrecioMateria;
import com.dmjm.util.ReporteLiberacion;
import com.dmjm.util.ReporteLiberacionSF;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named(value = "entradasBean")
@ViewScoped
public class EntradasBean extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(EntradasBean.class.getName());

	private List<Entradas> listarEntradas;
	private List<Entradas> listarEntradasPrecios;
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
	private double dato15 = 0.0;
	private double kilosEmbarcados = 0.0;
	private double kilosRecibidos = 0.0;
	private double kilosNetos = 0.0;
	private double kilosBascula = 0.0;
	private double kilosBasculaMerma = 0.0;

	private double ceros = 0.0;
	private String mensajeMes;

	private String datoHumedad = "";
	private String datoAlcalinidad = "";

	private UploadedFile pdfFile;

	private String file;

	private double porcentajeCalculo = 0.0;

	private Entradas entradasCertificado;

	private String alertaCalcios;
	private String alertaHumedad;

	private IvaMateriaPrima ivaMateriaPrima;

	@PostConstruct
	public void init() {
		listarEntradas = new ArrayList<>();
		listarEntradasPrecios = new ArrayList<>();
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
		entradasCertificado = new Entradas();
		file = "";

		ivaMateriaPrima = new IvaMateriaPrima();

	}

	public IvaMateriaPrima getIvaMateriaPrima() {
		return ivaMateriaPrima;
	}

	public void setIvaMateriaPrima(IvaMateriaPrima ivaMateriaPrima) {
		this.ivaMateriaPrima = ivaMateriaPrima;
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

	public List<Entradas> getListarEntradasPrecios() {
		IEntradasDao eDao = new EntradasDaoImpl();
		listarEntradasPrecios = eDao.listarEntradasPrecios();
		return listarEntradasPrecios;
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

	public String getDatoHumedad() {
		return datoHumedad;
	}

	public void setDatoHumedad(String datoHumedad) {
		this.datoHumedad = datoHumedad;
	}

	public String getDatoAlcalinidad() {
		return datoAlcalinidad;
	}

	public void setDatoAlcalinidad(String datoAlcalinidad) {
		this.datoAlcalinidad = datoAlcalinidad;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public UploadedFile getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(UploadedFile pdfFile) {
		this.pdfFile = pdfFile;
	}

	public double getPorcentajeCalculo() {
		return porcentajeCalculo;
	}

	public void setPorcentajeCalculo(double porcentajeCalculo) {
		this.porcentajeCalculo = porcentajeCalculo;
	}

	public Entradas getEntradasCertificado() {
		return entradasCertificado;
	}

	public void setEntradasCertificado(Entradas entradasCertificado) {
		this.entradasCertificado = entradasCertificado;
	}

	public void setListarEntradas(List<Entradas> listarEntradas) {
		this.listarEntradas = listarEntradas;
	}

	public double getDato15() {
		return dato15;
	}

	public void setDato15(double dato15) {
		this.dato15 = dato15;
	}

	public String getAlertaCalcios() {
		return alertaCalcios;
	}

	public void setAlertaCalcios(String alertaCalcios) {
		this.alertaCalcios = alertaCalcios;
	}

	public String getAlertaHumedad() {
		return alertaHumedad;
	}

	public void setAlertaHumedad(String alertaHumedad) {
		this.alertaHumedad = alertaHumedad;
	}

	// *****************GUARDAR*********************//
	public void guardar() throws SQLException {
		IEntradasDao eDao = new EntradasDaoImpl();

		preservacion.setIdPreservacion(buscarPreservacion(filterPreservacion));

		Optional<String> valorTransportista = Optional.ofNullable(filterTransportista);
		String datoTransportista = valorTransportista.orElse("POR DEFINIR");
		transportista.setIdTransportista(buscarTransportista(datoTransportista));

		proveedores.setIdProveedor(buscarProveedor(filterProveedor));
		materia.setIdMateria(buscarMateria(filterMateria));

		entradas.setTipoMoneda(prov(filterProveedor));
		entradas.setMateria(materia);
		entradas.setPreservacion(preservacion);
		entradas.setTransportista(transportista);
		entradas.setProveedores(proveedores);

		entradas.setKgEmbarcados(BigDecimal.valueOf(kilosEmbarcados));
		entradas.setKgRecibidos(BigDecimal.valueOf(kilosRecibidos));
		entradas.setKgNetos(BigDecimal.valueOf(kilosNetos));
		entradas.setKgBascula(BigDecimal.valueOf(kilosBascula));
		entradas.setKgBasculaMerma(BigDecimal.valueOf(kilosBasculaMerma));

		// **COLOCAR CERO A DESCUENTOS POR HUMEDAD Y ALCALINIDAD**//
		entradas.setDescuentoCalcioTa(new BigDecimal(0));
		entradas.setDescuentoCalcioTb(new BigDecimal(0));
		entradas.setDescuentoHumedadTa(new BigDecimal(0));
		entradas.setDescuentoHumedadTb(new BigDecimal(0));
		entradas.setDescuentoHumedad(new BigDecimal(0));
		entradas.setDescuentoCalcio(new BigDecimal(0));
		// **PRECIO DE LA CARNAZA CON PELO**//

		entradas.setCarnazaConPelo(BigDecimal.valueOf(dato1));
		entradas.setCarnazaPrimera(BigDecimal.valueOf(dato2));
		entradas.setCarnazaSegunda(BigDecimal.valueOf(dato3));
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
		entradas.setCerdoAmericano(BigDecimal.valueOf(dato15));

		// VALIDAR DATOS
//		if (entradas.getSucursal() == null || entradas.getFactura() == "" || entradas.getCertificado() == ""
//				|| transportista.getIdTransportista() == 0 || proveedores.getIdProveedor() == 0
//				|| materia.getIdMateria() == 0 || preservacion.getIdPreservacion() == 0) {
		if (entradas.getSucursal() == null || proveedores.getIdProveedor() == 0 || materia.getIdMateria() == 0
				|| preservacion.getIdPreservacion() == 0) {

			String suc = "";
//			String fac = "";
//			String cer = "";
//			String trans = "";
			String pro = "";
			String mat = "";
			String pre = "";
			String ticket = "";

			if (entradas.getSucursal() == null) {
				suc = "Sucursal, ";
			}

//			if (entradas.getFactura() == "") {
//				fac = "Factura, ";
//			}

//			if (entradas.getCertificado() == "") {
//				cer = "Certificado, ";
//			}
			if (materia.getIdMateria() == 0) {
				mat = "Identificación de la materia, ";
			}

//			if (transportista.getIdTransportista() == 0) {
//				trans = "Transportista, ";
//			}
			if (proveedores.getIdProveedor() == 0) {
				pro = "Proveedor, ";
			}
			if (preservacion.getIdPreservacion() == 0) {
				pre = "Método de preservación, ";
			}
//			if (entradas.getTicketBasculaToluca() == "") {
//				ticket = "Ticket Toluca, ";
//			}

			String mensaje = "Te faltan campos: " + suc + ticket + pro + mat + pre;
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + mensaje + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");

		} else {

			LOGGER.info("INFO DATOS: " + dato1 + dato2 + dato3 + dato4 + dato5 + dato6 + dato7 + dato8 + dato9 + dato10
					+ dato11 + dato12 + dato13 + dato14 + dato15);

			if (dato1 != ceros) {
				LOGGER.info("Dato1: " + dato1 + " CUERO INTEGRAL SALADO CON PELO " + proveedores.getIdProveedor());
				entradas.setPrecioCcp(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 1)));
			} else {
				entradas.setPrecioCcp(BigDecimal.valueOf(ceros));
			}

			if (dato2 != ceros) {
				LOGGER.info("Dato2: " + dato2 + " CARNAZA COMPLETA " + proveedores.getIdProveedor());
				entradas.setPrecioC1(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 2)));
			} else {
				entradas.setPrecioC1(BigDecimal.valueOf(ceros));
			}

			if (dato3 != ceros) {
				LOGGER.info("Dato3: " + dato3 + " CARNAZA PEDAZOS " + proveedores.getIdProveedor());
				entradas.setPrecioC2(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 3)));
			} else {
				entradas.setPrecioC2(BigDecimal.valueOf(ceros));
			}

			if (dato4 != ceros) {
				LOGGER.info("Dato4: " + dato4 + " CARNAZA SALADA " + proveedores.getIdProveedor());
				entradas.setPrecioCs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 4)));
			} else {
				entradas.setPrecioCs(BigDecimal.valueOf(ceros));
			}

			if (dato5 != ceros) {
				LOGGER.info("Dato5: " + dato5 + " DESBARBE / RECORTES " + proveedores.getIdProveedor());
				entradas.setPrecioDr(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 5)));
			} else {
				entradas.setPrecioDr(BigDecimal.valueOf(ceros));
			}

			if (dato6 != ceros) {
				LOGGER.info("Dato6: " + dato6 + " CERDO MEXICANO " + proveedores.getIdProveedor());
				entradas.setPrecioCm(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 6)));
			} else {
				entradas.setPrecioCm(BigDecimal.valueOf(ceros));
			}

			if (dato7 != ceros) {
				LOGGER.info("Dato7: " + dato7 + " CACHETE " + proveedores.getIdProveedor());
				entradas.setPrecioCo(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 7)));
			} else {
				entradas.setPrecioCo(BigDecimal.valueOf(ceros));
			}

			if (dato8 != ceros) {
				LOGGER.info("Dato8: " + dato8 + " RECORTE DE CUERO CON PELO " + proveedores.getIdProveedor());
				entradas.setPrecioPc(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 8)));
			} else {
				entradas.setPrecioPc(BigDecimal.valueOf(ceros));
			}

			if (dato9 != ceros) {
				LOGGER.info("Dato9: " + dato9 + " PEDACERÍA " + proveedores.getIdProveedor());
				entradas.setPrecioP(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 9)));
			} else {
				entradas.setPrecioP(BigDecimal.valueOf(ceros));
			}

			if (dato10 != ceros) {
				LOGGER.info("Dato10: " + dato10 + " DESCARNE ADHERIDO " + proveedores.getIdProveedor());
				entradas.setPrecioDa(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 10)));
			} else {
				entradas.setPrecioDa(BigDecimal.valueOf(ceros));
			}

			if (dato11 != ceros) {
				LOGGER.info("Dato11: " + dato11 + " DESCARNE SEPARADO " + proveedores.getIdProveedor());
				entradas.setPrecioDs(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 11)));
			} else {
				entradas.setPrecioDs(BigDecimal.valueOf(ceros));
			}

			if (dato12 != ceros) {
				LOGGER.info("Dato12: " + dato12 + " CUERO DEPILADO " + proveedores.getIdProveedor());
				entradas.setPrecioCdi(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 12)));
			} else {
				entradas.setPrecioCdi(BigDecimal.valueOf(ceros));
			}

			if (dato13 != ceros) {
				LOGGER.info("Dato13: " + dato13 + " GARRA Y FALDA " + proveedores.getIdProveedor());
				entradas.setPrecioG(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 13)));
			} else {
				entradas.setPrecioG(BigDecimal.valueOf(ceros));
			}

			if (dato14 != ceros) {
				LOGGER.info("Dato14: " + dato14 + " CUERO EN SANGRE " + proveedores.getIdProveedor());
				entradas.setPrecioCe(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 14)));
			} else {
				entradas.setPrecioCe(BigDecimal.valueOf(ceros));
			}
			if (dato15 != ceros) {
				LOGGER.info("Dato15: " + dato15 + " CERDO AMERICANO " + proveedores.getIdProveedor());
				entradas.setPrecioCa(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 15)));
			} else {

				entradas.setPrecioCa(BigDecimal.valueOf(ceros));
			}

			switch (us.getPerfiles().getNombrePerfil()) {
			case "Coordinador" -> entradas.setCoordinadorProduccion(us.getIniciales());
			case "Gerencia" -> entradas.setCoordinadorProduccion(us.getIniciales());
			case "Operador" -> entradas.setCoordinadorProduccion(us.getIniciales());
			default -> {
			}
			}

			if (us.getPerfiles().getNombrePerfil().equals("Coordinador")
					|| us.getPerfiles().getNombrePerfil().equals("Gerencia")
					|| us.getPerfiles().getNombrePerfil().equals("Operador")) {
				entradas.setEstado(0);

				entradas.setGerenciaProduccion(null);
				entradas.setFechaLiberacion(null);

			}

			Proveedores prov = new Proveedores();
			prov = buscarMermaProveedor(proveedores.getIdProveedor());
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			LOGGER.info(
					"Nombre del proveedor:" + prov.getNombre() + " Descuento por merma: " + prov.getDescuentoMerma());

			double porcentaje15 = 0.0;
			porcentaje15 = Double.valueOf(prov.getDescuentoMerma().toString());

			if (Double.parseDouble(entradas.getPorcentajeMerma().toString()) < porcentaje15) {
				porcentaje15 = 0.0;
			}

			// SE TOMA EL PORCENTAJE DE LA MERMA DEL PROVEEDOR
			entradas.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

			// VALIDAR NUEVAMENTE EL FOLIO
			LOGGER.info("FOLIO DE LA TOLVA ANTES DE REALIZAR NUEVAMENTE LA BÚSQUEDA:  " + entradas.getTolvas());
			entradas.setTolvas(buscarFolio());
			LOGGER.info("FOLIO DE LA TOLVA DESPUÉS DE REALIZAR LA BÚSQUEDA:  " + entradas.getTolvas());

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

			String script = "setTimeout(function() { window.location.href='Entradas.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
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

	public String prov(String nombre) throws SQLException {
		IProveedoresDao provDao = new ProveedoresDaoImpl();
		return provDao.buscarTipoMonedaProveedor(nombre);
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

	// **DESCUENTO DE MERMA DEL PROVEEDOR**//
	public Proveedores buscarMermaProveedor(int idPro) {
		Proveedores pro = new Proveedores();
		IProveedoresDao pDao = new ProveedoresDaoImpl();
		pro = pDao.buscarMerma(idPro);
		return pro;
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
			int year = 0;
			if (nombre.equals("diciembre")) {
				LocalDate lastYearDate = LocalDate.now().minusYears(1);
				year = lastYearDate.getYear();
			} else {
				year = LocalDate.now().getYear();
			}
			mensajeMes = " ESTA HABILITADA LA CAPTURA PARA EL MES DE " + nombre.toUpperCase() + ", AÑO " + year + " ";
			LOGGER.info(mensajeMes);
			folio = fDao.folioMax(year, nombre.toUpperCase());

		} else {

			Month mes = LocalDate.now().getMonth();
			int year = LocalDate.now().getYear();
			String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
			folio = fDao.folioMax(year, nombre.toUpperCase());
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

	public void validarLiberacion(String val1, String val2) {

		Optional<String> valHumedad = Optional.ofNullable(val1);
		Optional<String> valAlcalinidad = Optional.ofNullable(val2);

		if (valHumedad.isPresent()) {
			datoHumedad = valHumedad.orElse("NO HAY DATO DE HUMEDAD");

		}
		if (valAlcalinidad.isPresent()) {
			datoAlcalinidad = valAlcalinidad.orElse("NO HAY DATO DE HUMEDAD");
		}

	}

	// **ACTUALIZAR PERFIL CONTROL DE CALIDAD**//
	public void actualizar() {
		alertaCalcios = "";
		alertaHumedad = "";
		IEntradasDao eDao = new EntradasDaoImpl();
		switch (us.getPerfiles().getNombrePerfil()) {
		case "Coordinador" -> entradasEditar.setCoordinadorProduccion(us.getIniciales());
		case "Control de calidad" -> entradasEditar.setControlCalidad(us.getIniciales());
		case "Gerencia" -> entradasEditar.setGerenciaProduccion(us.getIniciales());
		default -> {
		}
		}

		// **BUSCAR ACTUALIZACIÓN DE PRECIO**//
		LOGGER.warn(">>>>>>>>>>>>>>>>>>INICIA BUSCAR ACTUALIZACIÓN DE PRECIO<<<<<<<<<<<<<<<<<<");
		if (Double.parseDouble(entradasEditar.getCarnazaConPelo().toString()) != ceros) {
			LOGGER.info("Dato1: " + entradasEditar.getCarnazaConPelo().toString()
					+ " CUERO INTEGRAL SALADO CON PELO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCcp(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 1)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCcp());
		} else {
			entradasEditar.setPrecioCcp(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaPrimera().toString()) != ceros) {
			LOGGER.info("Dato2: " + entradasEditar.getCarnazaPrimera().toString() + " CARNAZA COMPLETA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradas.setPrecioC1(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 2)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioC1());
		} else {
			entradasEditar.setPrecioC1(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaSegunda().toString()) != ceros) {
			LOGGER.info("Dato3: " + entradasEditar.getCarnazaSegunda().toString() + " CARNAZA PEDAZOS >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioC2(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 3)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioC2());
		} else {
			entradasEditar.setPrecioC2(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaSalada().toString()) != ceros) {
			LOGGER.info("Dato4: " + entradasEditar.getCarnazaSalada().toString() + " CARNAZA SALADA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCs(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 4)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCs());
		} else {
			entradasEditar.setPrecioCs(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDesbarbeRecorte().toString()) != ceros) {
			LOGGER.info("Dato5: " + entradasEditar.getDesbarbeRecorte().toString()
					+ " DESBARBE / RECORTES >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioDr(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 5)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDr());
		} else {
			entradasEditar.setPrecioDr(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCerdoMexicano().toString()) != ceros) {
			LOGGER.info("Dato6: " + entradasEditar.getCerdoMexicano().toString() + " CERDO MEXICANO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCm(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 6)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCm());
		} else {
			entradasEditar.setPrecioCm(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getOrejaCachete().toString()) != ceros) {
			LOGGER.info("Dato7: " + entradasEditar.getOrejaCachete().toString() + " CACHETE >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCo(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 7)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCo());
		} else {
			entradasEditar.setPrecioCo(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getPedaceriaConPelo().toString()) != ceros) {
			LOGGER.info("Dato8: " + entradasEditar.getPedaceriaConPelo().toString()
					+ " RECORTE DE CUERO CON PELO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioPc(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 8)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioPc());
		} else {
			entradasEditar.setPrecioPc(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getPedaceria().toString()) != ceros) {
			LOGGER.info("Dato9: " + entradasEditar.getPedaceria().toString() + " PEDACERÍA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioP(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 9)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioP());
		} else {
			entradasEditar.setPrecioP(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDescarneAdherido().toString()) != ceros) {
			LOGGER.info("Dato10: " + entradasEditar.getPrecioCcp().toString() + " DESCARNE ADHERIDO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioDa(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 10)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDa());
		} else {
			entradasEditar.setPrecioDa(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDescarneSeparado().toString()) != ceros) {
			LOGGER.info("Dato11: " + entradasEditar.getDescarneSeparado().toString()
					+ " DESCARNE SEPARADO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioDs(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 11)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDs());
		} else {
			entradasEditar.setPrecioDs(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCueroDepiladoIntegral().toString()) != ceros) {
			LOGGER.info("Dato12: " + entradasEditar.getCueroDepiladoIntegral().toString()
					+ " CUERO DEPILADO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCdi(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 12)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCdi());
		} else {
			entradasEditar.setPrecioCdi(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getGarra().toString()) != ceros) {
			LOGGER.info("Dato13: " + entradasEditar.getGarra().toString() + " GARRA Y FALDA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioG(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 13)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioG());
		} else {
			entradas.setPrecioG(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCueroEnSangre().toString()) != ceros) {
			LOGGER.info("Dato14: " + entradasEditar.getCueroEnSangre().toString() + " CUERO EN SANGRE >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCe(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 14)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCe());
		} else {

			entradasEditar.setPrecioCe(BigDecimal.valueOf(ceros));
		}
		if (Double.parseDouble(entradasEditar.getCerdoAmericano().toString()) != ceros) {
			LOGGER.info("Dato15: " + entradasEditar.getCerdoAmericano().toString() + " CERDO AMERICANO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCa(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 15)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCa());
		} else {

			entradasEditar.setPrecioCa(BigDecimal.valueOf(ceros));
		}
		LOGGER.warn(">>>>>>>>>>>>>>>>>>FIN BUSCAR ACTUALIZACIÓN DE PRECIO<<<<<<<<<<<<<<<<<<");
		@SuppressWarnings("unused")
		int banderaGerencia = 0;
		@SuppressWarnings("unused")
		int banderaControlCalidad = 0;

		double humedad = Optional.of(Double.valueOf(entradasEditar.getHumedad().toString())).orElse(0.0);
		double calcios = Optional.of(Double.valueOf(entradasEditar.getAlcalinidad().toString())).orElse(0.0);
		if (us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
			entradasEditar.setBloqueoEditar(1);
			entradasEditar.setEstado(2);
			banderaGerencia = 1;
		} else if (us.getPerfiles().getNombrePerfil().equals("Control de calidad")) {

			if (calcios != 0.0 && humedad == 0.0) {
				entradasEditar.setEstado(3);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES: " + humedad);
				LOGGER.info("VALIDACIÓN DE ALCALINIDAD ES: " + calcios);
			} else if (calcios != 0.0 && humedad > 0.0) {
				entradasEditar.setEstado(1);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES >: " + humedad);
				LOGGER.info("VALIDACIÓN DE CALCIOS ES >: " + calcios);
			}

			banderaControlCalidad = 1;

			// **INICIA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>INICA VALIDACIÓN DE DESCUENTOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			if (entradasEditar.getMateria().getDescuentoCalcioTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoCalcioTa(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "A")));
				LOGGER.info("CALCIOS TABLA A: " + entradasEditar.getAlcalinidad().toString());
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTa());
			}

			if (entradasEditar.getMateria().getDescuentoCalcioTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA B**//
				entradasEditar.setDescuentoCalcioTb(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "B")));
				LOGGER.info("CALCIOS TABLA B: " + entradasEditar.getAlcalinidad().toString());
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTa(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "A")));
				LOGGER.info("HUMEDAD TABLA A: " + entradasEditar.getHumedad().toString());
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTa());
			}
			if (entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTb(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "B")));
				LOGGER.info("HUMEDAD TABLA B: " + entradasEditar.getHumedad().toString());
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(0)
					&& entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(0)) {
				entradasEditar.setDescuentoHumedad(BigDecimal.valueOf(ceros));
				LOGGER.info("PROVEEDOR SIN DESCUENTOS EN HUMEDADES EN LAS TABLAS A y B->"
						+ entradasEditar.getProveedores().getNombre());
			}
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>FINALIZA VALIDACIÓN DE DESCUENTOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			// **FINALIZA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//

			// MANDAR CORREO EN CASO DE //
			switch (alertaCalcios + "-" + alertaHumedad) {
			case "FUERA DE RANGO-FUERA DE RANGO" -> {
				CorreoRangos cr = new CorreoRangos();
				cr.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), alertaHumedad, humedad, alertaCalcios, calcios,
						Double.valueOf(entradasEditar.getDescuentoHumedad().toString()),
						Double.valueOf(entradasEditar.getDescuentoCalcio().toString()));
			}
			case "FUERA DE RANGO-OK" -> {
				CorreoRangos cr = new CorreoRangos();
				cr.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), alertaHumedad, humedad, alertaCalcios, calcios,
						Double.valueOf(entradasEditar.getDescuentoHumedad().toString()),
						Double.valueOf(entradasEditar.getDescuentoCalcio().toString()));
			}
			case "OK-FUERA DE RANGO" -> {
				CorreoRangos cr = new CorreoRangos();
				cr.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), alertaHumedad, humedad, alertaCalcios, calcios,
						Double.valueOf(entradasEditar.getDescuentoHumedad().toString()),
						Double.valueOf(entradasEditar.getDescuentoCalcio().toString()));
			}
			case "null-FUERA DE RANGO" -> {
				CorreoRangos cr = new CorreoRangos();
				cr.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), alertaHumedad, humedad, alertaCalcios, calcios,
						Double.valueOf(entradasEditar.getDescuentoHumedad().toString()),
						Double.valueOf(entradasEditar.getDescuentoCalcio().toString()));
			}
			case "FUERA DE RANGO-null" -> {
				CorreoRangos cr = new CorreoRangos();
				cr.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), alertaHumedad, humedad, alertaCalcios, calcios,
						Double.valueOf(entradasEditar.getDescuentoHumedad().toString()),
						Double.valueOf(entradasEditar.getDescuentoCalcio().toString()));
			}
			default -> {

			}
			}
		}
		// PORCENTAJE MERMA PROVEEDORES//
		Proveedores prov = new Proveedores();
		prov = buscarMermaProveedor(entradasEditar.getProveedores().getIdProveedor());
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		LOGGER.info("Nombre del proveedor:" + prov.getNombre() + " Descuento por merma: " + prov.getDescuentoMerma());

//		double porcentaje15 = 0.0;
//		porcentaje15 = Double.valueOf(prov.getDescuentoMerma().toString());
//
//		if (Double.parseDouble(entradasEditar.getPorcentajeMerma().toString()) < porcentaje15) {
//			porcentaje15 = 0.0;
//		}
//
//		// SE TOMA EL PORCENTAJE DE LA MERMA DEL PROVEEDOR
//		entradasEditar.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

		// VALIDAR EL PROVEEDOR SI TIENE DESCUENTO EN LA MERMA
		double porcentajeDescuentoProv = 0.0;
		LOGGER.info("PROVEEDOR:" + entradasEditar.getProveedores().getNombre() + " DESCUENTO MERMA: "
				+ entradasEditar.getProveedores().getDescuentoMerma());
		porcentajeDescuentoProv = Double.parseDouble(entradasEditar.getProveedores().getDescuentoMerma().toString());
		// **KG PORCENTAJE**//

		double kg_porcentaje = 0.0;

		LOGGER.info("Tipo de moneda: " + entradasEditar.getTipoMoneda());

		double porcentajeDeMerma = 0.0;

		porcentajeDeMerma = Double.valueOf(entradasEditar.getPorcentajeMerma().toString());
		LOGGER.warn("**********************************************************************");
		LOGGER.info("El porcentaje la merma es de: " + porcentajeDeMerma);
		LOGGER.warn("**********************************************************************");

		if (porcentajeDeMerma > 1.5) {
			LOGGER.warn("**********************************************************************");
			LOGGER.info("El porcentaje la merma es mayor, se aplica descuento de 1.5");
			LOGGER.info("El porcentaje con el descuento de 1.5: " + (porcentajeDeMerma - porcentajeDescuentoProv)
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
					+ Double.valueOf(entradasEditar.getKgNetos().toString()));
			LOGGER.warn("**********************************************************************");

		}

		if (porcentajeDeMerma < 1.5) {
			porcentajeDeMerma = 0.0;

		}

		// VALIDAR SI EL PROVEEDOR TIENE 0, NO SE CALCULA EN EL P0RCENTAJE DE LA MERMA
		if (prov.getDescuentoMerma().toString().equals("0.0")) {
			LOGGER.warn("NO APLICA DESCUENTO PARA CALIDAD DE LA MATERIA, TIENE EL PROVEEDOR: "
					+ entradasEditar.getProveedores().getDescuentoMerma());
			porcentajeDeMerma = 0.0;
		}

		if (entradasEditar.getTipoMoneda().equals("USD")) {
			kg_porcentaje = Double.valueOf(entradasEditar.getKgNetos().toString())
					- (((Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
							+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString()) + porcentajeDeMerma
							- Double.valueOf(entradasEditar.getCalculoKgMerma().toString()))
							* Double.valueOf(entradasEditar.getKgNetos().toString())) / 100);
			LOGGER.info("KG CALIDADA DE LA MATERIA: " + kg_porcentaje + " TIPO DE MONEDA: "
					+ entradasEditar.getTipoMoneda());
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		} else {

			double sumarCero = 0.0;
			sumarCero = Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
					+ Double.valueOf(entradasEditar.getCalculoKgMerma().toString());
			LOGGER.info("KG EMBARCADOS: " + entradasEditar.getKgEmbarcados());
			LOGGER.info("DESCUENTO HUMEDAD: " + entradasEditar.getDescuentoHumedad());
			LOGGER.info("DESCUENTO CALCIOS: " + entradasEditar.getDescuentoCalcio());
			LOGGER.info("CALCULO KG MERMA: " + entradasEditar.getCalculoKgMerma());
			LOGGER.info("PORCENTAJE MERMA: " + porcentajeDeMerma);
			LOGGER.info("DESCUENTO HUMEDAD + DESCUENTO CALCIOS + % MERMA: " + sumarCero);
			LOGGER.info("SUMA DE PORCENTAJES" + (Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString()) + porcentajeDeMerma));

			kg_porcentaje = Double.valueOf(entradasEditar.getKgEmbarcados().toString())
					- (((Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
							+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString()) + porcentajeDeMerma
							- Double.valueOf(entradasEditar.getCalculoKgMerma().toString()))
							* Double.valueOf(entradasEditar.getKgEmbarcados().toString())) / 100);

			LOGGER.info("PORCENTAJE TOTAL: " + (Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
					+ Double.valueOf(entradasEditar.getPorcentajeMerma().toString()) + porcentajeDeMerma
					- Double.valueOf(entradasEditar.getCalculoKgMerma().toString())));

			LOGGER.info("KG CALIDAD DE LA MATERIA: " + kg_porcentaje + " TIPO DE MONEDA: "
					+ entradasEditar.getTipoMoneda());
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		}

		entradasEditar.setPrecioCalcCcp(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaConPelo().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCcp().toString())));
		entradasEditar.setPrecioCalcC1(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaPrimera().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioC1().toString())));
		entradasEditar.setPrecioCalcC2(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaSegunda().toString()) / 100)
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
		entradasEditar.setPrecioCalcCa(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCerdoAmericano().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCa().toString())));

		// double tax = .16;
		double sumaSubtotal = 0.0;
		double iva = 0.0;

//		iva = tax * Double.valueOf(this.entradasEditar.getPrecioCalcCcp().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcC1().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcC2().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcCs().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcDr().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcCm().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcCo().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcPc().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcP().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcDa().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcDs().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcCdi().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcG().toString()).doubleValue()
//				+ tax * Double.valueOf(this.entradasEditar.getPrecioCalcCa().toString()).doubleValue();

		ivaMP(entradasEditar.getProveedores().getMateriaPrima());

		iva = (ivaMateriaPrima.getIvaCueroIntegralSaladoConPelo()
				* (Double.valueOf(entradasEditar.getPrecioCalcCcp().toString()))
				+ (ivaMateriaPrima.getIvaCarnazaCompleta()
						* (Double.valueOf(entradasEditar.getPrecioCalcC1().toString()))
						+ (ivaMateriaPrima.getIvaCarnazaPedazos()
								* (Double.valueOf(entradasEditar.getPrecioCalcC2().toString()))
								+ (ivaMateriaPrima.getIvaCarnazaSalada()
										* (Double.valueOf(entradasEditar.getPrecioCalcCs().toString()))
										+ (ivaMateriaPrima.getIvaDesbarbeRecortes()
												* (Double.valueOf(entradasEditar.getPrecioCalcDr().toString()))
												+ (ivaMateriaPrima.getIvaCerdoMexicano()
														* (Double.valueOf(entradasEditar.getPrecioCalcCm().toString()))
														+ (ivaMateriaPrima.getIvaCachete()
																* (Double.valueOf(
																		entradasEditar.getPrecioCalcCo().toString()))
																+ (ivaMateriaPrima.getIvaRecorteConPelo()
																		* (Double.valueOf(entradasEditar
																				.getPrecioCalcPc().toString()))
																		+ (ivaMateriaPrima.getIvaPedaceria()
																				* (Double.valueOf(entradasEditar
																						.getPrecioCalcP().toString()))
																				+ (ivaMateriaPrima
																						.getIvaDescarneAdherido()
																						* (Double.valueOf(entradasEditar
																								.getPrecioCalcDa()
																								.toString()))
																						+ (ivaMateriaPrima
																								.getIvaDescarneSeparado()
																								* (Double.valueOf(
																										entradasEditar
																												.getPrecioCalcDs()
																												.toString()))
																								+ (ivaMateriaPrima
																										.getIvaCueroDepilado()
																										* (Double
																												.valueOf(
																														entradasEditar
																																.getPrecioCalcCdi()
																																.toString()))
																										+ (ivaMateriaPrima
																												.getIvaGarraFalda()
																												* (Double
																														.valueOf(
																																entradasEditar
																																		.getPrecioCalcG()
																																		.toString()))
																												+ (ivaMateriaPrima
																														.getIvaCueroEnSangre()
																														* (Double
																																.valueOf(
																																		entradasEditar
																																				.getPrecioCalcCe()
																																				.toString()))
																														+ (ivaMateriaPrima
																																.getIvaCerdoAmericano()
																																* (Double
																																		.valueOf(
																																				entradasEditar
																																						.getPrecioCalcCa()
																																						.toString())))))))))))))))));
		sumaSubtotal = Double.valueOf(entradasEditar.getPrecioCalcCcp().toString())
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
				+ Double.valueOf(entradasEditar.getPrecioCalcCe().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCa().toString());

		double tCatura = 0.0;
		tCatura = iva + sumaSubtotal;

		entradasEditar.setSubtotal(BigDecimal.valueOf(sumaSubtotal));
		entradasEditar.setIva(BigDecimal.valueOf(iva));
		entradasEditar.setTotalCaptura(BigDecimal.valueOf(tCatura));
		// **CALCULOS DE LOS PRECIOS**//

		LOGGER.info(
				"->PRECIO: " + kg_porcentaje + " ->SUBTOTAL:" + sumaSubtotal + " ->IVA:" + iva + " ->TOTAL:" + tCatura);

		if (us.getPerfiles().getIdPerfil() == 4) {
			if (entradasEditar.getFechaLiberacion() == null || entradasEditar.getFechaLiberacion().equals("")) {
				String info = "Te hace falta la fecha de liberación";

				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Advertencia!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: true,\n" + "  timer: 8000\n" + "})");
				LOGGER.error("No hay fecha de liberación");
			} else {
				eDao.actualizarEntradas(entradasEditar);
				Correo c = new Correo();

				if (banderaControlCalidad == 1) {
					c.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
							entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
							entradasEditar.getMateria().getTipo(), banderaGerencia, banderaControlCalidad);
				}
			}
		} else {
			eDao.actualizarEntradas(entradasEditar);
			Correo c = new Correo();

			if (banderaControlCalidad == 1) {
				c.enviarNotificacion(entradasEditar.getTolvas(), entradasEditar.getTicketBasculaToluca(),
						entradasEditar.getProveedores().getNombre(), entradasEditar.getFactura(),
						entradasEditar.getMateria().getTipo(), banderaGerencia, banderaControlCalidad);
			}

		}

		entradasEditar = new Entradas();
//		String script = "setTimeout(function() { window.location.href='Entradas.html'; }, 3000);";
//		PrimeFaces.current().executeScript(script);
	}

//****//	

	// **ACTUALIZAR PERFIL PRECIOS**//
	public void actualizarPrecio() {

		LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<");
		LOGGER.info("INICIA LA ACTUALIZACIÓN DE PRECIOS");
		alertaCalcios = "";
		alertaHumedad = "";
		IEntradasDao eDao = new EntradasDaoImpl();
		switch (us.getPerfiles().getNombrePerfil()) {
		case "Coordinador" -> entradasEditar.setCoordinadorProduccion(us.getIniciales());
		case "Control de calidad" -> entradasEditar.setControlCalidad(us.getIniciales());
		case "Gerencia" -> entradasEditar.setGerenciaProduccion(us.getIniciales());
		default -> {
		}
		}

		List<PrecioMateria> listaActualizacion = new ArrayList<>();
		// **BUSCAR ACTUALIZACIÓN DE PRECIO**//
		LOGGER.warn(">>>>>>>>>>>>>>>>>>INICIA BUSCAR ACTUALIZACIÓN DE PRECIO<<<<<<<<<<<<<<<<<<");
		if (Double.parseDouble(entradasEditar.getCarnazaConPelo().toString()) != ceros) {
			LOGGER.info("Dato1: " + entradasEditar.getCarnazaConPelo().toString()
					+ " CUERO INTEGRAL SALADO CON PELO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCcp(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 1)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCcp());
			// AGREGAR A LA LISTA
			listaActualizacion
					.add(new PrecioMateria(entradasEditar.getPrecioCcp().toString(), "CUERO INTEGRAL SALADO CON PELO"));

		} else {
			entradasEditar.setPrecioCcp(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaPrimera().toString()) != ceros) {
			LOGGER.info("Dato2: " + entradasEditar.getCarnazaPrimera().toString() + " CARNAZA COMPLETA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradas.setPrecioC1(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 2)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioC1());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioC1().toString(), "CARNAZA COMPLETA"));
		} else {
			entradasEditar.setPrecioC1(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaSegunda().toString()) != ceros) {
			LOGGER.info("Dato3: " + entradasEditar.getCarnazaSegunda().toString() + " CARNAZA PEDAZOS >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioC2(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 3)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioC2());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioC2().toString(), "CARNAZA PEDAZOS"));
		} else {
			entradasEditar.setPrecioC2(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCarnazaSalada().toString()) != ceros) {
			LOGGER.info("Dato4: " + entradasEditar.getCarnazaSalada().toString() + " CARNAZA SALADA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCs(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 4)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCs());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCs().toString(), "CARNAZA SALADA"));
		} else {
			entradasEditar.setPrecioCs(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDesbarbeRecorte().toString()) != ceros) {
			LOGGER.info("Dato5: " + entradasEditar.getDesbarbeRecorte().toString()
					+ " DESBARBE / RECORTES >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioDr(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 5)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDr());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioDr().toString(), "DESBARBE / RECORTES"));
		} else {
			entradasEditar.setPrecioDr(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCerdoMexicano().toString()) != ceros) {
			LOGGER.info("Dato6: " + entradasEditar.getCerdoMexicano().toString() + " CERDO MEXICANO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCm(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 6)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCm());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCm().toString(), "CERDO MEXICANO"));
		} else {
			entradasEditar.setPrecioCm(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getOrejaCachete().toString()) != ceros) {
			LOGGER.info("Dato7: " + entradasEditar.getOrejaCachete().toString() + " CACHETE >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioCo(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 7)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCo());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCo().toString(), "CACHETE"));
		} else {
			entradasEditar.setPrecioCo(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getPedaceriaConPelo().toString()) != ceros) {
			LOGGER.info("Dato8: " + entradasEditar.getPedaceriaConPelo().toString()
					+ " RECORTE DE CUERO CON PELO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioPc(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 8)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioPc());
			// AGREGAR A LA LISTA
			listaActualizacion
					.add(new PrecioMateria(entradasEditar.getPrecioPc().toString(), "RECORTE DE CUERO CON PELO"));
		} else {
			entradasEditar.setPrecioPc(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getPedaceria().toString()) != ceros) {
			LOGGER.info("Dato9: " + entradasEditar.getPedaceria().toString() + " PEDACERÍA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioP(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 9)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioP());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioP().toString(), "PEDACERÍA"));
		} else {
			entradasEditar.setPrecioP(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDescarneAdherido().toString()) != ceros) {
			LOGGER.info("Dato10: " + entradasEditar.getPrecioCcp().toString() + " DESCARNE ADHERIDO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioDa(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 10)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDa());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioDa().toString(), "DESCARNE ADHERIDO"));
		} else {
			entradasEditar.setPrecioDa(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getDescarneSeparado().toString()) != ceros) {
			LOGGER.info("Dato11: " + entradasEditar.getDescarneSeparado().toString()
					+ " DESCARNE SEPARADO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioDs(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 11)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioDs());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioDs().toString(), "DESCARNE SEPARADO"));
		} else {
			entradasEditar.setPrecioDs(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCueroDepiladoIntegral().toString()) != ceros) {
			LOGGER.info("Dato12: " + entradasEditar.getCueroDepiladoIntegral().toString()
					+ " CUERO DEPILADO >>>Proveedor: " + entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCdi(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 12)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCdi());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCdi().toString(), "CUERO DEPILADO"));
		} else {
			entradasEditar.setPrecioCdi(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getGarra().toString()) != ceros) {
			LOGGER.info("Dato13: " + entradasEditar.getGarra().toString() + " GARRA Y FALDA >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar
					.setPrecioG(BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 13)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioG());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioG().toString(), "GARRA Y FALDA"));
		} else {
			entradas.setPrecioG(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCueroEnSangre().toString()) != ceros) {
			LOGGER.info("Dato14: " + entradasEditar.getCueroEnSangre().toString() + " CUERO EN SANGRE >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCe(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 14)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCe());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCe().toString(), "CUERO EN SANGRE"));
		} else {

			entradasEditar.setPrecioCe(BigDecimal.valueOf(ceros));
		}

		if (Double.parseDouble(entradasEditar.getCerdoAmericano().toString()) != ceros) {
			LOGGER.info("Dato15: " + entradasEditar.getCerdoAmericano().toString() + " CERDO AMERICANO >>>Proveedor: "
					+ entradasEditar.getProveedores().getNombre());
			entradasEditar.setPrecioCa(
					BigDecimal.valueOf(buscarPrecio(entradasEditar.getProveedores().getIdProveedor(), 15)));
			LOGGER.info("Precio: " + entradasEditar.getPrecioCa());
			// AGREGAR A LA LISTA
			listaActualizacion.add(new PrecioMateria(entradasEditar.getPrecioCa().toString(), "CERDO AMERICANO"));
		} else {

			entradasEditar.setPrecioCa(BigDecimal.valueOf(ceros));
		}

		LOGGER.warn(">>>>>>>>>>>>>>>>>>FIN BUSCAR ACTUALIZACIÓN DE PRECIO<<<<<<<<<<<<<<<<<<");
		@SuppressWarnings("unused")
		int banderaGerencia = 0;
		@SuppressWarnings("unused")
		int banderaControlCalidad = 0;

		double humedad = Optional.of(Double.valueOf(entradasEditar.getHumedad().toString())).orElse(0.0);
		double calcios = Optional.of(Double.valueOf(entradasEditar.getAlcalinidad().toString())).orElse(0.0);
		if (us.getPerfiles().getNombrePerfil().equals("Gerencia")) {
			entradasEditar.setBloqueoEditar(1);
			entradasEditar.setEstado(2);
			banderaGerencia = 1;
		} else if (us.getPerfiles().getNombrePerfil().equals("Control de calidad")) {

			if (calcios != 0.0 && humedad == 0.0) {
				entradasEditar.setEstado(3);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES: " + humedad);
				LOGGER.info("VALIDACIÓN DE ALCALINIDAD ES: " + calcios);
			} else if (calcios != 0.0 && humedad > 0.0) {
				entradasEditar.setEstado(1);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				LOGGER.info("VALIDACIÓN DE HUMEDAD ES >: " + humedad);
				LOGGER.info("VALIDACIÓN DE CALCIOS ES >: " + calcios);
			}

			banderaControlCalidad = 1;

			// **INICIA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>INICA VALIDACIÓN DE DESCUENTOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			if (entradasEditar.getMateria().getDescuentoCalcioTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoCalcioTa(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "A")));
				LOGGER.info("CALCIOS TABLA A: " + entradasEditar.getAlcalinidad().toString());
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTa());
			}

			if (entradasEditar.getMateria().getDescuentoCalcioTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA B**//
				entradasEditar.setDescuentoCalcioTb(
						BigDecimal.valueOf(descuentoCalciosTabla_AB(entradasEditar.getAlcalinidad().toString(), "B")));
				LOGGER.info("CALCIOS TABLA B: " + entradasEditar.getAlcalinidad().toString());
				entradasEditar.setDescuentoCalcio(entradasEditar.getDescuentoCalcioTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTa(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "A")));
				LOGGER.info("HUMEDAD TABLA A: " + entradasEditar.getHumedad().toString());
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTa());
			}
			if (entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(1)) {
				// **EL 1 ES PARA LA TABLA A**//
				entradasEditar.setDescuentoHumedadTb(
						BigDecimal.valueOf(descuentoHumedadTabla_AB(entradasEditar.getHumedad().toString(), "B")));
				LOGGER.info("HUMEDAD TABLA B: " + entradasEditar.getHumedad().toString());
				entradasEditar.setDescuentoHumedad(entradasEditar.getDescuentoHumedadTb());
			}

			if (entradasEditar.getProveedores().getDescuentoHumedadTablaA().equals(0)
					&& entradasEditar.getProveedores().getDescuentoHumedadTablaB().equals(0)) {
				entradasEditar.setDescuentoHumedad(BigDecimal.valueOf(ceros));
				LOGGER.info("PROVEEDOR SIN DESCUENTOS EN HUMEDADES EN LAS TABLAS A y B->"
						+ entradasEditar.getProveedores().getNombre());
			}
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>FINALIZA VALIDACIÓN DE DESCUENTOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			// **FINALIZA VALIDAR LOS DESCUENTOS POR ALCALINIDAD Y HUMEDAD**//
		}

		// VALIDAR EL PROVEEDOR SI TIENE DESCUENTO EN LA MERMA
		double porcentajeDescuentoProv = 0.0;
		LOGGER.info("PROVEEDOR:" + entradasEditar.getProveedores().getNombre() + " DESCUENTO MERMA: "
				+ entradasEditar.getProveedores().getDescuentoMerma());
		porcentajeDescuentoProv = Double.parseDouble(entradasEditar.getProveedores().getDescuentoMerma().toString());
		// **KG PORCENTAJE**//

		double kg_porcentaje = 0.0;

		LOGGER.info("Tipo de moneda: " + entradasEditar.getTipoMoneda());

		double porcentajeDeMerma = 0.0;
		porcentajeDeMerma = Double.valueOf(entradasEditar.getPorcentajeMerma().toString());
		LOGGER.warn("**********************************************************************");
		LOGGER.info("El porcentaje la merma es de: " + porcentajeDeMerma);
		LOGGER.warn("**********************************************************************");

		if (porcentajeDeMerma > 1.5) {
			LOGGER.warn("**********************************************************************");
			LOGGER.info("El porcentaje la merma es mayor, se aplica descuento de 1.5");
			LOGGER.info("El porcentaje con el descuento de 1.5: " + (porcentajeDeMerma - porcentajeDescuentoProv)
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
					+ Double.valueOf(entradasEditar.getKgNetos().toString()));
			LOGGER.warn("**********************************************************************");

		}

		if (porcentajeDeMerma < 1.5) {
			porcentajeDeMerma = 0.0;

		}

		// VALIDAR SI EL PROVEEDOR TIENE 0, NO SE CALCULA EN EL P0RCENTAJE DE LA MERMA
		// PORCENTAJE MERMA PROVEEDORES//
		Proveedores prov = new Proveedores();
		prov = buscarMermaProveedor(entradasEditar.getProveedores().getIdProveedor());
		if (prov.getDescuentoMerma().toString().equals("0.0")) {
			LOGGER.warn("NO APLICA DESCUENTO PARA CALIDAD DE LA MATERIA, TIENE EL PROVEEDOR: "
					+ entradasEditar.getProveedores().getDescuentoMerma());
			porcentajeDeMerma = 0.0;
		}
		if (entradasEditar.getTipoMoneda().equals("USD")) {
			kg_porcentaje = Double.valueOf(entradasEditar.getKgNetos().toString())
					- (((Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
							+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString()) + porcentajeDeMerma
							- Double.valueOf(entradasEditar.getCalculoKgMerma().toString()))
							* Double.valueOf(entradasEditar.getKgNetos().toString())) / 100);
			LOGGER.info("KG MERMA: " + kg_porcentaje + " Tipo de moneda: " + entradasEditar.getTipoMoneda());
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		} else {

			double sumarCero = 0.0;
			sumarCero = Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
					+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString())
					+ Double.valueOf(entradasEditar.getCalculoKgMerma().toString());
			LOGGER.info("KG EMBARCADOS: " + entradasEditar.getKgEmbarcados());
			LOGGER.info("DESCUENTO HUMEDAD: " + entradasEditar.getDescuentoHumedad());
			LOGGER.info("DESCUENTO CALCIOS: " + entradasEditar.getDescuentoCalcio());
			LOGGER.info("CALCULO KG MERMA: " + entradasEditar.getCalculoKgMerma());
			LOGGER.info("PORCENTAJE MERMA: " + porcentajeDeMerma);
			LOGGER.info("DESCUENTO HUMEDAD + DESCUENTO CALCIOS + % MERMA: " + sumarCero);

			kg_porcentaje = Double.valueOf(entradasEditar.getKgEmbarcados().toString())
					- (((Double.valueOf(entradasEditar.getDescuentoHumedad().toString())
							+ Double.valueOf(entradasEditar.getDescuentoCalcio().toString()) + porcentajeDeMerma
							- Double.valueOf(entradasEditar.getCalculoKgMerma().toString()))
							* Double.valueOf(entradasEditar.getKgEmbarcados().toString())) / 100);

			LOGGER.info("KG MERMA: " + kg_porcentaje + " Tipo de moneda: " + entradasEditar.getTipoMoneda());
			entradasEditar.setKgCalidadMateria(BigDecimal.valueOf(kg_porcentaje));
		}

		entradasEditar.setPrecioCalcCcp(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaConPelo().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCcp().toString())));
		entradasEditar.setPrecioCalcC1(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaPrimera().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioC1().toString())));
		entradasEditar.setPrecioCalcC2(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCarnazaSegunda().toString()) / 100)
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
		entradasEditar.setPrecioCalcCa(
				BigDecimal.valueOf((kg_porcentaje * Double.valueOf(entradasEditar.getCerdoAmericano().toString()) / 100)
						* Double.valueOf(entradasEditar.getPrecioCa().toString())));

		// double tax = .16;
		double sumaSubtotal = 0.0;
		double iva = 0.0;

//		iva = (tax * (Double.valueOf(entradasEditar.getPrecioCalcCe().toString())) + (tax
//				* (Double.valueOf(entradasEditar.getPrecioCalcCcp().toString()))
//				+ (tax * (Double.valueOf(entradasEditar.getPrecioCalcC1().toString())) + (tax
//						* (Double.valueOf(entradasEditar.getPrecioCalcC2().toString()))
//						+ (tax * (Double.valueOf(entradasEditar.getPrecioCalcCs().toString())) + (tax
//								* (Double.valueOf(entradasEditar.getPrecioCalcDr().toString()))
//								+ (tax * (Double.valueOf(entradasEditar.getPrecioCalcCm().toString())) + (tax
//										* (Double.valueOf(entradasEditar.getPrecioCalcCo().toString()))
//										+ (tax * (Double.valueOf(entradasEditar.getPrecioCalcPc().toString())) + (tax
//												* (Double.valueOf(entradasEditar.getPrecioCalcP().toString()))
//												+ (tax * (Double.valueOf(entradasEditar.getPrecioCalcDa().toString()))
//														+ (tax * (Double
//																.valueOf(entradasEditar.getPrecioCalcDs().toString()))
//																+ (tax * (Double.valueOf(
//																		entradasEditar.getPrecioCalcCdi().toString()))
//																		+ (tax * (Double.valueOf(entradasEditar
//																				.getPrecioCalcG().toString()))
//																				+ (tax * (Double.valueOf(entradasEditar
//																						.getPrecioCalcCa()
//																						.toString())))))))))))))))));
		ivaMP(entradasEditar.getProveedores().getMateriaPrima());
		iva = (ivaMateriaPrima.getIvaCueroIntegralSaladoConPelo()
				* (Double.valueOf(entradasEditar.getPrecioCalcCcp().toString()))
				+ (ivaMateriaPrima.getIvaCarnazaCompleta()
						* (Double.valueOf(entradasEditar.getPrecioCalcC1().toString()))
						+ (ivaMateriaPrima.getIvaCarnazaPedazos()
								* (Double.valueOf(entradasEditar.getPrecioCalcC2().toString()))
								+ (ivaMateriaPrima.getIvaCarnazaSalada()
										* (Double.valueOf(entradasEditar.getPrecioCalcCs().toString()))
										+ (ivaMateriaPrima.getIvaDesbarbeRecortes()
												* (Double.valueOf(entradasEditar.getPrecioCalcDr().toString()))
												+ (ivaMateriaPrima.getIvaCerdoMexicano()
														* (Double.valueOf(entradasEditar.getPrecioCalcCm().toString()))
														+ (ivaMateriaPrima.getIvaCachete()
																* (Double.valueOf(
																		entradasEditar.getPrecioCalcCo().toString()))
																+ (ivaMateriaPrima.getIvaRecorteConPelo()
																		* (Double.valueOf(entradasEditar
																				.getPrecioCalcPc().toString()))
																		+ (ivaMateriaPrima.getIvaPedaceria()
																				* (Double.valueOf(entradasEditar
																						.getPrecioCalcP().toString()))
																				+ (ivaMateriaPrima
																						.getIvaDescarneAdherido()
																						* (Double.valueOf(entradasEditar
																								.getPrecioCalcDa()
																								.toString()))
																						+ (ivaMateriaPrima
																								.getIvaDescarneSeparado()
																								* (Double.valueOf(
																										entradasEditar
																												.getPrecioCalcDs()
																												.toString()))
																								+ (ivaMateriaPrima
																										.getIvaCueroDepilado()
																										* (Double
																												.valueOf(
																														entradasEditar
																																.getPrecioCalcCdi()
																																.toString()))
																										+ (ivaMateriaPrima
																												.getIvaGarraFalda()
																												* (Double
																														.valueOf(
																																entradasEditar
																																		.getPrecioCalcG()
																																		.toString()))
																												+ (ivaMateriaPrima
																														.getIvaCueroEnSangre()
																														* (Double
																																.valueOf(
																																		entradasEditar
																																				.getPrecioCalcCe()
																																				.toString()))
																														+ (ivaMateriaPrima
																																.getIvaCerdoAmericano()
																																* (Double
																																		.valueOf(
																																				entradasEditar
																																						.getPrecioCalcCa()
																																						.toString())))))))))))))))));

		sumaSubtotal = Double.valueOf(entradasEditar.getPrecioCalcCcp().toString())
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
				+ Double.valueOf(entradasEditar.getPrecioCalcCe().toString())
				+ Double.valueOf(entradasEditar.getPrecioCalcCa().toString());

		double tCatura = 0.0;
		tCatura = iva + sumaSubtotal;

		entradasEditar.setSubtotal(BigDecimal.valueOf(sumaSubtotal));
		entradasEditar.setIva(BigDecimal.valueOf(iva));
		entradasEditar.setTotalCaptura(BigDecimal.valueOf(tCatura));
		// **CALCULOS DE LOS PRECIOS**//
		eDao.actualizarEntradas(entradasEditar);
		LOGGER.info(
				"->PRECIO: " + kg_porcentaje + " ->SUBTOTAL:" + sumaSubtotal + " ->IVA:" + iva + " ->TOTAL:" + tCatura);
		List<String> listaPreciosActualizacion = new ArrayList<>();
		for (PrecioMateria p : listaActualizacion) {
			listaPreciosActualizacion.add(p.getMateriaNombre() + " ->$" + p.getPrecio());
		}

		String[] arreglo = listaPreciosActualizacion.toArray(new String[0]);
		LOGGER.info(Arrays.toString(arreglo));

		IBitacoraPreciosDao bDao = new BitacoraPreciosDaoImpl();
		BitacoraPrecios b = new BitacoraPrecios();
		b.setIdMateria(entradasEditar.getMateria().getIdMateria());
		b.setIdProveedor(entradasEditar.getProveedores().getIdProveedor());
		b.setFechaActualizacion(new Date());
		b.setActualizadoPor(us.getIdUsuario());
		b.setFechaSistema(new Date());
		b.setTipo("TOLVA NO. " + entradasEditar.getTolvas() + " | " + Arrays.toString(arreglo));

		bDao.guardarBitacoraPrecios(b);

		CorreoPrecios c = new CorreoPrecios();
		c.enviarNotificacion(Arrays.toString(arreglo), filterProveedor, entradasEditar.getTolvas());

		String info = "Se ha actualizado el precio de la Tolva";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");

		entradasEditar = new Entradas();
		LOGGER.info("FINALIZA LA ACTUALIZACIÓN DE PRECIOS");
		LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<");

	}

	public void desbloquear(int id, int tolvaNo) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS SET BLOQUEO_EDITAR=0 WHERE ID_ENTRADA='" + id + "'");
			ps.executeUpdate();
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "SE HA DESBLOQUEADO
			// CORRECTAMENTE LA CAPTURA"));

			String info = "SE HA DESBLOQUEADO CORRECTAMENTE LA CAPTURA DE LA TOLVA " + tolvaNo;

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			LOGGER.info(info + " ID_ENTRADA: " + id);
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
				+ dato13 + dato14 + dato15);
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
				+ Double.parseDouble(entradasEditar.getCarnazaPrimera().toString())
				+ Double.parseDouble(entradasEditar.getCarnazaSegunda().toString())
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
				+ Double.parseDouble(entradasEditar.getCueroEnSangre().toString())
				+ Double.parseDouble(entradasEditar.getCerdoAmericano().toString()));
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

		String valorProveedor = obtenerValorComponente("frmPrincipal:acco:pro");
		String tipoDeMoneda = "";
		IProveedoresDao pDao = new ProveedoresDaoImpl();
		try {
			tipoDeMoneda = pDao.buscarTipoMonedaProveedor(valorProveedor);
			LOGGER.info(tipoDeMoneda);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tipoDeMoneda.equals("USD")) {
			kilosBasculaMerma = kilosEmbarcados - kilosNetos;
		}

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

		if (kilosEmbarcados > 0.0) {
			porcentajeCalculo = (entradas.getMerma().doubleValue() * 100) / kilosEmbarcados;
			// porcentaje = (Double.parseDouble(entradas.getMerma().toString()) * 100) /
			// (kilosEmbarcados);
		}
		if (kilosNetos > 0.0) {
			porcentajeCalculo = (entradas.getMerma().doubleValue() * 100) / kilosNetos;
			// porcentaje = (Double.parseDouble(entradas.getMerma().toString()) * 100) /
			// (kilosNetos);
		}
		LOGGER.info("PORCENTAJE: " + porcentajeCalculo);
		entradas.setPorcentajeMerma(
				BigDecimal.valueOf(Double.parseDouble(String.format("%.2f", porcentajeCalculo).replace(",", "."))));
		LOGGER.info("PORCENTAJE MERMA: " + entradas.getPorcentajeMerma());
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
		double porcentajeDecimal = 0.0;
		porcentajeDecimal = Double.parseDouble(porcentaje);
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_A WHERE DE <= " + porcentajeDecimal
					+ " AND HASTA >= " + porcentajeDecimal + " ORDER BY ID_HUMEDAD ASC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_HUMEDAD_TABLA_B WHERE DE <= " + porcentajeDecimal
					+ " AND HASTA >= " + porcentajeDecimal + " ORDER BY ID_HUMEDAD ASC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				// LOGGER.error("HUMEDAD FUERA DE RANGO PERMITIDO: " + porcentaje);
				resultado = 0.0;
			} else {

				while (rs.next()) {

					resultado = rs.getDouble("DESCUENTO");
					// HUMEDAD HASTA >= 85.0 TABLA A
					// HUMEDAD HASTA >= 80.0 TABLA B
					if (Double.valueOf(porcentajeDecimal) >= 85.0 && tabla.equals("A")) {
						entradasEditar.setRangoHumedad(1);
						alertaHumedad = "FUERA DE RANGO";
						LOGGER.error("HUMEDAD FUERA DE RANGO PERMITIDO: " + porcentajeDecimal + " EN LA TABLA A");
					} else if (Double.valueOf(porcentajeDecimal) >= 80.0 && tabla.equals("B")) {
						entradasEditar.setRangoHumedad(1);
						alertaHumedad = "FUERA DE RANGO";
						LOGGER.error("HUMEDAD FUERA DE RANGO PERMITIDO: " + porcentajeDecimal + " EN LA TABLA B");
					} else {
						entradasEditar.setRangoHumedad(0);
						alertaHumedad = "OK";
					}
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
		double porcentajeDecimal = 0.0;
		porcentajeDecimal = Double.parseDouble(porcentaje);
		String sql = "";
		if (tabla.equals("A")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_A WHERE DE <= " + porcentajeDecimal
					+ " AND HASTA >= " + porcentajeDecimal + " ORDER BY ID_CALCIOS ASC";
		} else if (tabla.equals("B")) {
			sql = "SELECT  TOP(1) DESCUENTO FROM DESCUENTO_CALCIOS_TABLA_B WHERE DE <= " + porcentajeDecimal
					+ " AND HASTA >= " + porcentajeDecimal + " ORDER BY ID_CALCIOS ASC";
		}

		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				resultado = 0.0;
				LOGGER.error("CALCIOS FUERA DE RANGO PERMITIDO: " + porcentajeDecimal);
				entradasEditar.setRangoCalcios(1);
				alertaCalcios = "FUERA DE RANGO";
			} else {
				while (rs.next()) {

					resultado = rs.getDouble("DESCUENTO");
					// CALCIOS HASTA >= 1500.0 TABLA A y B
					if (Double.valueOf(porcentajeDecimal) >= 1500.0 && tabla.equals("A")) {
						entradasEditar.setRangoCalcios(1);
						alertaCalcios = "FUERA DE RANGO";
						LOGGER.error("CALCIOS FUERA DE RANGO PERMITIDO: " + porcentajeDecimal + " EN LA TABLA A");
					} else if (Double.valueOf(porcentajeDecimal) >= 1500.0 && tabla.equals("B")) {
						entradasEditar.setRangoCalcios(1);
						alertaCalcios = "FUERA DE RANGO";
						LOGGER.error("CALCIOS FUERA DE RANGO PERMITIDO: " + porcentajeDecimal + " EN LA TABLA B");
					} else {
						entradasEditar.setRangoCalcios(0);
						alertaCalcios = "OK";
					}
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

	public void visualizarReporte(String idEntrada, int tolva, int estado, String iniciales) throws SQLException {
		@SuppressWarnings("unused")
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
		case 3:
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

		// VALIDAR DATOS

		if (entradasEditar.getSucursal() == null || transportista.getIdTransportista() == 0
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

//			if (entradasEditar.getFactura() == "") {
//				fac = "Factura, ";
//			}
//
//			if (entradasEditar.getCertificado() == "") {
//				cer = "Certificado, ";
//			}
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

			if (Double.parseDouble(entradasEditar.getCarnazaPrimera().toString()) != ceros) {
				entradasEditar.setPrecioC1(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 2)));
			} else {
				entradasEditar.setPrecioC1(BigDecimal.valueOf(ceros));
			}

			if (Double.parseDouble(entradasEditar.getCarnazaSegunda().toString()) != ceros) {
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
			if (Double.parseDouble(entradasEditar.getCerdoAmericano().toString()) != ceros) {
				entradasEditar.setPrecioCe(BigDecimal.valueOf(buscarPrecio(proveedores.getIdProveedor(), 15)));
			} else {

				entradasEditar.setPrecioCe(BigDecimal.valueOf(ceros));
			}

			double porcentaje15 = 1.5;
			if (Double.parseDouble(entradasEditar.getPorcentajeMerma().toString()) < porcentaje15) {
				porcentaje15 = 0.0;
			}

//			entradasEditar.setCalculoKgMerma(BigDecimal.valueOf((porcentaje15)));

			eDao.actualizarPerfilCoord(entradasEditar);

			entradasEditar = null;
			preservacion = null;
			transportista = null;
			proveedores = null;
			materia = null;

			String info = "SE HA ACTUALIZADO LA CAPTURA DE LA MATERIA PRIMA";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");

			String script = "setTimeout(function() { window.location.href='Entradas.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public void upload() {
		if (pdfFile != null) {
			try {
				// Ruta donde se guardará el archivo

				leerConfig();
				String destination = getPathCert() + pdfFile.getFileName();

				IEntradasDao eDao = new EntradasDaoImpl();
				eDao.actualizarNombreArchivoCert(entradasCertificado.getIdEntrada(), pdfFile.getFileName());
				entradasCertificado = new Entradas();

				// Crear el directorio si no existe
				File directory = new File(getPathCert());
				if (!directory.exists()) {
					directory.mkdirs();
				}

				// Escribir el archivo en la ruta especificada
				FileOutputStream out = new FileOutputStream(destination);
				out.write(pdfFile.getContent());
				out.close();
				LOGGER.info("Nombre del archivo" + pdfFile.getFileName());
				// Mensaje de éxito
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"¡Éxito!", "Archivo cargado correctamente: " + pdfFile.getFileName()));
			} catch (IOException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "No se pudo cargar el archivo."));
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Debe seleccionar un archivo."));
		}

	}

	public void loadPdf(String docto) {
		leerConfig();
		// Ruta del archivo PDF
		file = getUrlCert() + docto;
	}

	public String obtenerValorComponente(String idComponente) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIComponent componente = context.getViewRoot().findComponent(idComponente);

		if (componente != null && componente instanceof UIInput) {
			UIInput input = (UIInput) componente;
			return (String) input.getValue();
		}

		return null;
	}

	public void ivaMP(String json) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			@SuppressWarnings("unchecked")
			Map<String, Integer> datos = mapper.readValue(json, Map.class);
			ivaMateriaPrima = new IvaMateriaPrima();
			for (Map.Entry<String, Integer> entry : datos.entrySet()) {
				LOGGER.info(entry.getKey() + " = " + entry.getValue());

				switch (entry.getKey()) {
				case "CUERO_INTEGRAL_SALADO_CON_PELO" ->
					ivaMateriaPrima.setIvaCueroIntegralSaladoConPelo(entry.getValue() / 100.0);
				case "CARNAZA_COMPLETA" -> ivaMateriaPrima.setIvaCarnazaCompleta(entry.getValue() / 100.0);
				case "CARNAZA_PEDAZOS" -> ivaMateriaPrima.setIvaCarnazaPedazos(entry.getValue() / 100.0);
				case "CARNAZA_SALADA" -> ivaMateriaPrima.setIvaCarnazaSalada(entry.getValue() / 100.0);
				case "DESBARBE_RECORTES" -> ivaMateriaPrima.setIvaDesbarbeRecortes(entry.getValue() / 100.0);
				case "CERDO_MEXICANO" -> ivaMateriaPrima.setIvaCerdoMexicano(entry.getValue() / 100.0);
				case "CACHETE" -> ivaMateriaPrima.setIvaCachete(entry.getValue() / 100.0);
				case "RECORTE_DE_CUERO_CON_PELO" -> ivaMateriaPrima.setIvaRecorteConPelo(entry.getValue() / 100.0);
				case "PEDACERIA" -> ivaMateriaPrima.setIvaPedaceria(entry.getValue() / 100.0);
				case "DESCARNE_ADHERIDO" -> ivaMateriaPrima.setIvaDescarneAdherido(entry.getValue() / 100.0);
				case "DESCARNE_SEPARADO" -> ivaMateriaPrima.setIvaDescarneSeparado(entry.getValue() / 100.0);
				case "GARRA_Y_FALDA" -> ivaMateriaPrima.setIvaGarraFalda(entry.getValue() / 100.0);
				case "CUERO_EN_SANGRE" -> ivaMateriaPrima.setIvaCueroEnSangre(entry.getValue() / 100.0);
				case "CERDO_AMERICANO" -> ivaMateriaPrima.setIvaCerdoAmericano(entry.getValue() / 100.0);
				case "CUERO_DEPILADO" -> ivaMateriaPrima.setIvaCueroDepilado(entry.getValue() / 100.0);
				default -> {
				}
				}

			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
