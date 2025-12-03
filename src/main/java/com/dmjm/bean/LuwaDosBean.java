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

import com.dmjm.dao.IFolioPreparacionLuwaDosDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaLuwaDosDao;
import com.dmjm.dao.ILuwaDosDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoLuwaDosDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.IResumenLuwaDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioPreparacionLuwaDosDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaLuwaDosDaoImpl;
import com.dmjm.impl.LuwaDosDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoLuwaDosDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.ResumenLuwaDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionLuwaDos;
import com.dmjm.model.LimpiezaLuwaDos;
import com.dmjm.model.LuwaDos;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoLuwaDos;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.ResumenLuwaDos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteEsterilizadores;

@Named("luwaDosBean")
@ViewScoped
public class LuwaDosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LuwaDos> listaLuwa;
	private LuwaDos luwa;
	private LuwaDos luwaEditar;

	private List<LuwaDos> listaFiltroLuwa;

	private Date fecha;
	private int folioFecha;
	private int folioPrepLuwa;
	private Date fechaFiltro;

	private LimpiezaLuwaDos limpieza;
	private List<LimpiezaLuwaDos> limpiezaLuwaDos;
	private LimpiezaLuwaDos limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoLuwaDos> listaOrdenManto;
	private OrdenMantenimientoLuwaDos ordenMantenimiento;
	private OrdenMantenimientoLuwaDos ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionLuwaDos> listaFolioLuwaDos;
	private FolioPreparacionLuwaDos folioPreparacionLuwaDos;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private List<ResumenLuwaDos> listaResumen;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(LuwaDosBean.class.getName());

	public LuwaDosBean() {

	}

	@PostConstruct
	public void init() {
		listaLuwa = new ArrayList<>();
		luwa = new LuwaDos();
		luwaEditar = new LuwaDos();

		limpieza = new LimpiezaLuwaDos();
		limpiezaLuwaDos = new ArrayList<>();
		limpiezaEditar = new LimpiezaLuwaDos();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoLuwaDos();
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaDos();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionLuwaDos = new FolioPreparacionLuwaDos();

		listaResumen = new ArrayList<>();

		primera();

	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public FolioPreparacionLuwaDos getFolioPreparacionLuwaDos() {
		return folioPreparacionLuwaDos;
	}

	public void setFolioPreparacionLuwaDos(FolioPreparacionLuwaDos folioPreparacionLuwaDos) {
		this.folioPreparacionLuwaDos = folioPreparacionLuwaDos;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaLuwaDosDao lDao = new LimpiezaLuwaDosDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepLuwa);
		return listaLimpiezas;
	}

	public List<FolioPreparacionLuwaDos> getListaFolioLuwaDos() {
		IFolioPreparacionLuwaDosDao lDao = new FolioPreparacionLuwaDosDaoImpl();
		listaFolioLuwaDos = lDao.listaFolioLuwaDos(folioPrepLuwa);
		return listaFolioLuwaDos;
	}

	public OrdenMantenimientoLuwaDos getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoLuwaDos ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoLuwaDos getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoLuwaDos ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoLuwaDos> getListaOrdenManto() {
		IOrdenMantoLuwaDosDao oDao = new OrdenMantoLuwaDosDaoImpl();
		IFolioPreparacionLuwaDosDao folioPrepDao = new FolioPreparacionLuwaDosDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaDosActual(fecha);
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
		listarRegistroTurnos = rDao.listaRegistroTurnosLuwaDosPlantaA(fecha);
		IFolioPreparacionLuwaDosDao folioPrepDao = new FolioPreparacionLuwaDosDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaDosActual(fecha);
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
		rt.setDescProceso("LUWA DOS PLANTA A");

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

	public LimpiezaLuwaDos getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaLuwaDos limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaLuwaDos getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaLuwaDos limpiezaEditar) {
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

	public void setLuwa(LuwaDos luwa) {
		this.luwa = luwa;
	}

	public LuwaDos getLuwaEditar() {
		return luwaEditar;
	}

	public void setLuwaEditar(LuwaDos luwaEditar) {
		this.luwaEditar = luwaEditar;
	}

	public List<LuwaDos> getListaLuwa() {
		ILuwaDosDao eDao = new LuwaDosDaoImpl();
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

	public List<LimpiezaLuwaDos> getLimpiezaLuwaDos() {
		ILimpiezaLuwaDosDao lDao = new LimpiezaLuwaDosDaoImpl();

		IFolioPreparacionLuwaDosDao folioPrepDao = new FolioPreparacionLuwaDosDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaDosActual(fecha);
		limpiezaLuwaDos = lDao.listarLimpieza(folioPrepLuwa);

		return limpiezaLuwaDos;
	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionLuwaDos f = new FolioPreparacionLuwaDos();
		f.setIdFolioPrep(folioPrepLuwa);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaLuwaDosDao validaDao = new LimpiezaLuwaDosDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepLuwa);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaLuwaDosDao lDao = new LimpiezaLuwaDosDaoImpl();

		ILuwaDosDao vDao = new LuwaDosDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionLuwaDos(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoLuwa(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaLuwaDos();
		}

	}

	public void actualizarLuwa() {

		ILuwaDosDao cDao = new LuwaDosDaoImpl();

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
		actualizarPromedios(luwaEditar.getFolioPreparacionLuwaDos().getIdFolioPrep());

		if (luwaEditar.getHora().equals("7:00")) {
			ILuwaDosDao aDao = new LuwaDosDaoImpl();
			aDao.actualizarLuwaPromedio(luwaEditar.getOperacion(), folioFecha);
		}
		luwaEditar = new LuwaDos();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {
		ILuwaDosDao actualizar_conc_entrada = new LuwaDosDaoImpl();
		actualizar_conc_entrada.actualizarEntradaConcentrado(folio);
		
		ILuwaDosDao actualizar_concentrado_porcentaje = new LuwaDosDaoImpl();
		actualizar_concentrado_porcentaje.actualizarConcentradoPorcentaje(folio);
		
		ILuwaDosDao actualizar_ph = new LuwaDosDaoImpl();
		actualizar_ph.actualizarPH(folio);
		
		ILuwaDosDao actualizar_temp_salida = new LuwaDosDaoImpl();
		actualizar_temp_salida.actualizarTempSalida(folio);
		
		ILuwaDosDao actualizar_redox = new LuwaDosDaoImpl();
		actualizar_redox.actualizarRedox(folio);
		
		ILuwaDosDao actualizar_presion_bomba = new LuwaDosDaoImpl();
		actualizar_presion_bomba.actualizarPresionBombaVacio(folio);
		
		ILuwaDosDao actualizar_valvula_reg = new LuwaDosDaoImpl();
		actualizar_valvula_reg.actualizarValvulaRegPresion(folio);
		
		ILuwaDosDao actualizar_cond_temp = new LuwaDosDaoImpl();
		actualizar_cond_temp.actualizarCondensadorTemp(folio);
		
		ILuwaDosDao actualizar_corriente_motor = new LuwaDosDaoImpl();
		actualizar_corriente_motor.actualizarCorrienteMotor(folio);

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ILuwaDosDao validaDao = new LuwaDosDaoImpl();
		validaDao.actualizarManto(folioPrepLuwa);

		IOrdenMantoLuwaDosDao iDao = new OrdenMantoLuwaDosDaoImpl();

		FolioPreparacionLuwaDos f = new FolioPreparacionLuwaDos();
		f.setIdFolioPrep(folioPrepLuwa);
		ordenMantenimiento.setFolioPreparacionLuwaDos(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoLuwaDos();
	}

	public void guardarObservaciones() {
		IFolioPreparacionLuwaDosDao fDao = new FolioPreparacionLuwaDosDaoImpl();
		fDao.guardarObservacion(folioPrepLuwa, folioPreparacionLuwaDos.getObservaciones());
		folioPreparacionLuwaDos = new FolioPreparacionLuwaDos();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioLuwaDos.size(); i++) {
			folioPreparacionLuwaDos.setObservaciones(listaFolioLuwaDos.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void actualizarOrdenManto() {
		IOrdenMantoLuwaDosDao iDao = new OrdenMantoLuwaDosDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoLuwaDos();
	}

	public void borrarOrdenManto() {
		IOrdenMantoLuwaDosDao iDao = new OrdenMantoLuwaDosDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaDos();
	}

	public void borrarVoBo() {
		ILimpiezaLuwaDosDao iDao = new LimpiezaLuwaDosDaoImpl();
		iDao.borrarVoBo(folioPrepLuwa, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaLuwaDosDao iDao = new LimpiezaLuwaDosDaoImpl();
		iDao.agregarVoBo(folioPrepLuwa, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void actualizarLimpieza() {
		ILimpiezaLuwaDosDao lDao = new LimpiezaLuwaDosDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaLuwaDos();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		ILuwaDosDao vDao = new LuwaDosDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, 0);
		ILimpiezaLuwaDosDao iDao = new LimpiezaLuwaDosDaoImpl();
		iDao.borrarLimpieza(folioPrepLuwa, noLimpiezaSeleccionadaBorrar);

	}

	public LuwaDos getLuwa() {
		ILuwaDosDao lDao = new LuwaDosDaoImpl();
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

	public List<LuwaDos> getListaFiltroLuwa() {
		return listaFiltroLuwa;
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(80, "FOLIO_PREPARACION_LUWA_DOS");
	}

	public void guardarLuwa() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_LUWA_DOS");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='LuwaDos.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				ILuwaDosDao cDao = new LuwaDosDaoImpl();
				luwa = new LuwaDos();

				// **FOLIO_COCEDORES**//
				int year = 0;
				int folio = 0;
				year = LocalDate.now().getYear();
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioLuwaDos(year);

				// **FOLIO_PREPARACION_LUWA**//

				IFolioPreparacionLuwaDosDao estDao = new FolioPreparacionLuwaDosDaoImpl();
				FolioPreparacionLuwaDos fpe = new FolioPreparacionLuwaDos();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					luwa.setFolioLuwa(folio);
					luwa.setHora(lista);
					luwa.setFolioPreparacionLuwaDos(fpe);
					luwa.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					cDao.guardarLuwa(luwa);
					luwa = new LuwaDos();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioLuwaDos(year, folio);
			}
			String script = "setTimeout(function() { window.location.href='LuwaDos.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public List<LuwaDos> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaLuwa.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaLuwa.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<LuwaDos> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionLuwaDosDao fDao = new FolioPreparacionLuwaDosDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionLuwaDosDao folioPrepDao = new FolioPreparacionLuwaDosDaoImpl();
				this.folioPrepLuwa = folioPrepDao.folioLuwaDosActual(fecha);
			}

		}
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaLuwa(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaLuwaDos();
		getListaOrdenManto();
		getListaFolioLuwaDos();
		getListaResumen();

	}

	public void primera() {
		IFolioPreparacionLuwaDosDao fDao = new FolioPreparacionLuwaDosDaoImpl();
		FolioPreparacionLuwaDos f = new FolioPreparacionLuwaDos();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionLuwaDosDao folioDao = new FolioPreparacionLuwaDosDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionLuwaDosDao folioPrepDao = new FolioPreparacionLuwaDosDaoImpl();
			this.folioPrepLuwa = folioPrepDao.folioLuwaDosActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaLuwaDos();
			getListaOrdenManto();
			getListaFolioLuwaDos();
			getListaResumen();

		}

	}

	public List<ResumenLuwaDos> getListaResumen() {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		listaResumen = rDao.listaResumenDos(folioPrepLuwa);
		return listaResumen;
	}

	// VALIDACIONES DE RESUMEN

	public void resumenLuwa() throws ParseException {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		rDao.borrarResumenDos(folioPrepLuwa);

		IResumenLuwaDao gDao = new ResumenLuwaDaoImpl();
		// OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
		ILuwaDosDao operacionesDao = new LuwaDosDaoImpl();
		List<String> listaOperaciones = operacionesDao.listarOperaciones(folioPrepLuwa);
		for (int i = 0; i < listaOperaciones.size(); i++) {
			System.out.println(listaOperaciones.get(i));
			LOGGER.info("OPERACION: " + listaOperaciones.get(i));
			// OBTERNER LA PRIMERA HORA DE LA OPERACION
			ILuwaDosDao primeraHoraDao = new LuwaDosDaoImpl();
			LOGGER.info("PRIMERA HORA: " + primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepLuwa));

			ILuwaDosDao ultimaHoraDao = new LuwaDosDaoImpl();
			LOGGER.info("ULTIMA HORA: " + ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepLuwa));

			ResumenLuwaDos resumen = new ResumenLuwaDos();
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

			FolioPreparacionLuwaDos f = new FolioPreparacionLuwaDos();
			f.setIdFolioPrep(folioPrepLuwa);
			resumen.setFolioPreparacionLuwaDos(f);
			gDao.guardarResumenDos(resumen);

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

		ruta = servletContext.getRealPath("/REP/luwa_dos_rep.jasper");
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
		String ruta = servletContext.getRealPath("/REP/luwa_dos_rep_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString());

		FacesContext.getCurrentInstance().responseComplete();

	}

}
