package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IFolioGeneralDao;
import com.dmjm.dao.IFolioPreparacionMoliendaDao;
import com.dmjm.dao.IMoliendaDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.FolioGeneralDaoImpl;
import com.dmjm.impl.FolioPreparacionMoliendaDaoImpl;
import com.dmjm.impl.MoliendaDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.FolioPreparacionMolienda;
import com.dmjm.model.Molienda;
import com.dmjm.model.Operador;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("moliendaBean")
@ViewScoped
public class MoliendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(MoliendaBean.class.getName());

	private List<Molienda> listaMolienda;
	private Molienda molienda;
	private Molienda moliendaEditar;
	private Date fecha;
	private String fechaHoja;
	private int folioFecha;
	private int folioPreMolienda;
	private Date fechaFiltro;
	private int folioPrepMolienda;

	// TURNOS//
	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;
	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	// OPERADORES //
	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	public MoliendaBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		listaMolienda = new ArrayList<>();
		molienda = new Molienda();
		moliendaEditar = new Molienda();
		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		primera();

	}

	public List<Molienda> getListaMolienda() {

		IMoliendaDao eDao = new MoliendaDaoImpl();
		if (fechaFiltro != null) {
			listaMolienda = eDao.listaPorFechaMolienda(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaMolienda.get(i).getFolio();
				fecha = listaMolienda.get(i).getFecha();
				SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
				fechaHoja = formato.format(fecha);
			}
		} else {

			listaMolienda = eDao.listaMolienda();

		}
		return listaMolienda;
	}

	public Molienda getMolienda() {
		return molienda;
	}

	public void setMolienda(Molienda molienda) {
		this.molienda = molienda;
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

	public int getFolioPreMolienda() {
		return folioPreMolienda;
	}

	public void setFolioPreMolienda(int folioPreMolienda) {
		this.folioPreMolienda = folioPreMolienda;
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

	public Molienda getMoliendaEditar() {
		return moliendaEditar;
	}

	public void setMoliendaEditar(Molienda moliendaEditar) {
		this.moliendaEditar = moliendaEditar;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public int getFolioPrepMolienda() {
		return folioPrepMolienda;
	}

	public void setFolioPrepMolienda(int folioPrepMolienda) {
		this.folioPrepMolienda = folioPrepMolienda;
	}

	public String getFechaHoja() {
		return fechaHoja;
	}

	public void setFechaHoja(String fechaHoja) {
		this.fechaHoja = fechaHoja;
	}

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosMolienda(fecha);
		IFolioPreparacionMoliendaDao folioPrepDao = new FolioPreparacionMoliendaDaoImpl();
		this.folioPreMolienda = folioPrepDao.folioMoliendaActual(fecha);
		return listarRegistroTurnos;
	}

	// TURNOS//
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
		return tDao.completeOperador(nombre, "Molienda");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Molienda");
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
		rt.setDescProceso("MOLIENDA");

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
	}

	// TURNOS//

	// OPERADORES //
	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorMolienda();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Molienda");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
	}
	// OPERADORES //

	// MOLIENDA //
	public void guardarMolienda() {

		IMoliendaDao cDao = new MoliendaDaoImpl();

		molienda = new Molienda();

		// **FOLIO_MOLIENDA**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioGeneralDao folDao = new FolioGeneralDaoImpl();
		folio = folDao.buscarFolio("MOLIENDA");

		// **FOLIO_PREPARACION_MOLIENDA**//
		IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		fpc.setIdFolioPrep(fDao.returnIDGuardarFolio(folio));

		molienda.setFolio(folio);
		molienda.setFolioPreparacionMolienda(fpc);
		molienda.setFecha(new Date());
		cDao.guardarMolienda(molienda);
		molienda = new Molienda();

		// **ACTUALIZAR FOLIO_MOLIENDA**//

		IFolioGeneralDao folioDao = new FolioGeneralDaoImpl();
		folioDao.actualizarFolio("MOLIENDA", folio);

		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void primera() {
		IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		fechaHoja = formato.format(fecha);

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionMoliendaDao folioDao = new FolioPreparacionMoliendaDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionMoliendaDao folioPrepDao = new FolioPreparacionMoliendaDaoImpl();
			this.folioPrepMolienda = folioPrepDao.folioMoliendaActual(fecha);
			getListarRegistroTurnos();
//			getLimpiezaVotatorB();
//			getListaOrdenManto();
//			getListaFolioVotatorPB();
//			getListaResumenVotator();
		}

	}

	public List<Molienda> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 1; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaMolienda.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaMolienda.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<Molienda> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionMoliendaDao folioPrepDao = new FolioPreparacionMoliendaDaoImpl();
				this.folioPrepMolienda = folioPrepDao.folioMoliendaActual(fecha);
			}

		}
	}

	// MOLIENDA //
}
