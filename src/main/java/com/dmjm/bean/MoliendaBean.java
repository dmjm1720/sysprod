package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import com.dmjm.dao.IRemoliendaDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioGeneralDaoImpl;
import com.dmjm.impl.FolioPreparacionMoliendaDaoImpl;
import com.dmjm.impl.MoliendaDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.RemoliendaDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionMolienda;
import com.dmjm.model.Molienda;
import com.dmjm.model.Operador;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Remolienda;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("moliendaBean")
@ViewScoped
public class MoliendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(MoliendaBean.class.getName());

	private List<Remolienda> listaRemolienda;
	private List<Molienda> listaMolienda;
	private Molienda molienda;
	private Molienda moliendaEditar;
	private Remolienda remolienda;
	private Remolienda remoliendaEditar;
	private Date fecha;
	private String fechaHoja;
	private int folioFecha;
	private int folioPreMolienda;
	private Date fechaFiltro;
	private int folioPrepMolienda;
	private String bandera;

	private List<FolioPreparacionMolienda> listaFolioMolienda;
	private FolioPreparacionMolienda folioPreparacionMolienda;

	private int folioMinimo;
	private int folioMaximo;
	private int folioSeleccionado;

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
		listaRemolienda = new ArrayList<>();
		molienda = new Molienda();
		moliendaEditar = new Molienda();
		remolienda = new Remolienda();
		remoliendaEditar = new Remolienda();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaFolioMolienda = new ArrayList<>();
		folioPreparacionMolienda = new FolioPreparacionMolienda();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();
		folioMin();
		folioMax();
		folioSeleccionado = folioMaximo;

		primera();
		listaInicialFechaActual();
		listaRemoliendaInicialFechaActual();

	}

	public Remolienda getRemolienda() {
		return remolienda;
	}

	public void setRemolienda(Remolienda remolienda) {
		this.remolienda = remolienda;
	}

	public Remolienda getRemoliendaEditar() {
		return remoliendaEditar;
	}

	public void setRemoliendaEditar(Remolienda remoliendaEditar) {
		this.remoliendaEditar = remoliendaEditar;
	}

	public List<Molienda> getListaMolienda() {

		return listaMolienda;
	}

	public List<Remolienda> getListaRemolienda() {
		return listaRemolienda;
	}

	public int getFolioMinimo() {
		return folioMinimo;
	}

	public void setFolioMinimo(int folioMinimo) {
		this.folioMinimo = folioMinimo;
	}

	public int getFolioMaximo() {
		return folioMaximo;
	}

	public void setFolioMaximo(int folioMaximo) {
		this.folioMaximo = folioMaximo;
	}

	public int getFolioSeleccionado() {
		return folioSeleccionado;
	}

	public void setFolioSeleccionado(int folioSeleccionado) {
		this.folioSeleccionado = folioSeleccionado;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public List<FolioPreparacionMolienda> getListaFolioMolienda() {
		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		listaFolioMolienda = lDao.listaFolioMolienda(folioPrepMolienda);
		return listaFolioMolienda;
	}

	public int folioMin() {
		this.folioMinimo = 0;
		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = lDao.folioMoliendaMinimo();
		Optional<Integer> min = Optional.ofNullable(f.getFolioMolienda());

		folioMinimo = min.orElse(0);

		return folioMinimo;
	}

	public int folioMax() {
		this.folioMaximo = 0;
		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = lDao.folioMoliendaMaximo();

		Optional<Integer> max = Optional.ofNullable(f.getFolioMolienda());

		folioMaximo = max.orElse(0);

		return folioMaximo;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
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

	public void initEditar() {
		moliendaEditar = new Molienda();
		moliendaEditar.setKg200(20);
		moliendaEditar.setKg100(25);
		moliendaEditar.setKg60(25);
		moliendaEditar.setKg30(25);
		moliendaEditar.setKg8(25);

	}

	public void initEditarRem() {
		remoliendaEditar = new Remolienda();
		remoliendaEditar.setKg200(20);
		remoliendaEditar.setKg100(25);
		remoliendaEditar.setKg60(25);
		remoliendaEditar.setKg30(25);
		remoliendaEditar.setKg8(25);

	}

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosMolienda(fecha);
		IFolioPreparacionMoliendaDao folioPrepDao = new FolioPreparacionMoliendaDaoImpl();
		this.folioPreMolienda = folioPrepDao.folioMoliendaActual(fecha);
		return listarRegistroTurnos;
	}

	// CALCULOS//

	public void calcularInfo200GH() {
		moliendaEditar.setGhKgTotales(moliendaEditar.getGhSacos() * moliendaEditar.getKg200());
		calcularTotalesGH();
	}

	public void calcularInfo200() {
		moliendaEditar.setM200KgTotales(moliendaEditar.getM200Sacos() * moliendaEditar.getKg200());
		calcularTotales();
	}

	public void calcularInfo100() {
		moliendaEditar.setM100KgTotales(moliendaEditar.getM100Sacos() * moliendaEditar.getKg100());
		calcularTotales();
	}

	public void calcularInfo60() {
		moliendaEditar.setM60KgTotales(moliendaEditar.getM60Sacos() * moliendaEditar.getKg60());
		calcularTotales();
	}

	public void calcularInfo30() {
		moliendaEditar.setM30KgTotales(moliendaEditar.getM30Sacos() * moliendaEditar.getKg30());
		calcularTotales();
	}

	public void calcularInfo8() {
		moliendaEditar.setM8KgTotales(moliendaEditar.getM8Sacos() * moliendaEditar.getKg8());
		calcularTotales();
	}

	public void calcularInfoR200GH() {
		int sacos = Objects.requireNonNullElse(moliendaEditar.getGhSacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getGhKgRestos(), 0);

		moliendaEditar.setGhKgTotales((sacos * moliendaEditar.getKg200()) + kgRestos);

		calcularTotalesGH();
	}

	public void calcularInfoR200() {
		int sacos = Objects.requireNonNullElse(moliendaEditar.getM200Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getM200KgRestos(), 0);

		moliendaEditar.setM200KgTotales((sacos * moliendaEditar.getKg200()) + kgRestos);

		calcularTotales();
	}

	public void calcularInfoR100() {

		int sacos = Objects.requireNonNullElse(moliendaEditar.getM100Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getM100KgRestos(), 0);

		moliendaEditar.setM100KgTotales((sacos * moliendaEditar.getKg100()) + kgRestos);

		calcularTotales();
	}

	public void calcularInfoR60() {

		int sacos = Objects.requireNonNullElse(moliendaEditar.getM60Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getM60KgRestos(), 0);

		moliendaEditar.setM60KgTotales((sacos * moliendaEditar.getKg60()) + kgRestos);

		calcularTotales();
	}

	public void calcularInfoR30() {

		int sacos = Objects.requireNonNullElse(moliendaEditar.getM30Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getM30KgRestos(), 0);

		moliendaEditar.setM30KgTotales((sacos * moliendaEditar.getKg30()) + kgRestos);

		calcularTotales();
	}

	public void calcularInfoR8() {

		int sacos = Objects.requireNonNullElse(moliendaEditar.getM8Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(moliendaEditar.getM8KgRestos(), 0);

		moliendaEditar.setM8KgTotales((sacos * moliendaEditar.getKg8()) + kgRestos);

		calcularTotales();
	}

	public void calcularTotales() {
		Optional<Integer> kg200 = Optional.ofNullable(moliendaEditar.getM200KgTotales());
		Optional<Integer> kg100 = Optional.ofNullable(moliendaEditar.getM100KgTotales());
		Optional<Integer> kg60 = Optional.ofNullable(moliendaEditar.getM60KgTotales());
		Optional<Integer> kg30 = Optional.ofNullable(moliendaEditar.getM30KgTotales());
		Optional<Integer> kg8 = Optional.ofNullable(moliendaEditar.getM8KgTotales());
		moliendaEditar.setTotal(kg200.orElse(0) + kg100.orElse(0) + kg60.orElse(0) + kg30.orElse(0) + kg8.orElse(0));

	}

	public void calcularTotalesGH() {
		Optional<Integer> kg200 = Optional.ofNullable(moliendaEditar.getGhKgTotales());
		moliendaEditar.setTotal(kg200.orElse(0));

	}

	// REMOLIENDA//

	public void calcularInfo200GHREM() {
		remoliendaEditar.setGhKgTotales(remoliendaEditar.getGhSacos() * remoliendaEditar.getKg200());
		calcularTotalesRR();
	}

	public void calcularInfo200REM() {
		remoliendaEditar.setM200KgTotales(remoliendaEditar.getM200Sacos() * remoliendaEditar.getKg200());
		calcularTotalesRR();
	}

	public void calcularInfo100REM() {
		remoliendaEditar.setM100KgTotales(remoliendaEditar.getM100Sacos() * remoliendaEditar.getKg100());
		calcularTotalesRR();
	}

	public void calcularInfo60REM() {
		remoliendaEditar.setM60KgTotales(remoliendaEditar.getM60Sacos() * remoliendaEditar.getKg60());
		calcularTotalesRR();
	}

	public void calcularInfo30REM() {
		remoliendaEditar.setM30KgTotales(remoliendaEditar.getM30Sacos() * remoliendaEditar.getKg30());
		calcularTotalesRR();
	}

	public void calcularInfo8REM() {
		remoliendaEditar.setM8KgTotales(remoliendaEditar.getM8Sacos() * remoliendaEditar.getKg8());
		calcularTotalesRR();
	}

	public void calcularInfoR200GHREM() {
		int sacos = Objects.requireNonNullElse(remoliendaEditar.getGhSacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getGhKgRestos(), 0);

		remoliendaEditar.setGhKgTotales((sacos * remoliendaEditar.getKg200()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularInfoR200REM() {
		int sacos = Objects.requireNonNullElse(remoliendaEditar.getM200Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getM200KgRestos(), 0);

		remoliendaEditar.setM200KgTotales((sacos * remoliendaEditar.getKg200()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularInfoR100REM() {

		int sacos = Objects.requireNonNullElse(remoliendaEditar.getM100Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getM100KgRestos(), 0);

		remoliendaEditar.setM100KgTotales((sacos * remoliendaEditar.getKg100()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularInfoR60REM() {

		int sacos = Objects.requireNonNullElse(remoliendaEditar.getM60Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getM60KgRestos(), 0);

		remoliendaEditar.setM60KgTotales((sacos * remoliendaEditar.getKg60()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularInfoR30REM() {

		int sacos = Objects.requireNonNullElse(remoliendaEditar.getM30Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getM30KgRestos(), 0);

		remoliendaEditar.setM30KgTotales((sacos * remoliendaEditar.getKg30()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularInfoR8REM() {

		int sacos = Objects.requireNonNullElse(remoliendaEditar.getM8Sacos(), 0);
		int kgRestos = Objects.requireNonNullElse(remoliendaEditar.getM8KgRestos(), 0);

		remoliendaEditar.setM8KgTotales((sacos * remoliendaEditar.getKg8()) + kgRestos);

		calcularTotalesRR();
	}

	public void calcularTotalesRR() {
		Optional<Integer> kg200 = Optional.ofNullable(remoliendaEditar.getM200KgTotales());
		Optional<Integer> kg100 = Optional.ofNullable(remoliendaEditar.getM100KgTotales());
		Optional<Integer> kg60 = Optional.ofNullable(remoliendaEditar.getM60KgTotales());
		Optional<Integer> kg30 = Optional.ofNullable(remoliendaEditar.getM30KgTotales());
		Optional<Integer> kg8 = Optional.ofNullable(remoliendaEditar.getM8KgTotales());
		remoliendaEditar.setTotal(kg200.orElse(0) + kg100.orElse(0) + kg60.orElse(0) + kg30.orElse(0) + kg8.orElse(0));

	}

	public void calcularTotalesGHRR() {
		Optional<Integer> kg200 = Optional.ofNullable(remoliendaEditar.getGhKgTotales());
		remoliendaEditar.setTotal(kg200.orElse(0));

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

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_MOLIENDA");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				IMoliendaDao cDao = new MoliendaDaoImpl();

				molienda = new Molienda();

				// **FOLIO_MOLIENDA**//
				int folio = 0;
				IFolioGeneralDao folDao = new FolioGeneralDaoImpl();
				folio = folDao.buscarFolio("MOLIENDA");

				// **FOLIO_PREPARACION_MOLIENDA**//
				IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
				FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
				fpc.setIdFolioPrep(fDao.returnIDGuardarFolio(folio, fec));

//				molienda.setFolio(folio);
//				molienda.setFolioPreparacionMolienda(fpc);
//				molienda.setFecha(fec);
//				cDao.guardarMolienda(molienda);
//				molienda = new Molienda();

				// **ACTUALIZAR FOLIO_MOLIENDA**//

				IFolioGeneralDao folioDao = new FolioGeneralDaoImpl();
				folioDao.actualizarFolio("MOLIENDA", folio);
			}
		}
		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);
	}

	public void guardarInfoMolienda() {
		IMoliendaDao cDao = new MoliendaDaoImpl();
		IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda fpm = new FolioPreparacionMolienda();
		
		fpm.setIdFolioPrep(fDao.folioMoliendaActual(fecha));
		moliendaEditar.setFolio(folioFecha);
		moliendaEditar.setFolioPreparacionMolienda(fpm);
		moliendaEditar.setFecha(fecha);
		// moliendaEditar.setFechaFolioPrep(fecha); //revisar

		cDao.guardarMolienda(moliendaEditar);
		moliendaEditar = new Molienda();
		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarInfoMolienda() {
		IMoliendaDao cDao = new MoliendaDaoImpl();

//		FolioPreparacionMolienda fpm = new FolioPreparacionMolienda();
//		fpm.setIdFolioPrep(2);
		//moliendaEditar.setFolio(folioFecha);
		//moliendaEditar.setFolioPreparacionMolienda(fpm);
		//moliendaEditar.setFecha(fecha);
		// moliendaEditar.setFechaFolioPrep(fecha); //revisar

		cDao.actualizarMolienda(moliendaEditar);
		moliendaEditar = new Molienda();
		bandera = "";
		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}
	
	public void borrarMolienda() {
		IMoliendaDao bDao = new MoliendaDaoImpl();
		bDao.borrarMolienda(moliendaEditar);
	}
	
	public void borrarRemolienda() {
		IRemoliendaDao bDao = new RemoliendaDaoImpl();
		bDao.borrarRemolienda(remoliendaEditar);
	}


	public void guardarInfoRemolienda() {
		IRemoliendaDao cDao = new RemoliendaDaoImpl();
		IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda fpm = new FolioPreparacionMolienda();
		
		fpm.setIdFolioPrep(fDao.folioMoliendaActual(fecha));
		remoliendaEditar.setFolio(folioFecha);
		remoliendaEditar.setFolioPreparacionMolienda(fpm);
		remoliendaEditar.setFecha(fecha);
		// moliendaEditar.setFechaFolioPrep(fecha); //revisar

		cDao.guardarRemolienda(remoliendaEditar);
		remoliendaEditar = new Remolienda();
		bandera = "";
		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarInfoRemolienda() {
		IRemoliendaDao cDao = new RemoliendaDaoImpl();

//		FolioPreparacionMolienda fpm = new FolioPreparacionMolienda();
//		fpm.setIdFolioPrep(2);
//		remoliendaEditar.setFolio(folioFecha);
//		remoliendaEditar.setFolioPreparacionMolienda(fpm);
//		remoliendaEditar.setFecha(fecha);
		// moliendaEditar.setFechaFolioPrep(fecha); //revisar

		cDao.actualizarRemolienda(remoliendaEditar);
		remoliendaEditar = new Remolienda();
		bandera = "";
		String script = "setTimeout(function() { window.location.href='Molienda.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void banderaEditar(String acccion) {
		bandera = acccion;
	}

	public void primera() {
		IFolioPreparacionMoliendaDao fDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = fDao.retornarFechaActual();

		Optional<Date> fHoy = Optional.ofNullable(f.getFecha());

		this.fecha = fHoy.orElse(new Date());

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

		fechaHoja = formato.format(fecha);

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionMoliendaDao folioDao = new FolioPreparacionMoliendaDaoImpl();
			Optional<Integer> folFec = Optional.ofNullable(folioDao.fechaFolioActual(fecha));
			this.folioFecha = folFec.orElse(0);
			IFolioPreparacionMoliendaDao folioPrepDao = new FolioPreparacionMoliendaDaoImpl();
			Optional<Integer> folPrep = Optional.ofNullable(folioPrepDao.folioMoliendaActual(fecha));
			this.folioPrepMolienda = folPrep.orElse(0);
			getListarRegistroTurnos();
//			getLimpiezaVotatorB();
//			getListaOrdenManto();
			getListaFolioMolienda();
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

	public void listaInicialFechaActual() {
		IMoliendaDao eDao = new MoliendaDaoImpl();
		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = lDao.folioMoliendaMaximo();
		listaMolienda = new ArrayList<>();
		listaMolienda = eDao.listaPorFechaMolienda(f.getFecha());
		
		Optional<Integer> fFol = Optional.ofNullable(f.getFolioMolienda());
		folioFecha = fFol.orElse(0);

		Optional<Date> fFec = Optional.ofNullable(f.getFecha());
		fecha = fFec.orElse(new Date());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		fechaHoja = formato.format(fecha);
	}

	public void listaRemoliendaInicialFechaActual() {
		IRemoliendaDao eDao = new RemoliendaDaoImpl();
		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();
		f = lDao.folioMoliendaMaximo();
		listaRemolienda = new ArrayList<>();
		listaRemolienda = eDao.listaPorFechaRemolienda(f.getFecha());

		Optional<Integer> fFol = Optional.ofNullable(f.getFolioMolienda());
		folioFecha = fFol.orElse(0);

		Optional<Date> fFec = Optional.ofNullable(f.getFecha());
		fecha = fFec.orElse(new Date());

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		fechaHoja = formato.format(fecha);
	}

	public void listar() {
		System.out.println("El folio seleccionado es: " + folioSeleccionado);

		IMoliendaDao eDao = new MoliendaDaoImpl();
		IRemoliendaDao rDao = new RemoliendaDaoImpl();

		IFolioPreparacionMoliendaDao lDao = new FolioPreparacionMoliendaDaoImpl();
		FolioPreparacionMolienda f = new FolioPreparacionMolienda();

		f = lDao.folioMoliendaFiltro(folioSeleccionado);
		listaMolienda = new ArrayList<>();
		listaMolienda = eDao.listaPorFechaMolienda(f.getFecha());

		listaRemolienda = new ArrayList<>();
		listaRemolienda = rDao.listaPorFechaRemolienda(f.getFecha());

		folioFecha = f.getFolioMolienda();
		fecha = f.getFecha();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		fechaHoja = formato.format(fecha);
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(30, "FOLIO_PREPARACION_MOLIENDA");
	}

}
