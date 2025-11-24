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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IFolioPreparacionLuwaTresDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaLuwaTresDao;
import com.dmjm.dao.ILuwaTresDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoLuwaTresDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.IResumenLuwaDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.FolioPreparacionLuwaTresDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaLuwaTresDaoImpl;
import com.dmjm.impl.LuwaTresDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoLuwaTresDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.ResumenLuwaDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.FolioPreparacionLuwaTres;
import com.dmjm.model.LimpiezaLuwaTres;
import com.dmjm.model.LuwaTres;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoLuwaTres;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.ResumenLuwaTres;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("luwaTresBean")
@ViewScoped
public class LuwaTresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LuwaTres> listaLuwa;
	private LuwaTres luwa;
	private LuwaTres luwaEditar;

	private List<LuwaTres> listaFiltroLuwa;

	private Date fecha;
	private int folioFecha;
	private int folioPrepLuwa;
	private Date fechaFiltro;

	private LimpiezaLuwaTres limpieza;
	private List<LimpiezaLuwaTres> limpiezaLuwaTres;
	private LimpiezaLuwaTres limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoLuwaTres> listaOrdenManto;
	private OrdenMantenimientoLuwaTres ordenMantenimiento;
	private OrdenMantenimientoLuwaTres ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionLuwaTres> listaFolioLuwaTres;
	private FolioPreparacionLuwaTres folioPreparacionLuwaTres;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private List<ResumenLuwaTres> listaResumen;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(LuwaTresBean.class.getName());

	public LuwaTresBean() {

	}

	@PostConstruct
	public void init() {
		listaLuwa = new ArrayList<>();
		luwa = new LuwaTres();
		luwaEditar = new LuwaTres();

		limpieza = new LimpiezaLuwaTres();
		limpiezaLuwaTres = new ArrayList<>();
		limpiezaEditar = new LimpiezaLuwaTres();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoLuwaTres();
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaTres();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionLuwaTres = new FolioPreparacionLuwaTres();

		listaResumen = new ArrayList<>();

		primera();

	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public FolioPreparacionLuwaTres getFolioPreparacionLuwaTres() {
		return folioPreparacionLuwaTres;
	}

	public void setFolioPreparacionLuwaTres(FolioPreparacionLuwaTres folioPreparacionLuwaTres) {
		this.folioPreparacionLuwaTres = folioPreparacionLuwaTres;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaLuwaTresDao lDao = new LimpiezaLuwaTresDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepLuwa);
		return listaLimpiezas;
	}

	public List<FolioPreparacionLuwaTres> getListaFolioLuwaTres() {
		IFolioPreparacionLuwaTresDao lDao = new FolioPreparacionLuwaTresDaoImpl();
		listaFolioLuwaTres = lDao.listaFolioLuwaTres(folioPrepLuwa);
		return listaFolioLuwaTres;
	}

	public OrdenMantenimientoLuwaTres getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoLuwaTres ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoLuwaTres getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoLuwaTres ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public List<OrdenMantenimientoLuwaTres> getListaOrdenManto() {
		IOrdenMantoLuwaTresDao oDao = new OrdenMantoLuwaTresDaoImpl();
		IFolioPreparacionLuwaTresDao folioPrepDao = new FolioPreparacionLuwaTresDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaTresActual(fecha);
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
		listarRegistroTurnos = rDao.listaRegistroTurnosLuwaTresPlantaA(fecha);
		IFolioPreparacionLuwaTresDao folioPrepDao = new FolioPreparacionLuwaTresDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaTresActual(fecha);
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
		rt.setDescProceso("LUWA TRES PLANTA A");

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

	public LimpiezaLuwaTres getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaLuwaTres limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaLuwaTres getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaLuwaTres limpiezaEditar) {
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

	public void setLuwa(LuwaTres luwa) {
		this.luwa = luwa;
	}

	public LuwaTres getLuwaEditar() {
		return luwaEditar;
	}

	public void setLuwaEditar(LuwaTres luwaEditar) {
		this.luwaEditar = luwaEditar;
	}

	public List<LuwaTres> getListaLuwa() {
		ILuwaTresDao eDao = new LuwaTresDaoImpl();
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

	public List<LimpiezaLuwaTres> getLimpiezaLuwaTres() {
		ILimpiezaLuwaTresDao lDao = new LimpiezaLuwaTresDaoImpl();

		IFolioPreparacionLuwaTresDao folioPrepDao = new FolioPreparacionLuwaTresDaoImpl();
		this.folioPrepLuwa = folioPrepDao.folioLuwaTresActual(fecha);
		limpiezaLuwaTres = lDao.listarLimpieza(folioPrepLuwa);

		return limpiezaLuwaTres;
	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
		f.setIdFolioPrep(folioPrepLuwa);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaLuwaTresDao validaDao = new LimpiezaLuwaTresDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepLuwa);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaLuwaTresDao lDao = new LimpiezaLuwaTresDaoImpl();

		ILuwaTresDao vDao = new LuwaTresDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionLuwaTres(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoLuwa(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaLuwaTres();
		}

	}

	public void actualizarLuwa() {

		ILuwaTresDao cDao = new LuwaTresDaoImpl();

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
		actualizarPromedios(luwaEditar.getFolioPreparacionLuwaTres().getIdFolioPrep());

		if (luwaEditar.getHora().equals("7:00")) {
			ILuwaTresDao aDao = new LuwaTresDaoImpl();
			aDao.actualizarLuwaPromedio(luwaEditar.getOperacion(), folioFecha);
		}
		luwaEditar = new LuwaTres();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {
		ILuwaTresDao actualizar_conc_entrada = new LuwaTresDaoImpl();
		actualizar_conc_entrada.actualizarEntradaConcentrado(folio);
		
		ILuwaTresDao actualizar_concentrado_porcentaje = new LuwaTresDaoImpl();
		actualizar_concentrado_porcentaje.actualizarConcentradoPorcentaje(folio);
		
		ILuwaTresDao actualizar_ph = new LuwaTresDaoImpl();
		actualizar_ph.actualizarPH(folio);
		
		ILuwaTresDao actualizar_temp_salida = new LuwaTresDaoImpl();
		actualizar_temp_salida.actualizarTempSalida(folio);
		
		ILuwaTresDao actualizar_redox = new LuwaTresDaoImpl();
		actualizar_redox.actualizarRedox(folio);
		
		ILuwaTresDao actualizar_presion_bomba = new LuwaTresDaoImpl();
		actualizar_presion_bomba.actualizarPresionBombaVacio(folio);
		
		ILuwaTresDao actualizar_valvula_reg = new LuwaTresDaoImpl();
		actualizar_valvula_reg.actualizarValvulaRegPresion(folio);
		
		ILuwaTresDao actualizar_cond_temp = new LuwaTresDaoImpl();
		actualizar_cond_temp.actualizarCondensadorTemp(folio);
		
		ILuwaTresDao actualizar_corriente_motor = new LuwaTresDaoImpl();
		actualizar_corriente_motor.actualizarCorrienteMotor(folio);

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ILuwaTresDao validaDao = new LuwaTresDaoImpl();
		validaDao.actualizarManto(folioPrepLuwa);

		IOrdenMantoLuwaTresDao iDao = new OrdenMantoLuwaTresDaoImpl();

		FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
		f.setIdFolioPrep(folioPrepLuwa);
		ordenMantenimiento.setFolioPreparacionLuwaTres(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoLuwaTres();
	}

	public void guardarObservaciones() {
		IFolioPreparacionLuwaTresDao fDao = new FolioPreparacionLuwaTresDaoImpl();
		fDao.guardarObservacion(folioPrepLuwa, folioPreparacionLuwaTres.getObservaciones());
		folioPreparacionLuwaTres = new FolioPreparacionLuwaTres();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioLuwaTres.size(); i++) {
			folioPreparacionLuwaTres.setObservaciones(listaFolioLuwaTres.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void actualizarOrdenManto() {
		IOrdenMantoLuwaTresDao iDao = new OrdenMantoLuwaTresDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoLuwaTres();
	}

	public void borrarOrdenManto() {
		IOrdenMantoLuwaTresDao iDao = new OrdenMantoLuwaTresDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoLuwaTres();
	}

	public void borrarVoBo() {
		ILimpiezaLuwaTresDao iDao = new LimpiezaLuwaTresDaoImpl();
		iDao.borrarVoBo(folioPrepLuwa, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaLuwaTresDao iDao = new LimpiezaLuwaTresDaoImpl();
		iDao.agregarVoBo(folioPrepLuwa, noLimpiezaVoBo, us.getIdUsuario());
	}

	public void actualizarLimpieza() {
		ILimpiezaLuwaTresDao lDao = new LimpiezaLuwaTresDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaLuwaTres();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		ILuwaTresDao vDao = new LuwaTresDaoImpl();
		vDao.actualizarLimpieza(folioPrepLuwa, 0);
		ILimpiezaLuwaTresDao iDao = new LimpiezaLuwaTresDaoImpl();
		iDao.borrarLimpieza(folioPrepLuwa, noLimpiezaSeleccionadaBorrar);

	}

	public LuwaTres getLuwa() {
		ILuwaTresDao lDao = new LuwaTresDaoImpl();
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

	public List<LuwaTres> getListaFiltroLuwa() {
		return listaFiltroLuwa;
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(80, "FOLIO_PREPARACION_LUWA_TRES");
	}

	public void guardarLuwa() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_LUWA_TRES");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='LuwaTres.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				ILuwaTresDao cDao = new LuwaTresDaoImpl();
				luwa = new LuwaTres();

				// **FOLIO_COCEDORES**//
				int year = 0;
				int folio = 0;
				year = LocalDate.now().getYear();
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioLuwaTres(year);

				// **FOLIO_PREPARACION_LUWA**//

				IFolioPreparacionLuwaTresDao estDao = new FolioPreparacionLuwaTresDaoImpl();
				FolioPreparacionLuwaTres fpe = new FolioPreparacionLuwaTres();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					luwa.setFolioLuwa(folio);
					luwa.setHora(lista);
					luwa.setFolioPreparacionLuwaTres(fpe);
					luwa.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					cDao.guardarLuwa(luwa);
					luwa = new LuwaTres();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioLuwaTres(year, folio);
			}
			String script = "setTimeout(function() { window.location.href='LuwaTres.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public List<LuwaTres> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaLuwa.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaLuwa.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<LuwaTres> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionLuwaTresDao fDao = new FolioPreparacionLuwaTresDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionLuwaTresDao folioPrepDao = new FolioPreparacionLuwaTresDaoImpl();
				this.folioPrepLuwa = folioPrepDao.folioLuwaTresActual(fecha);
			}

		}
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaLuwa(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaLuwaTres();
		getListaOrdenManto();
		getListaFolioLuwaTres();
		getListaResumen();

	}

	public void primera() {
		IFolioPreparacionLuwaTresDao fDao = new FolioPreparacionLuwaTresDaoImpl();
		FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionLuwaTresDao folioDao = new FolioPreparacionLuwaTresDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionLuwaTresDao folioPrepDao = new FolioPreparacionLuwaTresDaoImpl();
			this.folioPrepLuwa = folioPrepDao.folioLuwaTresActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaLuwaTres();
			getListaOrdenManto();
			getListaFolioLuwaTres();
			getListaResumen();

		}

	}

	public List<ResumenLuwaTres> getListaResumen() {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		listaResumen = rDao.listaResumenTres(folioPrepLuwa);
		return listaResumen;
	}

	// VALIDACIONES DE RESUMEN

	public void resumenLuwa() throws ParseException {
		IResumenLuwaDao rDao = new ResumenLuwaDaoImpl();
		rDao.borrarResumenTres(folioPrepLuwa);

		IResumenLuwaDao gDao = new ResumenLuwaDaoImpl();
		// OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
		ILuwaTresDao operacionesDao = new LuwaTresDaoImpl();
		List<String> listaOperaciones = operacionesDao.listarOperaciones(folioPrepLuwa);
		for (int i = 0; i < listaOperaciones.size(); i++) {
			System.out.println(listaOperaciones.get(i));
			LOGGER.info("OPERACION: " + listaOperaciones.get(i));
			// OBTERNER LA PRIMERA HORA DE LA OPERACION
			ILuwaTresDao primeraHoraDao = new LuwaTresDaoImpl();
			LOGGER.info("PRIMERA HORA: " + primeraHoraDao.obtenerPrimeraHora(listaOperaciones.get(i), folioPrepLuwa));

			ILuwaTresDao ultimaHoraDao = new LuwaTresDaoImpl();
			LOGGER.info("ULTIMA HORA: " + ultimaHoraDao.obtenerUltimaHora(listaOperaciones.get(i), folioPrepLuwa));

			ResumenLuwaTres resumen = new ResumenLuwaTres();
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

			FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
			f.setIdFolioPrep(folioPrepLuwa);
			resumen.setFolioPreparacionLuwaTres(f);
			gDao.guardarResumenTres(resumen);

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

}
