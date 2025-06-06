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

import com.dmjm.dao.ICocedoresDao;
import com.dmjm.dao.IEsterilizdorPlantaADao;
import com.dmjm.dao.IFolioPreparacionEsterilizadorPlantaADao;
import com.dmjm.dao.IFolioPreparcionCocedoresDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaEsterilizadorPlantaADao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoDao;
import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaADao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.CocedoresDaoImpl;
import com.dmjm.impl.EsterilizadorPlantaADaoImpl;
import com.dmjm.impl.FolioPreparacionCocedoresDaoImpl;
import com.dmjm.impl.FolioPreparacionEsterilizadorPlantaADaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaEsterilizadorPlantaADaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoDaoImpl;
import com.dmjm.impl.OrdenMantoEsterilizadorPlantaAImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.EsterilizadorPlantaA;
import com.dmjm.model.FolioPreparacionCocedores;
import com.dmjm.model.FolioPreparacionEstA;
import com.dmjm.model.LimpiezaEstA;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimiento;
import com.dmjm.model.OrdenMantenimientoEstA;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("esterilizadorBeanA")
@ViewScoped
public class EsterilizadorPABean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EsterilizadorPlantaA> listaEsterilizador;
	private EsterilizadorPlantaA esterilizadorPlantaA;
	private EsterilizadorPlantaA esterilizadorEditarPlantaA;

	private Date fecha;
	private int folioFecha;
	private int folioPrepEst;
	private Date fechaFiltro;

	private LimpiezaEstA limpieza;
	private List<LimpiezaEstA> limpiezaEstA;
	private LimpiezaEstA limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;
	
	private List<OrdenMantenimientoEstA> listaOrdenManto;
	private OrdenMantenimientoEstA ordenMantenimiento;
	private OrdenMantenimientoEstA ordenMantenimientoEditar;

	@PostConstruct
	public void init() {
		listaEsterilizador = new ArrayList<>();
		esterilizadorPlantaA = new EsterilizadorPlantaA();
		esterilizadorEditarPlantaA = new EsterilizadorPlantaA();

		limpieza = new LimpiezaEstA();
		limpiezaEstA = new ArrayList<>();
		limpiezaEditar = new LimpiezaEstA();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();
		
		
		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoEstA();
		ordenMantenimientoEditar = new OrdenMantenimientoEstA();


		primera();
		getListarRegistroTurnos();
		getLimpiezaEstA();
		getListaOrdenManto();
	}

	public EsterilizadorPABean() {

	}

	public EsterilizadorPlantaA getEsterilizadorPlantaA() {
		return esterilizadorPlantaA;
	}

	public void setEsterilizadorPlantaA(EsterilizadorPlantaA esterilizadorPlantaA) {
		this.esterilizadorPlantaA = esterilizadorPlantaA;
	}

	public EsterilizadorPlantaA getEsterilizadorEditarPlantaA() {
		return esterilizadorEditarPlantaA;
	}

	public void setEsterilizadorEditarPlantaA(EsterilizadorPlantaA esterilizadorEditarPlantaA) {
		this.esterilizadorEditarPlantaA = esterilizadorEditarPlantaA;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public LimpiezaEstA getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaEstA limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public List<EsterilizadorPlantaA> getListaEsterilizador() {
		IEsterilizdorPlantaADao eDao = new EsterilizadorPlantaADaoImpl();
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
	
	

	public OrdenMantenimientoEstA getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoEstA ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoEstA getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoEstA ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	
	public List<OrdenMantenimientoEstA> getListaOrdenManto() {
		IOrdenMantoEsterilizadorPalantaADao oDao = new OrdenMantoEsterilizadorPlantaAImpl();
		IFolioPreparacionEsterilizadorPlantaADao folioPrepDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstAActual(fecha);
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

		IEsterilizdorPlantaADao cDao = new EsterilizadorPlantaADaoImpl();

		esterilizadorPlantaA = new EsterilizadorPlantaA();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();
		folio = folDao.buscarFolio(year);

		// **FOLIO_PREPARACION_EST_A**//

		IFolioPreparacionEsterilizadorPlantaADao estDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
		FolioPreparacionEstA fpe = new FolioPreparacionEstA();
		fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			esterilizadorPlantaA.setFolioEsterilizador(folio);
			esterilizadorPlantaA.setHora(lista);
			esterilizadorPlantaA.setFolioPreparacionEstA(fpe);
			esterilizadorPlantaA.setFecha(new Date());
			cDao.guardarEsterilizador(esterilizadorPlantaA);
			esterilizadorPlantaA = new EsterilizadorPlantaA();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolio(year, folio);

		String script = "setTimeout(function() { window.location.href='EsterilizadorPlantaA.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarEsterilizador() {
		IEsterilizdorPlantaADao cDao = new EsterilizadorPlantaADaoImpl();

		String oper = esterilizadorEditarPlantaA.getOperacion().replaceAll("\\s+", "");
		esterilizadorEditarPlantaA.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		cDao.actualizarEsterilizador(esterilizadorEditarPlantaA);
		actualizarPromedios(esterilizadorEditarPlantaA.getFolioPreparacionEstA().getIdFolioPrep());

		if (esterilizadorEditarPlantaA.getHora().equals("7:00")) {
			IEsterilizdorPlantaADao aDao = new EsterilizadorPlantaADaoImpl();
			aDao.actualizarEsterilizadorPromedio(esterilizadorEditarPlantaA.getOperacion(),
					esterilizadorEditarPlantaA.getFolioPreparacionEstA().getIdFolioPrep());
		}
		esterilizadorEditarPlantaA = new EsterilizadorPlantaA();

	}

	public List<EsterilizadorPlantaA> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaEsterilizador.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaEsterilizador.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<EsterilizadorPlantaA> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionEsterilizadorPlantaADao fDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionEsterilizadorPlantaADao folioPrepDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
				this.folioPrepEst = folioPrepDao.folioEstAActual(fecha);
			}

		}
	}

	public void primera() {
		IFolioPreparacionEsterilizadorPlantaADao fDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
		FolioPreparacionEstA f = new FolioPreparacionEstA();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionEsterilizadorPlantaADao folioDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionEsterilizadorPlantaADao folioPrepDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
			this.folioPrepEst = folioPrepDao.folioEstAActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaEstA();
			getListaOrdenManto();
		}

	}

	public void actualizarPromedios(int folio) {
		IEsterilizdorPlantaADao porcentaje = new EsterilizadorPlantaADaoImpl();
		porcentaje.actualizarPromedioGrenetina(folio);

		IEsterilizdorPlantaADao pv = new EsterilizadorPlantaADaoImpl();
		pv.actualizarPromedioPresionVapor(folio);

		IEsterilizdorPlantaADao pva = new EsterilizadorPlantaADaoImpl();
		pva.actualizarPromedioPresionVacio(folio);

		IEsterilizdorPlantaADao pre = new EsterilizadorPlantaADaoImpl();
		pre.actualizarPromedioPrecalentador(folio);

		IEsterilizdorPlantaADao est = new EsterilizadorPlantaADaoImpl();
		est.actualizarPromedioEsterilizador(folio);

		IEsterilizdorPlantaADao valdiv = new EsterilizadorPlantaADaoImpl();
		valdiv.actualizarPromedioValDiv(folio);

		IEsterilizdorPlantaADao ba = new EsterilizadorPlantaADaoImpl();
		ba.actualizarPromedioBombaAlim(folio);

		IEsterilizdorPlantaADao test = new EsterilizadorPlantaADaoImpl();
		test.actualizarPromedioTimpoEst(folio);

		IEsterilizdorPlantaADao bs = new EsterilizadorPlantaADaoImpl();
		bs.actualizarPromedioBombaSalida(folio);

		IEsterilizdorPlantaADao flujo = new EsterilizadorPlantaADaoImpl();
		flujo.actualizarPromedioFlujoLitros(folio);

		IEsterilizdorPlantaADao presion = new EsterilizadorPlantaADaoImpl();
		presion.actualizarPromedioPresioSistema(folio);

		IEsterilizdorPlantaADao rede = new EsterilizadorPlantaADaoImpl();
		rede.actualizarPromedioRedoxEntrada(folio);

		IEsterilizdorPlantaADao reds = new EsterilizadorPlantaADaoImpl();
		reds.actualizarPromedioRedoxSalida(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ALCALINO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionEstA f = new FolioPreparacionEstA();
		f.setIdFolioPrep(folioPrepEst);

		ILimpiezaEsterilizadorPlantaADao lDao = new LimpiezaEsterilizadorPlantaADaoImpl();
		for (String l : datosLimpieza) {
			limpieza.setFolioPreparacionEstA(f);
			limpieza.setProceso(l);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaEstA();
		}

	}

	public List<LimpiezaEstA> getLimpiezaEstA() {
		ILimpiezaEsterilizadorPlantaADao lDao = new LimpiezaEsterilizadorPlantaADaoImpl();

		IFolioPreparacionEsterilizadorPlantaADao folioPrepDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstAActual(fecha);
		limpiezaEstA = lDao.listarLimpieza(folioPrepEst);
		return limpiezaEstA;
	}

	public void actualizarLimpieza() {
		ILimpiezaEsterilizadorPlantaADao lDao = new LimpiezaEsterilizadorPlantaADaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaEstA();
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
		rt.setDescProceso("ESTERILIZADOR PLANTA A");

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
		listarRegistroTurnos = rDao.listaRegistroTurnosEstA(fecha);
		IFolioPreparacionEsterilizadorPlantaADao folioPrepDao = new FolioPreparacionEsterilizadorPlantaADaoImpl();
		this.folioPrepEst = folioPrepDao.folioEstAActual(fecha);
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

		IOrdenMantoEsterilizadorPalantaADao iDao = new OrdenMantoEsterilizadorPlantaAImpl();
		FolioPreparacionEstA f = new FolioPreparacionEstA();
		f.setIdFolioPrep(folioPrepEst);
		ordenMantenimiento.setFolioPreparacionEstA(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoEstA();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoEsterilizadorPalantaADao iDao = new OrdenMantoEsterilizadorPlantaAImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoEstA();
	}
	
	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaEsterilizador(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaEstA();
		getListaOrdenManto();
	}


}
