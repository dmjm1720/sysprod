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

import com.dmjm.dao.IFolioPreparacionVotatorBDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaVotatorBDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoVotatorBDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.IResumenVotatorBDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IVotatorBDao;
import com.dmjm.impl.FolioPreparacionVotatorBDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaVotatorBDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoVotatorBDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.ResumenVotatorBDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.VotatorBDaoImpl;
import com.dmjm.model.FolioPreparacionVotatorB;
import com.dmjm.model.LimpiezaVotatorB;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoVotatorB;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.ResumenVotatorB;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.model.VotatorB;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("votatorBBean")
@ViewScoped
public class VotatorBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VotatorB> listaVotator;
	private VotatorB votator;
	private VotatorB votatorEditar;

	private List<VotatorB> listaFiltroVotatorPB;

	private Date fecha;
	private int folioFecha;
	private int folioPrepVotator;
	private Date fechaFiltro;

	private LimpiezaVotatorB limpieza;
	private List<LimpiezaVotatorB> limpiezaVotatorB;
	private LimpiezaVotatorB limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoVotatorB> listaOrdenManto;
	private OrdenMantenimientoVotatorB ordenMantenimiento;
	private OrdenMantenimientoVotatorB ordenMantenimientoEditar;

	private List<Integer> listaLimpiezas;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionVotatorB> listaFolioVotatorPB;
	private FolioPreparacionVotatorB folioPrepVotatorPB;
	private String cocedorSeleccionado;
	private List<String> procesos;
	
	private List<ResumenVotatorB> listaResumenVotator;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(VotatorBBean.class.getName());

	public VotatorBBean() {

	}

	@PostConstruct
	public void init() {
		listaVotator = new ArrayList<>();
		votator = new VotatorB();
		votatorEditar = new VotatorB();

		limpieza = new LimpiezaVotatorB();
		limpiezaVotatorB = new ArrayList<>();
		limpiezaEditar = new LimpiezaVotatorB();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoVotatorB();
		ordenMantenimientoEditar = new OrdenMantenimientoVotatorB();

		listaFiltroVotatorPB = new ArrayList<>();
		IVotatorBDao lfDao = new VotatorBDaoImpl();
		listaFiltroVotatorPB = lfDao.listaFiltroVotator();

		listaLimpiezas = new ArrayList<>();
		folioPrepVotatorPB = new FolioPreparacionVotatorB();
		listaResumenVotator = new ArrayList<>();
		
		primera();
		getListarRegistroTurnos();
		getLimpiezaVotatorB();
		getListaOrdenManto();
		getListaFolioVotatorPB();
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

	
	
	public List<ResumenVotatorB> getListaResumenVotator() {
		IResumenVotatorBDao rDao = new ResumenVotatorBDaoImpl();
		listaResumenVotator = rDao.listaResumen(folioPrepVotator);
		return listaResumenVotator;
	}

	public List<String> getProcesos() {
		return procesos;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaVotatorBDao lDao = new LimpiezaVotatorBDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepVotator);
		return listaLimpiezas;
	}

	public List<FolioPreparacionVotatorB> getListaFolioVotatorPB() {
		IFolioPreparacionVotatorBDao lDao = new FolioPreparacionVotatorBDaoImpl();
		listaFolioVotatorPB = lDao.listaFolioVotatorB(folioPrepVotator);
		return listaFolioVotatorPB;
	}

	public FolioPreparacionVotatorB getFolioPreparacionVotatorB() {
		return folioPrepVotatorPB;
	}

	public void setFolioPreparacionVotatorB(FolioPreparacionVotatorB folioPrepVotatorPB) {
		this.folioPrepVotatorPB = folioPrepVotatorPB;
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

	public List<VotatorB> getListaFiltroVotatorPB() {
		return listaFiltroVotatorPB;
	}

	public VotatorB getVotator() {
		return votator;
	}

	public void setVotator(VotatorB votator) {
		this.votator = votator;
	}

	public VotatorB getVotatorEditar() {
		return votatorEditar;
	}

	public void setVotatorEditar(VotatorB votatorEditar) {
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

	public LimpiezaVotatorB getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("AGUA");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaVotatorB limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<VotatorB> getListaVotator() {
		IVotatorBDao eDao = new VotatorBDaoImpl();
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

	public OrdenMantenimientoVotatorB getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoVotatorB ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoVotatorB getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoVotatorB ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoVotatorB> getListaOrdenManto() {
		IOrdenMantoVotatorBDao oDao = new OrdenMantoVotatorBDaoImpl();
		IFolioPreparacionVotatorBDao folioPrepDao = new FolioPreparacionVotatorBDaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorBActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepVotator);
		return listaOrdenManto;
	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorEstPlantaB();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Votator Planta B");
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

		IVotatorBDao cDao = new VotatorBDaoImpl();

		votator = new VotatorB();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

		folio = folDao.buscarFolioVotatorB(year);

		// **FOLIO_PREPARACION_VOTATOR_B**//

		IFolioPreparacionVotatorBDao estDao = new FolioPreparacionVotatorBDaoImpl();
		FolioPreparacionVotatorB fpe = new FolioPreparacionVotatorB();
		fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			votator.setFolioVotator(folio);
			votator.setHora(lista);
			votator.setFolioPreparacionVotatorB(fpe);
			votator.setFecha(new Date());
			cDao.guardarVotator(votator);
			votator = new VotatorB();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolioVotatorB(year, folio);

		String script = "setTimeout(function() { window.location.href='VotatorB.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarVotator() {

		IVotatorBDao cDao = new VotatorBDaoImpl();

		String oper = votatorEditar.getOperacion().replaceAll("\\s+", "");
		votatorEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		cDao.actualizarVotator(votatorEditar);
		actualizarPromedios(votatorEditar.getFolioPreparacionVotatorB().getFolioVotatorB());

		if (votatorEditar.getHora().equals("7:00")) {
			IVotatorBDao aDao = new VotatorBDaoImpl();
			aDao.actualizarVotatorPromedio(votatorEditar.getOperacion(),
					votatorEditar.getFolioPreparacionVotatorB().getIdFolioPrep());
		}
		votatorEditar = new VotatorB();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	public List<VotatorB> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaVotator.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaVotator.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<VotatorB> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionVotatorBDao fDao = new FolioPreparacionVotatorBDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionVotatorBDao folioPrepDao = new FolioPreparacionVotatorBDaoImpl();
				this.folioPrepVotator = folioPrepDao.folioVotatorBActual(fecha);
			}

		}
	}

	public void primera() {
		IFolioPreparacionVotatorBDao fDao = new FolioPreparacionVotatorBDaoImpl();
		FolioPreparacionVotatorB f = new FolioPreparacionVotatorB();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionVotatorBDao folioDao = new FolioPreparacionVotatorBDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionVotatorBDao folioPrepDao = new FolioPreparacionVotatorBDaoImpl();
			this.folioPrepVotator = folioPrepDao.folioVotatorBActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaVotatorB();
			getListaOrdenManto();
			getListaFolioVotatorPB();
			getListaResumenVotator();
		}

	}

	public void actualizarPromedios(int folio) {
		IVotatorBDao conc_refractometro = new VotatorBDaoImpl();
		conc_refractometro.actualizarConcentradoRefractometro(folio);

		IVotatorBDao ph = new VotatorBDaoImpl();
		ph.actualizarPH(folio);

		IVotatorBDao redox = new VotatorBDaoImpl();
		redox.actualizarRedoxHumeda(folio);

		IVotatorBDao temp_ent = new VotatorBDaoImpl();
		temp_ent.actualizarTempEntVotator(folio);

		IVotatorBDao temp_sal = new VotatorBDaoImpl();
		temp_sal.actualizarTempSalVotator(folio);

		IVotatorBDao receptor = new VotatorBDaoImpl();
		receptor.actualizarReceptorPSI(folio);

		IVotatorBDao succion = new VotatorBDaoImpl();
		succion.actualizarSuccion(folio);

		IVotatorBDao descarga = new VotatorBDaoImpl();
		descarga.actualizarDescarga(folio);

		IVotatorBDao aceite = new VotatorBDaoImpl();
		aceite.actualizarAceite(folio);

		IVotatorBDao motor = new VotatorBDaoImpl();
		motor.actualizarMotor(folio);

		IVotatorBDao bomba_alim = new VotatorBDaoImpl();
		bomba_alim.actualizarBombaAlim(folio);

		IVotatorBDao redox_seco = new VotatorBDaoImpl();
		redox_seco.actualizarRedoxSeco(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionVotatorB f = new FolioPreparacionVotatorB();
		f.setIdFolioPrep(folioPrepVotator);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaVotatorBDao validaDao = new LimpiezaVotatorBDaoImpl();

		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepVotator);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaVotatorBDao lDao = new LimpiezaVotatorBDaoImpl();

		IVotatorBDao vDao = new VotatorBDaoImpl();
		vDao.actualizarLimpieza(folioPrepVotator, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionVotatorB(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoVotator(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaVotatorB();
		}

	}

	public List<LimpiezaVotatorB> getLimpiezaVotatorB() {
		ILimpiezaVotatorBDao lDao = new LimpiezaVotatorBDaoImpl();

		IFolioPreparacionVotatorBDao folioPrepDao = new FolioPreparacionVotatorBDaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorBActual(fecha);
		limpiezaVotatorB = lDao.listarLimpieza(folioPrepVotator);
		return limpiezaVotatorB;
	}

	public void actualizarLimpieza() {
		ILimpiezaVotatorBDao lDao = new LimpiezaVotatorBDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaVotatorB();
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
		rt.setDescProceso("VOTATOR PLANTA B");

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
		listarRegistroTurnos = rDao.listaRegistroTurnosVotatorB(fecha);
		IFolioPreparacionVotatorBDao folioPrepDao = new FolioPreparacionVotatorBDaoImpl();
		this.folioPrepVotator = folioPrepDao.folioVotatorBActual(fecha);
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
		return tDao.completeOperador(nombre, "Est Planta B");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Est Planta B");
	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		IVotatorBDao validaDao = new VotatorBDaoImpl();
		validaDao.actualizarManto(folioPrepVotator);

		IOrdenMantoVotatorBDao iDao = new OrdenMantoVotatorBDaoImpl();
		FolioPreparacionVotatorB f = new FolioPreparacionVotatorB();
		f.setIdFolioPrep(folioPrepVotator);
		ordenMantenimiento.setFolioPreparacionVotatorB(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoVotatorB();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoVotatorBDao iDao = new OrdenMantoVotatorBDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoVotatorB();
	}

	public void borrarOrdenManto() {
		IOrdenMantoVotatorBDao iDao = new OrdenMantoVotatorBDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoVotatorB();
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaVotator(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaVotatorB();
		getListaOrdenManto();
		getListaFolioVotatorPB();
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

		ruta = servletContext.getRealPath("/REP/esterilizadores_rep_a.jasper");
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

		ruta = servletContext.getRealPath("/REP/esterilizadores_rep_a.jasper");

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
		String ruta = servletContext.getRealPath("/REP/esterilizadores_rep_a_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IVotatorBDao vDao = new VotatorBDaoImpl();
		vDao.actualizarLimpieza(folioPrepVotator, 0);
		ILimpiezaVotatorBDao iDao = new LimpiezaVotatorBDaoImpl();
		iDao.borrarLimpieza(folioPrepVotator, noLimpiezaSeleccionadaBorrar);

	}

	public void borrarVoBo() {
		ILimpiezaVotatorBDao iDao = new LimpiezaVotatorBDaoImpl();
		iDao.borrarVoBo(folioPrepVotator, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaVotatorBDao iDao = new LimpiezaVotatorBDaoImpl();
		iDao.agregarVoBo(folioPrepVotator, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void guardarObservaciones() {
		IFolioPreparacionVotatorBDao fDao = new FolioPreparacionVotatorBDaoImpl();
		fDao.guardarObservacion(folioPrepVotator, folioPrepVotatorPB.getObservaciones());
		folioPrepVotatorPB = new FolioPreparacionVotatorB();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioVotatorPB.size(); i++) {
			folioPrepVotatorPB.setObservaciones(listaFolioVotatorPB.get(i).getObservaciones());
		}
	}

	// VALIDACIONES DE RESUMEN

	public void resumenVotator() throws ParseException {
		IResumenVotatorBDao rDao = new ResumenVotatorBDaoImpl();
		rDao.borrarResumen(folioPrepVotator);

		IResumenVotatorBDao gDao = new ResumenVotatorBDaoImpl();
		// OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
		IVotatorBDao operacionesDao = new VotatorBDaoImpl();
		List<String> listaOperaciones = operacionesDao.listarOperaciones(folioPrepVotator);
		for (int i = 0; i < listaOperaciones.size(); i++) {
			System.out.println(listaOperaciones.get(i));
			LOGGER.info("OPERACION: " + listaOperaciones.get(i));
			// OBTERNER LA PRIMERA HORA DE LA OPERACION
			IVotatorBDao primeraHoraDao = new VotatorBDaoImpl();
			LOGGER.info(
					"PRIMERA HORA: " + primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepVotator));

			IVotatorBDao ultimaHoraDao = new VotatorBDaoImpl();
			LOGGER.info("ULTIMA HORA: " + ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepVotator));

			ResumenVotatorB resumen = new ResumenVotatorB();
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
			FolioPreparacionVotatorB f = new FolioPreparacionVotatorB();
			f.setIdFolioPrep(folioPrepVotator);
			resumen.setFolioPreparacionVotatorB(f);
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
