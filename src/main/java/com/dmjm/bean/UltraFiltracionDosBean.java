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
import com.dmjm.dao.IFolioPreparacionUltraDosDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaUltraDosDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoUltraDosDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUltraFiltracionDosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.CambioPrefiltroDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.FolioUltraDosDaoImpl;
import com.dmjm.impl.LimpiezaUltraDosDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoUltraDosDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UltraFiltracionDosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.CambioPrefiltro;
import com.dmjm.model.FolioPreparacionUltraDos;
import com.dmjm.model.LimpiezaUltraDos;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoUltraDos;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.UltrafiltracionDos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteEsterilizadores;

@Named("ultraDosBean")
@ViewScoped
public class UltraFiltracionDosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UltrafiltracionDos> listaUltrafiltracion;
	private UltrafiltracionDos ultraFiltracionDos;
	private UltrafiltracionDos ultraFiltracionDosEditar;

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
	
	private List<LimpiezaUltraDos> listaLimpieza;
	private LimpiezaUltraDos limpieza;
	private LimpiezaUltraDos limpiezaEditar;
	
	
	private List<OrdenMantenimientoUltraDos> listaOrdenManto;
	private OrdenMantenimientoUltraDos ordenMantenimiento;
	private OrdenMantenimientoUltraDos ordenMantenimientoEditar;
	

	@PostConstruct
	public void init() {
		listaUltrafiltracion = new ArrayList<>();
		ultraFiltracionDos = new UltrafiltracionDos();
		ultraFiltracionDosEditar = new UltrafiltracionDos();

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
		limpieza = new LimpiezaUltraDos();
		limpiezaEditar = new LimpiezaUltraDos();
		
		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoUltraDos();
		ordenMantenimientoEditar = new OrdenMantenimientoUltraDos();

		primera();
		getListaUltrafiltracion(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getListaCambio();
		getListaLimpieza();
		getListaOrdenManto();

	}

	public UltraFiltracionDosBean() {

	}

	public List<UltrafiltracionDos> getListaUltrafiltracion() {
		IUltraFiltracionDosDao uDao = new UltraFiltracionDosDaoImpl();
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

	public void setListaUltrafiltracion(List<UltrafiltracionDos> listaUltrafiltracion) {
		this.listaUltrafiltracion = listaUltrafiltracion;
	}

	public UltrafiltracionDos getUltraFiltracionDos() {
		return ultraFiltracionDos;
	}

	public void setUltraFiltracionDos(UltrafiltracionDos ultraFiltracionDos) {
		this.ultraFiltracionDos = ultraFiltracionDos;
	}

	
	public void setUltraFiltracionDosEditar(UltrafiltracionDos ultraFiltracionDosEditar) {
		this.ultraFiltracionDosEditar = ultraFiltracionDosEditar;
	}

	public UltrafiltracionDos getUltraFiltracionDosEditar() {
		return ultraFiltracionDosEditar;
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
		listaOperadores = oDao.listaOperadorUltraDos();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Ultrafiltración Dos");
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
	
	
	

	public LimpiezaUltraDos getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaUltraDos limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaUltraDos getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("Agua");
		}
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaUltraDos limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}
	
	

	public OrdenMantenimientoUltraDos getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoUltraDos ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoUltraDos getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoUltraDos ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoUltraDos> getListaOrdenManto() {
		IOrdenMantoUltraDosDao oDao = new OrdenMantoUltraDosDaoImpl();
		IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraDosActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepUltra);
		return listaOrdenManto;
	}

	public List<LimpiezaUltraDos> getListaLimpieza() {
		ILimpiezaUltraDosDao lDao = new LimpiezaUltraDosDaoImpl();
		IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraDosActual(fecha);
		listaLimpieza = lDao.listarLimpieza(folioPrepUltra);
		return listaLimpieza;
	}
	
	
	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "LIMPIEZA MECÁNICA", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE",
				"ENJUAGUE" };

		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		f.setIdFolioPrep(folioPrepUltra);

		ILimpiezaUltraDosDao lDao = new LimpiezaUltraDosDaoImpl();
		for (String l : datosLimpieza) {
			limpieza.setFolioPreparacionUltraDos(f);
			limpieza.setProceso(l);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaUltraDos();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaUltraDosDao lDao = new LimpiezaUltraDosDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpieza = new LimpiezaUltraDos();
	}
	
	
	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento si hubo o no hubo
		//ICocedoresDao validaDao = new CocedoresDaoImpl();

		//validaDao.actualizarManto(folioPrepCocedor);

		IOrdenMantoUltraDosDao iDao = new OrdenMantoUltraDosDaoImpl();

		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		f.setIdFolioPrep(folioPrepUltra);
		ordenMantenimiento.setFolioPreparacionUltraDos(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoUltraDos();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoUltraDosDao iDao = new OrdenMantoUltraDosDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoUltraDos();
	}



	public void guardarUltraDos() {

		String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
				"5:00", "6:00", "PROM." };

		IUltraFiltracionDosDao cDao = new UltraFiltracionDosDaoImpl();

		ultraFiltracionDos = new UltrafiltracionDos();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioProcesosDao folDao = new FolioProcesosDaoImpl();
		folio = folDao.buscarFolioUltraDos(year);

		// **FOLIO_PREPARACION ULTRA**//

		IFolioPreparacionUltraDosDao ultraDao = new FolioUltraDosDaoImpl();
		FolioPreparacionUltraDos fpe = new FolioPreparacionUltraDos();
		fpe.setIdFolioPrep(ultraDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			ultraFiltracionDos.setFolioUltra(folio);
			ultraFiltracionDos.setHora(lista);
			ultraFiltracionDos.setFolioPreparacionUltraDos(fpe);
			ultraFiltracionDos.setFecha(new Date());
			cDao.guardarUltrafiltracion(ultraFiltracionDos);
			ultraFiltracionDos = new UltrafiltracionDos();
		}
		// **ACTUALIZAR FOLIO_PROCESOS**//
		IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
		folioDao.actualizarFolioUltraDos(year, folio);

		String script = "setTimeout(function() { window.location.href='UltraFiltracionDos.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}
	
	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void actualizarUltraDos() {
		IUltraFiltracionDosDao cDao = new UltraFiltracionDosDaoImpl();

		String oper = ultraFiltracionDosEditar.getOperacion().replaceAll("\\s+", "");
		ultraFiltracionDosEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		if (Boolean.TRUE.equals(ultraFiltracionDosEditar.getEstadoAR())) {
			ultraFiltracionDosEditar.setEstadoA("X");
			ultraFiltracionDosEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(ultraFiltracionDosEditar.getEstadoAR())) {
			ultraFiltracionDosEditar.setEstadoR("X");
			ultraFiltracionDosEditar.setEstadoA(null);
		}
		
		cDao.actualizarUltrafiltracion(ultraFiltracionDosEditar);
		actualizarPromedios(ultraFiltracionDosEditar.getFolioPreparacionUltraDos().getIdFolioPrep());

		if (ultraFiltracionDosEditar.getHora().equals("7:00")) {

			IUltraFiltracionDosDao aDao = new UltraFiltracionDosDaoImpl();
			aDao.actualizarUltrafiltracionPromedio(ultraFiltracionDosEditar.getOperacion(),
					ultraFiltracionDosEditar.getFolioPreparacionUltraDos().getIdFolioPrep());
		}
		ultraFiltracionDosEditar = new UltrafiltracionDos();

	}

	public List<UltrafiltracionDos> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaUltrafiltracion.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaUltrafiltracion.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<UltrafiltracionDos> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionUltraDosDao fDao = new FolioUltraDosDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
				this.folioPrepUltra = folioPrepDao.folioUltraDosActual(fecha);
			}

		}
	}

	public void primera() {
		
		IFolioPreparacionUltraDosDao fDao = new FolioUltraDosDaoImpl();
		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionUltraDosDao folioDao = new FolioUltraDosDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
			this.folioPrepUltra = folioPrepDao.folioUltraDosActual(fecha);
			getListaUltrafiltracion();
			getListarRegistroTurnos();
			getListaCambio();
			getListaLimpieza();
			getListaOrdenManto();

		}

	}

	public void actualizarPromedios(int folio) {

		IUltraFiltracionDosDao presionEntrada = new UltraFiltracionDosDaoImpl();
		presionEntrada.actualizarPromedioPresionEntrada(folio);

		IUltraFiltracionDosDao presionSalida = new UltraFiltracionDosDaoImpl();
		presionSalida.actualizarPromedioPresionSalida(folio);

		IUltraFiltracionDosDao flujoEnt = new UltraFiltracionDosDaoImpl();
		flujoEnt.actualizarPromedioFlujoEnt(folio);

		IUltraFiltracionDosDao temp01 = new UltraFiltracionDosDaoImpl();
		temp01.actualizarPromedioTemp01(folio);

		IUltraFiltracionDosDao concEntrada = new UltraFiltracionDosDaoImpl();
		concEntrada.actualizarPromedioConcEnt(folio);

		IUltraFiltracionDosDao ph = new UltraFiltracionDosDaoImpl();
		ph.actualizarPromedioPH(folio);

		IUltraFiltracionDosDao presionBar = new UltraFiltracionDosDaoImpl();
		presionBar.actualizarPromedioPresionBar(folio);

		IUltraFiltracionDosDao porcentajeBomba = new UltraFiltracionDosDaoImpl();
		porcentajeBomba.actualizarPromedioPorcentajeBomba(folio);

		IUltraFiltracionDosDao temp02 = new UltraFiltracionDosDaoImpl();
		temp02.actualizarPromedioTemp02(folio);

		IUltraFiltracionDosDao presionPSI01 = new UltraFiltracionDosDaoImpl();
		presionPSI01.actualizarPromedioPresionPSI01(folio);

		IUltraFiltracionDosDao flujoPerm01 = new UltraFiltracionDosDaoImpl();
		flujoPerm01.actualizarPromedioFlujoPerm01(folio);

		IUltraFiltracionDosDao temp03 = new UltraFiltracionDosDaoImpl();
		temp03.actualizarPromedioTemp03(folio);

		IUltraFiltracionDosDao presionPSI02 = new UltraFiltracionDosDaoImpl();
		presionPSI02.actualizarPromedioPresionPSI02(folio);

		IUltraFiltracionDosDao flujoPerm02 = new UltraFiltracionDosDaoImpl();
		flujoPerm02.actualizarPromedioFlujoPerm02(folio);

		IUltraFiltracionDosDao temp04 = new UltraFiltracionDosDaoImpl();
		temp04.actualizarPromedioTemp04(folio);

		IUltraFiltracionDosDao presionPSI03 = new UltraFiltracionDosDaoImpl();
		presionPSI03.actualizarPromedioPresionPSI03(folio);

		IUltraFiltracionDosDao flujoPerm03 = new UltraFiltracionDosDaoImpl();
		flujoPerm03.actualizarPromedioFlujoPerm03(folio);

		IUltraFiltracionDosDao temp05 = new UltraFiltracionDosDaoImpl();
		temp05.actualizarPromedioTemp05(folio);

		IUltraFiltracionDosDao presionPSI04 = new UltraFiltracionDosDaoImpl();
		presionPSI04.actualizarPromedioPresionPSI04(folio);

		IUltraFiltracionDosDao flujoPerm04 = new UltraFiltracionDosDaoImpl();
		flujoPerm04.actualizarPromedioFlujoPerm04(folio);

		IUltraFiltracionDosDao concSalida = new UltraFiltracionDosDaoImpl();
		concSalida.actualizarPromedioConcSalida(folio);

		IUltraFiltracionDosDao ratio = new UltraFiltracionDosDaoImpl();
		ratio.actualizarPromedioRatio(folio);

		IUltraFiltracionDosDao flujoSalida = new UltraFiltracionDosDaoImpl();
		flujoSalida.actualizarPromedioFlujoSalida(folio);

		IUltraFiltracionDosDao concPermeado = new UltraFiltracionDosDaoImpl();
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
		rt.setDescProceso("ULTRAFILTRACIÓN DOS");

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
		listarRegistroTurnos = rDao.listaRegistroTurnosUltraDos(fecha);
		IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
		this.folioPrepUltra = folioPrepDao.folioUltraDosActual(fecha);
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
		return tDao.completeOperador(nombre, "Ultrafiltración Dos");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Ultrafiltración Dos");
	}

	// **CAMBIO PREFILTRO**//
	public void guardarCambioPreFiltro() {

		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		f.setIdFolioPrep(folioPrepUltra);

		cambioEditar.setFolioPreparacionUltraDos(f);
		ICambioPrefiltroDao cDao = new CambioPrefiltroDaoImpl();
		cDao.guardarCambioPre(cambioEditar);
		cambioEditar = new CambioPrefiltro();
	}

	
	public List<CambioPrefiltro> getListaCambio() {
		ICambioPrefiltroDao cDao = new CambioPrefiltroDaoImpl();
		
		IFolioPreparacionUltraDosDao folioPrepDao = new FolioUltraDosDaoImpl();
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
		getListaOrdenManto();
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

		ruta = servletContext.getRealPath("/REP/esterilizadores_rep_a.jasper");
		reporte.getReporte(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

}
