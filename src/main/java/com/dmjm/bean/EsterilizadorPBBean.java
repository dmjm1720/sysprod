package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IEsterilizdorPlantaBDao;
import com.dmjm.dao.IFolioPreparacionEsterilizadorPlantaBDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaEsterilizadorPlantaBDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaADao;
import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaBDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.EsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.FolioPreparacionEsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaEsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoEsterilizadorPlantaAImpl;
import com.dmjm.impl.OrdenMantoEsterilizadorPlantaBImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.EsterilizadorPlantaB;
import com.dmjm.model.FolioPreparacionEstB;
import com.dmjm.model.LimpiezaEstB;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoEstA;
import com.dmjm.model.OrdenMantenimientoEstB;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("esterilizadorBeanB")
@ViewScoped
public class EsterilizadorPBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EsterilizadorPlantaB> listaEsterilizador;
	private EsterilizadorPlantaB esterilizadorPlantaB;
	private EsterilizadorPlantaB esterilizadorEditarPlantaB;
	
	private List<EsterilizadorPlantaB> listaFiltroEsterilizadoresPB;

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

	private List<Integer> listaLimpiezas;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionEstB> listaFolioEstPB;
	private FolioPreparacionEstB folioPrepEstPB;
	private String cocedorSeleccionado;
	private List<String> procesos;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");

	public EsterilizadorPBBean() {

	}

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
		
		listaFiltroEsterilizadoresPB = new ArrayList<>();
		IEsterilizdorPlantaBDao lfDao = new EsterilizadorPlantaBDaoImpl();
		listaFiltroEsterilizadoresPB = lfDao.listaFiltroEsterilizador();

		listaLimpiezas = new ArrayList<>();
		folioPrepEstPB = new FolioPreparacionEstB();

		primera();
		getListarRegistroTurnos();
		getLimpiezaEstB();
		getListaOrdenManto();
		getListaFolioEstPB();
	}

	public List<String> getProcesos() {
		return procesos;
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

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepEst);
		return listaLimpiezas;
	}

	public List<EsterilizadorPlantaB> getListaFiltroEsterilizadoresPB() {
		return listaFiltroEsterilizadoresPB;
	}

	public FolioPreparacionEstB getFolioPrepEstPB() {
		return folioPrepEstPB;
	}

	public void setFolioPrepEstPB(FolioPreparacionEstB folioPrepEstPB) {
		this.folioPrepEstPB = folioPrepEstPB;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public List<FolioPreparacionEstB> getListaFolioEstPB() {
		IFolioPreparacionEsterilizadorPlantaBDao lDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		listaFolioEstPB = lDao.listaFolioEstB(folioPrepEst);
		return listaFolioEstPB;
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
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("Agua");
		}
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
		listaOperadores = oDao.listaOperadorEstPlantaB();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Est Planta B");
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
			getListaFolioEstPB();
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
		String datosLimpieza[] = { "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionEstB f = new FolioPreparacionEstB();
		f.setIdFolioPrep(folioPrepEst);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO
		ILimpiezaEsterilizadorPlantaBDao validaDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepEst);
		
		// validación de limpieza para agregar en la tabla de cocedores
		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		IEsterilizdorPlantaBDao vDao = new EsterilizadorPlantaBDaoImpl();
		vDao.actualizarLimpieza(folioPrepEst, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVoBo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionEstB(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoCocedor(cocedorSeleccionado);
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
		IEsterilizdorPlantaBDao validaDao = new EsterilizadorPlantaBDaoImpl();

		validaDao.actualizarManto(folioPrepEst);

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
	
	public void borrarOrdenManto() {
		IOrdenMantoEsterilizadorPalantaBDao iDao = new OrdenMantoEsterilizadorPlantaBImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoEstB();
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaEsterilizador(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaEstB();
		getListaOrdenManto();
		getListaFolioEstPB();
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

		ruta = servletContext.getRealPath("/REP/esterilizadores_rep_b.jasper");
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

		ruta = servletContext.getRealPath("/REP/esterilizadores_rep_b.jasper");

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
		String ruta = servletContext.getRealPath("/REP/esterilizadores_rep_b_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}
	
	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IEsterilizdorPlantaBDao vDao = new EsterilizadorPlantaBDaoImpl();
		vDao.actualizarLimpieza(folioPrepEst, 0);
		ILimpiezaEsterilizadorPlantaBDao iDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		iDao.borrarLimpieza(folioPrepEst, noLimpiezaSeleccionadaBorrar);

	}

	public void borrarVoBo() {
		ILimpiezaEsterilizadorPlantaBDao iDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		iDao.borrarVoBo(folioPrepEst, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaEsterilizadorPlantaBDao iDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		iDao.agregarVoBo(folioPrepEst, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void guardarObservaciones() {
		IFolioPreparacionEsterilizadorPlantaBDao fDao = new FolioPreparacionEsterilizadorPlantaBDaoImpl();
		fDao.guardarObservacion(folioPrepEst, folioPrepEstPB.getObservaciones());
		folioPrepEstPB = new FolioPreparacionEstB();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioEstPB.size(); i++) {
			folioPrepEstPB.setObservaciones(listaFolioEstPB.get(i).getObservaciones());
		}
	}

}
