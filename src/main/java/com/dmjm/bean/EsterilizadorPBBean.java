package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IEsterilizdorPlantaBDao;
import com.dmjm.dao.IFolioPreparacionEsterilizadorPlantaBDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaEsterilizadorPlantaBDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaBDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.EsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.FolioPreparacionEsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaEsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoEsterilizadorPlantaBImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.EsterilizadorPlantaB;
import com.dmjm.model.FolioPreparacionEstB;
import com.dmjm.model.LimpiezaEstB;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoEstB;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("esterilizadorBeanB")
@ViewScoped
public class EsterilizadorPBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EsterilizadorPlantaB> listaEsterilizador;
	private EsterilizadorPlantaB esterilizadorPlantaB;
	private EsterilizadorPlantaB esterilizadorEditarPlantaB;

	private Date fecha;
	private int folioFecha;
	private int folioPrepEst;
	private Date fechaFiltro;

	private LimpiezaEstB limpieza;
	private List<LimpiezaEstB> limpiezaEstB;
	private LimpiezaEstB limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;
	
	private List<OrdenMantenimientoEstB> listaOrdenManto;
	private OrdenMantenimientoEstB ordenMantenimiento;
	private OrdenMantenimientoEstB ordenMantenimientoEditar;

	@PostConstruct
	public void init() {
		listaEsterilizador = new ArrayList<>();
		esterilizadorPlantaB = new EsterilizadorPlantaB();
		esterilizadorEditarPlantaB = new EsterilizadorPlantaB();

		limpieza = new LimpiezaEstB();
		limpiezaEstB = new ArrayList<>();
		limpiezaEditar = new LimpiezaEstB();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();
		
		
		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoEstB();
		ordenMantenimientoEditar = new OrdenMantenimientoEstB();


		primera();
		getListarRegistroTurnos();
		getLimpiezaEstB();
		getListaOrdenManto();
	}

	public EsterilizadorPBBean() {

	}

	public EsterilizadorPlantaB getEsterilizadorPlantaB() {
		return esterilizadorPlantaB;
	}

	public void setEsterilizadorPlantaB(EsterilizadorPlantaB esterilizadorPlantaB) {
		this.esterilizadorPlantaB = esterilizadorPlantaB;
	}

	public EsterilizadorPlantaB getEsterilizadorEditarPlantaB() {
		return esterilizadorEditarPlantaB;
	}

	public void setEsterilizadorEditarPlantaB(EsterilizadorPlantaB esterilizadorEditarPlantaB) {
		this.esterilizadorEditarPlantaB = esterilizadorEditarPlantaB;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public LimpiezaEstB getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaEstB limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<EsterilizadorPlantaB> getListaEsterilizador() {
		IEsterilizdorPlantaBDao eDao = new EsterilizadorPlantaBDaoImpl();
		if (fechaFiltro != null) {
			listaEsterilizador = eDao.listaPorFechaEsterilizador(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaEsterilizador.get(i).getFolioEsterilizador();
				fecha = listaEsterilizador.get(i).getFecha();
			}
		} else {
			listaEsterilizador = eDao.listaEsterilizador();
		}

		return listaEsterilizador;
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

	public int getFolioPrepEst() {
		return folioPrepEst;
	}

	public void setFolioPrepEst(int folioPrepEst) {
		this.folioPrepEst = folioPrepEst;
	}
	
	

	public OrdenMantenimientoEstB getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoEstB ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoEstB getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoEstB ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	
	public List<OrdenMantenimientoEstB> getListaOrdenManto() {
		IOrdenMantoEsterilizadorPalantaBDao oDao = new OrdenMantoEsterilizadorPlantaBImpl();
		IFolioPreparacionEsterilizadorPlantaBDao folioPrepDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstBActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepEst);
		return listaOrdenManto;
	}
	
	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorCocedores();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Cocedores");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
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

	public void guardarEsterilizador() {

		String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
				"5:00", "6:00", "PROM." };

		IEsterilizdorPlantaBDao cDao = new EsterilizadorPlantaBDaoImpl();

		esterilizadorPlantaB = new EsterilizadorPlantaB();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();
		folio = folDao.buscarFolioB(year);

		// **FOLIO_PREPARACION_EST_A**//

		IFolioPreparacionEsterilizadorPlantaBDao estDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		FolioPreparacionEstB fpe = new FolioPreparacionEstB();
		fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			esterilizadorPlantaB.setFolioEsterilizador(folio);
			esterilizadorPlantaB.setHora(lista);
			esterilizadorPlantaB.setFolioPreparacionEstB(fpe);
			esterilizadorPlantaB.setFecha(new Date());
			cDao.guardarEsterilizador(esterilizadorPlantaB);
			esterilizadorPlantaB = new EsterilizadorPlantaB();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolioB(year, folio);

		String script = "setTimeout(function() { window.location.href='EsterilizadorPlantaB.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarEsterilizador() {
		IEsterilizdorPlantaBDao cDao = new EsterilizadorPlantaBDaoImpl();

		String oper = esterilizadorEditarPlantaB.getOperacion().replaceAll("\\s+", "");
		esterilizadorEditarPlantaB.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		cDao.actualizarEsterilizador(esterilizadorEditarPlantaB);
		actualizarPromedios(esterilizadorEditarPlantaB.getFolioPreparacionEstB().getIdFolioPrep());

		if (esterilizadorEditarPlantaB.getHora().equals("7:00")) {
			IEsterilizdorPlantaBDao aDao = new EsterilizadorPlantaBDaoImpl();
			aDao.actualizarEsterilizadorPromedio(esterilizadorEditarPlantaB.getOperacion(),
					esterilizadorEditarPlantaB.getFolioPreparacionEstB().getIdFolioPrep());
		}
		esterilizadorEditarPlantaB = new EsterilizadorPlantaB();

	}

	public List<EsterilizadorPlantaB> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaEsterilizador.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaEsterilizador.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<EsterilizadorPlantaB> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionEsterilizadorPlantaBDao fDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionEsterilizadorPlantaBDao folioPrepDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
				this.folioPrepEst = folioPrepDao.folioEstBActual(fecha);
			}

		}
	}

	public void primera() {
		IFolioPreparacionEsterilizadorPlantaBDao fDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		FolioPreparacionEstB f = new FolioPreparacionEstB();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionEsterilizadorPlantaBDao folioDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionEsterilizadorPlantaBDao folioPrepDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
			this.folioPrepEst = folioPrepDao.folioEstBActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaEstB();
			getListaOrdenManto();
		}

	}

	public void actualizarPromedios(int folio) {
		IEsterilizdorPlantaBDao porcentaje = new EsterilizadorPlantaBDaoImpl();
		porcentaje.actualizarPromedioGrenetina(folio);

		IEsterilizdorPlantaBDao pv = new EsterilizadorPlantaBDaoImpl();
		pv.actualizarPromedioPresionVapor(folio);

		IEsterilizdorPlantaBDao pva = new EsterilizadorPlantaBDaoImpl();
		pva.actualizarPromedioPresionVacio(folio);

		IEsterilizdorPlantaBDao pre = new EsterilizadorPlantaBDaoImpl();
		pre.actualizarPromedioPrecalentador(folio);

		IEsterilizdorPlantaBDao est = new EsterilizadorPlantaBDaoImpl();
		est.actualizarPromedioEsterilizador(folio);

		IEsterilizdorPlantaBDao valdiv = new EsterilizadorPlantaBDaoImpl();
		valdiv.actualizarPromedioValDiv(folio);

		IEsterilizdorPlantaBDao ba = new EsterilizadorPlantaBDaoImpl();
		ba.actualizarPromedioBombaAlim(folio);

		IEsterilizdorPlantaBDao test = new EsterilizadorPlantaBDaoImpl();
		test.actualizarPromedioTimpoEst(folio);

		IEsterilizdorPlantaBDao bs = new EsterilizadorPlantaBDaoImpl();
		bs.actualizarPromedioBombaSalida(folio);

		IEsterilizdorPlantaBDao flujo = new EsterilizadorPlantaBDaoImpl();
		flujo.actualizarPromedioFlujoLitros(folio);

		IEsterilizdorPlantaBDao presion = new EsterilizadorPlantaBDaoImpl();
		presion.actualizarPromedioPresioSistema(folio);

		IEsterilizdorPlantaBDao rede = new EsterilizadorPlantaBDaoImpl();
		rede.actualizarPromedioRedoxEntrada(folio);

		IEsterilizdorPlantaBDao reds = new EsterilizadorPlantaBDaoImpl();
		reds.actualizarPromedioRedoxSalida(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ALCALINO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionEstB f = new FolioPreparacionEstB();
		f.setIdFolioPrep(folioPrepEst);

		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		for (String l : datosLimpieza) {
			limpieza.setFolioPreparacionEstB(f);
			limpieza.setProceso(l);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaEstB();
		}

	}

	public List<LimpiezaEstB> getLimpiezaEstB() {
		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();

		IFolioPreparacionEsterilizadorPlantaBDao folioPrepDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstBActual(fecha);
		limpiezaEstB = lDao.listarLimpieza(folioPrepEst);
		return limpiezaEstB;
	}

	public void actualizarLimpieza() {
		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaEstB();
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
		rt.setDescProceso("ESTERILIZADOR PLANTA B");

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
		listarRegistroTurnos = rDao.listaRegistroTurnosEstB(fecha);
		IFolioPreparacionEsterilizadorPlantaBDao folioPrepDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstBActual(fecha);
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
		return tDao.completeOperador(nombre);
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre);
	}
	

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
//		ICocedoresDao validaDao = new CocedoresDaoImpl();
//
//		validaDao.actualizarManto(folioPrepCocedor);

		IOrdenMantoEsterilizadorPalantaBDao iDao = new OrdenMantoEsterilizadorPlantaBImpl();
		FolioPreparacionEstB f = new FolioPreparacionEstB();
		f.setIdFolioPrep(folioPrepEst);
		ordenMantenimiento.setFolioPreparacionEstB(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoEstB();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoEsterilizadorPalantaBDao iDao = new OrdenMantoEsterilizadorPlantaBImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoEstB();
	}
	
	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaEsterilizador(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaEstB();
		getListaOrdenManto();
	}


}
