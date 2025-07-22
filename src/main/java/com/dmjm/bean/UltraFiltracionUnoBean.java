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

import com.dmjm.dao.ICambioPrefiltroDao;
import com.dmjm.dao.IFolioPreparacionUltraUnoDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaUltraUnoDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoUltraUnoDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUltraFiltracionUnoDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.CambioPrefiltroDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.FolioUltraUnoDaoImpl;
import com.dmjm.impl.LimpiezaUltraUnoDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoUltraUnoDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UltraFiltracionUnoDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.CambioPrefiltro;
import com.dmjm.model.FolioPreparacionUltraUno;
import com.dmjm.model.LimpiezaUltraUno;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoUltraUno;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.UltrafiltracionUno;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("ultraUnoBean")
@ViewScoped
public class UltraFiltracionUnoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UltrafiltracionUno> listaUltrafiltracion;
	private UltrafiltracionUno ultraFiltracionUno;
	private UltrafiltracionUno ultraFiltracionUnoEditar;

	private List<UltrafiltracionUno> listaFiltroUltraUno;

	private Date fecha;
	private int folioFecha;
	private int folioPrepUltra;
	private Date fechaFiltro;

	private CambioPrefiltro cambio;
	private List<CambioPrefiltro> listaCambio;
	private CambioPrefiltro cambioEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<LimpiezaUltraUno> listaLimpieza;
	private LimpiezaUltraUno limpieza;
	private LimpiezaUltraUno limpiezaEditar;

	private List<OrdenMantenimientoUltraUno> listaOrdenManto;
	private OrdenMantenimientoUltraUno ordenMantenimiento;
	private OrdenMantenimientoUltraUno ordenMantenimientoEditar;

	private List<Integer> listaLimpiezas;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionUltraUno> listaFolioUltraUno;
	private FolioPreparacionUltraUno folioPrepUltraUno;
	private String cocedorSeleccionado;
	private List<String> procesos;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");

	public UltraFiltracionUnoBean() {

	}

	@PostConstruct
	public void init() {
		listaUltrafiltracion = new ArrayList<>();
		ultraFiltracionUno = new UltrafiltracionUno();
		ultraFiltracionUnoEditar = new UltrafiltracionUno();

		cambio = new CambioPrefiltro();
		cambioEditar = new CambioPrefiltro();
		listaCambio = new ArrayList<>();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaLimpieza = new ArrayList<>();
		limpieza = new LimpiezaUltraUno();
		limpiezaEditar = new LimpiezaUltraUno();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoUltraUno();
		ordenMantenimientoEditar = new OrdenMantenimientoUltraUno();

		listaFiltroUltraUno = new ArrayList<>();
		IUltraFiltracionUnoDao lfDao = new UltraFiltracionUnoDaoImpl();
		listaFiltroUltraUno = lfDao.listaFiltroUltrafiltracion();

		listaLimpiezas = new ArrayList<>();
		folioPrepUltraUno = new FolioPreparacionUltraUno();

		primera();
		getListaUltrafiltracion(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getListaCambio();
		getListaLimpieza();
		getListaOrdenManto();
		getListaFolioUltraUno();

	}

	public List<UltrafiltracionUno> getListaFiltroUltraUno() {
		return listaFiltroUltraUno;
	}

	public List<String> getProcesos() {
		return procesos;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public FolioPreparacionUltraUno getFolioPrepUltraUno() {
		return folioPrepUltraUno;
	}

	public void setFolioPrepUltraUno(FolioPreparacionUltraUno folioPrepUltraUno) {
		this.folioPrepUltraUno = folioPrepUltraUno;
	}

	public List<FolioPreparacionUltraUno> getListaFolioUltraUno() {
		IFolioPreparacionUltraUnoDao lDao = new FolioUltraUnoDaoImpl();
		listaFolioUltraUno = lDao.listaDeFolioUltraUno(folioPrepUltra);
		return listaFolioUltraUno;
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

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaUltraUnoDao lDao = new LimpiezaUltraUnoDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepUltra);
		return listaLimpiezas;
	}

	public List<UltrafiltracionUno> getListaUltrafiltracion() {
		IUltraFiltracionUnoDao uDao = new UltraFiltracionUnoDaoImpl();
		if (fechaFiltro != null) {
			listaUltrafiltracion = uDao.listaPorFechaUltrafiltracion(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaUltrafiltracion.get(i).getFolioUltra();
				fecha = listaUltrafiltracion.get(i).getFecha();
			}
		} else {
			listaUltrafiltracion = uDao.listaUltrafiltracion();
		}

		return listaUltrafiltracion;
	}

	public void setListaUltrafiltracion(List<UltrafiltracionUno> listaUltrafiltracion) {
		this.listaUltrafiltracion = listaUltrafiltracion;
	}

	public UltrafiltracionUno getUltraFiltracionUno() {
		return ultraFiltracionUno;
	}

	public void setUltraFiltracionUno(UltrafiltracionUno ultraFiltracionUno) {
		this.ultraFiltracionUno = ultraFiltracionUno;
	}

	public void setUltraFiltracionUnoEditar(UltrafiltracionUno ultraFiltracionUnoEditar) {
		this.ultraFiltracionUnoEditar = ultraFiltracionUnoEditar;
	}

	public UltrafiltracionUno getUltraFiltracionUnoEditar() {
		return ultraFiltracionUnoEditar;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public CambioPrefiltro getCambio() {
		return cambio;
	}

	public void setCambio(CambioPrefiltro cambio) {
		this.cambio = cambio;
	}

	public CambioPrefiltro getCambioEditar() {

		return cambioEditar;
	}

	public void setCambioEditar(CambioPrefiltro cambioEditar) {
		this.cambioEditar = cambioEditar;
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

	public int getFolioPrepUltra() {
		return folioPrepUltra;
	}

	public void setFolioPrepUltra(int folioPrepUltra) {
		this.folioPrepUltra = folioPrepUltra;
	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorUltraUno();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Ultrafiltración Uno");
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

	public LimpiezaUltraUno getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaUltraUno limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaUltraUno getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("Agua");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaUltraUno limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public OrdenMantenimientoUltraUno getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoUltraUno ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoUltraUno getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoUltraUno ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoUltraUno> getListaOrdenManto() {

		IOrdenMantoUltraUnoDao oDao = new OrdenMantoUltraUnoDaoImpl();
		IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraUnoActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepUltra);
		return listaOrdenManto;
	}

	public List<LimpiezaUltraUno> getListaLimpieza() {

		ILimpiezaUltraUnoDao lDao = new LimpiezaUltraUnoDaoImpl();
		IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraUnoActual(fecha);
		listaLimpieza = lDao.listarLimpieza(folioPrepUltra);
		return listaLimpieza;

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "DESCONCETRACIÓN", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE",
				"ENJUAGUE" };

		FolioPreparacionUltraUno f = new FolioPreparacionUltraUno();
		f.setIdFolioPrep(folioPrepUltra);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO
		ILimpiezaUltraUnoDao validaDao = new LimpiezaUltraUnoDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepUltra);

		ILimpiezaUltraUnoDao lDao = new LimpiezaUltraUnoDaoImpl();
		IUltraFiltracionUnoDao vDao = new UltraFiltracionUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepUltra, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVoBo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionUltraUno(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoCocedor(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaUltraUno();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaUltraUnoDao lDao = new LimpiezaUltraUnoDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpieza = new LimpiezaUltraUno();
	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {

		// validación de mantenimiento si hubo o no hubo
		IUltraFiltracionUnoDao validaDao = new UltraFiltracionUnoDaoImpl();

		validaDao.actualizarManto(folioPrepUltra);

		IOrdenMantoUltraUnoDao iDao = new OrdenMantoUltraUnoDaoImpl();

		FolioPreparacionUltraUno f = new FolioPreparacionUltraUno();
		f.setIdFolioPrep(folioPrepUltra);
		ordenMantenimiento.setFolioPreparacionUltraUno(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoUltraUno();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoUltraUnoDao iDao = new OrdenMantoUltraUnoDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoUltraUno();
	}
	
	public void borrarOrdenManto() {
		IOrdenMantoUltraUnoDao iDao = new OrdenMantoUltraUnoDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
	}

	public void guardarUltraUno() {

		String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
				"5:00", "6:00", "PROM." };

		IUltraFiltracionUnoDao cDao = new UltraFiltracionUnoDaoImpl();

		ultraFiltracionUno = new UltrafiltracionUno();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();
		folio = folDao.buscarFolioUltraUno(year);

		// **FOLIO_PREPARACION ULTRA**//

		IFolioPreparacionUltraUnoDao ultraDao = new FolioUltraUnoDaoImpl();
		FolioPreparacionUltraUno fpe = new FolioPreparacionUltraUno();
		fpe.setIdFolioPrep(ultraDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			ultraFiltracionUno.setFolioUltra(folio);
			ultraFiltracionUno.setHora(lista);
			ultraFiltracionUno.setFolioPreparacionUltraUno(fpe);
			ultraFiltracionUno.setFecha(new Date());
			cDao.guardarUltrafiltracion(ultraFiltracionUno);
			ultraFiltracionUno = new UltrafiltracionUno();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolioUltraUno(year, folio);

		String script = "setTimeout(function() { window.location.href='UltraFiltracionUno.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarUltraUno() {
		IUltraFiltracionUnoDao cDao = new UltraFiltracionUnoDaoImpl();

		String oper = ultraFiltracionUnoEditar.getOperacion().replaceAll("\\s+", "");
		ultraFiltracionUnoEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		if (Boolean.TRUE.equals(ultraFiltracionUnoEditar.getEstadoAR())) {
			ultraFiltracionUnoEditar.setEstadoA("X");
			ultraFiltracionUnoEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(ultraFiltracionUnoEditar.getEstadoAR())) {
			ultraFiltracionUnoEditar.setEstadoR("X");
			ultraFiltracionUnoEditar.setEstadoA(null);
		}

		cDao.actualizarUltrafiltracion(ultraFiltracionUnoEditar);
		actualizarPromedios(ultraFiltracionUnoEditar.getFolioPreparacionUltraUno().getIdFolioPrep());

		if (ultraFiltracionUnoEditar.getHora().equals("7:00")) {

			IUltraFiltracionUnoDao aDao = new UltraFiltracionUnoDaoImpl();
			aDao.actualizarUltrafiltracionPromedio(ultraFiltracionUnoEditar.getOperacion(),
					ultraFiltracionUnoEditar.getFolioPreparacionUltraUno().getIdFolioPrep());
		}
		ultraFiltracionUnoEditar = new UltrafiltracionUno();

	}

	public List<UltrafiltracionUno> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaUltrafiltracion.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaUltrafiltracion.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<UltrafiltracionUno> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionUltraUnoDao fDao = new FolioUltraUnoDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
				this.folioPrepUltra = folioPrepDao.folioUltraUnoActual(fecha);
			}

		}
	}

	public void primera() {

		IFolioPreparacionUltraUnoDao fDao = new FolioUltraUnoDaoImpl();
		FolioPreparacionUltraUno f = new FolioPreparacionUltraUno();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionUltraUnoDao folioDao = new FolioUltraUnoDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
			this.folioPrepUltra = folioPrepDao.folioUltraUnoActual(fecha);
			getListaUltrafiltracion();
			getListarRegistroTurnos();
			getListaCambio();
			getListaLimpieza();
			getListaOrdenManto();
			getListaFolioUltraUno();

		}

	}

	public void actualizarPromedios(int folio) {

		IUltraFiltracionUnoDao flujoEnt = new UltraFiltracionUnoDaoImpl();
		flujoEnt.actualizarPromedioFlujoEnt(folio);

		IUltraFiltracionUnoDao temp01 = new UltraFiltracionUnoDaoImpl();
		temp01.actualizarPromedioTemp01(folio);

		IUltraFiltracionUnoDao concEntrada = new UltraFiltracionUnoDaoImpl();
		concEntrada.actualizarPromedioConcEnt(folio);

		IUltraFiltracionUnoDao temp02 = new UltraFiltracionUnoDaoImpl();
		temp02.actualizarPromedioTemp02(folio);

		IUltraFiltracionUnoDao presionPSI01 = new UltraFiltracionUnoDaoImpl();
		presionPSI01.actualizarPromedioPresionPSI01(folio);

		IUltraFiltracionUnoDao flujoPerm01 = new UltraFiltracionUnoDaoImpl();
		flujoPerm01.actualizarPromedioFlujoPerm01(folio);

		IUltraFiltracionUnoDao concSalida = new UltraFiltracionUnoDaoImpl();
		concSalida.actualizarPromedioConcSalida(folio);

		IUltraFiltracionUnoDao flujoSalida = new UltraFiltracionUnoDaoImpl();
		flujoSalida.actualizarPromedioFlujoSalida(folio);

		IUltraFiltracionUnoDao concPermeado = new UltraFiltracionUnoDaoImpl();
		concPermeado.actualizarPromedioConcPermeado(folio);
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
		rt.setDescProceso("ULTRAFILTRACIÓN UNO");

		rDao.guardaRegistroTurnos(rt);

		u = new Usuarios();
		o = new Operador();
		t = new Turnos();
		rt = new RegistroTurnos();
		filterUsuario = null;
		filterOperador = null;
		filterTurno = null;

	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
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
		listarRegistroTurnos = rDao.listaRegistroTurnosUltraUno(fecha);
		IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraUnoActual(fecha);
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
		return tDao.completeOperador(nombre, "Ultrafiltración Uno");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Ultrafiltración Uno");
	}

	// **CAMBIO PREFILTRO**//
	public void guardarCambioPreFiltro() {

		CambioPrefiltro cp = new CambioPrefiltro();
		cp.setIdPrefiltro(folioPrepUltra);

		ICambioPrefiltroDao cDao = new CambioPrefiltroDaoImpl();
		cDao.guardarCambioPre(cp);

	}

	public List<CambioPrefiltro> getListaCambio() {
		ICambioPrefiltroDao cDao = new CambioPrefiltroDaoImpl();

		IFolioPreparacionUltraUnoDao folioPrepDao = new FolioUltraUnoDaoImpl();
		this.folioPrepUltra = folioPrepDao.fechaFolioActual(fecha);
		listaCambio = cDao.listarCambioPrefiltro(folioPrepUltra);
		return listaCambio;
	}

	public void actualizarCambio() {
		ICambioPrefiltroDao cDao = new CambioPrefiltroDaoImpl();
		cDao.actualizarCambioPre(cambioEditar);
		cambioEditar = new CambioPrefiltro();
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaCambio(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getListaCambio();
		getListaLimpieza();
		getListaOrdenManto();

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

		ruta = servletContext.getRealPath("/REP/ultrafiltracion_uno.jasper");
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

		ruta = servletContext.getRealPath("/REP/ultrafiltracion_uno.jasper");

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
		String ruta = servletContext.getRealPath("/REP/ultrafiltracion_uno_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IUltraFiltracionUnoDao vDao = new UltraFiltracionUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepUltra, 0);
		ILimpiezaUltraUnoDao iDao = new LimpiezaUltraUnoDaoImpl();
		iDao.borrarLimpieza(folioPrepUltra, noLimpiezaSeleccionadaBorrar);

	}

	public void borrarVoBo() {
		ILimpiezaUltraUnoDao iDao = new LimpiezaUltraUnoDaoImpl();
		iDao.borrarVoBo(folioPrepUltra, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaUltraUnoDao iDao = new LimpiezaUltraUnoDaoImpl();
		iDao.agregarVoBo(folioPrepUltra, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void guardarObservaciones() {
		IFolioPreparacionUltraUnoDao fDao = new FolioUltraUnoDaoImpl();
		fDao.guardarObservacion(folioPrepUltra, folioPrepUltraUno.getObservaciones());
		folioPrepUltraUno = new FolioPreparacionUltraUno();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioUltraUno.size(); i++) {
			folioPrepUltraUno.setObservaciones(listaFolioUltraUno.get(i).getObservaciones());
		}
	}

}
