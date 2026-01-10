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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.IDafDosDao;
import com.dmjm.dao.IDafUnoPrepFlolucolanteDao;
import com.dmjm.dao.IFolioPreparacionDafDosDao;
import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.dao.ILimpiezaDafDosDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantenimientoDafDosDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.impl.DafDosDaoImpl;
import com.dmjm.impl.DafUnoPrepFlolucolanteDaoImpl;
import com.dmjm.impl.FolioPreparacionDafDosDaoImpl;
import com.dmjm.impl.FolioProcesosDaoImpl;
import com.dmjm.impl.LimpiezaDafDosDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantenimientoDafDosDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.impl.ValidacionFolioDaoImpl;
import com.dmjm.model.DafDos;
import com.dmjm.model.FolioPreparacionDafDos;
import com.dmjm.model.LimpiezaDafDos;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimientoDafDos;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;
import com.dmjm.util.ReporteEsterilizadores;

@Named("dafDosBean")
@ViewScoped
public class DafDosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<DafDos> listaDaf;
	private DafDos daf;
	private DafDos dafEditar;

	private List<DafDos> listaFiltroDaf;

	private Date fecha;
	private int folioFecha;
	private int folioPrepDaf;
	private Date fechaFiltro;

	private LimpiezaDafDos limpieza;
	private List<LimpiezaDafDos> limpiezaDafDos;
	private LimpiezaDafDos limpiezaEditar;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private List<OrdenMantenimientoDafDos> listaOrdenManto;
	private OrdenMantenimientoDafDos ordenMantenimiento;
	private OrdenMantenimientoDafDos ordenMantenimientoEditar;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	private List<FolioPreparacionDafDos> listaFolioDafDos;
	private FolioPreparacionDafDos folioPreparacionDafDos;
	private List<Integer> listaLimpiezas;
	private String cocedorSeleccionado;

	private List<Integer> listarFoliosPreparacion;
	private int folioSeleccionado;

	private String bandera;

	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private static final Logger LOGGER = LogManager.getLogger(DafDosBean.class.getName());

	public DafDosBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		listaDaf = new ArrayList<>();
		daf = new DafDos();
		dafEditar = new DafDos();

		limpieza = new LimpiezaDafDos();
		limpiezaDafDos = new ArrayList<>();
		limpiezaEditar = new LimpiezaDafDos();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimientoDafDos();
		ordenMantenimientoEditar = new OrdenMantenimientoDafDos();

		listaLimpiezas = new ArrayList<>();
		folioPreparacionDafDos = new FolioPreparacionDafDos();

		listarFoliosPreparacion = new ArrayList<>();
		listaFiltroDaf = new ArrayList<>();
		IDafDosDao lDafDao = new DafDosDaoImpl();
		listaFiltroDaf = lDafDao.listaFiltroDaf();
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

	public List<DafDos> getListaDaf() {
		IDafDosDao eDao = new DafDosDaoImpl();
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

	public DafDos getDaf() {
		IDafDosDao lDao = new DafDosDaoImpl();
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

	public void setDaf(DafDos daf) {
		this.daf = daf;
	}

	public DafDos getDafEditar() {
		return dafEditar;
	}

	public void setDafEditar(DafDos dafEditar) {
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

	public LimpiezaDafDos getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(LimpiezaDafDos limpieza) {
		this.limpieza = limpieza;
	}

	public LimpiezaDafDos getLimpiezaEditar() {
		return limpiezaEditar;
	}

	public void setLimpiezaEditar(LimpiezaDafDos limpiezaEditar) {
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

	public OrdenMantenimientoDafDos getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimientoDafDos ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimientoDafDos getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimientoDafDos ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public FolioPreparacionDafDos getFolioPreparacionDafDos() {
		return folioPreparacionDafDos;
	}

	public void setFolioPreparacionDafDos(FolioPreparacionDafDos folioPreparacionDafDos) {
		this.folioPreparacionDafDos = folioPreparacionDafDos;
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

	public List<LimpiezaDafDos> getLimpiezaDafDos() {
		ILimpiezaDafDosDao lDao = new LimpiezaDafDosDaoImpl();

		IFolioPreparacionDafDosDao folioPrepDao = new FolioPreparacionDafDosDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafDosActual(fecha);
		limpiezaDafDos = lDao.listarLimpieza(folioPrepDaf);

		return limpiezaDafDos;
	}

	public List<DafDos> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaDaf.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaDaf.subList(inicio, fin);
	}

	public List<DafDos> getListaFiltroDaf() {
		return listaFiltroDaf;
	}

	public List<Date> buscarFechasFaltantes() {
		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		return vDao.validarFechasFaltantes(30, "FOLIO_PREPARACION_DAF_DOS");
	}

	public void guardarDaf() {

		IValidacionFolioDao vDao = new ValidacionFolioDaoImpl();
		boolean validacion = vDao.validarFolio(new Date(), "FOLIO_PREPARACION_DAF_DOS");
		if (validacion) {
			LOGGER.error("YA EXISTE UNA HOJA CON LA MISMA FECHA");
			String info = "Ya existe una hoja con la misma fecha";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
			String script = "setTimeout(function() { window.location.href='DafDos.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		} else {

			// VALIDAMOS LAS FECHAS FALTANTES
			List<Date> listarFechas = new ArrayList<>();
			listarFechas = buscarFechasFaltantes();

			for (Date fec : listarFechas) {

				String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
						"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00",
						"3:00", "4:00", "5:00", "6:00", "PROM." };

				IDafDosDao cDao = new DafDosDaoImpl();
				daf = new DafDos();

				// **FOLIO**//
				int folio = 0;
				// year = LocalDate.now().getYear();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fec);
				int newYear = calendario.get(Calendar.YEAR);
				IFolioProcesosDao folDao = new FolioProcesosDaoImpl();

				folio = folDao.buscarFolioDafDos(newYear);

				// **FOLIO_PREPARACION_DAF**//

				IFolioPreparacionDafDosDao estDao = new FolioPreparacionDafDosDaoImpl();
				FolioPreparacionDafDos fpe = new FolioPreparacionDafDos();
				fpe.setIdFolioPrep(estDao.returnIDGuardarFolio(folio, fec)); // FECHA DEL FOLIO FALTANTE

				for (String lista : listaHora) {
					daf.setFolioDaf(folio);
					daf.setHora(lista);
					daf.setFolioPreparacionDafDos(fpe);
					daf.setFecha(fec); // FECHA DEL FOLIO FALTANTE
					cDao.guardarDaf(daf);
					daf = new DafDos();
				}
				// **ACTUALIZAR FOLIO_PROCESOS**//
				IFolioProcesosDao folioDao = new FolioProcesosDaoImpl();
				folioDao.actualizarFolioDafDos(newYear, folio);
			}
			String script = "setTimeout(function() { window.location.href='DafDos.html'; }, 3000);";
			PrimeFaces.current().executeScript(script);
		}
	}

	public void actualizaDaf() {

		IDafDosDao cDao = new DafDosDaoImpl();

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

		//dafEditar.setFolioPreparacion(folioSeleccionado);
		cDao.actualizarDaf(dafEditar);
		actualizarPromedios(dafEditar.getFolioPreparacionDafDos().getIdFolioPrep());

		if (dafEditar.getHora().equals("7:00")) {
			IDafDosDao aDao = new DafDosDaoImpl();
			aDao.actualizarDafPromedio(dafEditar.getOperacion(), dafEditar.getFolioPreparacionDafDos().getIdFolioPrep());
		}
		dafEditar = new DafDos();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");

	}

	private void actualizarPromedios(Integer folio) {
		IDafDosDao actualizar_conc_porcentaje = new DafDosDaoImpl();
		actualizar_conc_porcentaje.actualizarConcentradoPorcentaje(folio);

		IDafDosDao actualizar_flujo_grenetina = new DafDosDaoImpl();
		actualizar_flujo_grenetina.actualizarFlujoGrenetina(folio);

		IDafDosDao actualizar_ph = new DafDosDaoImpl();
		actualizar_ph.actualizarPH(folio);

		IDafDosDao actualizar_ntu_entrada = new DafDosDaoImpl();
		actualizar_ntu_entrada.actualizarNTUEntrada(folio);

		IDafDosDao actualizar_ntu_salida = new DafDosDaoImpl();
		actualizar_ntu_salida.actualizarNTUSalida(folio);

		IDafDosDao actualizar_presion_aire = new DafDosDaoImpl();
		actualizar_presion_aire.actualizarPresionAire(folio);

		IDafDosDao actualizar_frecBomba_flolucolante = new DafDosDaoImpl();
		actualizar_frecBomba_flolucolante.actualizarFrecBombaFlolucolante(folio);

		IDafDosDao actualizar_valvula_reg_presion = new DafDosDaoImpl();
		actualizar_valvula_reg_presion.actualizarValvulaRegPresion(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "ENJUAGUE", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionDafDos f = new FolioPreparacionDafDos();
		f.setIdFolioPrep(folioPrepDaf);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO

		ILimpiezaDafDosDao validaDao = new LimpiezaDafDosDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepDaf);

		// validación de limpieza para agregar en la tabla de cocedores

		ILimpiezaDafDosDao lDao = new LimpiezaDafDosDaoImpl();

		IDafDosDao vDao = new DafDosDaoImpl();
		vDao.actualizarLimpieza(folioPrepDaf, noDeLimpieza);
		for (String l : datosLimpieza) {
			limpieza.setVobo("PENDIENTE");
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setFolioPreparacionDafDos(f);
			limpieza.setProceso(l);
			limpieza.setIdUsuario(1028);
			limpieza.setNoDaf(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new LimpiezaDafDos();
		}

	}

	public void actualizarLimpieza() {
		ILimpiezaDafDosDao lDao = new LimpiezaDafDosDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpiezaEditar = new LimpiezaDafDos();
	}

	public void deleteLimpieza() {
		// validación de limpieza para agregar en la tabla de cocedores
		IDafDosDao vDao = new DafDosDaoImpl();
		vDao.actualizarLimpieza(folioPrepDaf, 0);
		ILimpiezaDafDosDao iDao = new LimpiezaDafDosDaoImpl();
		iDao.borrarLimpieza(folioPrepDaf, noLimpiezaSeleccionadaBorrar);

	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Daf Dos");
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
		listaOperadores = oDao.listaOperadorDafDos();
		return listaOperadores;
	}

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnosDafDos(fecha);
		IFolioPreparacionDafDosDao folioPrepDao = new FolioPreparacionDafDosDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafDosActual(fecha);
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
		rt.setDescProceso("DAF DOS");

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
		return tDao.completeOperador(nombre, "Daf Dos");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Daf Dos");
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

	public List<OrdenMantenimientoDafDos> getListaOrdenManto() {
		IOrdenMantenimientoDafDosDao oDao = new OrdenMantenimientoDafDosDaoImpl();
		IFolioPreparacionDafDosDao folioPrepDao = new FolioPreparacionDafDosDaoImpl();
		this.folioPrepDaf = folioPrepDao.folioDafDosActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepDaf);
		return listaOrdenManto;
	}

	public void actualizarOrdenManto() {
		IOrdenMantenimientoDafDosDao iDao = new OrdenMantenimientoDafDosDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimientoDafDos();
	}

	public void borrarOrdenManto() {
		IOrdenMantenimientoDafDosDao iDao = new OrdenMantenimientoDafDosDaoImpl();
		iDao.borrarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimientoEditar = new OrdenMantenimientoDafDos();
	}

	public void borrarVoBo() {
		ILimpiezaDafDosDao iDao = new LimpiezaDafDosDaoImpl();
		iDao.borrarVoBo(folioPrepDaf, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaDafDosDao iDao = new LimpiezaDafDosDaoImpl();
		iDao.agregarVoBo(folioPrepDaf, noLimpiezaVoBo, us.getIdUsuario());
	}

	public List<FolioPreparacionDafDos> getListaFolioDafDos() {
		IFolioPreparacionDafDosDao lDao = new FolioPreparacionDafDosDaoImpl();
		listaFolioDafDos = lDao.listaFolioDafDos(folioPrepDaf);
		return listaFolioDafDos;
	}

	public void primera() {
		IFolioPreparacionDafDosDao fDao = new FolioPreparacionDafDosDaoImpl();
		FolioPreparacionDafDos f = new FolioPreparacionDafDos();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparacionDafDosDao folioDao = new FolioPreparacionDafDosDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparacionDafDosDao folioPrepDao = new FolioPreparacionDafDosDaoImpl();
			this.folioPrepDaf = folioPrepDao.folioDafDosActual(fecha);
			getListarRegistroTurnos();
			getLimpiezaDafDos();
			getListaOrdenManto();
			getListaFolioDafDos();
		}
	}

	public List<Integer> getListaLimpiezas() throws SQLException {
		ILimpiezaDafDosDao lDao = new LimpiezaDafDosDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepDaf);
		return listaLimpiezas;
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<DafDos> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparacionDafDosDao fDao = new FolioPreparacionDafDosDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparacionDafDosDao folioPrepDao = new FolioPreparacionDafDosDaoImpl();
				this.folioPrepDaf = folioPrepDao.folioDafDosActual(fecha);
			}

		}
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaDaf(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getLimpiezaDafDos();
		getListaOrdenManto();
		getListaFolioDafDos();

	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		IDafDosDao validaDao = new DafDosDaoImpl();
		validaDao.actualizarManto(folioPrepDaf);

		IOrdenMantenimientoDafDosDao iDao = new OrdenMantenimientoDafDosDaoImpl();

		FolioPreparacionDafDos f = new FolioPreparacionDafDos();
		f.setIdFolioPrep(folioPrepDaf);
		ordenMantenimiento.setFolioPreparacionDafDos(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimientoDafDos();
	}

	public void obtenerObservacion() {
		for (int i = 0; i < listaFolioDafDos.size(); i++) {
			folioPreparacionDafDos.setObservaciones(listaFolioDafDos.get(i).getObservaciones());
		}
	}

	public void borrarTurnos() {
		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		rDao.borrarRegistroTurno(registroTurnosEditar);
	}

	public void guardarObservaciones() {
		IFolioPreparacionDafDosDao fDao = new FolioPreparacionDafDosDaoImpl();
		fDao.guardarObservacion(folioPrepDaf, folioPreparacionDafDos.getObservaciones());
		folioPreparacionDafDos = new FolioPreparacionDafDos();
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
	
	
	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteEsterilizadores reporte = new ReporteEsterilizadores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/votator_rep_b.jasper");
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

		ruta = servletContext.getRealPath("/REP/votator_rep_b.jasper");

		reporte.getReporte(ruta, fec, folioFechaRep);

		FacesContext.getCurrentInstance().responseComplete();

	}

}
