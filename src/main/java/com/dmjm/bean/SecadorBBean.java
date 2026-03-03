package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.dmjm.dao.IFolioPreparacionSecadorBDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimitesDao;
import com.dmjm.dao.ILimpiezaSecadorBDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantenimientoSecadorBDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ISecadorBDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioPreparacionSecadorBDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimitesDaoImpl;
import com.dmjm.impl.LimpiezaSecadorBDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantenimientoSecadorBDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.SecadorBDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionSecadorB;
import com.dmjm.model.LimitesEspecificosA;
import com.dmjm.model.LimitesEspecificosB;
import com.dmjm.model.LimitesReferenciaA;
import com.dmjm.model.LimitesReferenciaB;
import com.dmjm.model.LimpiezaSecadorB;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoSecadorB;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.SecadorB;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("secadorBBean")
@ViewScoped
public class SecadorBBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SecadorB> listaSecador;
	private SecadorB secador;
	private SecadorB secadorEditar;

	private List<SecadorB> listaFiltroSecador;

	private Date fecha;
	private int folioFecha;
	private int folioPrepSec;
	private Date fechaFiltro;

	private LimpiezaSecadorB limpieza;
	private List<LimpiezaSecadorB> limpiezaSecadorB;
	private LimpiezaSecadorB limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoSecadorB> listaOrdenManto;
	private OrdenMantenimientoSecadorB ordenMantenimiento;
	private OrdenMantenimientoSecadorB ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionSecadorB> listaFolioSecadorB;
	private FolioPreparacionSecadorB folioPreparacionSecadorB;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private int folioSeleccionado;

	private String bandera;

	private LimitesEspecificosB limEsp;
	private LimitesReferenciaB limRef;
	private int folioLimEsp;
	private int folioLimRef;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(SecadorBBean.class.getName());

	public SecadorBBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		listaSecador = new ArrayList<>();
		secador = new SecadorB();
		secadorEditar = new SecadorB();

		limpieza = new LimpiezaSecadorB();
		limpiezaSecadorB = new ArrayList<>();
		limpiezaEditar = new LimpiezaSecadorB();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoSecadorB();
		ordenMantenimientoEditar = new OrdenMantenimientoSecadorB();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionSecadorB = new FolioPreparacionSecadorB();

		listaFiltroSecador = new ArrayList<SecadorB>();
		ISecadorBDao lSecadorDao = new SecadorBDaoImpl();
		listaFiltroSecador = lSecadorDao.listaFiltroSecador();

		cargarListaSecador();
		primera();

	}

	public FolioPreparacionSecadorB getFolioPreparacionSecadorB() {
		return folioPreparacionSecadorB;
	}

	public void setFolioPreparacionSecadorB(FolioPreparacionSecadorB folioPreparacionSecadorB) {
		this.folioPreparacionSecadorB = folioPreparacionSecadorB;
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

	public LimitesEspecificosB getLimEsp() {
		return limEsp;
	}

	public void setLimEsp(LimitesEspecificosB limEsp) {
		this.limEsp = limEsp;
	}

	public LimitesReferenciaB getLimRef() {
		return limRef;
	}

	public void setLimRef(LimitesReferenciaB limRef) {
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

	public SecadorB getSecador() {
		return secador;
	}

	public void setSecador(SecadorB secador) {
		this.secador = secador;
	}

	public SecadorB getSecadorEditar() {
		return secadorEditar;
	}

	public void setSecadorEditar(SecadorB secadorEditar) {
		this.secadorEditar = secadorEditar;
	}

	public List<SecadorB> getListaSecador() {

		return listaSecador;
	}

	public List<SecadorB> getListaFiltroSecador() {
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

	public LimpiezaSecadorB getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaSecadorB limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaSecadorB getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && ("ENJUAGUE".equals(limpiezaEditar.getProceso())
				|| "LIMPIEZA MECÁNICA".equals(limpiezaEditar.getProceso()))) {
			limpiezaEditar.setQuimico("AGUA");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaSecadorB limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<LimpiezaSecadorB> getLimpiezaSecadorB() {

		ILimpiezaSecadorBDao lDao = new LimpiezaSecadorBDaoImpl();

		IFolioPreparacionSecadorBDao folioPrepDao = new FolioPreparacionSecadorBDaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorBActual(fecha);
		limpiezaSecadorB = lDao.listarLimpieza(folioPrepSec);
		return limpiezaSecadorB;
	}

	public List<SecadorB> obtenerElementosDePagina(int pagina) {
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
		return vDao.validarFechasFaltantes(80, "FOLIO_PREPARACION_SECADOR_B");
	}

	public void guardarSecadorB() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_SECADOR_B");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='SecadorB.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				ISecadorBDao cDao = new SecadorBDaoImpl();
				secador = new SecadorB();

				// **FOLIO**//
				int folio = 0;
				// year = LocalDate.now().getYear();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fec);
				int newYear = calendario.get(Calendar.YEAR);
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioSeacadorB(newYear);

				// **FOLIO_PREPARACION_SECADOR**//

				IFolioPreparacionSecadorBDao estDao = new FolioPreparacionSecadorBDaoImpl();
				FolioPreparacionSecadorB fpe = new FolioPreparacionSecadorB();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					secador.setFolioSecador(folio);
					secador.setHora(lista);
					secador.setFolioPreparacionSecadorB(fpe);
					secador.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					secador.setFolioLm(folioLm());
					secador.setFolioLr(folioLr());
					cDao.guardarSecador(secador);
					secador = new SecadorB();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioSecadorB(newYear, folio);
			}
			String script = "setTimeout(function() { window.location.href='SecadorB.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public void actualizaSecadorB() {

		ISecadorBDao cDao = new SecadorBDaoImpl();

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
		actualizarPromedios(secadorEditar.getFolioPreparacionSecadorB().getIdFolioPrep());

		if (secadorEditar.getHora().equals("7:00")) {
			ISecadorBDao aDao = new SecadorBDaoImpl();
			aDao.actualizarSecadorPromedio(secadorEditar.getOperacion(),
					secadorEditar.getFolioPreparacionSecadorB().getIdFolioPrep());
		}
		secadorEditar = new SecadorB();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {

		ISecadorBDao l1 = new SecadorBDaoImpl();
		l1.actualizarLimiteUno(folio);

		ISecadorBDao l2 = new SecadorBDaoImpl();
		l2.actualizarLimiteDos(folio);

		ISecadorBDao l3 = new SecadorBDaoImpl();
		l3.actualizarLimiteTres(folio);

		ISecadorBDao l4 = new SecadorBDaoImpl();
		l4.actualizarLimiteCuatro(folio);

		ISecadorBDao l5 = new SecadorBDaoImpl();
		l5.actualizarLimiteCinco(folio);

		ISecadorBDao l6 = new SecadorBDaoImpl();
		l6.actualizarLimiteSeis(folio);

		ISecadorBDao l7 = new SecadorBDaoImpl();
		l7.actualizarLimiteSiete(folio);

		ISecadorBDao l8 = new SecadorBDaoImpl();
		l8.actualizarLimiteOcho(folio);

		ISecadorBDao l9 = new SecadorBDaoImpl();
		l9.actualizarLimiteNueve(folio);

		ISecadorBDao v = new SecadorBDaoImpl();
		v.actualizarVapor(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionSecadorB f = new FolioPreparacionSecadorB();
		f.setIdFolioPrep(folioPrepSec);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaSecadorBDao validaDao = new LimpiezaSecadorBDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepSec);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaSecadorBDao lDao = new LimpiezaSecadorBDaoImpl();

		ISecadorBDao vDao = new SecadorBDaoImpl();
		vDao.actualizarLimpieza(folioPrepSec, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionSecadorB(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoSecador(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaSecadorB();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaSecadorBDao lDao = new LimpiezaSecadorBDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaSecadorB();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		ISecadorBDao vDao = new SecadorBDaoImpl();
		vDao.actualizarLimpieza(folioPrepSec, 0);
		ILimpiezaSecadorBDao iDao = new LimpiezaSecadorBDaoImpl();
		iDao.borrarLimpieza(folioPrepSec, noLimpiezaSeleccionadaBorrar);

	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Secador B");
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
		listaOperadores = oDao.listaOperadorSecadorB();
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
		listarRegistroTurnos = rDao.listaRegistroTurnosSecadorB(fecha);
		IFolioPreparacionSecadorBDao folioPrepDao = new FolioPreparacionSecadorBDaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorBActual(fecha);
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
		rt.setDescProceso("SECADOR B");

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
		return tDao.completeOperador(nombre, "Secador B");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Secador B");
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

	public OrdenMantenimientoSecadorB getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoSecadorB ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoSecadorB getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoSecadorB ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoSecadorB> getListaOrdenManto() {
		IOrdenMantenimientoSecadorBDao oDao = new OrdenMantenimientoSecadorBDaoImpl();
		IFolioPreparacionSecadorBDao folioPrepDao = new FolioPreparacionSecadorBDaoImpl();
		this.folioPrepSec = folioPrepDao.folioSecadorBActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepSec);
		return listaOrdenManto;
	}

	public void actualizarOrdenManto() {
		IOrdenMantenimientoSecadorBDao iDao = new OrdenMantenimientoSecadorBDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoSecadorB();
	}

	public void borrarOrdenManto() {
		IOrdenMantenimientoSecadorBDao iDao = new OrdenMantenimientoSecadorBDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoSecadorB();
	}

	public void borrarVoBo() {
		ILimpiezaSecadorBDao iDao = new LimpiezaSecadorBDaoImpl();
		iDao.borrarVoBo(folioPrepSec, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaSecadorBDao iDao = new LimpiezaSecadorBDaoImpl();
		iDao.agregarVoBo(folioPrepSec, noLimpiezaVoBo, us.getIdUsuario());
	}

	public List<FolioPreparacionSecadorB> getListaFolioSecadorB() {
		IFolioPreparacionSecadorBDao lDao = new FolioPreparacionSecadorBDaoImpl();
		listaFolioSecadorB = lDao.listaFolioSecadorB(folioPrepSec);
		return listaFolioSecadorB;
	}

	public void primera() {
		IFolioPreparacionSecadorBDao fDao = new FolioPreparacionSecadorBDaoImpl();
		FolioPreparacionSecadorB f = new FolioPreparacionSecadorB();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionSecadorBDao folioDao = new FolioPreparacionSecadorBDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionSecadorBDao folioPrepDao = new FolioPreparacionSecadorBDaoImpl();
			this.folioPrepSec = folioPrepDao.folioSecadorBActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaSecadorB();
			getListaOrdenManto();
			getListaFolioSecadorB();
//			llenadoLimites();

		}
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaSecadorBDao lDao = new LimpiezaSecadorBDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepSec);
		return listaLimpiezas;
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<SecadorB> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			this.folioLimEsp = paginaActual.get(0).getFolioLm();
			this.folioLimRef = paginaActual.get(0).getFolioLr();

			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionSecadorBDao fDao = new FolioPreparacionSecadorBDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionSecadorBDao folioPrepDao = new FolioPreparacionSecadorBDaoImpl();
				this.folioPrepSec = folioPrepDao.folioSecadorBActual(fecha);
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
		getLimpiezaSecadorB();
		getListaOrdenManto();
		getListaFolioSecadorB();
//		llenadoLimites();

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ISecadorBDao validaDao = new SecadorBDaoImpl();
		validaDao.actualizarManto(folioPrepSec);

		IOrdenMantenimientoSecadorBDao iDao = new OrdenMantenimientoSecadorBDaoImpl();

		FolioPreparacionSecadorB f = new FolioPreparacionSecadorB();
		f.setIdFolioPrep(folioPrepSec);
		ordenMantenimiento.setFolioPreparacionSecadorB(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoSecadorB();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioSecadorB.size(); i++) {
			folioPreparacionSecadorB.setObservaciones(listaFolioSecadorB.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void guardarObservaciones() {
		IFolioPreparacionSecadorBDao fDao = new FolioPreparacionSecadorBDaoImpl();
		fDao.guardarObservacion(folioPrepSec, folioPreparacionSecadorB.getObservaciones());
		folioPreparacionSecadorB = new FolioPreparacionSecadorB();
	}

	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/SecadorB.jasper");
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

		ruta = servletContext.getRealPath("/REP/SecadorB.jasper");

		reporte.getReporte(ruta, fec, folioFechaRep);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void llenadoLimites() {
		ILimitesDao lEDao = new LimitesDaoImpl();
		limEsp = lEDao.limEspB(folioLimEsp);

		ILimitesDao lRDao = new LimitesDaoImpl();
		limRef = lRDao.limRefB(folioLimRef);

	}

	public int folioLm() {
		// VALIDAMOS LOS FOLIOS DE LIMITES ESPECIFICOS Y DE REFERENCIA

		ILimitesDao lmDao = new LimitesDaoImpl();

		return lmDao.limEspB().getFolioLm();
	}

	public int folioLr() {
		// VALIDAMOS LOS FOLIOS DE LIMITES ESPECIFICOS Y DE REFERENCIA

		ILimitesDao lmDao = new LimitesDaoImpl();

		return lmDao.limRefB().getFolioLr();
	}

	public void cargarListaSecador() {
		ISecadorBDao eDao = new SecadorBDaoImpl();

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
