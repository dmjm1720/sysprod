package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
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

import com.dmjm.dao.IDafUnoDao;
import com.dmjm.dao.IDafUnoPrepFlolucolanteDao;
import com.dmjm.dao.IFolioPreparacionDafUnoDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaDafUnoDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantenimientoDafUnoDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.DafUnoDaoImpl;
import com.dmjm.impl.DafUnoPrepFlolucolanteDaoImpl;
import com.dmjm.impl.FolioPreparacionDafUnoDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaDafUnoDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantenimientoDafUnoDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.DafUno;
import com.dmjm.model.DafUnoPrepFlolucolante;
import com.dmjm.model.FolioPreparacionDafUno;
import com.dmjm.model.LimpiezaDafUno;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoDafUno;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;

@Named("dafUnoBean")
@ViewScoped
public class DafUnoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<DafUno> listaDaf;
	private DafUno daf;
	private DafUno dafEditar;

	private List<DafUno> listaFiltroDaf;

	private Date fecha;
	private int folioFecha;
	private int folioPrepDaf;
	private Date fechaFiltro;

	private LimpiezaDafUno limpieza;
	private List<LimpiezaDafUno> limpiezaDafUno;
	private LimpiezaDafUno limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoDafUno> listaOrdenManto;
	private OrdenMantenimientoDafUno ordenMantenimiento;
	private OrdenMantenimientoDafUno ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionDafUno> listaFolioDafUno;
	private FolioPreparacionDafUno folioPreparacionDafUno;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private List<Integer> listarFoliosPreparacion;
	private int folioSeleccionado;

	private String bandera;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(DafUnoBean.class.getName());

	public DafUnoBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		listaDaf = new ArrayList<>();
		daf = new DafUno();
		dafEditar = new DafUno();

		limpieza = new LimpiezaDafUno();
		limpiezaDafUno = new ArrayList<>();
		limpiezaEditar = new LimpiezaDafUno();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoDafUno();
		ordenMantenimientoEditar = new OrdenMantenimientoDafUno();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionDafUno = new FolioPreparacionDafUno();

		listarFoliosPreparacion = new ArrayList<>();

		primera();

	}

	public int getFolioSeleccionado() {
		return folioSeleccionado;
	}

	public void setFolioSeleccionado(int folioSeleccionado) {
		this.folioSeleccionado = folioSeleccionado;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	public List<DafUno> getListaDaf() {
		IDafUnoDao eDao = new DafUnoDaoImpl();
		if (fechaFiltro != null) {
			listaDaf = eDao.listaPorFechaDaf(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaDaf.get(i).getFolioDaf();
				fecha = listaDaf.get(i).getFecha();
			}
		} else {
			listaDaf = eDao.listaDaf();
		}

		return listaDaf;
	}

	public DafUno getDaf() {
		IDafUnoDao lDao = new DafUnoDaoImpl();
		if (fechaFiltro != null) {
			listaDaf = lDao.listaPorFechaDaf(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaDaf.get(i).getFolioDaf();
				fecha = listaDaf.get(i).getFecha();
			}
		} else {
			listaDaf = lDao.listaDaf();
		}
		return daf;
	}

	public void dafPrep(String dato) {
		this.bandera = dato;
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

	public void setDaf(DafUno daf) {
		this.daf = daf;
	}

	public DafUno getDafEditar() {
		return dafEditar;
	}

	public void setDafEditar(DafUno dafEditar) {
		this.dafEditar = dafEditar;
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

	public int getFolioPrepDaf() {
		return folioPrepDaf;
	}

	public void setFolioPrepDaf(int folioPrepDaf) {
		this.folioPrepDaf = folioPrepDaf;
	}

	public LimpiezaDafUno getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaDafUno limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaDafUno getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaDafUno limpiezaEditar) {
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

	public OrdenMantenimientoDafUno getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoDafUno ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoDafUno getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoDafUno ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public FolioPreparacionDafUno getFolioPreparacionDafUno() {
		return folioPreparacionDafUno;
	}

	public void setFolioPreparacionDafUno(FolioPreparacionDafUno folioPreparacionDafUno) {
		this.folioPreparacionDafUno = folioPreparacionDafUno;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
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

	public List<LimpiezaDafUno> getLimpiezaDafUno() {
		ILimpiezaDafUnoDao lDao = new LimpiezaDafUnoDaoImpl();

		IFolioPreparacionDafUnoDao folioPrepDao = new FolioPreparacionDafUnoDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafUnoActual(fecha);
		limpiezaDafUno = lDao.listarLimpieza(folioPrepDaf);

		return limpiezaDafUno;
	}

	public List<DafUno> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaDaf.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaDaf.subList(inicio, fin);
	}

	public List<DafUno> getListaFiltroDaf() {
		return listaFiltroDaf;
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(30, "FOLIO_PREPARACION_DAF_UNO");
	}

	public void guardarDaf() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_DAF_UNO");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='DafUno.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				IDafUnoDao cDao = new DafUnoDaoImpl();
				daf = new DafUno();

				// **FOLIO**//
				int folio = 0;
				// year = LocalDate.now().getYear();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fec);
				int newYear = calendario.get(Calendar.YEAR);
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioDafUno(newYear);

				// **FOLIO_PREPARACION_DAF**//

				IFolioPreparacionDafUnoDao estDao = new FolioPreparacionDafUnoDaoImpl();
				FolioPreparacionDafUno fpe = new FolioPreparacionDafUno();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					daf.setFolioDaf(folio);
					daf.setHora(lista);
					daf.setFolioPreparacionDafUno(fpe);
					daf.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					cDao.guardarDaf(daf);
					daf = new DafUno();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioDafUno(newYear, folio);
			}
			String script = "setTimeout(function() { window.location.href='DafUno.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public void actualizaDaf() {

		IDafUnoDao cDao = new DafUnoDaoImpl();

		String oper = dafEditar.getOperacion().replaceAll("\\s+", "");
		dafEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		if (Boolean.TRUE.equals(dafEditar.getEstadoAR())) {
			dafEditar.setEstadoA("X");
			dafEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(dafEditar.getEstadoAR())) {
			dafEditar.setEstadoR("X");
			dafEditar.setEstadoA(null);
		}

		dafEditar.setFolioPreparacion(folioSeleccionado);
		cDao.actualizarDaf(dafEditar);
		actualizarPromedios(dafEditar.getFolioPreparacionDafUno().getIdFolioPrep());

		if (dafEditar.getHora().equals("7:00")) {
			IDafUnoDao aDao = new DafUnoDaoImpl();
			aDao.actualizarDafPromedio(dafEditar.getOperacion(), folioFecha);
		}
		dafEditar = new DafUno();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {
		IDafUnoDao actualizar_conc_porcentaje = new DafUnoDaoImpl();
		actualizar_conc_porcentaje.actualizarConcentradoPorcentaje(folio);

		IDafUnoDao actualizar_flujo_grenetina = new DafUnoDaoImpl();
		actualizar_flujo_grenetina.actualizarFlujoGrenetina(folio);

		IDafUnoDao actualizar_ph = new DafUnoDaoImpl();
		actualizar_ph.actualizarPH(folio);

		IDafUnoDao actualizar_ntu_entrada = new DafUnoDaoImpl();
		actualizar_ntu_entrada.actualizarNTUEntrada(folio);

		IDafUnoDao actualizar_ntu_salida = new DafUnoDaoImpl();
		actualizar_ntu_salida.actualizarNTUSalida(folio);

		IDafUnoDao actualizar_presion_aire = new DafUnoDaoImpl();
		actualizar_presion_aire.actualizarPresionAire(folio);

		IDafUnoDao actualizar_frecBomba_flolucolante = new DafUnoDaoImpl();
		actualizar_frecBomba_flolucolante.actualizarFrecBombaFlolucolante(folio);

		IDafUnoDao actualizar_valvula_reg_presion = new DafUnoDaoImpl();
		actualizar_valvula_reg_presion.actualizarValvulaRegPresion(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionDafUno f = new FolioPreparacionDafUno();
		f.setIdFolioPrep(folioPrepDaf);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaDafUnoDao validaDao = new LimpiezaDafUnoDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepDaf);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaDafUnoDao lDao = new LimpiezaDafUnoDaoImpl();

		IDafUnoDao vDao = new DafUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepDaf, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionDafUno(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoDaf(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaDafUno();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaDafUnoDao lDao = new LimpiezaDafUnoDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaDafUno();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IDafUnoDao vDao = new DafUnoDaoImpl();
		vDao.actualizarLimpieza(folioPrepDaf, 0);
		ILimpiezaDafUnoDao iDao = new LimpiezaDafUnoDaoImpl();
		iDao.borrarLimpieza(folioPrepDaf, noLimpiezaSeleccionadaBorrar);

	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Daf Uno");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorDafUno();
		return listaOperadores;
	}

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosDafUno(fecha);
		IFolioPreparacionDafUnoDao folioPrepDao = new FolioPreparacionDafUnoDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafUnoActual(fecha);
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
		rt.setDescProceso("DAF UNO");

		rDao.guardaRegistroTurnos(rt);

		u = new Usuarios();
		o = new Operador();
		t = new Turnos();
		rt = new RegistroTurnos();
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
		return tDao.completeOperador(nombre, "Daf Uno");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Daf Uno");
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

	public List<OrdenMantenimientoDafUno> getListaOrdenManto() {
		IOrdenMantenimientoDafUnoDao oDao = new OrdenMantenimientoDafUnoDaoImpl();
		IFolioPreparacionDafUnoDao folioPrepDao = new FolioPreparacionDafUnoDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafUnoActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepDaf);
		return listaOrdenManto;
	}

	public void actualizarOrdenManto() {
		IOrdenMantenimientoDafUnoDao iDao = new OrdenMantenimientoDafUnoDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoDafUno();
	}

	public void borrarOrdenManto() {
		IOrdenMantenimientoDafUnoDao iDao = new OrdenMantenimientoDafUnoDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoDafUno();
	}

	public void borrarVoBo() {
		ILimpiezaDafUnoDao iDao = new LimpiezaDafUnoDaoImpl();
		iDao.borrarVoBo(folioPrepDaf, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaDafUnoDao iDao = new LimpiezaDafUnoDaoImpl();
		iDao.agregarVoBo(folioPrepDaf, noLimpiezaVoBo, us.getIdUsuario());
	}

	public List<FolioPreparacionDafUno> getListaFolioDafUno() {
		IFolioPreparacionDafUnoDao lDao = new FolioPreparacionDafUnoDaoImpl();
		listaFolioDafUno = lDao.listaFolioDafUno(folioPrepDaf);
		return listaFolioDafUno;
	}

	public void primera() {
		IFolioPreparacionDafUnoDao fDao = new FolioPreparacionDafUnoDaoImpl();
		FolioPreparacionDafUno f = new FolioPreparacionDafUno();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionDafUnoDao folioDao = new FolioPreparacionDafUnoDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionDafUnoDao folioPrepDao = new FolioPreparacionDafUnoDaoImpl();
			this.folioPrepDaf = folioPrepDao.folioDafUnoActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaDafUno();
			getListaOrdenManto();
			getListaFolioDafUno();
		}
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaDafUnoDao lDao = new LimpiezaDafUnoDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepDaf);
		return listaLimpiezas;
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<DafUno> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionDafUnoDao fDao = new FolioPreparacionDafUnoDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionDafUnoDao folioPrepDao = new FolioPreparacionDafUnoDaoImpl();
				this.folioPrepDaf = folioPrepDao.folioDafUnoActual(fecha);
			}

		}
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaDaf(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaDafUno();
		getListaOrdenManto();
		getListaFolioDafUno();

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		IDafUnoDao validaDao = new DafUnoDaoImpl();
		validaDao.actualizarManto(folioPrepDaf);

		IOrdenMantenimientoDafUnoDao iDao = new OrdenMantenimientoDafUnoDaoImpl();

		FolioPreparacionDafUno f = new FolioPreparacionDafUno();
		f.setIdFolioPrep(folioPrepDaf);
		ordenMantenimiento.setFolioPreparacionDafUno(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoDafUno();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioDafUno.size(); i++) {
			folioPreparacionDafUno.setObservaciones(listaFolioDafUno.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void guardarObservaciones() {
		IFolioPreparacionDafUnoDao fDao = new FolioPreparacionDafUnoDaoImpl();
		fDao.guardarObservacion(folioPrepDaf, folioPreparacionDafUno.getObservaciones());
		folioPreparacionDafUno = new FolioPreparacionDafUno();
	}

	public List<Integer> getListarFoliosPreparacion() {
		IDafUnoPrepFlolucolanteDao lDao = new DafUnoPrepFlolucolanteDaoImpl();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		int newYear = calendario.get(Calendar.YEAR);
		listarFoliosPreparacion = lDao.listarFolios(newYear);
		return listarFoliosPreparacion;
	}
	
	public void actualizarFolioPrepFloculante() {
		
	}

}
