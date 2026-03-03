package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IFolioPreparacionSecadorADao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimitesDao;
import com.dmjm.dao.ILimpiezaSecadorADao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantenimientoSecadorADao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ISecadorADao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioPreparacionSecadorADaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimitesDaoImpl;
import com.dmjm.impl.LimpiezaSecadorADaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantenimientoSecadorADaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.SecadorADaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionSecadorA;
import com.dmjm.model.LimitesEspecificosA;
import com.dmjm.model.LimitesReferenciaA;
import com.dmjm.model.LimpiezaSecadorA;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoSecadorA;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.SecadorA;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("secadorABean")
@ViewScoped
public class SecadorABean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SecadorA> listaSecador;
	private SecadorA secador;
	private SecadorA secadorEditar;

	private List<SecadorA> listaFiltroSecador;

	private Date fecha;
	private int folioFecha;
	private int folioPrepSec;
	private Date fechaFiltro;

	private LimpiezaSecadorA limpieza;
	private List<LimpiezaSecadorA> limpiezaSecadorA;
	private LimpiezaSecadorA limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoSecadorA> listaOrdenManto;
	private OrdenMantenimientoSecadorA ordenMantenimiento;
	private OrdenMantenimientoSecadorA ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionSecadorA> listaFolioSecadorA;
	private FolioPreparacionSecadorA folioPreparacionSecadorA;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private int folioSeleccionado;

	private String bandera;

	private LimitesEspecificosA limEsp;
	private LimitesReferenciaA limRef;
	private int folioLimEsp;
	private int folioLimRef;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(SecadorABean.class.getName());

	public SecadorABean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		listaSecador = new ArrayList<>();
		secador = new SecadorA();
		secadorEditar = new SecadorA();

		limpieza = new LimpiezaSecadorA();
		limpiezaSecadorA = new ArrayList<>();
		limpiezaEditar = new LimpiezaSecadorA();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoSecadorA();
		ordenMantenimientoEditar = new OrdenMantenimientoSecadorA();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionSecadorA = new FolioPreparacionSecadorA();

		listaFiltroSecador = new ArrayList<SecadorA>();
		ISecadorADao lSecadorDao = new SecadorADaoImpl();
		listaFiltroSecador = lSecadorDao.listaFiltroSecador();

		cargarListaSecador();
		primera();

	}

	public FolioPreparacionSecadorA getFolioPreparacionSecadorA() {
		return folioPreparacionSecadorA;
	}

	public void setFolioPreparacionSecadorA(FolioPreparacionSecadorA folioPreparacionSecadorA) {
		this.folioPreparacionSecadorA = folioPreparacionSecadorA;
	}

	public int getNoLimpiezaVoBo() {
		return noLimpiezaVoBo;
	}

	public void setNoLimpiezaVoBo(int noLimpiezaVoBo) {
		this.noLimpiezaVoBo = noLimpiezaVoBo;
	}

	public int getNoLimpiezaSeleccionadaBorrar() {
		return noLimpiezaSeleccionadaBorrar;
	}

	public void setNoLimpiezaSeleccionadaBorrar(int noLimpiezaSeleccionadaBorrar) {
		this.noLimpiezaSeleccionadaBorrar = noLimpiezaSeleccionadaBorrar;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public int getFolioLimEsp() {
		return folioLimEsp;
	}

	public void setFolioLimEsp(int folioLimEsp) {
		this.folioLimEsp = folioLimEsp;
	}

	public int getFolioLimRef() {
		return folioLimRef;
	}

	public void setFolioLimRef(int folioLimRef) {
		this.folioLimRef = folioLimRef;
	}

	public LimitesEspecificosA getLimEsp() {
		return limEsp;
	}

	public void setLimEsp(LimitesEspecificosA limEsp) {
		this.limEsp = limEsp;
	}

	public LimitesReferenciaA getLimRef() {
		return limRef;
	}

	public void setLimRef(LimitesReferenciaA limRef) {
		this.limRef = limRef;
	}

	public int getFolioSeleccionado() {
		return folioSeleccionado;
	}

	public void setFolioSeleccionado(int folioSeleccionado) {
		this.folioSeleccionado = folioSeleccionado;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	public SecadorA getSecador() {
		return secador;
	}

	public void setSecador(SecadorA secador) {
		this.secador = secador;
	}

	public SecadorA getSecadorEditar() {
		return secadorEditar;
	}

	public void setSecadorEditar(SecadorA secadorEditar) {
		this.secadorEditar = secadorEditar;
	}

	public List<SecadorA> getListaSecador() {

		return listaSecador;
	}

	public List<SecadorA> getListaFiltroSecador() {
		return listaFiltroSecador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getFolioFecha() {
		return folioFecha;
	}

	public void setFolioFecha(int folioFecha) {
		this.folioFecha = folioFecha;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public int getFolioPrepSec() {
		return folioPrepSec;
	}

	public void setFolioPrepSec(int folioPrepSec) {
		this.folioPrepSec = folioPrepSec;
	}

	public LimpiezaSecadorA getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaSecadorA limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaSecadorA getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && ("ENJUAGUE".equals(limpiezaEditar.getProceso())
				|| "LIMPIEZA MECÁNICA".equals(limpiezaEditar.getProceso()))) {
			limpiezaEditar.setQuimico("AGUA");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaSecadorA limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<LimpiezaSecadorA> getLimpiezaSecadorA() {

		ILimpiezaSecadorADao lDao = new LimpiezaSecadorADaoImpl();

		IFolioPreparacionSecadorADao folioPrepDao = new FolioPreparacionSecadorADaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorAActual(fecha);
		limpiezaSecadorA = lDao.listarLimpieza(folioPrepSec);
		return limpiezaSecadorA;
	}

	public List<SecadorA> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaSecador.size());

		for (int i = 0; i < listaSecador.size(); i++) {
			if (i == pagina) {
				folioLimEsp = listaFiltroSecador.get(pagina).getFolioLm();
				folioLimRef = listaFiltroSecador.get(pagina).getFolioLr();
				llenadoLimites();
				break;
			}

		}

		// Retornar la sublista correspondiente a la página solicitada
		return listaSecador.subList(inicio, fin);
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(80, "FOLIO_PREPARACION_SECADOR_A");
	}

	public void guardarSecadorA() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_SECADOR_A");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='SecadorA.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				ISecadorADao cDao = new SecadorADaoImpl();
				secador = new SecadorA();

				// **FOLIO**//
				int folio = 0;
				// year = LocalDate.now().getYear();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fec);
				int newYear = calendario.get(Calendar.YEAR);
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioSeacadorA(newYear);

				// **FOLIO_PREPARACION_SECADOR**//

				IFolioPreparacionSecadorADao estDao = new FolioPreparacionSecadorADaoImpl();
				FolioPreparacionSecadorA fpe = new FolioPreparacionSecadorA();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					secador.setFolioSecador(folio);
					secador.setHora(lista);
					secador.setFolioPreparacionSecadorA(fpe);
					secador.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					secador.setFolioLm(folioLm());
					secador.setFolioLr(folioLr());
					cDao.guardarSecador(secador);
					secador = new SecadorA();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioSecadorA(newYear, folio);
			}
			String script = "setTimeout(function() { window.location.href='SecadorA.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public void actualizaSecadorA() {

		ISecadorADao cDao = new SecadorADaoImpl();

		String oper = secadorEditar.getOperacion().replaceAll("\\s+", "");
		secadorEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		if (Boolean.TRUE.equals(secadorEditar.getEstadoAR())) {
			secadorEditar.setEstadoA("X");
			secadorEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(secadorEditar.getEstadoAR())) {
			secadorEditar.setEstadoR("X");
			secadorEditar.setEstadoA(null);
		}

		// secadorEditar.setFolioPreparacion(folioSeleccionado);
		cDao.actualizarSecador(secadorEditar);
		actualizarPromedios(secadorEditar.getFolioPreparacionSecadorA().getIdFolioPrep());

		if (secadorEditar.getHora().equals("7:00")) {
			ISecadorADao aDao = new SecadorADaoImpl();
			aDao.actualizarSecadorPromedio(secadorEditar.getOperacion(),
					secadorEditar.getFolioPreparacionSecadorA().getIdFolioPrep());
		}
		secadorEditar = new SecadorA();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {

		ISecadorADao l1 = new SecadorADaoImpl();
		l1.actualizarLimiteUno(folio);

		ISecadorADao l2 = new SecadorADaoImpl();
		l2.actualizarLimiteDos(folio);

		ISecadorADao l3 = new SecadorADaoImpl();
		l3.actualizarLimiteTres(folio);

		ISecadorADao l4 = new SecadorADaoImpl();
		l4.actualizarLimiteCuatro(folio);

		ISecadorADao l5 = new SecadorADaoImpl();
		l5.actualizarLimiteCinco(folio);

		ISecadorADao l6 = new SecadorADaoImpl();
		l6.actualizarLimiteSeis(folio);

		ISecadorADao l7 = new SecadorADaoImpl();
		l7.actualizarLimiteSiete(folio);

		ISecadorADao l8 = new SecadorADaoImpl();
		l8.actualizarLimiteOcho(folio);

		ISecadorADao l9 = new SecadorADaoImpl();
		l9.actualizarLimiteNueve(folio);

		ISecadorADao v = new SecadorADaoImpl();
		v.actualizarVapor(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionSecadorA f = new FolioPreparacionSecadorA();
		f.setIdFolioPrep(folioPrepSec);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaSecadorADao validaDao = new LimpiezaSecadorADaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepSec);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaSecadorADao lDao = new LimpiezaSecadorADaoImpl();

		ISecadorADao vDao = new SecadorADaoImpl();
		vDao.actualizarLimpieza(folioPrepSec, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionSecadorA(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoSecador(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaSecadorA();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaSecadorADao lDao = new LimpiezaSecadorADaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaSecadorA();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		ISecadorADao vDao = new SecadorADaoImpl();
		vDao.actualizarLimpieza(folioPrepSec, 0);
		ILimpiezaSecadorADao iDao = new LimpiezaSecadorADaoImpl();
		iDao.borrarLimpieza(folioPrepSec, noLimpiezaSeleccionadaBorrar);

	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Secador A");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
	}

	public String getFilterTurno() {
		return filterTurno;
	}

	public void setFilterTurno(String filterTurno) {
		this.filterTurno = filterTurno;
	}

	public String getFilterUsuario() {
		return filterUsuario;
	}

	public void setFilterUsuario(String filterUsuario) {
		this.filterUsuario = filterUsuario;
	}

	public String getFilterOperador() {
		return filterOperador;
	}

	public void setFilterOperador(String filterOperador) {
		this.filterOperador = filterOperador;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Operador getOperadorEditar() {
		return operadorEditar;
	}

	public void setOperadorEditar(Operador operadorEditar) {
		this.operadorEditar = operadorEditar;
	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorSecadorA();
		return listaOperadores;
	}

	public RegistroTurnos getRegistroTurnos() {
		return registroTurnos;
	}

	public void setRegistroTurnos(RegistroTurnos registroTurnos) {
		this.registroTurnos = registroTurnos;
	}

	public RegistroTurnos getRegistroTurnosEditar() {
		return registroTurnosEditar;
	}

	public void setRegistroTurnosEditar(RegistroTurnos registroTurnosEditar) {
		this.registroTurnosEditar = registroTurnosEditar;
	}

	public List<RegistroTurnos> getListarRegistroTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosSecadorA(fecha);
		IFolioPreparacionSecadorADao folioPrepDao = new FolioPreparacionSecadorADaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorAActual(fecha);
		return listarRegistroTurnos;
	}

	public void guardarRegistroTurnos() throws SQLException {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		Usuarios u = new Usuarios();
		Operador o = new Operador();
		Turnos t = new Turnos();
		RegistroTurnos rt = new RegistroTurnos();

		u.setIdUsuario(buscarCoordinador(filterUsuario));
		o.setIdOperador(buscarOperador(filterOperador));
		t.setIdTurno(buscarTurno(filterTurno));

		rt.setTurnos(t);
		rt.setOperador(o);
		rt.setUsuarios(u);

		rt.setFecha(fecha);
		rt.setFolio(folioFecha);
		rt.setDescProceso("SECADOR A");

		rDao.guardaRegistroTurnos(rt);

		u = new Usuarios();
		o = new Operador();
		t = new Turnos();
		rt = new RegistroTurnos();
		filterUsuario = null;
		filterOperador = null;
		filterTurno = null;

	}

	// **DATOS DEL TURNO, NOMBRE**//
	public List<String> buscarNombreTurno(String nombre) throws SQLException {
		ITurnosDao tDao = new TurnosDaoImpl();
		return tDao.completeTurnos(nombre);
	}

	// **DATOS DEL TURNO, ID**//
	public int buscarTurno(String nombre) throws SQLException {
		ITurnosDao tDao = new TurnosDaoImpl();
		return tDao.buscarTurnos(nombre);
	}

	// **DATOS DEL COORDINADOR, NOMBRE**//
	public List<String> buscarNombreCoordinador(String nombre) throws SQLException {
		IUsuarioDao tDao = new UsuarioDaoImpl();
		return tDao.completeUsuario(nombre);
	}

	// **DATOS DEL COORDINADOR, ID**//
	public int buscarCoordinador(String nombre) throws SQLException {
		IUsuarioDao tDao = new UsuarioDaoImpl();
		return tDao.buscarUsuario(nombre);
	}

	// **DATOS DEL OPERADOR, NOMBRE**//
	public List<String> buscarNombreOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.completeOperador(nombre, "Secador A");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Secador A");
	}

	public void actualizarTurnos() throws SQLException {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		Usuarios u = new Usuarios();
		u.setIdUsuario(buscarCoordinador(filterUsuario));
		Operador o = new Operador();
		o.setIdOperador(buscarOperador(filterOperador));
		registroTurnosEditar.setOperador(o);
		registroTurnosEditar.setUsuarios(u);
		rDao.actualizarRegistroTurnos(registroTurnosEditar);
		registroTurnosEditar = new RegistroTurnos();
		filterUsuario = null;
		filterOperador = null;
		filterTurno = null;
	}

	public OrdenMantenimientoSecadorA getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoSecadorA ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoSecadorA getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoSecadorA ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoSecadorA> getListaOrdenManto() {
		IOrdenMantenimientoSecadorADao oDao = new OrdenMantenimientoSecadorADaoImpl();
		IFolioPreparacionSecadorADao folioPrepDao = new FolioPreparacionSecadorADaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorAActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepSec);
		return listaOrdenManto;
	}

	public void actualizarOrdenManto() {
		IOrdenMantenimientoSecadorADao iDao = new OrdenMantenimientoSecadorADaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoSecadorA();
	}

	public void borrarOrdenManto() {
		IOrdenMantenimientoSecadorADao iDao = new OrdenMantenimientoSecadorADaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoSecadorA();
	}

	public void borrarVoBo() {
		ILimpiezaSecadorADao iDao = new LimpiezaSecadorADaoImpl();
		iDao.borrarVoBo(folioPrepSec, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaSecadorADao iDao = new LimpiezaSecadorADaoImpl();
		iDao.agregarVoBo(folioPrepSec, noLimpiezaVoBo, us.getIdUsuario());
	}

	public List<FolioPreparacionSecadorA> getListaFolioSecadorA() {
		IFolioPreparacionSecadorADao lDao = new FolioPreparacionSecadorADaoImpl();
		listaFolioSecadorA = lDao.listaFolioSecadorA(folioPrepSec);
		return listaFolioSecadorA;
	}

	public void primera() {
		IFolioPreparacionSecadorADao fDao = new FolioPreparacionSecadorADaoImpl();
		FolioPreparacionSecadorA f = new FolioPreparacionSecadorA();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionSecadorADao folioDao = new FolioPreparacionSecadorADaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionSecadorADao folioPrepDao = new FolioPreparacionSecadorADaoImpl();
			this.folioPrepSec = folioPrepDao.folioSecadorAActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaSecadorA();
			getListaOrdenManto();
			getListaFolioSecadorA();
//			llenadoLimites();

		}
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaSecadorADao lDao = new LimpiezaSecadorADaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepSec);
		return listaLimpiezas;
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<SecadorA> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			this.folioLimEsp = paginaActual.get(0).getFolioLm();
			this.folioLimRef = paginaActual.get(0).getFolioLr();

			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionSecadorADao fDao = new FolioPreparacionSecadorADaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionSecadorADao folioPrepDao = new FolioPreparacionSecadorADaoImpl();
				this.folioPrepSec = folioPrepDao.folioSecadorAActual(fecha);
				this.folioLimEsp = paginaActual.get(0).getFolioLm();
				this.folioLimRef = paginaActual.get(0).getFolioLr();

			}
//			llenadoLimites();

		}

	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		cargarListaSecador();
		// getListaSecador(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaSecadorA();
		getListaOrdenManto();
		getListaFolioSecadorA();
//		llenadoLimites();

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ISecadorADao validaDao = new SecadorADaoImpl();
		validaDao.actualizarManto(folioPrepSec);

		IOrdenMantenimientoSecadorADao iDao = new OrdenMantenimientoSecadorADaoImpl();

		FolioPreparacionSecadorA f = new FolioPreparacionSecadorA();
		f.setIdFolioPrep(folioPrepSec);
		ordenMantenimiento.setFolioPreparacionSecadorA(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoSecadorA();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioSecadorA.size(); i++) {
			folioPreparacionSecadorA.setObservaciones(listaFolioSecadorA.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void guardarObservaciones() {
		IFolioPreparacionSecadorADao fDao = new FolioPreparacionSecadorADaoImpl();
		fDao.guardarObservacion(folioPrepSec, folioPreparacionSecadorA.getObservaciones());
		folioPreparacionSecadorA = new FolioPreparacionSecadorA();
	}

	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/secadorA.jasper");
		reporte.getReporte(ruta, fecha.toString(), folioFecha);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void visualizarReporteFiltros(String fec, int folioPrep, int folioFechaRep) throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteCocedores reporte = new ReporteCocedores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/secadorA.jasper");

		reporte.getReporte(ruta, fec, folioFechaRep);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void llenadoLimites() {
		ILimitesDao lEDao = new LimitesDaoImpl();
		limEsp = lEDao.limEspA(folioLimEsp);

		ILimitesDao lRDao = new LimitesDaoImpl();
		limRef = lRDao.limRefA(folioLimRef);

	}

	public int folioLm() {
		// VALIDAMOS LOS FOLIOS DE LIMITES ESPECIFICOS Y DE REFERENCIA

		ILimitesDao lmDao = new LimitesDaoImpl();

		return lmDao.limEspA().getFolioLm();
	}

	public int folioLr() {
		// VALIDAMOS LOS FOLIOS DE LIMITES ESPECIFICOS Y DE REFERENCIA

		ILimitesDao lmDao = new LimitesDaoImpl();

		return lmDao.limRefA().getFolioLr();
	}

	public void cargarListaSecador() {
		ISecadorADao eDao = new SecadorADaoImpl();

		if (fechaFiltro != null) {
			listaSecador = eDao.listaPorFechaSecador(fechaFiltro);

			for (int j = 0; j < 1; j++) {
				folioFecha = listaSecador.get(j).getFolioSecador();
				fecha = listaSecador.get(j).getFecha();
				if (j == 0) {
					folioLimEsp = listaSecador.get(j).getFolioLm();
					folioLimRef = listaSecador.get(j).getFolioLr();
					llenadoLimites();
					break;
				}

			}

		} else {
			listaSecador = eDao.listaSecador();
			if (!listaSecador.isEmpty()) {
				for (int j = 0; j < 1; j++) {

					if (j == 0) {
						folioLimEsp = listaSecador.get(j).getFolioLm();
						folioLimRef = listaSecador.get(j).getFolioLr();
						llenadoLimites();
						break;
					}

				}
			}
		}

	}
}
