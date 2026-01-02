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

import com.dmjm.dao.IFolioPreparacionLuwaUnoDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaLuwaUnoDao;
import com.dmjm.dao.ILuwaUnoDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoLuwaUnoDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.IResumenLuwaDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioPreparacionLuwaUnoDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaLuwaUnoDaoImpl;
import com.dmjm.impl.LuwaUnoDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoLuwaUnoDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.ResumenLuwaDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionLuwaUno;
import com.dmjm.model.LimpiezaLuwaUno;
import com.dmjm.model.LuwaUno;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoLuwaUno;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.ResumenLuwaUno;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteEsterilizadores;

@Named("luwaUnoBean")
@ViewScoped
public class LuwaUnoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LuwaUno> listaLuwa;
	private LuwaUno luwa;
	private LuwaUno luwaEditar;

	private List<LuwaUno> listaFiltroLuwa;

	private Date fecha;
	private int folioFecha;
	private int folioPrepLuwa;
	private Date fechaFiltro;

	private LimpiezaLuwaUno limpieza;
	private List<LimpiezaLuwaUno> limpiezaLuwaUno;
	private LimpiezaLuwaUno limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoLuwaUno> listaOrdenManto;
	private OrdenMantenimientoLuwaUno ordenMantenimiento;
	private OrdenMantenimientoLuwaUno ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionLuwaUno> listaFolioLuwaUno;
	private FolioPreparacionLuwaUno folioPreparacionLuwaUno;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private List<ResumenLuwaUno> listaResumen;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(LuwaUnoBean.class.getName());

	public LuwaUnoBean() {

	}

	@PostConstruct
	public void init() {
		listaLuwa = new ArrayList<>();
		luwa = new LuwaUno();
		luwaEditar = new LuwaUno();

		limpieza = new LimpiezaLuwaUno();
		limpiezaLuwaUno = new ArrayList<>();
		limpiezaEditar = new LimpiezaLuwaUno();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoLuwaUno();
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaUno();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionLuwaUno = new FolioPreparacionLuwaUno();

		listaResumen = new ArrayList<>();

		primera();

	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public FolioPreparacionLuwaUno getFolioPreparacionLuwaUno() {
		return folioPreparacionLuwaUno;
	}

	public void setFolioPreparacionLuwaUno(FolioPreparacionLuwaUno folioPreparacionLuwaUno) {
		this.folioPreparacionLuwaUno = folioPreparacionLuwaUno;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaLuwaUnoDao lDao = new LimpiezaLuwaUnoDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepLuwa);
		return listaLimpiezas;
	}

	public List<FolioPreparacionLuwaUno> getListaFolioLuwaUno() {
		IFolioPreparacionLuwaUnoDao lDao = new FolioPreparacionLuwaUnoDaoImpl();
		listaFolioLuwaUno = lDao.listaFolioLuwaUno(folioPrepLuwa);
		return listaFolioLuwaUno;
	}

	public OrdenMantenimientoLuwaUno getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoLuwaUno ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoLuwaUno getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoLuwaUno ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoLuwaUno> getListaOrdenManto() {
		IOrdenMantoLuwaUnoDao oDao = new OrdenMantoLuwaUnoDaoImpl();
		IFolioPreparacionLuwaUnoDao folioPrepDao = new FolioPreparacionLuwaUnoDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaUnoActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepLuwa);
		return listaOrdenManto;
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
		listarRegistroTurnos = rDao.listaRegistroTurnosLuwaUnoPlantaA(fecha);
		IFolioPreparacionLuwaUnoDao folioPrepDao = new FolioPreparacionLuwaUnoDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaUnoActual(fecha);
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
		rt.setDescProceso("LUWA UNO PLANTA A");

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
		return tDao.completeOperador(nombre, "Luwa Planta A");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Luwa Planta A");
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
		listaOperadores = oDao.listaOperadorLuwaPA();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Luwa Planta A");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
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

	public LimpiezaLuwaUno getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaLuwaUno limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaLuwaUno getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaLuwaUno limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
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

	public int getFolioPrepLuwa() {
		return folioPrepLuwa;
	}

	public void setFolioPrepLuwa(int folioPrepLuwa) {
		this.folioPrepLuwa = folioPrepLuwa;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public void setLuwa(LuwaUno luwa) {
		this.luwa = luwa;
	}

	public LuwaUno getLuwaEditar() {
		return luwaEditar;
	}

	public void setLuwaEditar(LuwaUno luwaEditar) {
		this.luwaEditar = luwaEditar;
	}

	public List<LuwaUno> getListaLuwa() {
		ILuwaUnoDao eDao = new LuwaUnoDaoImpl();
		if (fechaFiltro != null) {
			listaLuwa = eDao.listaPorFechaLuwa(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaLuwa.get(i).getFolioLuwa();
				fecha = listaLuwa.get(i).getFecha();
			}
		} else {
			listaLuwa = eDao.listaLuwa();
		}

		return listaLuwa;
	}

	public List<LimpiezaLuwaUno> getLimpiezaLuwaUno() {
		ILimpiezaLuwaUnoDao lDao = new LimpiezaLuwaUnoDaoImpl();

		IFolioPreparacionLuwaUnoDao folioPrepDao = new FolioPreparacionLuwaUnoDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaUnoActual(fecha);
		limpiezaLuwaUno = lDao.listarLimpieza(folioPrepLuwa);

		return limpiezaLuwaUno;
	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionLuwaUno f = new FolioPreparacionLuwaUno();
		f.setIdFolioPrep(folioPrepLuwa);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaLuwaUnoDao validaDao = new LimpiezaLuwaUnoDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepLuwa);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaLuwaUnoDao lDao = new LimpiezaLuwaUnoDaoImpl();

		ILuwaUnoDao vDao = new LuwaUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionLuwaUno(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoLuwa(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaLuwaUno();
		}

	}

	public void actualizarLuwa() {

		ILuwaUnoDao cDao = new LuwaUnoDaoImpl();

		String oper = luwaEditar.getOperacion().replaceAll("\\s+", "");
		luwaEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		if (Boolean.TRUE.equals(luwaEditar.getEstadoAR())) {
			luwaEditar.setEstadoA("X");
			luwaEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(luwaEditar.getEstadoAR())) {
			luwaEditar.setEstadoR("X");
			luwaEditar.setEstadoA(null);
		}

		cDao.actualizarLuwa(luwaEditar);
		actualizarPromedios(luwaEditar.getFolioPreparacionLuwaUno().getIdFolioPrep());

		if (luwaEditar.getHora().equals("7:00")) {
			ILuwaUnoDao aDao = new LuwaUnoDaoImpl();
			aDao.actualizarLuwaPromedio(luwaEditar.getOperacion(), folioFecha);
		}
		luwaEditar = new LuwaUno();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {
		ILuwaUnoDao actualizar_conc_entrada = new LuwaUnoDaoImpl();
		actualizar_conc_entrada.actualizarEntradaConcentrado(folio);
		
		ILuwaUnoDao actualizar_concentrado_porcentaje = new LuwaUnoDaoImpl();
		actualizar_concentrado_porcentaje.actualizarConcentradoPorcentaje(folio);
		
		ILuwaUnoDao actualizar_ph = new LuwaUnoDaoImpl();
		actualizar_ph.actualizarPH(folio);
		
		ILuwaUnoDao actualizar_temp_salida = new LuwaUnoDaoImpl();
		actualizar_temp_salida.actualizarTempSalida(folio);
		
		ILuwaUnoDao actualizar_redox = new LuwaUnoDaoImpl();
		actualizar_redox.actualizarRedox(folio);
		
		ILuwaUnoDao actualizar_presion_bomba = new LuwaUnoDaoImpl();
		actualizar_presion_bomba.actualizarPresionBombaVacio(folio);
		
		ILuwaUnoDao actualizar_valvula_reg = new LuwaUnoDaoImpl();
		actualizar_valvula_reg.actualizarValvulaRegPresion(folio);
		
		ILuwaUnoDao actualizar_cond_temp = new LuwaUnoDaoImpl();
		actualizar_cond_temp.actualizarCondensadorTemp(folio);
		
		ILuwaUnoDao actualizar_corriente_motor = new LuwaUnoDaoImpl();
		actualizar_corriente_motor.actualizarCorrienteMotor(folio);

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ILuwaUnoDao validaDao = new LuwaUnoDaoImpl();
		validaDao.actualizarManto(folioPrepLuwa);

		IOrdenMantoLuwaUnoDao iDao = new OrdenMantoLuwaUnoDaoImpl();

		FolioPreparacionLuwaUno f = new FolioPreparacionLuwaUno();
		f.setIdFolioPrep(folioPrepLuwa);
		ordenMantenimiento.setFolioPreparacionLuwaUno(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoLuwaUno();
	}

	public void guardarObservaciones() {
		IFolioPreparacionLuwaUnoDao fDao = new FolioPreparacionLuwaUnoDaoImpl();
		fDao.guardarObservacion(folioPrepLuwa, folioPreparacionLuwaUno.getObservaciones());
		folioPreparacionLuwaUno = new FolioPreparacionLuwaUno();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioLuwaUno.size(); i++) {
			folioPreparacionLuwaUno.setObservaciones(listaFolioLuwaUno.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void actualizarOrdenManto() {
		IOrdenMantoLuwaUnoDao iDao = new OrdenMantoLuwaUnoDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoLuwaUno();
	}

	public void borrarOrdenManto() {
		IOrdenMantoLuwaUnoDao iDao = new OrdenMantoLuwaUnoDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaUno();
	}

	public void borrarVoBo() {
		ILimpiezaLuwaUnoDao iDao = new LimpiezaLuwaUnoDaoImpl();
		iDao.borrarVoBo(folioPrepLuwa, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaLuwaUnoDao iDao = new LimpiezaLuwaUnoDaoImpl();
		iDao.agregarVoBo(folioPrepLuwa, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void actualizarLimpieza() {
		ILimpiezaLuwaUnoDao lDao = new LimpiezaLuwaUnoDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaLuwaUno();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		ILuwaUnoDao vDao = new LuwaUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, 0);
		ILimpiezaLuwaUnoDao iDao = new LimpiezaLuwaUnoDaoImpl();
		iDao.borrarLimpieza(folioPrepLuwa, noLimpiezaSeleccionadaBorrar);

	}

	public LuwaUno getLuwa() {
		ILuwaUnoDao lDao = new LuwaUnoDaoImpl();
		if (fechaFiltro != null) {
			listaLuwa = lDao.listaPorFechaLuwa(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaLuwa.get(i).getFolioLuwa();
				fecha = listaLuwa.get(i).getFecha();
			}
		} else {
			listaLuwa = lDao.listaLuwa();
		}
		return luwa;
	}

	public List<LuwaUno> getListaFiltroLuwa() {
		return listaFiltroLuwa;
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(30, "FOLIO_PREPARACION_LUWA_UNO");
	}

	public void guardarLuwa() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_LUWA_UNO");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='LuwaUno.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				ILuwaUnoDao cDao = new LuwaUnoDaoImpl();
				luwa = new LuwaUno();

				// **FOLIO**//
				int folio = 0;
				//year = LocalDate.now().getYear();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fec);
				int newYear = calendario.get(Calendar.YEAR);
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioLuwaUno(newYear);

				// **FOLIO_PREPARACION_LUWA**//

				IFolioPreparacionLuwaUnoDao estDao = new FolioPreparacionLuwaUnoDaoImpl();
				FolioPreparacionLuwaUno fpe = new FolioPreparacionLuwaUno();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					luwa.setFolioLuwa(folio);
					luwa.setHora(lista);
					luwa.setFolioPreparacionLuwaUno(fpe);
					luwa.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					cDao.guardarLuwa(luwa);
					luwa = new LuwaUno();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioLuwaUno(newYear, folio);
			}
			String script = "setTimeout(function() { window.location.href='LuwaUno.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public List<LuwaUno> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaLuwa.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaLuwa.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<LuwaUno> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionLuwaUnoDao fDao = new FolioPreparacionLuwaUnoDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionLuwaUnoDao folioPrepDao = new FolioPreparacionLuwaUnoDaoImpl();
				this.folioPrepLuwa = folioPrepDao.folioLuwaUnoActual(fecha);
			}

		}
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaLuwa(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaLuwaUno();
		getListaOrdenManto();
		getListaFolioLuwaUno();
		getListaResumen();

	}

	public void primera() {
		IFolioPreparacionLuwaUnoDao fDao = new FolioPreparacionLuwaUnoDaoImpl();
		FolioPreparacionLuwaUno f = new FolioPreparacionLuwaUno();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionLuwaUnoDao folioDao = new FolioPreparacionLuwaUnoDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionLuwaUnoDao folioPrepDao = new FolioPreparacionLuwaUnoDaoImpl();
			this.folioPrepLuwa = folioPrepDao.folioLuwaUnoActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaLuwaUno();
			getListaOrdenManto();
			getListaFolioLuwaUno();
			getListaResumen();

		}

	}

	public List<ResumenLuwaUno> getListaResumen() {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		listaResumen = rDao.listaResumenUno(folioPrepLuwa);
		return listaResumen;
	}

	// VALIDACIONES DE RESUMEN

	public void resumenLuwa() throws ParseException {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		rDao.borrarResumenUno(folioPrepLuwa);

		IResumenLuwaDao gDao = new ResumenLuwaDaoImpl();
		// OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
		ILuwaUnoDao operacionesDao = new LuwaUnoDaoImpl();
		List<String> listaOperaciones = operacionesDao.listarOperaciones(folioPrepLuwa);
		for (int i = 0; i < listaOperaciones.size(); i++) {
			System.out.println(listaOperaciones.get(i));
			LOGGER.info("OPERACION: " + listaOperaciones.get(i));
			// OBTERNER LA PRIMERA HORA DE LA OPERACION
			ILuwaUnoDao primeraHoraDao = new LuwaUnoDaoImpl();
			LOGGER.info("PRIMERA HORA: " + primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepLuwa));

			ILuwaUnoDao ultimaHoraDao = new LuwaUnoDaoImpl();
			LOGGER.info("ULTIMA HORA: " + ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepLuwa));

			ResumenLuwaUno resumen = new ResumenLuwaUno();
			resumen.setNoOperacion(listaOperaciones.get(i));

			String horaPStr = primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepLuwa);

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

			String horaUStr = ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepLuwa);
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

			FolioPreparacionLuwaUno f = new FolioPreparacionLuwaUno();
			f.setIdFolioPrep(folioPrepLuwa);
			resumen.setFolioPreparacionLuwaUno(f);
			gDao.guardarResumenUno(resumen);

		}

	}

	public Date sumarUnDia(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public Boolean validarHora(String hora) {
		if (hora.equals("00:00") || hora.equals("1:00") || hora.equals("2:00") || hora.equals("3:00")
				|| hora.equals("4:00") || hora.equals("5:00") || hora.equals("6:00")) {
			return true;
		}
		return false;
	}
	
	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		// ACTUALIZAR EL RESUMEN
		try {
			resumenLuwa();
		} catch (ParseException e) {
			LOGGER.error("ERROR AL ACTUALIZAR EL RESUMEN");
			e.printStackTrace();
		}
		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/luwa_uno_rep.jasper");
		reporte.getReporte(ruta, fecha.toString(), folioFecha);

		FacesContext.getCurrentInstance().responseComplete();

	}
	
	public void visualizarReporteExcel() throws SQLException {
		@SuppressWarnings("unused")

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		// ACTUALIZAR EL RESUMEN
		try {
			resumenLuwa();
		} catch (ParseException e) {
			LOGGER.error("ERROR AL ACTUALIZAR EL RESUMEN");
			e.printStackTrace();
		}

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("/REP/luwa_uno_rep_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

}
