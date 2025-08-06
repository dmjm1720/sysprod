package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import com.dmjm.dao.IFolioPreparacionVotatorADao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaVotatorADao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoVotatorADao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.IResumenVotatorADao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IVotatorADao;
import com.dmjm.impl.FolioPreparacionVotatorADaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaVotatorADaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoVotatorADaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.ResumenVotatorADaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.VotatorADaoImpl;
import com.dmjm.model.FolioPreparacionVotatorA;
import com.dmjm.model.LimpiezaVotatorA;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoVotatorA;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.ResumenVotatorA;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.model.VotatorA;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("votatorABean")
@ViewScoped
public class VotatorABean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VotatorA> listaVotator;
	private VotatorA votator;
	private VotatorA votatorEditar;

	private List<VotatorA> listaFiltroVotatorPA;

	private Date fecha;
	private int folioFecha;
	private int folioPrepVotator;
	private Date fechaFiltro;

	private LimpiezaVotatorA limpieza;
	private List<LimpiezaVotatorA> limpiezaVotatorA;
	private LimpiezaVotatorA limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoVotatorA> listaOrdenManto;
	private OrdenMantenimientoVotatorA ordenMantenimiento;
	private OrdenMantenimientoVotatorA ordenMantenimientoEditar;

	private List<Integer> listaLimpiezas;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionVotatorA> listaFolioVotatorPA;
	private FolioPreparacionVotatorA folioPrepVotatorPA;
	private String cocedorSeleccionado;
	private List<String> procesos;
	
	private List<ResumenVotatorA> listaResumenVotator;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(VotatorABean.class.getName());

	public VotatorABean() {

	}

	@PostConstruct
	public void init() {
		listaVotator = new ArrayList<>();
		votator = new VotatorA();
		votatorEditar = new VotatorA();

		limpieza = new LimpiezaVotatorA();
		limpiezaVotatorA = new ArrayList<>();
		limpiezaEditar = new LimpiezaVotatorA();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoVotatorA();
		ordenMantenimientoEditar = new OrdenMantenimientoVotatorA();

		listaFiltroVotatorPA = new ArrayList<>();
		IVotatorADao lfDao = new VotatorADaoImpl();
		listaFiltroVotatorPA = lfDao.listaFiltroVotator();

		listaLimpiezas = new ArrayList<>();
		folioPrepVotatorPA = new FolioPreparacionVotatorA();
		listaResumenVotator = new ArrayList<>();
		
		primera();
		getListarRegistroTurnos();
		getLimpiezaVotatorA();
		getListaOrdenManto();
		getListaFolioVotatorPA();
		getListaResumenVotator();
		procesos = new ArrayList<>();
		procesos.add("Cocedor 1");
		procesos.add("Cocedor 2");
		procesos.add("Cocedor 3");
		procesos.add("Cocedor 4");
		procesos.add("Cocedor 5");
		procesos.add("Cocedor 6");
		procesos.add("Cocedor 7");
		procesos.add("Cocedor 8");
		procesos.add("Cocedor 9");
		procesos.add("Cocedor 10");

	}

	
	
	public List<ResumenVotatorA> getListaResumenVotator() {
		IResumenVotatorADao rDao = new ResumenVotatorADaoImpl();
		listaResumenVotator = rDao.listaResumen(folioPrepVotator);
		return listaResumenVotator;
	}

	public List<String> getProcesos() {
		return procesos;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaVotatorADao lDao = new LimpiezaVotatorADaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepVotator);
		return listaLimpiezas;
	}

	public List<FolioPreparacionVotatorA> getListaFolioVotatorPA() {
		IFolioPreparacionVotatorADao lDao = new FolioPreparacionVotatorADaoImpl();
		listaFolioVotatorPA = lDao.listaFolioVotatorA(folioPrepVotator);
		return listaFolioVotatorPA;
	}

	public FolioPreparacionVotatorA getFolioPreparacionVotatorA() {
		return folioPrepVotatorPA;
	}

	public void setFolioPreparacionVotatorA(FolioPreparacionVotatorA folioPrepVotatorPA) {
		this.folioPrepVotatorPA = folioPrepVotatorPA;
	}

	public int getNoLimpiezaSeleccionadaBorrar() {
		return noLimpiezaSeleccionadaBorrar;
	}

	public void setNoLimpiezaSeleccionadaBorrar(int noLimpiezaSeleccionadaBorrar) {
		this.noLimpiezaSeleccionadaBorrar = noLimpiezaSeleccionadaBorrar;
	}

	public int getNoLimpiezaVoBo() {
		return noLimpiezaVoBo;
	}

	public void setNoLimpiezaVoBo(int noLimpiezaVoBo) {
		this.noLimpiezaVoBo = noLimpiezaVoBo;
	}

	public List<VotatorA> getListaFiltroVotatorPA() {
		return listaFiltroVotatorPA;
	}

	public VotatorA getVotator() {
		return votator;
	}

	public void setVotator(VotatorA votator) {
		this.votator = votator;
	}

	public VotatorA getVotatorEditar() {
		return votatorEditar;
	}

	public void setVotatorEditar(VotatorA votatorEditar) {
		this.votatorEditar = votatorEditar;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public LimpiezaVotatorA getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("AGUA");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaVotatorA limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<VotatorA> getListaVotator() {
		IVotatorADao eDao = new VotatorADaoImpl();
		if (fechaFiltro != null) {
			listaVotator = eDao.listaPorFechaVotator(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaVotator.get(i).getFolioVotator();
				fecha = listaVotator.get(i).getFecha();
			}
		} else {
			listaVotator = eDao.listaVotator();
		}

		return listaVotator;
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

	public int getFolioPrepVotator() {
		return folioPrepVotator;
	}

	public void setFolioPrepVotator(int folioPrepVotator) {
		this.folioPrepVotator = folioPrepVotator;
	}

	public OrdenMantenimientoVotatorA getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoVotatorA ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoVotatorA getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoVotatorA ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoVotatorA> getListaOrdenManto() {
		IOrdenMantoVotatorADao oDao = new OrdenMantoVotatorADaoImpl();
		IFolioPreparacionVotatorADao folioPrepDao = new FolioPreparacionVotatorADaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorAActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepVotator);
		return listaOrdenManto;
	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorEstPlantaA();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Votator Planta A");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
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

	public void guardarVotator() {

		String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
				"5:00", "6:00", "PROM." };

		IVotatorADao cDao = new VotatorADaoImpl();

		votator = new VotatorA();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

		folio = folDao.buscarFolioVotatorA(year);

		// **FOLIO_PREPARACION_VOTATOR_A**//

		IFolioPreparacionVotatorADao estDao = new FolioPreparacionVotatorADaoImpl();
		FolioPreparacionVotatorA fpe = new FolioPreparacionVotatorA();
		fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			votator.setFolioVotator(folio);
			votator.setHora(lista);
			votator.setFolioPreparacionVotatorA(fpe);
			votator.setFecha(new Date());
			cDao.guardarVotator(votator);
			votator = new VotatorA();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolioVotatorA(year, folio);

		String script = "setTimeout(function() { window.location.href='VotatorA.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarVotator() {

		IVotatorADao cDao = new VotatorADaoImpl();

		String oper = votatorEditar.getOperacion().replaceAll("\\s+", "");
		votatorEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		cDao.actualizarVotator(votatorEditar);
		actualizarPromedios(votatorEditar.getFolioPreparacionVotatorA().getFolioVotatorA());

		if (votatorEditar.getHora().equals("7:00")) {
			IVotatorADao aDao = new VotatorADaoImpl();
			aDao.actualizarVotatorPromedio(votatorEditar.getOperacion(),
					votatorEditar.getFolioPreparacionVotatorA().getIdFolioPrep());
		}
		votatorEditar = new VotatorA();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	public List<VotatorA> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaVotator.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaVotator.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<VotatorA> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionVotatorADao fDao = new FolioPreparacionVotatorADaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionVotatorADao folioPrepDao = new FolioPreparacionVotatorADaoImpl();
				this.folioPrepVotator = folioPrepDao.folioVotatorAActual(fecha);
			}

		}
	}

	public void primera() {
		IFolioPreparacionVotatorADao fDao = new FolioPreparacionVotatorADaoImpl();
		FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionVotatorADao folioDao = new FolioPreparacionVotatorADaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionVotatorADao folioPrepDao = new FolioPreparacionVotatorADaoImpl();
			this.folioPrepVotator = folioPrepDao.folioVotatorAActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaVotatorA();
			getListaOrdenManto();
			getListaFolioVotatorPA();
			getListaResumenVotator();
		}

	}

	public void actualizarPromedios(int folio) {
		IVotatorADao conc_refractometro = new VotatorADaoImpl();
		conc_refractometro.actualizarConcentradoRefractometro(folio);

		IVotatorADao ph = new VotatorADaoImpl();
		ph.actualizarPH(folio);

		IVotatorADao redox = new VotatorADaoImpl();
		redox.actualizarRedoxHumeda(folio);

		IVotatorADao temp_ent = new VotatorADaoImpl();
		temp_ent.actualizarTempEntVotator(folio);

		IVotatorADao temp_sal = new VotatorADaoImpl();
		temp_sal.actualizarTempSalVotator(folio);

		IVotatorADao receptor = new VotatorADaoImpl();
		receptor.actualizarReceptorPSI(folio);

		IVotatorADao succion = new VotatorADaoImpl();
		succion.actualizarSuccion(folio);

		IVotatorADao descarga = new VotatorADaoImpl();
		descarga.actualizarDescarga(folio);

		IVotatorADao aceite = new VotatorADaoImpl();
		aceite.actualizarAceite(folio);

		IVotatorADao motor = new VotatorADaoImpl();
		motor.actualizarMotor(folio);

		IVotatorADao bomba_alim = new VotatorADaoImpl();
		bomba_alim.actualizarBombaAlim(folio);

		IVotatorADao redox_seco = new VotatorADaoImpl();
		redox_seco.actualizarRedoxSeco(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
		f.setIdFolioPrep(folioPrepVotator);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaVotatorADao validaDao = new LimpiezaVotatorADaoImpl();

		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepVotator);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaVotatorADao lDao = new LimpiezaVotatorADaoImpl();

		IVotatorADao vDao = new VotatorADaoImpl();
		vDao.actualizarLimpieza(folioPrepVotator, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionVotatorA(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoVotator(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaVotatorA();
		}

	}

	public List<LimpiezaVotatorA> getLimpiezaVotatorA() {
		ILimpiezaVotatorADao lDao = new LimpiezaVotatorADaoImpl();

		IFolioPreparacionVotatorADao folioPrepDao = new FolioPreparacionVotatorADaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorAActual(fecha);
		limpiezaVotatorA = lDao.listarLimpieza(folioPrepVotator);
		return limpiezaVotatorA;
	}

	public void actualizarLimpieza() {
		ILimpiezaVotatorADao lDao = new LimpiezaVotatorADaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaVotatorA();
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
		rt.setDescProceso("VOTATOR PLANTA A");

		rDao.guardaRegistroTurnos(rt);

		u = new Usuarios();
		o = new Operador();
		t = new Turnos();
		rt = new RegistroTurnos();
		filterUsuario = null;
		filterOperador = null;
		filterTurno = null;

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

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosVotatorA(fecha);
		IFolioPreparacionVotatorADao folioPrepDao = new FolioPreparacionVotatorADaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorAActual(fecha);
		return listarRegistroTurnos;
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
		return tDao.completeOperador(nombre, "Est Planta A");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Est Planta A");
	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		IVotatorADao validaDao = new VotatorADaoImpl();
		validaDao.actualizarManto(folioPrepVotator);

		IOrdenMantoVotatorADao iDao = new OrdenMantoVotatorADaoImpl();
		FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
		f.setIdFolioPrep(folioPrepVotator);
		ordenMantenimiento.setFolioPreparacionVotatorA(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoVotatorA();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoVotatorADao iDao = new OrdenMantoVotatorADaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoVotatorA();
	}

	public void borrarOrdenManto() {
		IOrdenMantoVotatorADao iDao = new OrdenMantoVotatorADaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoVotatorA();
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaVotator(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaVotatorA();
		getListaOrdenManto();
		getListaFolioVotatorPA();
		getListaResumenVotator();
	}

	// **REPORTE ESTERILIZADORES**//
	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/votator_rep_a.jasper");
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

		ruta = servletContext.getRealPath("/REP/votator_rep_a.jasper");

		reporte.getReporte(ruta, fec, folioFechaRep);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void visualizarReporteExcel() throws SQLException {
		@SuppressWarnings("unused")

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("/REP/votator_rep_a_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IVotatorADao vDao = new VotatorADaoImpl();
		vDao.actualizarLimpieza(folioPrepVotator, 0);
		ILimpiezaVotatorADao iDao = new LimpiezaVotatorADaoImpl();
		iDao.borrarLimpieza(folioPrepVotator, noLimpiezaSeleccionadaBorrar);

	}

	public void borrarVoBo() {
		ILimpiezaVotatorADao iDao = new LimpiezaVotatorADaoImpl();
		iDao.borrarVoBo(folioPrepVotator, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaVotatorADao iDao = new LimpiezaVotatorADaoImpl();
		iDao.agregarVoBo(folioPrepVotator, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void guardarObservaciones() {
		IFolioPreparacionVotatorADao fDao = new FolioPreparacionVotatorADaoImpl();
		fDao.guardarObservacion(folioPrepVotator, folioPrepVotatorPA.getObservaciones());
		folioPrepVotatorPA = new FolioPreparacionVotatorA();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioVotatorPA.size(); i++) {
			folioPrepVotatorPA.setObservaciones(listaFolioVotatorPA.get(i).getObservaciones());
		}
	}

	// VALIDACIONES DE RESUMEN

	public void resumenVotator() throws ParseException {
		IResumenVotatorADao rDao = new ResumenVotatorADaoImpl();
		rDao.borrarResumen(folioPrepVotator);

		IResumenVotatorADao gDao = new ResumenVotatorADaoImpl();
		// OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
		IVotatorADao operacionesDao = new VotatorADaoImpl();
		List<String> listaOperaciones = operacionesDao.listarOperaciones(folioPrepVotator);
		for (int i = 0; i < listaOperaciones.size(); i++) {
			System.out.println(listaOperaciones.get(i));
			LOGGER.info("OPERACION: " + listaOperaciones.get(i));
			// OBTERNER LA PRIMERA HORA DE LA OPERACION
			IVotatorADao primeraHoraDao = new VotatorADaoImpl();
			LOGGER.info(
					"PRIMERA HORA: " + primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepVotator));

			IVotatorADao ultimaHoraDao = new VotatorADaoImpl();
			LOGGER.info("ULTIMA HORA: " + ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepVotator));

			ResumenVotatorA resumen = new ResumenVotatorA();
			resumen.setNoOperacion(listaOperaciones.get(i));

			String horaPStr = primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepVotator);

			if (horaPStr.equals(":00")) {
				horaPStr = "00:00";
			}

			Boolean horaInicial = validarHora(horaPStr.trim());
			if (horaInicial.equals(true)) {
				resumen.setFechaInicio(sumarUnDia(fecha));
			} else {
				resumen.setFechaInicio(fecha);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date horaInicio = sdf.parse(horaPStr);

			String horaUStr = ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepVotator);
			if (horaUStr.equals(":00")) {
				horaUStr = "00:00";
			}
			Boolean horaFinal = validarHora(horaUStr.trim());

			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			Date horaFin = sdf2.parse(horaUStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(horaFin);
			calendar.add(Calendar.HOUR_OF_DAY, 1); // suma una hora

			Date horaFinMasUna = calendar.getTime();

			resumen.setHoraInicio(horaInicio);
			if (horaFinal.equals(true)) {
				resumen.setFechaFinal(sumarUnDia(fecha));
			} else {
				resumen.setFechaFinal(fecha);
			}

			resumen.setHoraFinal(horaFinMasUna);

			// Ajustar si se cruzó medianoche

			int horaInicioH = horaInicio.getHours();
			int horaFinH = horaFin.getHours();

			// Ajustar si se cruzó medianoche
			if (horaFinH < horaInicioH) {
				horaFinH += 24;
			}
			int difHoras = horaFinH - horaInicioH;

			resumen.setTiempo(difHoras + 1);
			FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
			f.setIdFolioPrep(folioPrepVotator);
			resumen.setFolioPreparacionVotatorA(f);
			gDao.guardarResumen(resumen);

		}

	}

	public Date sumarUnDia(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public Boolean validarHora(String hora) {
		if (hora.equals(":00") || hora.equals("1:00") || hora.equals("2:00") || hora.equals("3:00")
				|| hora.equals("4:00") || hora.equals("5:00") || hora.equals("6:00")) {
			return true;
		}
		return false;
	}

}
