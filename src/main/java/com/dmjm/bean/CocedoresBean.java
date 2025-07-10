package com.dmjm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.ICocedoresDao;
import com.dmjm.dao.IFolioCocedoresDao;
import com.dmjm.dao.IFolioPreparcionCocedoresDao;
import com.dmjm.dao.ILimpiezaDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IOrdenMantoDao;
import com.dmjm.dao.IPurgasDao;
import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.dao.ITurnosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.CocedoresDaoImpl;
import com.dmjm.impl.FolioPreparacionCocedoresDaoImpl;
import com.dmjm.impl.FoliosCocedoresDaoImpl;
import com.dmjm.impl.LimpiezaDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.OrdenMantoDaoImpl;
import com.dmjm.impl.PurgasDaoImpl;
import com.dmjm.impl.RegistroTurnoDaoImpl;
import com.dmjm.impl.TurnosDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Cocedores;
import com.dmjm.model.FolioPreparacionCocedores;
import com.dmjm.model.Limpieza;
import com.dmjm.model.Operador;
import com.dmjm.model.OrdenMantenimiento;
import com.dmjm.model.Purgas;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.model.Turnos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteCocedores;

@Named("cocedoresBean")
@ViewScoped
public class CocedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CocedoresBean.class.getName());
	private List<Cocedores> listaCocedores;
	private Cocedores cocedores;
	private Cocedores cocedoresEditar;
	private Date fecha;
	private int folioFecha;
	private int folioPrepCocedor;
	private List<Cocedores> listaFiltroPromedioCocedores;

	private List<OrdenMantenimiento> listaOrdenManto;
	private OrdenMantenimiento ordenMantenimiento;
	private OrdenMantenimiento ordenMantenimientoEditar;

	private List<Limpieza> listaLimpieza;
	private Limpieza limpieza;
	private Limpieza limpiezaEditar;

	private List<Purgas> listaPurgas;
	private Purgas purgas;
	private Purgas purgasEditar;

	private String radioButton;

	private Date fechaFiltro;

	private String filterTurno;
	private String filterUsuario;
	private String filterOperador;
	private RegistroTurnos registroTurnos;
	private RegistroTurnos registroTurnosEditar;
	private List<RegistroTurnos> listarRegistroTurnos;

	private Operador operador;
	private Operador operadorEditar;
	private List<Operador> listaOperadores;

	private String[] selectedProcess;

	private String[] selectedProcess2;
	private List<String> procesos;

	private List<String> cocedorNo;

	private String cocedorSeleccionado;

	private List<Integer> listaLimpiezas;

	private int noLimpiezaSeleccionadaBorrar;
	private int noLimpiezaVoBo;

	public CocedoresBean() {

	}

	@PostConstruct
	public void init() throws SQLException {
		listaCocedores = new ArrayList<Cocedores>();
		cocedores = new Cocedores();
		cocedoresEditar = new Cocedores();
		listaFiltroPromedioCocedores = new ArrayList<>();

		listaOrdenManto = new ArrayList<>();
		ordenMantenimiento = new OrdenMantenimiento();
		ordenMantenimientoEditar = new OrdenMantenimiento();

		listaLimpieza = new ArrayList<>();
		limpieza = new Limpieza();
		limpiezaEditar = new Limpieza();

		listaPurgas = new ArrayList<>();
		purgas = new Purgas();
		purgasEditar = new Purgas();

		registroTurnos = new RegistroTurnos();
		listarRegistroTurnos = new ArrayList<>();
		registroTurnosEditar = new RegistroTurnos();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		primera();
		getListarRegistroTurnos();
		getListaLimpieza();
		getListaPurgas();
		getListaOrdenManto();

		procesos = new ArrayList<>();
		procesos.add("Cocedor 1");
		procesos.add("Cocedor 2");
		procesos.add("Cocedor 3");
		procesos.add("Cocedor 4");
		procesos.add("Cocedor 5");
		procesos.add("Cocedor 6");
		procesos.add("Cocedor 7");
		procesos.add("Cocedor 8");
		procesos.add("Cocedor 9");
		procesos.add("Cocedor 10");

		listaLimpiezas = new ArrayList<>();

	}

	public String getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}

	public Cocedores getCocedores() {
		return cocedores;
	}

	public void setCocedores(Cocedores cocedores) {
		this.cocedores = cocedores;
	}

	public Cocedores getCocedoresEditar() {
		return cocedoresEditar;
	}

	public void setCocedoresEditar(Cocedores cocedoresEditar) {
		this.cocedoresEditar = cocedoresEditar;
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

	public int getFolioPrepCocedor() {
		return folioPrepCocedor;
	}

	public Date getFechaFiltro() {
		return fechaFiltro;
	}

	public void setFechaFiltro(Date fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	public void setFolioPrepCocedor(int folioPrepCocedor) {
		this.folioPrepCocedor = folioPrepCocedor;
	}

	public void setFolioFecha(int folioFecha) {
		this.folioFecha = folioFecha;
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

	public RegistroTurnos getRegistroTurnos() {
		return registroTurnos;
	}

	public void setRegistroTurnos(RegistroTurnos registroTurnos) {
		this.registroTurnos = registroTurnos;
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

	public String[] getSelectedProcess() {
		return selectedProcess;
	}

	public void setSelectedProcess(String[] selectedProcess) {
		this.selectedProcess = selectedProcess;
	}

	public String[] getSelectedProcess2() {
		return selectedProcess2;
	}

	public void setSelectedProcess2(String[] selectedProcess2) {
		this.selectedProcess2 = selectedProcess2;
	}

	public List<String> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<String> procesos) {
		this.procesos = procesos;
	}

	public List<String> getCocedorNo() {
		return cocedorNo;
	}

	public void setCocedorNo(List<String> cocedorNo) {
		this.cocedorNo = cocedorNo;
	}

	public String getCocedorSeleccionado() {
		return cocedorSeleccionado;
	}

	public void setCocedorSeleccionado(String cocedorSeleccionado) {
		this.cocedorSeleccionado = cocedorSeleccionado;
	}

	public List<Integer> getListaLimpiezas() throws SQLException {

		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		listaLimpiezas = lDao.noLimpieza(folioPrepCocedor);
		return listaLimpiezas;
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

	public List<RegistroTurnos> getListarRegistroTurnos() {

		IRegistroTurnosDao rDao = new RegistroTurnoDaoImpl();
		listarRegistroTurnos = rDao.listaRegistroTurnos(fecha);
		IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
		this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
		return listarRegistroTurnos;
	}

	public String getFilterOperador() {
		return filterOperador;
	}

	public void setFilterOperador(String filterOperador) {
		this.filterOperador = filterOperador;
	}

	public RegistroTurnos getRegistroTurnosEditar() {
		return registroTurnosEditar;
	}

	public void setRegistroTurnosEditar(RegistroTurnos registroTurnosEditar) {
		this.registroTurnosEditar = registroTurnosEditar;
	}

	public List<Cocedores> getListaCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();
		if (fechaFiltro != null) {
			listaCocedores = cDao.listaPorFechaCocedores(fechaFiltro);
			for (int i = 0; i < 1; i++) {
				folioFecha = listaCocedores.get(i).getFolioCocedor();
				fecha = listaCocedores.get(i).getFecha();
			}

		} else {
			listaCocedores = cDao.listaCocedores();
		}

		return listaCocedores;
	}

	public List<Cocedores> getListaFiltroPromedioCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();
		listaFiltroPromedioCocedores = cDao.listaFiltroCocedores();
		return listaFiltroPromedioCocedores;
	}

	public OrdenMantenimiento getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public OrdenMantenimiento getOrdenMantenimientoEditar() {
		return ordenMantenimientoEditar;
	}

	public void setOrdenMantenimientoEditar(OrdenMantenimiento ordenMantenimientoEditar) {
		this.ordenMantenimientoEditar = ordenMantenimientoEditar;
	}

	public Limpieza getLimpieza() {

		return limpieza;
	}

	public void setLimpieza(Limpieza limpieza) {
		this.limpieza = limpieza;
	}

	public Limpieza getLimpiezaEditar() {
		if (Objects.nonNull(limpiezaEditar) && "ENJUAGUE".equals(limpiezaEditar.getProceso())) {
			limpiezaEditar.setQuimico("AGUA");
		}

		return limpiezaEditar;
	}

	public void setLimpiezaEditar(Limpieza limpiezaEditar) {
		this.limpiezaEditar = limpiezaEditar;
	}

	public Purgas getPurgas() {
		return purgas;
	}

	public void setPurgas(Purgas purgas) {
		this.purgas = purgas;
	}

	public Purgas getPurgasEditar() {
		if (purgasEditar != null && purgasEditar.getNoCocedor() != null) {
			selectedProcess = purgasEditar.getNoCocedor().split(", ");
		}

		return purgasEditar;
	}

	public void setPurgasEditar(Purgas purgasEditar) {
		this.purgasEditar = purgasEditar;
	}

	public List<OrdenMantenimiento> getListaOrdenManto() {
		IOrdenMantoDao oDao = new OrdenMantoDaoImpl();
		IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
		this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
		listaOrdenManto = oDao.listaOrdenManto(folioPrepCocedor);
		return listaOrdenManto;
	}

	public List<Limpieza> getListaLimpieza() {
		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
		this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
		listaLimpieza = lDao.listarLimpieza(folioPrepCocedor);
		return listaLimpieza;
	}

	public List<Purgas> getListaPurgas() {
		IPurgasDao pDao = new PurgasDaoImpl();
		IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
		this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
		listaPurgas = pDao.listaPurgas(folioPrepCocedor);
		return listaPurgas;
	}

	public void guardarCocedores() {

		String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
				"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
				"5:00", "6:00", "PROM." };

		ICocedoresDao cDao = new CocedoresDaoImpl();

		cocedores = new Cocedores();

		// **FOLIO_COCEDORES**//
		int year = 0;
		int folio = 0;
		year = LocalDate.now().getYear();
		IFolioCocedoresDao folDao = new FoliosCocedoresDaoImpl();
		folio = folDao.buscarFolio(year);

		// **FOLIO_PREPARACION_COCEDORES**//
		IFolioPreparcionCocedoresDao fDao = new FolioPreparacionCocedoresDaoImpl();
		FolioPreparacionCocedores fpc = new FolioPreparacionCocedores();
		fpc.setIdFolioPrep(fDao.returnIDGuardarFolio(folio));

		for (String lista : listaHora) {
			cocedores.setFolioCocedor(folio);
			cocedores.setHoraLimitesEspecificos(lista);
			cocedores.setFolioPreparacionCocedores(fpc);
			cocedores.setFecha(new Date());
			cDao.guardarCocedores(cocedores);
			cocedores = new Cocedores();
		}
		// **ACTUALIZAR FOLIO_COCEDORES**//
		IFolioCocedoresDao folioDao = new FoliosCocedoresDaoImpl();
		folioDao.actualizarFolio(year, folio);

		String script = "setTimeout(function() { window.location.href='Cocedores.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);

	}

	public void actualizarCocedores() throws SQLException {
		ICocedoresDao cDao = new CocedoresDaoImpl();

		String oper = cocedoresEditar.getOperacion().replaceAll("\\s+", "");
		cocedoresEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		// SE VALIDA CUANDO NO SEA NULL
		// BigDecimal ph =
		// Optional.ofNullable(cocedoresEditar.getPh()).orElse(BigDecimal.ZERO);

//		if (ph.doubleValue() > 0) {
//			if (ph.doubleValue() < 2.7 || ph.doubleValue() > 4.0) {
//				LOGGER.warn("VALIDACIÓN DE PH: " + ph.doubleValue());
//				String info = "El Rango de PH debe estar entre 2.7 y 4.0";
//				PrimeFaces.current()
//						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
//								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
//								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
//			} else {
//
//				if (Boolean.TRUE.equals(cocedoresEditar.getEstadoAR())) {
//					cocedoresEditar.setEstadoA("X");
//					cocedoresEditar.setEstadoR(null);
//				}
//
//				if (Boolean.FALSE.equals(cocedoresEditar.getEstadoAR())) {
//					cocedoresEditar.setEstadoR("X");
//					cocedoresEditar.setEstadoA(null);
//				}
//
//				// **ACTUALIZAR EL PROMEDIO DE LOS COCEDORES POR FILA, CAMPO CONCENTRADO**//
//				ICocedoresDao actualizaPromFilaDao = new CocedoresDaoImpl();
//
//				cDao.actualizarCocedores(cocedoresEditar);
//
//				actualizaPromFilaDao.actualizarPromediosPorFila(cocedoresEditar.getIdCocedor());
//
//				actualizarPromedios(cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());
//
//				if (cocedoresEditar.getHoraLimitesEspecificos().equals("7:00")) {
//					ICocedoresDao aDao = new CocedoresDaoImpl();
//					aDao.actualizarCocedoresPromedio(cocedoresEditar.getOperacion(),
//							cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());
//				}
//				cocedoresEditar = new Cocedores();
//				PrimeFaces.current().executeScript("PF('dlgEditar').hide();");
//			}
//		} else {

		if (Boolean.TRUE.equals(cocedoresEditar.getEstadoAR())) {
			cocedoresEditar.setEstadoA("X");
			cocedoresEditar.setEstadoR(null);
		}

		if (Boolean.FALSE.equals(cocedoresEditar.getEstadoAR())) {
			cocedoresEditar.setEstadoR("X");
			cocedoresEditar.setEstadoA(null);
		}

		// **ACTUALIZAR EL PROMEDIO DE LOS COCEDORES POR FILA, CAMPO CONCENTRADO**//
		ICocedoresDao actualizaPromFilaDao = new CocedoresDaoImpl();

		cDao.actualizarCocedores(cocedoresEditar);

		actualizaPromFilaDao.actualizarPromediosPorFila(cocedoresEditar.getIdCocedor());

		actualizarPromedios(cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());

		if (cocedoresEditar.getHoraLimitesEspecificos().equals("7:00")) {
			ICocedoresDao aDao = new CocedoresDaoImpl();
			aDao.actualizarCocedoresPromedio(cocedoresEditar.getOperacion(),
					cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());
		}
		cocedoresEditar = new Cocedores();
		PrimeFaces.current().executeScript("PF('dlgEditar').hide();");
	}

//	}

	public List<Cocedores> obtenerElementosDePagina(int pagina) {
		int elementosPorPagina = 25; // Número de elementos por página
		int inicio = pagina * elementosPorPagina;
		int fin = Math.min(inicio + elementosPorPagina, listaCocedores.size());

		// Retornar la sublista correspondiente a la página solicitada
		return listaCocedores.subList(inicio, fin);
	}

	public void onPageChange(PageEvent event) {
		int nuevaPagina = event.getPage();

		// Obtener la lista de elementos en la página actual
		List<Cocedores> paginaActual = obtenerElementosDePagina(nuevaPagina);

		// Obtener la fecha del primer elemento de la nueva página
		if (!paginaActual.isEmpty()) {
			// **FECHA PÁGINA ACTUAL**//
			this.fecha = paginaActual.get(0).getFecha();
			// **FOLIO DE LA FECHA ACTUAL**//
			if (this.fecha != null) {
				IFolioPreparcionCocedoresDao fDao = new FolioPreparacionCocedoresDaoImpl();
				this.folioFecha = fDao.fechaFolioActual(fecha);
				IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
				this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
			}

		}
	}

	public void primera() {
		IFolioPreparcionCocedoresDao fDao = new FolioPreparacionCocedoresDaoImpl();
		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();

		// **FOLIO DE LA FECHA ACTUAL**//
		if (this.fecha != null) {
			IFolioPreparcionCocedoresDao folioDao = new FolioPreparacionCocedoresDaoImpl();
			this.folioFecha = folioDao.fechaFolioActual(fecha);
			IFolioPreparcionCocedoresDao folioPrepDao = new FolioPreparacionCocedoresDaoImpl();
			this.folioPrepCocedor = folioPrepDao.folioCocedorActual(fecha);
			getListarRegistroTurnos();
			getListaLimpieza();
			getListaPurgas();
			getListaOrdenManto();
		}

	}

	// **ACTUALIZAR PROMEDIOS**//
	public void actualizarPromedios(int folio) {
		ICocedoresDao grados = new CocedoresDaoImpl();
		grados.actualizarPromedioGrados(folio);

		ICocedoresDao flujo = new CocedoresDaoImpl();
		flujo.actualizarPromedioFlujo(folio);

		ICocedoresDao ph = new CocedoresDaoImpl();
		ph.actualizarPromedioPH(folio);

		ICocedoresDao ntu = new CocedoresDaoImpl();
		ntu.actualizarPromedioNTU(folio);

		ICocedoresDao calcios = new CocedoresDaoImpl();
		calcios.actualizarPromedioCalcios(folio);

		ICocedoresDao viscocidad = new CocedoresDaoImpl();
		viscocidad.actualizarPromedioViscocidad(folio);

		ICocedoresDao condensacion = new CocedoresDaoImpl();
		condensacion.actualizarPromedioCondensacion(folio);

		ICocedoresDao concentrado = new CocedoresDaoImpl();
		concentrado.actualizarPromedioConcentrado(folio);

		ICocedoresDao tempC01 = new CocedoresDaoImpl();
		tempC01.actualizarPromedioTempCoc01(folio);
		ICocedoresDao concC01 = new CocedoresDaoImpl();
		concC01.actualizarPromedioConCoc01(folio);

		ICocedoresDao tempC02 = new CocedoresDaoImpl();
		tempC02.actualizarPromedioTempCoc02(folio);
		ICocedoresDao concC02 = new CocedoresDaoImpl();
		concC02.actualizarPromedioConCoc02(folio);

		ICocedoresDao tempC03 = new CocedoresDaoImpl();
		tempC03.actualizarPromedioTempCoc03(folio);
		ICocedoresDao concC03 = new CocedoresDaoImpl();
		concC03.actualizarPromedioConCoc03(folio);

		ICocedoresDao tempC04 = new CocedoresDaoImpl();
		tempC04.actualizarPromedioTempCoc04(folio);
		ICocedoresDao concC04 = new CocedoresDaoImpl();
		concC04.actualizarPromedioConCoc04(folio);

		ICocedoresDao tempC05 = new CocedoresDaoImpl();
		tempC05.actualizarPromedioTempCoc05(folio);
		ICocedoresDao concC05 = new CocedoresDaoImpl();
		concC05.actualizarPromedioConCoc05(folio);

		ICocedoresDao tempC06 = new CocedoresDaoImpl();
		tempC06.actualizarPromedioTempCoc06(folio);
		ICocedoresDao concC06 = new CocedoresDaoImpl();
		concC06.actualizarPromedioConCoc06(folio);

		ICocedoresDao tempC07 = new CocedoresDaoImpl();
		tempC07.actualizarPromedioTempCoc07(folio);
		ICocedoresDao concC07 = new CocedoresDaoImpl();
		concC07.actualizarPromedioConCoc07(folio);

		ICocedoresDao tempC08 = new CocedoresDaoImpl();
		tempC08.actualizarPromedioTempCoc08(folio);
		ICocedoresDao concC08 = new CocedoresDaoImpl();
		concC08.actualizarPromedioConCoc08(folio);

		ICocedoresDao tempC09 = new CocedoresDaoImpl();
		tempC09.actualizarPromedioTempCoc09(folio);
		ICocedoresDao concC09 = new CocedoresDaoImpl();
		concC09.actualizarPromedioConCoc09(folio);

		ICocedoresDao tempC10 = new CocedoresDaoImpl();
		tempC10.actualizarPromedioTempCoc10(folio);
		ICocedoresDao concC10 = new CocedoresDaoImpl();
		concC10.actualizarPromedioConCoc10(folio);

	}

	// **LIMPIEZA**//
	public void guardarLimpieza() {
		String datosLimpieza[] = { "DESCONCETRACIÓN", "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE",
				"ENJUAGUE" };

		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f.setIdFolioPrep(folioPrepCocedor);

		// VALIDAR SI HAY LIMPIEZA PARA ASIGNAR EL CONSECUTIVO
		ILimpiezaDao validaDao = new LimpiezaDaoImpl();
		int noDeLimpieza = 0;
		noDeLimpieza = validaDao.validarNoLimpieza(folioPrepCocedor);

		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		for (String l : datosLimpieza) {
			limpieza.setNoLimpieza(noDeLimpieza);
			limpieza.setVoBo("PENDIENTE");
			limpieza.setFolioPreparacionCocedores(f);
			limpieza.setProceso(l);
			limpieza.setNoCocedor(cocedorSeleccionado);
			lDao.guardarLimpieza(limpieza);
			limpieza = new Limpieza();
		}

		cocedorSeleccionado = null;

	}

	public void actualizarLimpieza() {
		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		lDao.actualizarLimpieza(limpiezaEditar);
		limpieza = new Limpieza();
	}

	// **PURGAS**//
	public void guardarPurgas() {
		IPurgasDao pDao = new PurgasDaoImpl();
		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f.setIdFolioPrep(folioPrepCocedor);

		String procesosGuardados = String.join(", ", selectedProcess);

		purgas.setNoCocedor(procesosGuardados);
		purgas.setFolioPreparacionCocedores(f);
		pDao.guardaPurgas(purgas);
		selectedProcess = null;
		purgas = new Purgas();
	}

	public void actualizarPurgas() {
		IPurgasDao pDao = new PurgasDaoImpl();
		String procesosGuardados = String.join(", ", selectedProcess);
		purgasEditar.setNoCocedor(procesosGuardados);
		pDao.actualizarPurgas(purgasEditar);
		selectedProcess = null;
		purgas = new Purgas();
	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
		// validación de mantenimiento
		ICocedoresDao validaDao = new CocedoresDaoImpl();

		validaDao.actualizarManto(folioPrepCocedor);

		IOrdenMantoDao iDao = new OrdenMantoDaoImpl();
		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f.setIdFolioPrep(folioPrepCocedor);
		ordenMantenimiento.setFolioPreparacionCocedores(f);
		iDao.guardarOrdenManto(ordenMantenimiento);
		ordenMantenimiento = new OrdenMantenimiento();
	}

	public void actualizarOrdenManto() {
		IOrdenMantoDao iDao = new OrdenMantoDaoImpl();
		iDao.actualizarOrdenManto(ordenMantenimientoEditar);
		ordenMantenimiento = new OrdenMantenimiento();
	}

	// **FILTRAR POR FECHA**//
	public void filtrarPorFecha() {
		getListaCocedores(); // CAMBIAR PARAMETROS PARA EL REPORTE,
		getListarRegistroTurnos();
		getListaLimpieza();
		getListaPurgas();
		getListaOrdenManto();
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
		return tDao.completeOperador(nombre, "Cocedores");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Cocedores");
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
		rt.setDescProceso("COCEDORES");

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

	public void visualizarReporte() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteCocedores reporte = new ReporteCocedores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ruta = servletContext.getRealPath("/REP/cocedores_rep.jasper");
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

		ruta = servletContext.getRealPath("/REP/cocedores_rep.jasper");

		reporte.getReporte(ruta, fec, folioFechaRep);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public void visualizarReporteExcel() throws SQLException {
		@SuppressWarnings("unused")

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		ReporteCocedores reporte = new ReporteCocedores();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("/REP/cocedores_rep_excel.jasper");

		// Llamar a la versión que exporta a Excel
		reporte.getReporteExcel(ruta, fecha.toString(), folioPrepCocedor, folioFecha);

		FacesContext.getCurrentInstance().responseComplete();

	}

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorCocedores();
		return listaOperadores;
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Cocedores");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
	}

	public void onToggleSelect(ToggleSelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Toggled: " + event.isSelected());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onItemSelect(@SuppressWarnings("rawtypes") SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item selected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onItemUnselect(@SuppressWarnings("rawtypes") UnselectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item unselected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onToggleSelect2(ToggleSelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Toggled: " + event.isSelected());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onItemSelect2(@SuppressWarnings("rawtypes") SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item selected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onItemUnselect2(@SuppressWarnings("rawtypes") UnselectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item unselected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void validarPH() {
		BigDecimal ph = cocedoresEditar.getPh();
		if (ph == null || ph.doubleValue() < 2.7 || ph.doubleValue() > 4.0) {
			String info = "El Rango de PH debe estar entre 2.7 y 4.0";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
							+ "  timer: 8000\n" + "})");
		}
	}

	public void deleteLimpieza() {
		ILimpiezaDao iDao = new LimpiezaDaoImpl();
		iDao.borrarLimpieza(folioPrepCocedor, noLimpiezaSeleccionadaBorrar);
	}

	public void borrarVoBo() {
		ILimpiezaDao iDao = new LimpiezaDaoImpl();
		iDao.borrarVoBo(folioPrepCocedor, noLimpiezaVoBo);
	}

	public void agregarVoBo() {
		ILimpiezaDao iDao = new LimpiezaDaoImpl();
		iDao.agregarVoBo(folioPrepCocedor, noLimpiezaVoBo);
	}

}
