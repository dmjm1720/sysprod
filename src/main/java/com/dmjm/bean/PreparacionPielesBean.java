package com.dmjm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

import com.dmjm.dao.IEntradasDao;
import com.dmjm.dao.IEtapasDao;
import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.dao.IFolioPreparacionDao;
import com.dmjm.dao.ILavadorasDao;
import com.dmjm.dao.ILavadorasEstatusOperacionDao;
import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.dao.IOperadorDao;
import com.dmjm.dao.IPreparacionPielesDao;
import com.dmjm.dao.IQuimicosDao;
import com.dmjm.dao.IResumenLavadorasDao;
import com.dmjm.dao.IResumenTraspasosDao;
import com.dmjm.dao.ISaldoFacturaDao;
import com.dmjm.dao.ITraspasoSilosDao;
import com.dmjm.dao.IUsuarioDao;
import com.dmjm.impl.EntradasDaoImpl;
import com.dmjm.impl.EtapasDaoImpl;
import com.dmjm.impl.FacturaPielesDaoImpl;
import com.dmjm.impl.FoliosPreparacionDaoImpl;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.impl.LavadorasEstatusOperacionDaoImpl;
import com.dmjm.impl.OperacionLavadorasDaoImpl;
import com.dmjm.impl.OperadorDaoImpl;
import com.dmjm.impl.PreparacionDaoImpl;
import com.dmjm.impl.QuimicosDaoImpl;
import com.dmjm.impl.ResumenLavadorasDaoImpl;
import com.dmjm.impl.ResumenTraspasoDaoImpl;
import com.dmjm.impl.SaldoFacturaDaoImpl;
import com.dmjm.impl.TraspasoDaoImpl;
import com.dmjm.impl.UsuarioDaoImpl;
import com.dmjm.model.Categorias;
import com.dmjm.model.Entradas;
import com.dmjm.model.Etapas;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.model.LavadorasEstatusOperacion;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.model.Operador;
import com.dmjm.model.PreparacionPieles;
import com.dmjm.model.Proveedores;
import com.dmjm.model.Quimicos;
import com.dmjm.model.ResumenLavadoras;
import com.dmjm.model.ResumenTraspasos;
import com.dmjm.model.SaldoFactura;
import com.dmjm.model.TraspasoSilos;
import com.dmjm.model.Usuarios;
import com.dmjm.util.ReporteLavadoras;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Named(value = "preparacionBean")
@ViewScoped
public class PreparacionPielesBean implements Serializable {

	private static final Logger LOGGER = LogManager.getLogger(PreciosBean.class.getName());
	private static final long serialVersionUID = 1L;
	private List<PreparacionPieles> listarPreparacion;
	private PreparacionPieles prePieles;
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private List<Etapas> listaDeEtapas;
	private List<Etapas> listaDeEtapas2;
	private List<Etapas> listaDeEtapas3;
	private List<Etapas> listaDeEtapas4;
	private List<Etapas> listaDeEtapas5;
	private List<Etapas> listaEtapasLavadorasChicas;

	private Etapas etapas;
	private Etapas etapasLavadorasChicas;
	private OperacionLavadoras operacionLavadoras;

	private List<FacturasPieles> listaFacturaPieles;
	private FacturasPieles facturasPieles;

	private Lavadoras lavadoras;

	private String filterLavadoras;

	private List<OperacionLavadoras> listaOperaciones;

	private int folio = 0;
	private int idPrep = 0;

	private String filterLavadora;
	private String filterLavadora2;

	private List<Integer> listaOperacionesDisponibles;

	private List<String> listarQuimicos;

	private List<String> listarLavadorasDisponibles;
	private List<String> listarLavadorasDisponibles2;

	private List<String> listaFacturasTolvas;

	private double saldoDisponibleParaAgregar = 0.0;

	private String[] selectedProcess;
	private String[] selectedLavadorasChicas;
	private String[] selectedLavadorasChicasTras;
	private List<String> procesos;

	private String estadoLavadora = "";

	private String noFacturaLavadorasChicas = "";
	private Double kilosLavadorasChicas = 0.0;
	private int noLavadorasChicas;

	private Double kg1 = 0.0;
	private Double kg2 = 0.0;
	private Double kg3 = 0.0;
	private Double kg4 = 0.0;
	private Double kg5 = 0.0;
	private Double kg6 = 0.0;

	private Proveedores proveedor;

	private Categorias categorias;

	private List<TraspasoSilos> listarTraspasos;
	private TraspasoSilos traspasoSilos;
	private TraspasoSilos traspasoSilosEditar;

	private List<String> listarLavadorasChicas;

	private int distribucionKilos;
	private int distribucionKilosSilos;
	private int distribucionKilosEncimar;
	private List<String> listarAsignacionKilosLavadorasChicas;

	private ResumenLavadoras resumenLavadoras;

	private Operador operador;
	private Operador operadorEditar;

	private String filterUsuario;
	private String filterOperador;
	private List<Operador> listaOperadores;

	private List<ResumenTraspasos> listarResumenTraspasos;
	private ResumenTraspasos resumenTraspasos;
	private ResumenTraspasos resumenTraspasosAct;
	private Etapas etapasEditarTraspaso;

	private List<LavadorasEstatusOperacion> listarLavadorasEstatusOperacion;
	private LavadorasEstatusOperacion estatusOperacion;

	private String filterOperacion;

	private List<String> listarOperaciones;

	private Integer folioOperacion;

	private List<Integer> listarLavadorasChicasDisponibles;

	private Double saldoPrevio;

	private PreparacionPieles observacionesEditar;

	@PostConstruct
	public void init() throws SQLException {
		listarPreparacion = new ArrayList<>();
		prePieles = new PreparacionPieles();
		listarPreparacion = new ArrayList<>();
		facturasPieles = new FacturasPieles();
		lavadoras = new Lavadoras();
		prePieles.setNoOperacion(buscarFolio());
		etapas = new Etapas();
		etapasLavadorasChicas = new Etapas();
		listaDeEtapas = new ArrayList<>();
		listaDeEtapas2 = new ArrayList<>();
		listaDeEtapas3 = new ArrayList<>();
		listaDeEtapas4 = new ArrayList<>();
		listaDeEtapas5 = new ArrayList<>();
		operacionLavadoras = new OperacionLavadoras();
		listaOperaciones = new ArrayList<>();
		listaOperacionesDisponibles = new ArrayList<>();
		listarQuimicos = new ArrayList<>();
		listarLavadorasDisponibles = new ArrayList<>();
		listarLavadorasDisponibles2 = new ArrayList<>();
		listaFacturasTolvas = new ArrayList<>();
		proveedor = new Proveedores();

		categorias = new Categorias();

		listarTraspasos = new ArrayList<>();
		traspasoSilos = new TraspasoSilos();
		traspasoSilosEditar = new TraspasoSilos();

		listarLavadorasChicas = new ArrayList<>();
		listarAsignacionKilosLavadorasChicas = new ArrayList<>();

		resumenLavadoras = new ResumenLavadoras();

		operador = new Operador();
		operadorEditar = new Operador();
		listaOperadores = new ArrayList<>();

		listarResumenTraspasos = new ArrayList<>();
		resumenTraspasos = new ResumenTraspasos();
		resumenTraspasosAct = new ResumenTraspasos();

		listarLavadorasEstatusOperacion = new ArrayList<>();
		estatusOperacion = new LavadorasEstatusOperacion();

		listaEtapasLavadorasChicas = new ArrayList<>();

		etapasEditarTraspaso = new Etapas();

		listarOperaciones = new ArrayList<>();
		listarLavadorasChicasDisponibles = new ArrayList<>();
		cargarLavadorasChicas();

		observacionesEditar = new PreparacionPieles();
	}

	public PreparacionPieles getObservacionesEditar() {
		return observacionesEditar;
	}

	public void setObservacionesEditar(PreparacionPieles observacionesEditar) {
		this.observacionesEditar = observacionesEditar;
	}

	public Integer getFolioOperacion() {
		return folioOperacion;
	}

	public void setFolioOperacion(Integer folioOperacion) {
		this.folioOperacion = folioOperacion;
	}

	public List<String> getListarOperaciones() throws SQLException {

		if (folio > 0) {
			ILavadorasDao lDao = new LavadorasDaoImpl();
			listarOperaciones = lDao.listaDeOperaciones(folio);
			return listarOperaciones;
		}

		return listarOperaciones;
	}

	public void obtenerListaLavadorasChicasDisponibles() {
		ILavadorasDao lDao = new LavadorasDaoImpl();

		List<Lavadoras> lista = new ArrayList<>();
		lista.clear();
		listarLavadorasChicasDisponibles.clear();
		if (folio > 0) {
			lista = lDao.listarLavodorasPorOperacion(folioOperacion);

			for (Lavadoras lavadoras : lista) {
				listarLavadorasChicasDisponibles.add(Integer.parseInt(lavadoras.getDescripcion()));
			}

		}
		getListarLavadorasChicasDisponibles();

	}

	public Double getSaldoPrevio() {
		return saldoPrevio;
	}

	public void setSaldoPrevio(Double saldoPrevio) {
		this.saldoPrevio = saldoPrevio;
	}

	public List<Integer> getListarLavadorasChicasDisponibles() {

		return listarLavadorasChicasDisponibles;
	}

	public void setListarLavadorasChicasDisponibles(List<Integer> listarLavadorasChicasDisponibles) {
		this.listarLavadorasChicasDisponibles = listarLavadorasChicasDisponibles;
	}

	public String getFilterOperacion() {
		return filterOperacion;
	}

	public void setFilterOperacion(String filterOperacion) {
		this.filterOperacion = filterOperacion;
	}

	public Etapas getEtapasEditarTraspaso() {
		return etapasEditarTraspaso;
	}

	public void setEtapasEditarTraspaso(Etapas etapasEditarTraspaso) {
		this.etapasEditarTraspaso = etapasEditarTraspaso;
	}

	public ResumenTraspasos getResumenTraspasosAct() {
		return resumenTraspasosAct;
	}

	public void setResumenTraspasosAct(ResumenTraspasos resumenTraspasosAct) {
		this.resumenTraspasosAct = resumenTraspasosAct;
	}

	public List<LavadorasEstatusOperacion> getListarLavadorasEstatusOperacion() {
		return listarLavadorasEstatusOperacion;
	}

	public void setListarLavadorasEstatusOperacion(List<LavadorasEstatusOperacion> listarLavadorasEstatusOperacion) {
		this.listarLavadorasEstatusOperacion = listarLavadorasEstatusOperacion;
	}

	public LavadorasEstatusOperacion getEstatusOperacion() {
		return estatusOperacion;
	}

	public void setEstatusOperacion(LavadorasEstatusOperacion estatusOperacion) {
		this.estatusOperacion = estatusOperacion;
	}

	public ResumenTraspasos getResumenTraspasos() {
		return resumenTraspasos;
	}

	public void setResumenTraspasos(ResumenTraspasos resumenTraspasos) {
		this.resumenTraspasos = resumenTraspasos;
	}

	public List<ResumenTraspasos> getListarResumenTraspasos() {
		return listarResumenTraspasos;
	}

	public int getDistribucionKilosEncimar() {
		return distribucionKilosEncimar;
	}

	public void setDistribucionKilosEncimar(int distribucionKilosEncimar) {
		this.distribucionKilosEncimar = distribucionKilosEncimar;
	}

	public int getDistribucionKilosSilos() {
		return distribucionKilosSilos;
	}

	public void setDistribucionKilosSilos(int distribucionKilosSilos) {
		this.distribucionKilosSilos = distribucionKilosSilos;
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

	public Etapas getEtapasLavadorasChicas() {
		return etapasLavadorasChicas;
	}

	public void setEtapasLavadorasChicas(Etapas etapasLavadorasChicas) {
		this.etapasLavadorasChicas = etapasLavadorasChicas;
	}

	public ResumenLavadoras getResumenLavadoras() {
		return resumenLavadoras;
	}

	public void setResumenLavadoras(ResumenLavadoras resumenLavadoras) {
		this.resumenLavadoras = resumenLavadoras;
	}

	public int getDistribucionKilos() {
		return distribucionKilos;
	}

	public void setDistribucionKilos(int distribucionKilos) {
		this.distribucionKilos = distribucionKilos;
	}

	public TraspasoSilos getTraspasoSilos() {
		return traspasoSilos;
	}

	public void setTraspasoSilos(TraspasoSilos traspasoSilos) {
		this.traspasoSilos = traspasoSilos;
	}

	public TraspasoSilos getTraspasoSilosEditar() {
		return traspasoSilosEditar;
	}

	public void setTraspasoSilosEditar(TraspasoSilos traspasoSilosEditar) {
		this.traspasoSilosEditar = traspasoSilosEditar;
	}

	public List<TraspasoSilos> getListarTraspasos() {
		ITraspasoSilosDao tDao = new TraspasoDaoImpl();
		listarTraspasos = tDao.listaTraspasos(idPrep);
		return listarTraspasos;
	}

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public PreparacionPieles getPrePieles() {
		return prePieles;
	}

	public void setPrePieles(PreparacionPieles prePieles) {
		this.prePieles = prePieles;
	}

	public List<PreparacionPieles> getListarPreparacion() {

		return listarPreparacion;
	}

	public void setListaOperaciones(List<OperacionLavadoras> listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	public List<FacturasPieles> getListaFacturaPieles() {

		return listaFacturaPieles;
	}

	public void setListaFacturaPieles(List<FacturasPieles> listaFacturaPieles) {
		this.listaFacturaPieles = listaFacturaPieles;
	}

	public FacturasPieles getFacturasPieles() {
		return facturasPieles;
	}

	public void setListarPreparacion(List<PreparacionPieles> listarPreparacion) {
		this.listarPreparacion = listarPreparacion;
	}

	public Lavadoras getLavadoras() {
		return lavadoras;
	}

	public void setLavadoras(Lavadoras lavadoras) {
		this.lavadoras = lavadoras;
	}

	public String getFilterLavadoras() {
		return filterLavadoras;
	}

	public void setFilterLavadoras(String filterLavadoras) {
		this.filterLavadoras = filterLavadoras;
	}

	public String getFilterLavadora2() {
		return filterLavadora2;
	}

	public void setFilterLavadora2(String filterLavadora2) {
		this.filterLavadora2 = filterLavadora2;
	}

	public Etapas getEtapas() {
		return etapas;
	}

	public void setEtapas(Etapas etapas) {
		this.etapas = etapas;
	}

	public OperacionLavadoras getOperacionLavadoras() {
		return operacionLavadoras;
	}

	public void setOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		this.operacionLavadoras = operacionLavadoras;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getIdPrep() {
		return idPrep;
	}

	public void setIdPrep(int idPrep) {
		this.idPrep = idPrep;
	}

	public List<OperacionLavadoras> getListaOperaciones() {

		return listaOperaciones;
	}

	public String getFilterLavadora() {
		return filterLavadora;
	}

	public void setFilterLavadora(String filterLavadora) {
		this.filterLavadora = filterLavadora;
	}

	public double getSaldoDisponibleParaAgregar() {
		return saldoDisponibleParaAgregar;
	}

	public void setSaldoDisponibleParaAgregar(double saldoDisponibleParaAgregar) {
		this.saldoDisponibleParaAgregar = saldoDisponibleParaAgregar;
	}

	public List<String> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<String> procesos) {
		this.procesos = procesos;
	}

	public String[] getSelectedProcess() {
		return selectedProcess;
	}

	public void setSelectedProcess(String[] selectedProcess) {
		this.selectedProcess = selectedProcess;
	}

	public String[] getSelectedLavadorasChicas() {
		return selectedLavadorasChicas;
	}

	public void setSelectedLavadorasChicas(String[] selectedLavadorasChicas) {
		this.selectedLavadorasChicas = selectedLavadorasChicas;
	}

	public String[] getSelectedLavadorasChicasTras() {
		return selectedLavadorasChicasTras;
	}

	public void setSelectedLavadorasChicasTras(String[] selectedLavadorasChicasTras) {
		this.selectedLavadorasChicasTras = selectedLavadorasChicasTras;
	}

	public String getEstadoLavadora() {
		return estadoLavadora;
	}

	public void setEstadoLavadora(String estadoLavadora) {
		this.estadoLavadora = estadoLavadora;
	}

	public String getNoFacturaLavadorasChicas() {
		return noFacturaLavadorasChicas;
	}

	public void setNoFacturaLavadorasChicas(String noFacturaLavadorasChicas) {
		this.noFacturaLavadorasChicas = noFacturaLavadorasChicas;
	}

	public Double getKilosLavadorasChicas() {
		return kilosLavadorasChicas;
	}

	public void setKilosLavadorasChicas(Double kilosLavadorasChicas) {
		this.kilosLavadorasChicas = kilosLavadorasChicas;
	}

	public int getNoLavadorasChicas() {
		return noLavadorasChicas;
	}

	public void setNoLavadorasChicas(int noLavadorasChicas) {
		this.noLavadorasChicas = noLavadorasChicas;
	}

	public Double getKg1() {
		return kg1;
	}

	public void setKg1(Double kg1) {
		this.kg1 = kg1;
	}

	public Double getKg2() {
		return kg2;
	}

	public void setKg2(Double kg2) {
		this.kg2 = kg2;
	}

	public Double getKg3() {
		return kg3;
	}

	public void setKg3(Double kg3) {
		this.kg3 = kg3;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public List<String> getListarAsignacionKilosLavadorasChicas() {
		return listarAsignacionKilosLavadorasChicas;
	}

	public void setListarAsignacionKilosLavadorasChicas(List<String> listarAsignacionKilosLavadorasChicas) {
		this.listarAsignacionKilosLavadorasChicas = listarAsignacionKilosLavadorasChicas;
	}

	public void onToggleSelect(ToggleSelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Toggled: " + event.isSelected());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

	}

	public void onItemSelect(@SuppressWarnings("rawtypes") SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item selected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		calcularKilos();

	}

	public void onToggleSelectCK() {
		calcularKilos();
	}

	public void onItemSelectCK() {
		calcularKilos();
	}

	public void onItemUnselect(@SuppressWarnings("rawtypes") UnselectEvent event) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item unselected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		calcularKilos();

	}

	// **OBTENER LA LISTA DE LAS OPERACIONES DISPONIBLES**//
	public List<Integer> getListaOperacionesDisponibles() {

		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		List<PreparacionPieles> listar = new ArrayList<>();

		listar = pDao.listaPreparacion();
		listaOperacionesDisponibles.clear();
		for (PreparacionPieles preparacionPieles : listar) {
			listaOperacionesDisponibles.add(preparacionPieles.getNoOperacion());
		}

		return listaOperacionesDisponibles;
	}

	// **OBTENER LA LISTA DE LAVADORAS CHICAS DISPONIBLES**//
	public List<String> getListarLavadorasChicas() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		List<Lavadoras> listar = new ArrayList<>();
		listar.clear();
		listarLavadorasChicas.clear();
		listar = lDao.listaLavadorasChicas();

		for (Lavadoras lavadoras : listar) {
			listarLavadorasChicas.add(lavadoras.getNombre() + " " + lavadoras.getDescripcion());
		}

		return listarLavadorasChicas;
	}

	// **OBTENER LA LISTA DE LAS LAVADORAS DISPONIBLES**//
	public List<String> getListarLavadorasDisponibles() {
		ILavadorasDao lava = new LavadorasDaoImpl();
		List<Lavadoras> listar = lava.listarLavadorasDisponibles();
		listarLavadorasDisponibles.clear();
		for (Lavadoras l : listar) {
			listarLavadorasDisponibles.add(l.getDescripcion());
		}
		return listarLavadorasDisponibles;
	}

	// **OBTENER LA LISTA DE LAS LAVADORAS DISPONIBLES**//
	public List<String> getListarLavadorasDisponibles2() {
		ILavadorasDao lava = new LavadorasDaoImpl();
		List<Lavadoras> listar = lava.listarLavadorasDisponibles();
		listarLavadorasDisponibles2.clear();
		for (Lavadoras l : listar) {
			listarLavadorasDisponibles2.add(l.getNombre());
		}
		return listarLavadorasDisponibles2;
	}

	// **OBTENER LA LISTA DE LOS QUÍMICOS**//
	public List<String> getListarQuimicos() {
		IQuimicosDao qDao = new QuimicosDaoImpl();
		List<Quimicos> listar = qDao.listarQuimicos();
		listarQuimicos.clear();
		for (Quimicos q : listar) {
			listarQuimicos.add(q.getNombre());
		}
		return listarQuimicos;
	}

	// **OBTENER LA LISTA DE LAS FACTURAS DE LAS TOLVAS**//

	public List<String> getListaFacturasTolvas() {
		IEntradasDao eDao = new EntradasDaoImpl();
		listaFacturasTolvas.clear();

		List<Entradas> listar = new ArrayList<>();
		listar = eDao.listarEntradasFactura();
		for (Entradas e : listar) {
			listaFacturasTolvas.add(e.getTicketBasculaToluca());
		}

		return listaFacturasTolvas;
	}

	// **GUARDAR LA PREPARACIÓN**//
	public void guardarPreparacion() throws SQLException, ParseException {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		prePieles.setIdPreparacion(buscarFolio());
		prePieles.setEstado("Carga");
		int idPre = 0;

		idPre = pDao.guardarPreparacionPieles(prePieles);

		PreparacionPieles pPieles = new PreparacionPieles();
		pPieles.setIdPreparacion(idPre);
		facturasPieles.setPreparacionPieles(pPieles);

		PreparacionPieles pieles = new PreparacionPieles();
		pieles.setIdPreparacion(idPre);
		operacionLavadoras.setPreparacionPieles(pieles);

		// PRIMERA ETAPA
		String[] listaEtapas = { "Carga", "Depilado", "Lavado de Depilado", "Blanqueo", "Lavadas de Blanqueo",
				"Proceso Enzimático", "Lavadas Enzimáticas", "Pre Acidulación", "Acidulación", "Control 1", "Control 2",
				"Control 3", "Control 4", "Control 5", "Control 6", "Control 7", "Control 8" };
		// SEGUNDA ETAPA
		String[] listaEtapaBlanqueo = { "Lavadas Finales (1)", "Lavadas Finales (2)", "Lavadas Finales (3)",
				"Lavadas Finales (4)", "Lavadas Finales (5)" };
		// TERCERA ETAPA APARTADO 1
//		String[] listaEtapaLavadorasChicas = { "Lavadas Finales -1-", "Lavadas Finales -2-", "Lavadas Finales -3-",
//				"Lavadas Finales -4-", "Lavadas Finales -5-", "Lavadas Finales -6-" };
		// TERCERA ETAPA APARTADO 2
//		String[] listaEtapaLavadorasChicas2 = { "Lavadora Chica 1", "Lavadora Chica 2", "Lavadora Chica 3",
//				"Lavadora Chica 4" };
//		// TERCERA ETAPA APARTADO 3
//		String[] listaEtapaLavadorasChicas3 = { "Lavadora Chica 1", "Lavadora Chica 2", "Lavadora Chica 3",
//				"Lavadora Chica 4" };
		// CUARTA ETAPA
		String[] listaSilos = { "Silos" };
		List<Etapas> listaAgregarEtapas = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // tuFechaBase es un Date;

		//Date fecInicio = calendar.getTime();

		Calendar calendar1 = Calendar.getInstance();
		Date horaIni = calendar1.getTime();

		// GUARDAR PRIMERA ETAPA
		for (String lEtapas : listaEtapas) {
			Etapas e = new Etapas();
			e.setPreparacionPieles(pieles);
			e.setEstado(false);
			if (lEtapas.equals("Carga")) {
				e.setDiaInicio(buscarFechaPrep(idPre));
				e.setHoraInicio(horaIni);
				e.setOperador(us.getNombre());
				e.setEstado(true);
				e.setNormalidad(new BigDecimal(-1));
				e.setAlcalinidad(-1);
				e.setConductividad(-1);
				e.setQuimico1("-1");
				e.setCantidad1("-1");
				e.setLote1("-1");
				e.setQuimico2("-1");
				e.setCantidad2("-1");
				e.setLote2("-1");
			}
			if (lEtapas.equals("Lavado de Depilado") || lEtapas.equals("Lavadas de Blanqueo")
					|| lEtapas.equals("Lavadas Enzimáticas")) {
				e.setNormalidad(new BigDecimal(-1));
				e.setAlcalinidad(-1);
				e.setConductividad(-1);
				e.setQuimico1("-1");
				e.setCantidad1("-1");
				e.setLote1("-1");
				e.setQuimico2("-1");
				e.setCantidad2("-1");
				e.setLote2("-1");
			}
			if (lEtapas.equals("Depilado")) {
				e.setNormalidad(new BigDecimal(-1));
				e.setAlcalinidad(-1);
				e.setConductividad(-1);
			}
			if (lEtapas.equals("Blanqueo") || lEtapas.equals("Proceso Enzimático")) {
				e.setNormalidad(new BigDecimal(-1));
				e.setAlcalinidad(-1);
				e.setConductividad(-1);
				e.setQuimico2("-1");
				e.setCantidad2("-1");
				e.setLote2("-1");
			}

			if (lEtapas.equals("Pre Acidulación")) {
				e.setNormalidad(new BigDecimal(-1));

			}
			if (lEtapas.equals("Acidulación")) {
				e.setAlcalinidad(-1);
				e.setConductividad(-1);

			}

			if (lEtapas.equals("Control 1") || lEtapas.equals("Control 2") || lEtapas.equals("Control 3")
					|| lEtapas.equals("Control 4") || lEtapas.equals("Control 5") || lEtapas.equals("Control 6")
					|| lEtapas.equals("Control 7")) {
				e.setDiaInicio(new Date(1969 - 12 - 31));
				e.setDiaFin(new Date(1969 - 12 - 31));
				LocalDate today = LocalDate.now(); // Mantiene la fecha actual
				LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
				Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
				e.setHoraFin(date);
				e.setHoraFin(date);
				e.setQuimico1("-1");
				e.setCantidad1("-1");
				e.setLote1("-1");
				e.setQuimico2("-1");
				e.setCantidad2("-1");
				e.setLote2("-1");
				e.setAgua("Z");
			}
			if (lEtapas.equals("Control 8")) {
				e.setDiaInicio(new Date(1969 - 12 - 31));
				e.setAlcalinidad(-1);
				e.setConductividad(-1);
				e.setQuimico1("-1");
				e.setCantidad1("-1");
				e.setLote1("-1");
				e.setQuimico2("-1");
				e.setCantidad2("-1");
				e.setLote2("-1");
				e.setAgua("Z");
			}
			e.setLavadora(filterLavadora);
			e.setEtapa(lEtapas);
			e.setEstadoEtapa("Etapa 1");
			listaAgregarEtapas.add(e);
		}

		// GUARDAR SEGUNDA ETAPA
		for (String segundaEtapa : listaEtapaBlanqueo) {
			Etapas e = new Etapas();
			e.setPreparacionPieles(pieles);
			e.setEstado(false);
			e.setLavadora(filterLavadora);
			e.setEtapa(segundaEtapa);
			e.setEstadoEtapa("Etapa 2");
			listaAgregarEtapas.add(e);
		}

		IEtapasDao eDao = new EtapasDaoImpl();
		eDao.guardarListaEtapas(listaAgregarEtapas);

		actualizarFolio(prePieles.getNoOperacion());

		if (idPre > 0) {
			IOperacionLavadorasDao lDao = new OperacionLavadorasDaoImpl();
			prePieles.setIdPreparacion(idPre);

			operacionLavadoras.setPreparacionPieles(prePieles);
			lavadoras.setIdLavadora(buscarLavadora(filterLavadora));
			operacionLavadoras.setLavadoras(lavadoras);

			lDao.guardarOperacionLavadoras(operacionLavadoras);
			IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
			listaOperaciones = oDao.listaOperacionLavadoras(idPre);
			getListaOperaciones();
		} else {
			String info = "LAVADORA NO AGREGADA, SE REQUIERE EL NÚMERRO DE OPERACIÓN";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡ERROR AL GUARDAR!',\n" + "  text: '" + info + "',\n"
							+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
		}

		ILavadorasDao lavDao = new LavadorasDaoImpl();
		lavDao.actualizarEstadoLavadora(1, "Carga", lavadoras.getIdLavadora());

		IPreparacionPielesDao preDao = new PreparacionDaoImpl();
		listarPreparacion = preDao.listaPreparacionPieles(prePieles.getNoOperacion());

		for (PreparacionPieles preP : listarPreparacion) {
			idPrep = preP.getIdPreparacion();
		}
		getListarPreparacion();

		IEtapasDao etDao = new EtapasDaoImpl();
		listaDeEtapas = etDao.listaEtapas(idPre);
		getListaDeEtapas();

		prePieles = new PreparacionPieles();
		lavadoras = new Lavadoras();
		pieles = new PreparacionPieles();
		operacionLavadoras = new OperacionLavadoras();
		facturasPieles = new FacturasPieles();

		prePieles.setNoOperacion(buscarFolio());

	}

	@SuppressWarnings("unlikely-arg-type")
	public void guardarFacturaPieles() {

		String saldoRevisar = "";

		double saldoFactura = 0.0;
		double capacidad = 0.0;

		IFacturaPielesDao facDao = new FacturaPielesDaoImpl();

		saldoFactura = facDao.sumaSaldo(idPrep);

		IOperacionLavadorasDao lavDao = new OperacionLavadorasDaoImpl();
		OperacionLavadoras lavadoras = new OperacionLavadoras();
		lavadoras = lavDao.operacionLavadoraId(idPrep);

		double cantidadIngresada = 0.0;
		if (facturasPieles.getCategoria1() != null && Double.valueOf(facturasPieles.getCategoria1().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria1().doubleValue();
		}

		if (facturasPieles.getCategoria2() != null && Double.valueOf(facturasPieles.getCategoria2().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria2().doubleValue();
		}

		if (facturasPieles.getCategoria3() != null && Double.valueOf(facturasPieles.getCategoria3().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria3().doubleValue();
		}

		if (facturasPieles.getCategoria4() != null && Double.valueOf(facturasPieles.getCategoria4().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria4().doubleValue();
		}

		if (facturasPieles.getCategoria5() != null && Double.valueOf(facturasPieles.getCategoria5().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria5().doubleValue();
		}
		if (facturasPieles.getCategoria6() != null && Double.valueOf(facturasPieles.getCategoria6().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria6().doubleValue();
		}
		if (facturasPieles.getCategoria7() != null && Double.valueOf(facturasPieles.getCategoria7().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria7().doubleValue();
		}
		if (facturasPieles.getCategoria8() != null && Double.valueOf(facturasPieles.getCategoria8().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria8().doubleValue();
		}
		if (facturasPieles.getCategoria9() != null && Double.valueOf(facturasPieles.getCategoria9().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCategoria9().doubleValue();
		}
		capacidad = Double.valueOf(lavadoras.getLavadoras().getCapacidadKilos().doubleValue());
		saldoFactura = saldoFactura + cantidadIngresada;

		if (saldoFactura > capacidad) {
			String info = "LA CANTIDAD INGRESADA " + cantidadIngresada + " MÁS EL SALDO REGISTRADO DA UN TOTAL DE "
					+ saldoFactura + " REBASA LA CAPACIDAD DE LA LAVADORA->" + capacidad;
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡ERROR DE CAPACIDAD DE LAVADORA!',\n" + "  text: '" + info + "',\n"
							+ "  showConfirmButton: false,\n" + "  timer: 15000\n" + "})");
		} else {
			saldoRevisar = validarSaldoAntesDeGuardar(facturasPieles.getFactura(), idPrep);
			if (saldoRevisar.equals("OK") && idPrep > 0) {

				IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
				// **OBTENER EL VALOR ACTUAL DEL ID_PRERACIÓN**//
				prePieles.setIdPreparacion(idPrep);
				if (facturasPieles.getCategoria1() != null
						&& Double.valueOf(facturasPieles.getCategoria1().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria1().doubleValue();
				}

				if (facturasPieles.getCategoria2() != null
						&& Double.valueOf(facturasPieles.getCategoria2().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria2().doubleValue();
				}

				if (facturasPieles.getCategoria3() != null
						&& Double.valueOf(facturasPieles.getCategoria3().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria3().doubleValue();
				}

				if (facturasPieles.getCategoria4() != null
						&& Double.valueOf(facturasPieles.getCategoria4().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria4().doubleValue();
				}

				if (facturasPieles.getCategoria5() != null
						&& Double.valueOf(facturasPieles.getCategoria5().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria5().doubleValue();
				}
				if (facturasPieles.getCategoria6() != null
						&& Double.valueOf(facturasPieles.getCategoria6().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria6().doubleValue();
				}
				if (facturasPieles.getCategoria7() != null
						&& Double.valueOf(facturasPieles.getCategoria7().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria7().doubleValue();
				}
				if (facturasPieles.getCategoria8() != null
						&& Double.valueOf(facturasPieles.getCategoria8().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria8().doubleValue();
				}
				if (facturasPieles.getCategoria9() != null
						&& Double.valueOf(facturasPieles.getCategoria9().toString()) > 0) {
					cantidadIngresada = facturasPieles.getCategoria9().doubleValue();

				}
				LOGGER.info("ID DE PREPARACIÓN: " + idPrep);
				LOGGER.info("FACTURA DE PREPARACIÓN: " + facturasPieles.getFactura());
				Entradas ent = new Entradas();
				IEntradasDao revDao = new EntradasDaoImpl();
				ent = revDao.obtenerIdEntrada(facturasPieles.getFactura());
				facturasPieles.setProveedor(ent.getProveedores().getNombre());
				facturasPieles.setOrigen(ent.getProveedores().getNacionalImportacion());
				facturasPieles.setFechaInicial(buscarFechaPrep(idPrep));
				// VALIDACIÓN PARA ACTUALIZAR LA ENTRADA DE LA FACTURA
				ISaldoFacturaDao saldosDao = new SaldoFacturaDaoImpl();
				double revisarSaldo = 0.0;
				revisarSaldo = saldosDao.saldo(facturasPieles.getFactura());// hace un top a la selección

				if (ent.getProveedores().getNacionalImportacion().equals("NACIONAL")
						&& !ent.getMateria().getTipo().equals("CUERO EN SANGRE")) {

					facturasPieles.setTotalFactura(ent.getKgRecibidos());
				} else if (ent.getProveedores().getNacionalImportacion().equals("IMPORTACIÓN")
						|| ent.getMateria().getTipo().equals("CUERO EN SANGRE")) {
					if (ent.getTipoMoneda().equals("USD")) {
						facturasPieles.setTotalFactura(ent.getKgNetos());
					} else {
						facturasPieles.setTotalFactura(ent.getKgEmbarcados());
					}

				}

				// VALIDAR CON UN COUNT CUANTAS VECES SE HA INGRESADO, SI ES 1 COLOCAR EL TOTAL
				// ORIGEN, SI ES MAYOR
				LOGGER.info("SALDO ANTES DE GUARDAR: " + this.saldoPrevio);
				facturasPieles.setTotalFactura(new BigDecimal(this.saldoPrevio));

				facturasPieles.setKilosDisponibles(new BigDecimal(revisarSaldo));
				facturasPieles.setFechaInicial(buscarFechaPrep(idPrep)); //VALIDAR LA FECHA
				facturasPieles.setPreparacionPieles(prePieles);
				fDao.guardarFacturasPieles(facturasPieles); // GUARDAR EN LA TABLA FACTURA_PIELES

				IFacturaPielesDao pDao = new FacturaPielesDaoImpl();
				listaFacturaPieles = pDao.listaFacturaPieles(idPrep);

				// GUARDAR LAVADORA EN EL RESÚMEN DE LAVADORAS
				IResumenLavadorasDao resDao = new ResumenLavadorasDaoImpl();

				resumenLavadoras.setCantidad(new BigDecimal(cantidadIngresada)); // CANTIDAD INGRESADA AL PROCESO
				resumenLavadoras.setNoOperacion(folio); // NÚMERO DE OPERACIÓN

				IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
				List<OperacionLavadoras> lOperaciones = new ArrayList<>();
				OperacionLavadoras lavadorasOp = new OperacionLavadoras();

				lOperaciones = oDao.listaOperacionLavadoras(idPrep);
				if (!lOperaciones.isEmpty()) {
					lavadorasOp = lOperaciones.get(0);
				}
				resumenLavadoras.setNumero(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion())); // NÚMERO DE
																											// LAVADORA
				resumenLavadoras.setTipo(lavadorasOp.getLavadoras().getNombre()); // DESCRIPCIÓN DE LA LAVADORA

				IResumenTraspasosDao resTrasDao = new ResumenTraspasoDaoImpl(); // RESUMEN TRASPASOS

				resumenTraspasos.setOperacionOrigen(folio);
				resumenTraspasos.setKgCantidad((int) Math.round(cantidadIngresada));
				resumenTraspasos.setTipo("CARGA INICIAL +");
				resumenTraspasos.setFechaInicial(buscarFechaPrep(idPrep)); //VALIDAR LA FECHA
				resumenTraspasos.setLavadoraGCT(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion()));
				resumenTraspasos.setKgCantidadRestante((int) Math.round(cantidadIngresada));
				resTrasDao.guardarResumenTraspasos(resumenTraspasos);

				resumenTraspasos = new ResumenTraspasos();

				// GUARDAR EL ESTATUS DE LA LAVADORA

				ILavadorasEstatusOperacionDao lavadorasEstatusDao = new LavadorasEstatusOperacionDaoImpl();
				ILavadorasEstatusOperacionDao validarRegDao = new LavadorasEstatusOperacionDaoImpl();

				estatusOperacion.setOperacion(folio);
				setLavadoraStatus(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion()), true);

				// ACTUALIZAR LA OPERACION EN LA LAVADORA
				actualizarOperacionesLavadora(folio, lavadorasOp.getLavadoras().getDescripcion());

				// VALIDAR EL ESTATUS SI YA FUE INGRESADO ANTERIORMENTE
				if (validarRegDao.validarRegistro(folio) == -1) {
					lavadorasEstatusDao.guardarLavadorasEstatusOperacion(estatusOperacion);
				}

				resDao.guardarResumenLavadoras(resumenLavadoras);

				// VALIDACIÓN PARA ACTUALIZAR LA ENTRADA DE LA FACTURA
				double valorSaldo = 0.0;
				ISaldoFacturaDao saldoDao = new SaldoFacturaDaoImpl();

				IFacturaPielesDao fact = new FacturaPielesDaoImpl();
				Entradas entradas = new Entradas();

				IEntradasDao eDao = new EntradasDaoImpl();
				entradas = eDao.obtenerIdEntrada(fact.factura(idPrep));
				valorSaldo = saldoDao.saldo(fact.factura(idPrep));// hace un top a la selección
				if (valorSaldo == 0.0) {
					ISaldoFacturaDao sDao = new SaldoFacturaDaoImpl();
					sDao.actualizarFacturaSaldoEntradas(entradas.getIdEntrada());
				}

				facturasPieles = new FacturasPieles();
				getListaFacturaPieles();

			} else {
				String info = "FACTURA NO AGREGADA, REVISA TU CANTIDAD DE MATERIAL DISPONIBLE";

				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡ERROR AL GUARDAR, REVISA TU INFORMACIÓN!',\n" + "  text: '" + info
								+ "',\n" + "  showConfirmButton: false,\n" + "  timer: 15000\n" + "})");
			}
		}
	}

	// **GUARDAR LA LAVADORA**//
	public void guardarLavadora() throws SQLException {
		if (idPrep > 0) {
			IOperacionLavadorasDao lDao = new OperacionLavadorasDaoImpl();
			prePieles.setIdPreparacion(idPrep);

			operacionLavadoras.setPreparacionPieles(prePieles);
			lavadoras.setIdLavadora(buscarLavadora(filterLavadora));
			operacionLavadoras.setLavadoras(lavadoras);

			lDao.guardarOperacionLavadoras(operacionLavadoras);

			IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
			listaOperaciones = oDao.listaOperacionLavadoras(idPrep);
			getListaOperaciones();
		} else {
			String info = "LAVADORA NO AGREGADA, SE REQUIERE EL NÚMERRO DE OPERACIÓN";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Error al guardar!',\n" + "  text: '" + info + "',\n"
							+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
		}
	}

	// **DATOS DE LA LAVADORA**//
	public List<String> buscarNombreLavadora(String nombre) throws SQLException {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		return lDao.completeLavadoras(nombre);
	}

	// **FOLIO DE PREPARACIÓN DE PIELES**//
	public int buscarFolio() {
		IFolioPreparacionDao fDao = new FoliosPreparacionDaoImpl();
		return fDao.buscarFolio();

	}

	public Date buscarFechaPrep(int idPreparacionFecha) {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		return pDao.fechaInicial(idPreparacionFecha);
	}

	// **ACTUALIZAR FOLIO DE PREPARACIÓN DE PIELES**//
	public void actualizarFolio(int folio) {
		IFolioPreparacionDao fDao = new FoliosPreparacionDaoImpl();
		fDao.actualizarFolio(folio);
	}

	// **BUSCAR ID DE LA LAVADORA**//
	public int idLavadora(String nombre) throws SQLException {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		return lDao.buscarLavadora(nombre);
	}

	// **ACTUALIZAR ESTADO DE LAVADORA**//
	public void actualizarEstadoLavadora() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.actualizarLavadoras(lavadoras);
	}

	public List<Etapas> getListaDeEtapas() {
		return listaDeEtapas;
	}

	public List<Etapas> getListaDeEtapas2() {
		return listaDeEtapas2;
	}

	public List<Etapas> getListaDeEtapas3() {
		return listaDeEtapas3;
	}

	public List<Etapas> getListaDeEtapas4() {
		return listaDeEtapas4;
	}

	public List<Etapas> getListaDeEtapas5() {
		return listaDeEtapas5;
	}

	// **ACTUALIZAR LAS ETAPAS DEL PROCESO**//
	@SuppressWarnings("unlikely-arg-type")
	public void actualizarEtapa(String lavadoraActual) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // tuFechaBase es un Date;

		Date fecInicio = calendar.getTime();

		int hora = calendar.get(Calendar.HOUR_OF_DAY); // Hora en formato 24 horas
		int minuto = calendar.get(Calendar.MINUTE); // Minuto
		int segundo = calendar.get(Calendar.SECOND); // Segundo

		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);

		Date horaIni = calendar.getTime();

		IEtapasDao eDao = new EtapasDaoImpl();

		Etapas e = new Etapas();
		etapas.setOperador(us.getNombre());

		PreparacionPieles p = new PreparacionPieles();
		p.setIdPreparacion(etapas.getPreparacionPieles().getIdPreparacion());

		e.setPreparacionPieles(p);
		IEtapasDao etapaDao = new EtapasDaoImpl();
		IEtapasDao etapaHoraFinDao = new EtapasDaoImpl();
		if (etapas.getEtapa().equals("Carga")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Depilado");
				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);

					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");// REGLA
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA

					e.setQuimico1(null);// NULL
					e.setCantidad1(null);// NULL
					e.setLote1(null);// NULL
					e.setQuimico2(null);// NULL
					e.setCantidad2(null);// NULL
					e.setLote2(null);// NULL

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					// etapaHoraFinDao.actualizarHoraFinEtapaAnterior(etapas.getHoraFin(),
					// etapas.getIdEtapa());
					// etapaDao.actualizarSiguienteEtapa(fecInicio, horaIni, true, e.getIdEtapa(),
					// us.getNombre());
					etapaDao.actualizarSiguienteEtapa(e);
					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Depilado, está en el estado CERO, se actualiza al estado 1 ");
				}
			}
		}

		if (etapas.getEtapa().equals("Depilado")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavado de Depilado");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");// REGLA
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico1("-1");// REGLA
					e.setCantidad1("-1");// REGLA
					e.setLote1("-1");// REGLA
					e.setQuimico2("-1");// REGLA
					e.setCantidad2("-1");// REGLA
					e.setLote2("-1");// REGLA
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Lavado de Depilado, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavado de Depilado")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Blanqueo");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");// REGLA
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico2("-1");// REGLA
					e.setCantidad2("-1");// REGLA
					e.setLote2("-1");// REGLA

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Blanqueo, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Blanqueo")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavadas de Blanqueo");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");// REGLA
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico1("-1");// REGLA
					e.setCantidad1("-1");// REGLA
					e.setLote1("-1");// REGLA
					e.setQuimico2("-1");// REGLA
					e.setCantidad2("-1");// REGLA
					e.setLote2("-1");// REGLA

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Lavadas de Blanqueo, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavadas de Blanqueo")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Proceso Enzimático");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");// REGLA
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico2("-1");// REGLA
					e.setCantidad2("-1");// REGLA
					e.setLote2("-1");// REGLA

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Proceso Enzimático, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Proceso Enzimático")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavadas Enzimáticas");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setNormalidad(new BigDecimal(-1));// REGLA
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico1("-1");// REGLA
					e.setCantidad1("-1");// REGLA
					e.setLote1("-1");// REGLA
					e.setQuimico2("-1");// REGLA
					e.setCantidad2("-1");// REGLA
					e.setLote2("-1");// REGLA
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Lavadas Enzimáticas, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavadas Enzimáticas")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Pre Acidulación");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setNormalidad(new BigDecimal(-1));// REGLA

					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					// e.setHoraFin(date);

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Pre Acidulación, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Pre Acidulación")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {

				e.setEtapa("Acidulación");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA

					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					// e.setHoraFin(date);

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Acidulación, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Acidulación")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Control 1");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");

					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());

					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 1, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 1")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 2");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 2, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 2")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 3");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 3, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 3")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 4");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 4, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}
		if (etapas.getEtapa().equals("Control 4")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 5");

				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 5, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 5")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 6");

				if (estadoEtapa(e).getEstado().equals(false)) {
					// e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());

					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 6, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}
		if (etapas.getEtapa().equals("Control 6")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 7");

				if (estadoEtapa(e).getEstado().equals(false)) {
					// e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));
					e.setDiaFin(new Date(1969 - 12 - 31));
					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());

					e.setHoraFin(date);
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 7, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 7")) {
			if (etapas.getNormalidad() != null && !etapas.getNormalidad().toString().isEmpty()) {

				e.setEtapa("Control 8");

				if (estadoEtapa(e).getEstado().equals(false)) {
					// e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 1");
					e.setDiaInicio(new Date(1969 - 12 - 31));

					LocalDate today = LocalDate.now(); // Mantiene la fecha actual
					LocalTime time = LocalTime.of(0, 59, 59); // Establece la hora deseada
					Date date = Date.from(time.atDate(today).atZone(ZoneId.systemDefault()).toInstant());
					e.setAlcalinidad(-1);// REGLA
					e.setConductividad(-1);// REGLA
					e.setQuimico1("-1");
					e.setCantidad1("-1");
					e.setLote1("-1");
					e.setQuimico2("-1");
					e.setCantidad2("-1");
					e.setLote2("-1");
					e.setAgua("Z");

					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Control 8, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Control 8")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				etapas.setDiaInicio(new Date(1969 - 12 - 31));
				etapas.setAgua("Z");

				e.setEtapa("Lavadas Finales (1)");
				if (estadoEtapa(e).getEstado().equals(false)) {
//					e.setDiaInicio(fecInicio);
//					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);

					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Control 8, está en el estado CERO, se actualiza al estado 1 ");

				}

			}

		}

		if (etapas.getEtapa().equals("Lavadas Finales (1)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales (2)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales (1), está en el estado CERO, se actualiza al estado 1 ");

				}

			}
		}

		if (etapas.getEtapa().equals("Lavadas Finales (2)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales (3)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Lavadas Finales (2), está en el estado CERO, se actualiza al estado 1 ");

				}

			}

		}

		if (etapas.getEtapa().equals("Lavadas Finales (2)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales (3)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales (3), está en el estado CERO, se actualiza al estado 1 ");

				}

			}

		}

		if (etapas.getEtapa().equals("Lavadas Finales (3)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales (4)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales (4), está en el estado CERO, se actualiza al estado 1 ");

				}

			}

		}

		if (etapas.getEtapa().equals("Lavadas Finales (4)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales (5)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 2");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales (5), está en el estado CERO, se actualiza al estado 1 ");

				}

			}

		}

		if (etapas.getEtapa().equals("Lavadas Finales (5)")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -1-");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -1-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavadas Finales -1-")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -2-)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -2-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavadas Finales -2-")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -3-)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -3-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getEtapa().equals("Lavadas Finales -3-")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -4-)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -4-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}
		if (etapas.getEtapa().equals("Lavadas Finales -4-")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -5-)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -5-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}
		if (etapas.getEtapa().equals("Lavadas Finales -5-")) {
			if (etapas.getHoraFin() != null && !etapas.getHoraFin().equals("NULL")) {
				e.setEtapa("Lavadas Finales -6-)");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);

					e.setEstadoEtapa("Etapa 3");
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());
					// **ACTUALIZAR EL ESTADO A DISPONIBLE EN LA LAVADORA**//
					LOGGER.info("Etapa: Lavadas Finales -6-, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}

		if (etapas.getAlcalinidad() != null && etapas.getAlcalinidad().equals("")) {
			etapas.setAlcalinidad(null);
		}
		if (etapas.getConductividad() != null && etapas.getConductividad().equals("")) {
			etapas.setConductividad(null);
		}
		if (etapas.getQuimico1() != null && etapas.getQuimico1().equals("")) {
			etapas.setQuimico1(null);
		}
		if (etapas.getCantidad1() != null && etapas.getCantidad1().equals("")) {
			etapas.setCantidad1(null);
		}
		if (etapas.getLote1() != null && etapas.getLote1().equals("")) {
			etapas.setLote1(null);
		}
		if (etapas.getQuimico2() != null && etapas.getQuimico2().equals("")) {
			etapas.setQuimico2(null);
		}
		if (etapas.getCantidad2() != null && etapas.getCantidad2().equals("")) {
			etapas.setCantidad2(null);
		}
		if (etapas.getLote2() != null && etapas.getLote2().equals("")) {
			etapas.setLote2(null);
		}

		eDao.actualizarEtapas(etapas);
		etapas = new Etapas();
		// ETAPA 1//
		IEtapasDao eTDao = new EtapasDaoImpl();
		listaDeEtapas = eTDao.listaEtapas(idPrep);
		getListaDeEtapas();

		// ETAPA 2//
		IEtapasDao eDao2 = new EtapasDaoImpl();
		listaDeEtapas2 = eDao2.listaEtapas2(idPrep);
		getListaDeEtapas2();

		// ETAPA 3//
		IEtapasDao eDao3 = new EtapasDaoImpl();
		listaDeEtapas3 = eDao3.listaEtapas3(idPrep);
		getListaDeEtapas3();

		// ETAPA 4//
		IEtapasDao eDao4 = new EtapasDaoImpl();
		listaDeEtapas4 = eDao4.listaEtapas3(idPrep);
		getListaDeEtapas4();

		// ETAPA 5//
		IEtapasDao eDao5 = new EtapasDaoImpl();
		listaDeEtapas4 = eDao5.listaEtapas3(idPrep);
		getListaDeEtapas3();

		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
		listaOperaciones = oDao.listaOperacionLavadoras(idPrep);
		getListaOperaciones();

		dlgMostrarTraspasoMaterial(); // MOSTRAR DIÁLOGO DE TRASPASO DE MATERIAL

	}

	public Etapas estadoEtapa(Etapas e) {
		Etapas datoReusltado = new Etapas();
		IEtapasDao eDao = new EtapasDaoImpl();
		datoReusltado = eDao.estado(e);
		return datoReusltado;
	}

	// **BUSCAR EL FOLIO DE PREPARACIÓN**//
	public void buscarRegistro() {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		listarPreparacion = pDao.listaPreparacionPieles(folio);

		for (PreparacionPieles preP : listarPreparacion) {
			idPrep = preP.getIdPreparacion();
		}
		getListarPreparacion();

		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
		listaFacturaPieles = fDao.listaFacturaPieles(idPrep);
		getListaFacturaPieles();

		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
		listaOperaciones = oDao.listaOperacionLavadoras(idPrep);
		getListaOperaciones();

		// ETAPA 1//
		IEtapasDao eDao1 = new EtapasDaoImpl();
		listaDeEtapas = eDao1.listaEtapas(idPrep);
		getListaDeEtapas();

		// ETAPA 2//
		IEtapasDao eDao2 = new EtapasDaoImpl();
		listaDeEtapas2 = eDao2.listaEtapas2(idPrep);
		getListaDeEtapas2();

		// ETAPA 3 APARTADO//
		IEtapasDao eDao3 = new EtapasDaoImpl();
		listaDeEtapas3 = eDao3.listaEtapas3(idPrep);
		getListaDeEtapas3();

		// ETAPA 3 APARTADO 2//
		IEtapasDao eDao4 = new EtapasDaoImpl();
		listaDeEtapas4 = eDao4.listaEtapas4(idPrep);
		getListaDeEtapas4();

		// ETAPA 3 APARTADO 3//
		IEtapasDao eDao5 = new EtapasDaoImpl();
		listaDeEtapas5 = eDao5.listaEtapas5(idPrep);
		getListaDeEtapas5();

		getListarTraspasos();

	}

	// **DATOS DE LA LAVADORA, NOMBRE**//
	public List<String> buscarNombreDeLavadora(String nombre) throws SQLException {
		ILavadorasDao lDao = new LavadorasDaoImpl();

		return lDao.completeLavadoras(nombre);
	}

	// **DATOS DE LA LAVADORA, ID**//
	public int buscarLavadora(String nombre) throws SQLException {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		return lDao.buscarLavadora(nombre);
	}

	public void capacidad(String nombre) {
		Lavadoras l = new Lavadoras();
		l.setNombre(nombre);
		ILavadorasDao lDao = new LavadorasDaoImpl();
		prePieles.setTotalKilos(lDao.listarCapaciad(l).getCapacidadKilos());
	}

	// **ACTUALIZAR LOS ESTATUS DE LAS ETAPAS DE LAS CARGAS**//

	public void actualizarEtapas(int idPreparacion, String etapa) {
		/**
		 * OBTENER EL ID DE LA LAVADORA EN LA TABLA OPERACION_LAVADORAS ID_LAVADORA
		 * (Para buscar por el ID de la Lavadora)
		 **/
		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

		/**
		 * ACTUALIZAR EL ESTADO DE LA TABLA DE PREPARACION_PIELES ESTADO (Carga, etc)
		 **/
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		pDao.actualizaEstatusEtapa(
				oDao.listaOperacionLavadorasEtapa(idPreparacion).getPreparacionPieles().getIdPreparacion(), etapa);

		/**
		 * ACTUALIZAR LA ETAPA DE LA LAVADORA EN LA TABLA LAVADORAS ETAPA (Carga, etc)
		 **/
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.actualizarEtapaLavadora(etapa,
				oDao.listaOperacionLavadorasEtapa(idPreparacion).getLavadoras().getIdLavadora());

	}

	// **VALIDACIONES DE LA FACTURA**//
	public void validacionFactura(String fact) {
		ISaldoFacturaDao sDao = new SaldoFacturaDaoImpl();
		double validarSaldo = 0.0;

		String tipoMat = "";
		int tipoM = 0;

		IEntradasDao eDao = new EntradasDaoImpl();
		Entradas e = new Entradas();
		e = eDao.obtenerIdEntrada(fact);
		String nombre = e.getProveedores().getNombre();
		proveedor.setNombre(e.getProveedores().getNombre());
		proveedor.setIdProveedor(e.getProveedores().getIdProveedor());

		LOGGER.info("PROVEEDOR ASOCIADO A LA FACTURA: " + nombre);
		validarSaldo = sDao.saldo(fact);// hace un top a la selección
		SaldoFactura sf = new SaldoFactura();
		if (validarSaldo == -99.0) {
			facturasPieles = new FacturasPieles();
			ISaldoFacturaDao saldoDao = new SaldoFacturaDaoImpl();

			sf.setFactura(fact);
			if (e.getProveedores().getNacionalImportacion().equals("NACIONAL")
					&& !e.getMateria().getTipo().equals("CUERO EN SANGRE")) {
				LOGGER.info("MATERIAL NACIONAL Y DIFERENTE DE CUERO EN SANGRE");
				LOGGER.info("PROVEEDOR:" + e.getProveedores().getNombre() + " ORIGEN:"
						+ e.getProveedores().getNacionalImportacion() + " TIPO DE MP:" + e.getMateria().getTipo());
				sf.setNuevoSaldo(e.getKgRecibidos());
				sf.setTotalFactura(e.getKgRecibidos());

			} else if (e.getProveedores().getNacionalImportacion().equals("IMPORTACIÓN")
					|| e.getMateria().getTipo().equals("CUERO EN SANGRE")) {
				LOGGER.info("MATERIAL IMPORTACIÓN Y/O CUERO EN SANGRE");
				LOGGER.info("PROVEEDOR:" + e.getProveedores().getNombre() + " ORIGEN:"
						+ e.getProveedores().getNacionalImportacion() + " TIPO DE MP:" + e.getMateria().getTipo());
				if (e.getTipoMoneda().equals("USD")) {
					sf.setNuevoSaldo(e.getKgNetos());
					sf.setTotalFactura(e.getKgNetos());
				} else {
					sf.setNuevoSaldo(e.getKgEmbarcados());
					sf.setTotalFactura(e.getKgEmbarcados());
				}

			}

			sf.setFecha(new Date());
			sf.setIdEntrada(e.getIdEntrada());
			saldoDao.guardarSaldoFactura(sf);

			tipoM = e.getMateria().getIdMateria();
			tipoMat = e.getMateria().getTipo();

			// **VALIDAR EL TIPO DE MATERIAL**//
			ISaldoFacturaDao vDao = new SaldoFacturaDaoImpl();
			double saldoFactura = 0.0;
			saldoFactura = vDao.saldo(fact);// hace un top a la selección
			validarMaterial(tipoM, tipoMat, saldoFactura);

			LOGGER.info("SE HA IDENTIFICADO LA MATERIA: " + tipoM);

			LOGGER.info(
					"NO EXISTE LA FACTURA, SE INGRESA FACTURA Y TOTAL DE KILOS: " + fact + "->" + e.getKgRecibidos());
		} else {
			tipoM = e.getMateria().getIdMateria();
			LOGGER.info("Mostramos el saldo de kilos: " + validarSaldo);
			LOGGER.info("SE HA IDENTIFICADO LA MATERIA: " + tipoM);
			facturasPieles = new FacturasPieles();

			ISaldoFacturaDao vDao = new SaldoFacturaDaoImpl();
			double saldoFactura = 0.0;
			saldoFactura = vDao.saldo(fact);// hace un top a la selección
			validarMaterial(tipoM, tipoMat, saldoFactura);

		}
	}

	public void validarMaterial(int tipoDeMaterial, String tipo, double saldoRestante) {

		// **CLASIFICACIÓN DE LA MATERIA**//
		LOGGER.info("ID MATERIAL: " + tipoDeMaterial);
		LOGGER.info("MATERIAL A BUSCAR: " + tipo);
		switch (tipoDeMaterial) {

		case 2, 3, 12 -> {
			LOGGER.info("CARNAZA");
			LOGGER.info("CATEGORIA 2");
			facturasPieles.setCategoria2(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria2().doubleValue();
		}

		case 4 -> {
			LOGGER.info("CARNAZA SALADA");
			LOGGER.info("CATEGORIA 3");
			facturasPieles.setCategoria3(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria3().doubleValue();
		}

		case 6 -> {
			LOGGER.info("CERDO NACIONAL");
			LOGGER.info("CATEGORIA 4");
			facturasPieles.setCategoria4(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria4().doubleValue();
		}

		case 1 -> {
			LOGGER.info("CUERO ROCHA");
			LOGGER.info("CATEGORIA 1");
			facturasPieles.setCategoria1(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria1().doubleValue();
		}

		case 11 -> {
			LOGGER.info("DESCARNE SEPARADO");
			LOGGER.info("CATEGORIA 5");
			facturasPieles.setCategoria5(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria5().doubleValue();
		}

		case 7, 9, 10 -> {
			LOGGER.info("OREJA Y CACHETE");

			facturasPieles.setCategoria6(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria6().doubleValue();
		}

		case 8, 15 -> {
			LOGGER.info("PEDACERIA CON PELO");

			facturasPieles.setCategoria7(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria7().doubleValue();
		}

		case 14 -> {
			LOGGER.info("PELO EN SANGRE");

			facturasPieles.setCategoria8(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria8().doubleValue();
		}
		case 5, 13 -> {
			LOGGER.info("TENERIA LEON");

			facturasPieles.setCategoria9(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCategoria9().doubleValue();
		}

		default -> System.out.println("Tipo de material desconocido.");
		}
	}

	public String validarSaldoAntesDeGuardar(String factura, int idP) {

		String validar = "";
		if (idP > 0) {
			ISaldoFacturaDao sDao = new SaldoFacturaDaoImpl();
			double validarSaldo = 0.0;
			this.saldoPrevio = 0.0;

			int tipoM = 0;

			IEntradasDao eDao = new EntradasDaoImpl();
			Entradas e = new Entradas();
			e = eDao.obtenerIdEntrada(factura);
			validarSaldo = sDao.saldo(factura);// hace un top a la selección
			this.saldoPrevio = validarSaldo;
			SaldoFactura sf = new SaldoFactura();

			ISaldoFacturaDao saldoDao = new SaldoFacturaDaoImpl();
			tipoM = e.getMateria().getIdMateria();

			if (cantidadIngresada(tipoM) > 0 && cantidadIngresada(tipoM) <= validarSaldo) {
				sf.setFactura(factura);
				sf.setNuevoSaldo(new BigDecimal(validarSaldo - cantidadIngresada(tipoM)));

				if (e.getProveedores().getNacionalImportacion().equals("NACIONAL")
						&& !e.getMateria().getTipo().equals("CUERO EN SANGRE")) {
					sf.setTotalFactura(e.getKgRecibidos());
				} else if (e.getProveedores().getNacionalImportacion().equals("IMPORTACIÓN")
						|| e.getMateria().getTipo().equals("CUERO EN SANGRE")) {
					if (e.getTipoMoneda().equals("USD")) {
						sf.setTotalFactura(e.getKgNetos());
					} else {
						sf.setTotalFactura(e.getKgEmbarcados());
					}

				}

				sf.setFecha(new Date());
				sf.setIdEntrada(e.getIdEntrada());
				sf.setOperacion(folio); // FOLIO DE LA OPERACIÓN
				saldoDao.guardarSaldoFactura(sf);
				validar = "OK";
			} else {
				validar = "REVISAR";
			}
		}

		return validar;
	}

	public double cantidadIngresada(int identificacionMaterial) {

		double cantidad = 0.0;

		switch (identificacionMaterial) {
		case 2, 3, 12 -> {
			cantidad = Double.valueOf(facturasPieles.getCategoria2().toString());
		}
		case 4 -> {
			LOGGER.info("Procesar material tipo OC");
			cantidad = Double.valueOf(facturasPieles.getCategoria3().toString());
		}
		case 6 -> {
			LOGGER.info("Procesar material tipo DS");
			cantidad = Double.valueOf(facturasPieles.getCategoria4().toString());
		}
		case 1 -> {
			LOGGER.info("Procesar material tipo CN");
			cantidad = Double.valueOf(facturasPieles.getCategoria1().toString());
		}

		case 11 -> {
			LOGGER.info("Procesar material tipo CAM");
			cantidad = Double.valueOf(facturasPieles.getCategoria5().toString());
		}

		case 7, 9, 10 -> {
			LOGGER.info("Procesar material con RP");
			cantidad = Double.valueOf(facturasPieles.getCategoria6().toString());
		}
		case 8, 15 -> {
			LOGGER.info("Procesar material con RP");
			cantidad = Double.valueOf(facturasPieles.getCategoria7().toString());
		}
		case 14 -> {
			LOGGER.info("Procesar material con RP");
			cantidad = Double.valueOf(facturasPieles.getCategoria8().toString());
		}
		case 5, 13 -> {
			LOGGER.info("Procesar material con RP");
			cantidad = Double.valueOf(facturasPieles.getCategoria9().toString());
		}
		default -> System.out.println("Tipo de material desconocido.");
		}
		return cantidad;

	}

	public void mostrar(String lavadora, int estadoLavadora) throws SQLException {

		// **ACTUALIZA EL CAMBIO DE LAVADORA EN LA ETAPA**//
		IEtapasDao eDao = new EtapasDaoImpl();
		// **VALIDAR LOS PROCESOS RESTANTES**//
		IEtapasDao etaDao = new EtapasDaoImpl();
		List<String> listaEtapasPendientes = new ArrayList<String>();

		listaEtapasPendientes = etaDao.listaProcesosPendientesEtapa(idPrep);
		for (String etapa : listaEtapasPendientes) {
			eDao.actualizarEtapaCambioLavadora(idPrep, etapa, lavadora);
			LOGGER.info("Actualización de Lavdora: " + lavadora + " id preparación: " + idPrep + " etapa: " + etapa);
		}

//		for (String procesosE : selectedProcess) {
//			eDao.actualizarEtapaCambioLavadora(idPrep, procesosE, lavadora);
//			LOGGER.info(
//					"Actualización de Lavdora: " + lavadora + " id preparación: " + idPrep + " etapa: " + procesosE);
//		}

		// **BUSCAR LA LAVADORA EN LA TABLA OPERACION_LAVADORAS**//
		IOperacionLavadorasDao opDao = new OperacionLavadorasDaoImpl();

		// **ACTUALIZAR EL ESTADO DE LA LAVADORA CON EL ESTATUS 2 DE MATENIMIENTO**//
		ILavadorasDao lavDao = new LavadorasDaoImpl();
		lavDao.actualizarEstadoLavadora(estadoLavadora, "", opDao.obtenerIdLavadora(idPrep));

		// **ACTUALIZAR EL ESTADO DE LA LAVADORA CON EL ESTATUS DONDE TOMARA EL
		// PROCESO**//
		ILavadorasDao lavadoraDao = new LavadorasDaoImpl();
		IPreparacionPielesDao prepDao = new PreparacionDaoImpl();
		lavadoraDao.actualizarEstadoLavadora(1, prepDao.nombreEstado(idPrep), buscarLavadora(lavadora));

		// **ACTUALIZA EL CAMBIO DE LAVADORA POR EL NUEVO ID EN LA LA TABLA
		// OPERACION_LAVADORAS**//
		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
		oDao.actualizarCambioLavadora(idPrep, buscarLavadora(lavadora));

		buscarRegistro();
		String info = "Se ha realizado el cambio de lavadora";
		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
						+ "  timer: 8000\n" + "})");

	}

	public void dlgMostrarTraspasoMaterial() {

		PrimeFaces.current().executeScript("PF('dlgTraspasoMaterial').show();");
	}

	public List<Double> calcularKilos() {

		List<Double> listaKilos = new ArrayList<>();

		int numeroLavadoras = selectedLavadorasChicas.length;

		IFacturaPielesDao pDao = new FacturaPielesDaoImpl();
		listaFacturaPieles = pDao.listaFacturaPieles(idPrep);

		double kilosTotales = distribucionKilos;

		for (FacturasPieles fp : listaFacturaPieles) {
			noFacturaLavadorasChicas = fp.getFactura();
		}

		if (numeroLavadoras < 1 || numeroLavadoras > 6) {
			return listaKilos;
		}

		// Parte entera de la división
		int kilosBase = (int) (kilosTotales / numeroLavadoras);

		// Kilos sobrantes
		int restante = (int) (kilosTotales % numeroLavadoras);

		// Repartir los kilos
		for (int i = 0; i < numeroLavadoras; i++) {

			double kilos = kilosBase;

			// A las primeras "restante" lavadoras se les agrega 1 kg
			if (i < restante) {
				kilos++;
			}

			listaKilos.add(kilos);
		}

		// Mostrar la distribución
		listarAsignacionKilosLavadorasChicas.clear();

		for (int i = 0; i < selectedLavadorasChicas.length && i < listaKilos.size(); i++) {
			listarAsignacionKilosLavadorasChicas.add(selectedLavadorasChicas[i] + ": " + listaKilos.get(i) + " kg.");
		}

		return listaKilos;
	}

	public void guardarTaspasoSilos() throws SQLException {
		// distribucionKilosSilos ES LO QUE EL USUSARIO INTRODUCE

		int cantidadRestante = validarCantidad(folio);

		if (cantidadRestante == 0) {
			// VALIDA LA CANTIDAD, SI ES 0 YA NO HAY NADA QUE HACER
			String info = "No queda cantidad disponible para el folio " + folio + "";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} else if (cantidadRestante > 0) {
			if (distribucionKilosSilos <= 0) {
				System.out.println("La cantidad ingresada debe ser mayor que 0.");
				String info = "La cantidad ingresada debe ser mayor que 0.";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else if (distribucionKilosSilos > cantidadRestante) {

				String info = "La cantidad ingresada excede la cantidad restante (" + cantidadRestante + ").";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else {
				// Aquí ya pasa todas las validaciones
				LOGGER.info("Cantidad válida: " + distribucionKilosSilos);
				// Lógica para guardar el traspaso...

				ITraspasoSilosDao tDao = new TraspasoDaoImpl();

				PreparacionPieles p = new PreparacionPieles();
				p.setIdPreparacion(idPrep);
				Usuarios u = new Usuarios();
				u.setIdUsuario(buscarCoordinador(filterUsuario));
				Operador op = new Operador();
				op.setIdOperador(buscarOperador(filterOperador));

				traspasoSilos.setOperador(op);
				traspasoSilos.setUsuarios(u);
				traspasoSilos.setPreparacionPieles(p);
				traspasoSilos.setIdOperacion(folio);
				traspasoSilos.setNoOperacion(folio);
				traspasoSilos.setCantidad(distribucionKilosSilos);
				if (distribucionKilosSilos > validarCantidad(folio)) {
					String info = "Revisa la cantidad, no debe ser superior a los kilos disponibles";
					PrimeFaces.current()
							.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
									+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
									+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");

				} else {
					// GUARDAR EL ESTATUS DE LA LAVADORA
					ILavadorasEstatusOperacionDao lavadorasEstatusDao = new LavadorasEstatusOperacionDaoImpl();

					List<OperacionLavadoras> lOperaciones = new ArrayList<>();
					OperacionLavadoras lavadorasOp = new OperacionLavadoras();
					IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

					lOperaciones = oDao.listaOperacionLavadoras(idPrep);
					if (!lOperaciones.isEmpty()) {
						lavadorasOp = lOperaciones.get(0);
					}

					estatusOperacion.setOperacion(folio);

					tDao.guardarTraspasoSilos(traspasoSilos);

					IResumenTraspasosDao resTrasDao = new ResumenTraspasoDaoImpl(); // RESUMEN TRASPASOS

					resumenTraspasosAct.setFechaInicial(traspasoSilos.getFechaInicial());
					resumenTraspasosAct.setFechaFinal(traspasoSilos.getFechaFinal());
					resumenTraspasosAct.setHoraInicial(traspasoSilos.getHoraInicial());
					resumenTraspasosAct.setHoraFinal(traspasoSilos.getHoraFinal());

					resumenTraspasosAct.setOperacionOrigen(folio);
					resumenTraspasosAct.setKgCantidad((int) Math.round(distribucionKilosSilos));
					resumenTraspasosAct.setTipo("TRASPASO SILOS -");
					resumenTraspasosAct.setLavadoraGCT(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion()));
					// VALIDAR LA CANTIDAD RESTANTE
					resumenTraspasosAct
							.setKgCantidadRestante((int) Math.round(validarCantidad(folio) - distribucionKilosSilos));
					resTrasDao.guardarResumenTraspasos(resumenTraspasosAct);
					// SI ES CERO SE ACTUALIZA LA TABLA LAVADORAS_ESTATUS_OPERACION
					if (validarCantidad(folio) == 0) {
						IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
						fDao.actualizarFechaFinal(idPrep, traspasoSilos.getFechaFinal());

						lavadorasEstatusDao.actualizarLavadoraFalse(
								lavadoraTipo(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion())), folio);

						ILavadorasDao lavDao = new LavadorasDaoImpl();
						lavDao.actualizarEstadoLavadora(0, lavadorasOp.getLavadoras().getDescripcion());
					}

					resumenTraspasosAct = new ResumenTraspasos();

				}

				traspasoSilos = new TraspasoSilos();
				//distribucionKilosSilos = 0;
				filterUsuario=null;
				filterOperador=null;
			}
		}

	}

	public void guardarTaspasoSilosEncimado() throws SQLException {
		// distribucionKilosSilos ES LO QUE EL USUSARIO INTRODUCE
		int idEtapa = 0;
		int cantidad = 0;
		idEtapa = etapasEditarTraspaso.getIdEtapa();

		IEtapasDao etaDao = new EtapasDaoImpl();
		cantidad = etaDao.cantidadMasEncimado(idEtapa);

		int nuevaCantidad = etapasEditarTraspaso.getCantidad();
		// Validar la cantidad que se traspaso y actalizar las cantidad de las etapas
		int cantidadRestante = validarCantidadEncimado(folio);

		if (cantidadRestante == 0) {
			// VALIDA LA CANTIDAD, SI ES 0 YA NO HAY NADA QUE HACER
			String info = "No queda cantidad disponible para el folio " + folio + "";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} else if (cantidadRestante > 0) {
			if (etapasEditarTraspaso.getCantidad() <= 0) {

				String info = "La cantidad ingresada debe ser mayor que 0.";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else if (etapasEditarTraspaso.getCantidad() > cantidadRestante) {

				String info = "La cantidad ingresada excede la cantidad restante (" + cantidadRestante + ").";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else {
				// Aquí ya pasa todas las validaciones
				LOGGER.info("Cantidad válida: " + etapasEditarTraspaso.getCantidad());
				// Lógica para guardar el traspaso...

				ITraspasoSilosDao tDao = new TraspasoDaoImpl();

				PreparacionPieles p = new PreparacionPieles();
				p.setIdPreparacion(idPrep);
				Usuarios u = new Usuarios();
				u.setIdUsuario(buscarCoordinador(filterUsuario));
				Operador op = new Operador();
				op.setIdOperador(buscarOperador(filterOperador));

				traspasoSilos.setOperador(op);
				traspasoSilos.setUsuarios(u);
				traspasoSilos.setPreparacionPieles(p);
				traspasoSilos.setIdOperacion(folio);
				traspasoSilos.setNoOperacion(folio);
				traspasoSilos.setCantidad(etapasEditarTraspaso.getCantidad());
				if (etapasEditarTraspaso.getCantidad() > validarCantidadEncimado(folio)) {
					String info = "Revisa la cantidad, no debe ser superior a los kilos disponibles";
					PrimeFaces.current()
							.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
									+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
									+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");

				} else {
					// GUARDAR EL ESTATUS DE LA LAVADORA
					ILavadorasEstatusOperacionDao lavadorasEstatusDao = new LavadorasEstatusOperacionDaoImpl();

					List<OperacionLavadoras> lOperaciones = new ArrayList<>();
					OperacionLavadoras lavadorasOp = new OperacionLavadoras();
					IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

					lOperaciones = oDao.listaOperacionLavadoras(idPrep);
					if (!lOperaciones.isEmpty()) {
						lavadorasOp = lOperaciones.get(0);
					}

					estatusOperacion.setOperacion(folio);

					tDao.guardarTraspasoSilos(traspasoSilos);

					IResumenTraspasosDao resTrasDao = new ResumenTraspasoDaoImpl(); // RESUMEN TRASPASOS

					resumenTraspasosAct.setFechaInicial(traspasoSilos.getFechaInicial());
					resumenTraspasosAct.setFechaFinal(traspasoSilos.getFechaFinal());
					resumenTraspasosAct.setHoraInicial(traspasoSilos.getHoraInicial());
					resumenTraspasosAct.setHoraFinal(traspasoSilos.getHoraFinal());
					resumenTraspasosAct.setOperacionOrigen(folio);
					resumenTraspasosAct.setKgCantidad((int) Math.round(etapasEditarTraspaso.getCantidad()));
					resumenTraspasosAct.setTipo("TRASPASO SILOS -");
					resumenTraspasosAct.setLavadoraGCT(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion()));
					// VALIDAR LA CANTIDAD RESTANTE
					resumenTraspasosAct.setKgCantidadRestante(
							(int) Math.round(validarCantidadEncimado(folio) - etapasEditarTraspaso.getCantidad()));
					resTrasDao.guardarResumenTraspasos(resumenTraspasosAct);

					// SE ACTUALIZA LA NUEVA CANTIDAD
					IEtapasDao nuevaCantidadDao = new EtapasDaoImpl();
					nuevaCantidadDao.actualizarCantidad((cantidad - nuevaCantidad), idEtapa);

					// SE CONSULTA LA CANTIDAD ACTUALIZADA
					IEtapasDao etapaDao = new EtapasDaoImpl();
					int cantidadValidar = 0;
					cantidadValidar = etapaDao.cantidadMasEncimado(idEtapa);

					// SI ES CERO SE ACTUALIZA LA TABLA LAVADORAS_ESTATUS_OPERACION
					if (cantidadValidar == 0) {

						IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
						fDao.actualizarFechaFinal(idPrep, traspasoSilos.getFechaFinal());

						lavadorasEstatusDao.actualizarLavadoraFalse(
								lavadoraTipo(Integer.parseInt(etapasEditarTraspaso.getLavadora())), folio);

						ILavadorasDao lavDao = new LavadorasDaoImpl();
						lavDao.actualizarEstadoLavadora(0, etapasEditarTraspaso.getLavadora());
					}

					resumenTraspasosAct = new ResumenTraspasos();

				}

				traspasoSilos = new TraspasoSilos();
			}
		}

	}

	public void guardarEncimadoLavadoras() throws SQLException {
		// distribucionKilosSilos ES LO QUE EL USUSARIO INTRODUCE
		int idEtapa = 0;
		int cantidad = 0;
		idEtapa = etapasEditarTraspaso.getIdEtapa();

		IEtapasDao etaDao = new EtapasDaoImpl();
		cantidad = etaDao.cantidadMasEncimado(idEtapa);

		int nuevaCantidad = etapasEditarTraspaso.getCantidad();
		// Validar la cantidad que se traspaso y actalizar las cantidad de las etapas
		int cantidadRestante = validarCantidadEncimado(folio);

		if (cantidadRestante == 0) {
			// VALIDA LA CANTIDAD, SI ES 0 YA NO HAY NADA QUE HACER
			String info = "No queda cantidad disponible para el folio " + folio + "";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} else if (cantidadRestante > 0) {
			if (etapasEditarTraspaso.getCantidad() <= 0) {

				String info = "La cantidad ingresada debe ser mayor que 0.";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else if (etapasEditarTraspaso.getCantidad() > cantidadRestante) {

				String info = "La cantidad ingresada excede la cantidad restante (" + cantidadRestante + ").";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else {
				// Aquí ya pasa todas las validaciones
				LOGGER.info("Cantidad válida: " + etapasEditarTraspaso.getCantidad());
				// Lógica para guardar el traspaso...

				ITraspasoSilosDao tDao = new TraspasoDaoImpl();

				PreparacionPieles p = new PreparacionPieles();
				p.setIdPreparacion(idPrep);
				Usuarios u = new Usuarios();
				u.setIdUsuario(buscarCoordinador(filterUsuario));
				Operador op = new Operador();
				op.setIdOperador(buscarOperador(filterOperador));

				traspasoSilos.setOperador(op);
				traspasoSilos.setUsuarios(u);
				traspasoSilos.setPreparacionPieles(p);
				traspasoSilos.setIdOperacion(folio);
				traspasoSilos.setNoOperacion(folio);
				traspasoSilos.setCantidad(etapasEditarTraspaso.getCantidad());
				if (etapasEditarTraspaso.getCantidad() > validarCantidadEncimado(folio)) {
					String info = "Revisa la cantidad, no debe ser superior a los kilos disponibles";
					PrimeFaces.current()
							.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
									+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
									+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");

				} else {
					// GUARDAR EL ESTATUS DE LA LAVADORA
					ILavadorasEstatusOperacionDao lavadorasEstatusDao = new LavadorasEstatusOperacionDaoImpl();

					List<OperacionLavadoras> lOperaciones = new ArrayList<>();
					OperacionLavadoras lavadorasOp = new OperacionLavadoras();
					IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

					lOperaciones = oDao.listaOperacionLavadoras(idPrep);
					if (!lOperaciones.isEmpty()) {
						lavadorasOp = lOperaciones.get(0);
					}

					estatusOperacion.setOperacion(folio);

					// tDao.guardarTraspasoSilos(traspasoSilos); //QUITAR ESTO

					IResumenTraspasosDao resTrasDao = new ResumenTraspasoDaoImpl(); // RESUMEN TRASPASOS
					resumenTraspasosAct.setFechaInicial(etapasLavadorasChicas.getDiaInicio());
					resumenTraspasosAct.setFechaFinal(etapasLavadorasChicas.getDiaFin());
					resumenTraspasosAct.setHoraInicial(etapasLavadorasChicas.getHoraInicio());
					resumenTraspasosAct.setHoraFinal(etapasLavadorasChicas.getHoraFin());

					resumenTraspasosAct.setOperacionOrigen(folio);
					resumenTraspasosAct.setKgCantidad((int) Math.round(etapasEditarTraspaso.getCantidad()));
					resumenTraspasosAct.setTipo("ENCIMADO +");
					resumenTraspasosAct.setOperacionDestino(etapasEditarTraspaso.getOperacionDestino());
					resumenTraspasosAct.setLavadoraGCT(Integer.parseInt(etapasEditarTraspaso.getLavadora()));
					resumenTraspasosAct.setLavadoraGCTDestino(etapasEditarTraspaso.getLavadoraDestino());
					// VALIDAR LA CANTIDAD RESTANTE
					resumenTraspasosAct.setKgCantidadRestante(cantidad - nuevaCantidad);//
					resTrasDao.guardarResumenTraspasos(resumenTraspasosAct);

					// SE ACTUALIZA LA NUEVA CANTIDAD
					IEtapasDao nuevaCantidadDao = new EtapasDaoImpl();
					nuevaCantidadDao.actualizarCantidad((cantidad - nuevaCantidad), idEtapa);

					// SE CONSULTA LA CANTIDAD ACTUALIZADA
					IEtapasDao etapaDao = new EtapasDaoImpl();
					int cantidadValidar = 0;
					cantidadValidar = etapaDao.cantidadMasEncimado(idEtapa);

					// SUMAR EL ENCIMADO A LAVADORAS
					IEtapasDao actualizarDao = new EtapasDaoImpl();
					IEtapasDao actualizarCantDao = new EtapasDaoImpl();
					Etapas et = new Etapas();

					et = actualizarDao.cantidadTraspasoEncimado(etapasEditarTraspaso.getLavadoraDestino());
					actualizarCantDao.actualizarCantidad(et.getCantidad() + nuevaCantidad, et.getIdEtapa());

					// SI ES CERO SE ACTUALIZA LA TABLA LAVADORAS_ESTATUS_OPERACION
					if (cantidadValidar == 0) {

						lavadorasEstatusDao.actualizarLavadoraFalse(
								lavadoraTipo(Integer.parseInt(etapasEditarTraspaso.getLavadora())), folio);

						ILavadorasDao lavDao = new LavadorasDaoImpl();
						lavDao.actualizarEstadoLavadora(0, etapasEditarTraspaso.getLavadora());
					}

					resumenTraspasosAct = new ResumenTraspasos();

				}
				String info = "Se ha realizado el encimado a la lavadora: " + etapasEditarTraspaso.getLavadoraDestino()
						+ " por la cantidad de: " + nuevaCantidad + " kilos";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");

				traspasoSilos = new TraspasoSilos();
			}
		}

	}

	public void guardarTraspasoLavadoras() {

		// distribucionKilosSilos ES LO QUE EL USUSARIO INTRODUCE
		int cantidadRestante = validarCantidad(folio);

		if (cantidadRestante == 0) {
			// VALIDA LA CANTIDAD, SI ES 0 YA NO HAY NADA QUE HACER
			String info = "No queda cantidad disponible para el folio " + folio + "";
			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} else if (cantidadRestante > 0) {
			if (distribucionKilos <= 0) {
				System.out.println("La cantidad ingresada debe ser mayor que 0.");
				String info = "La cantidad ingresada debe ser mayor que 0.";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else if (distribucionKilos > cantidadRestante) {

				String info = "La cantidad ingresada excede la cantidad restante (" + cantidadRestante + ").";
				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
			} else {
				// Aquí ya pasa todas las validaciones
				LOGGER.info("Cantidad válida: " + distribucionKilos);
				// Lógica para guardar el traspaso...

				// GUARDAR EL ESTATUS DE LA LAVADORA
				ILavadorasEstatusOperacionDao lavadorasEstatusDao = new LavadorasEstatusOperacionDaoImpl();
				// GUARDAR EL ESTATUS DE LA LAVADORA CHICA
				ILavadorasEstatusOperacionDao lavadorasChicasEstatusDao = new LavadorasEstatusOperacionDaoImpl();

				List<OperacionLavadoras> lOperaciones = new ArrayList<>();
				OperacionLavadoras lavadorasOp = new OperacionLavadoras();
				IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

				lOperaciones = oDao.listaOperacionLavadoras(idPrep);
				if (!lOperaciones.isEmpty()) {
					lavadorasOp = lOperaciones.get(0);
				}

				Etapas e = new Etapas();
				PreparacionPieles p = new PreparacionPieles();

				for (int i = 0; i < selectedLavadorasChicas.length; i++) {
					String lavadoras[] = null;
					lavadoras = selectedLavadorasChicas;
					p.setIdPreparacion(idPrep);
					e.setPreparacionPieles(p);
					String cadena = "";

					cadena = listarAsignacionKilosLavadorasChicas.get(i);
					Matcher m = java.util.regex.Pattern.compile("\\d+\\.\\d+").matcher(cadena);
					int cantidadIngresada = 0;

					if (m.find()) {
						// Convierte a double y luego a entero
						double valor = Double.parseDouble(m.group());
						cantidadIngresada = (int) valor;
					}

					if (i == 0) {
						e.setDiaInicio(etapasLavadorasChicas.getDiaInicio());
						e.setDiaFin(etapasLavadorasChicas.getDiaFin());
						e.setHoraInicio(etapasLavadorasChicas.getHoraInicio());
						e.setHoraFin(etapasLavadorasChicas.getHoraFin());
						resumenTraspasosAct.setFechaInicial(etapasLavadorasChicas.getDiaInicio());
						resumenTraspasosAct.setFechaFinal(etapasLavadorasChicas.getDiaFin());
						resumenTraspasosAct.setHoraInicial(etapasLavadorasChicas.getHoraInicio());
						resumenTraspasosAct.setHoraFinal(etapasLavadorasChicas.getHoraFin());
					}

					e.setEstado(true);
					e.setLavadora(lavadoras[i].replaceAll("\\D", ""));
					e.setEtapa("Lavadora chica");

					e.setCantidadTraspaso(cantidadIngresada);
					e.setCantidad(cantidadIngresada);
					e.setEstadoEtapa("Etapa 3");

					for (int j = 0; j < 5; j++) {

						IEtapasDao eDao = new EtapasDaoImpl();
						if (j == 0) {
							// GUARDAR EL TRASPASO EN RESUMEN_TRASPASOS
							IResumenTraspasosDao resTrasDao = new ResumenTraspasoDaoImpl(); // RESUMEN TRASPASOS

							resumenTraspasosAct.setOperacionOrigen(folio);
							resumenTraspasosAct.setKgCantidad(cantidadIngresada);
							resumenTraspasosAct.setTipo("TRASPASO LC -");
							resumenTraspasosAct.setLavadoraGCT(Integer.parseInt(lavadoras[i].replaceAll("\\D", "")));
							// VALIDAR LA CANTIDAD RESTANTE
							LOGGER.info("CANTIDAD RESTANTE: " + validarCantidad(folio));
							resumenTraspasosAct.setKgCantidadRestante(
									(int) Math.round(validarCantidad(folio) - cantidadIngresada));
							resTrasDao.guardarResumenTraspasos(resumenTraspasosAct);

							// ACTUALIZAR LAVADORAS CHICAS A TRUE EN LAVADORAS_ESTATUS_OPERACION
							lavadorasChicasEstatusDao.actualizarLavadoraTrue(
									lavadoraTipo(Integer.parseInt(lavadoras[i].replaceAll("\\D", ""))), folio);

							// ACTUALIZAR EL NÚMERO DE OPERACIÓN DE LA LAVADORA
							actualizarOperacionesLavadora(folio, lavadoras[i].replaceAll("\\D", ""));

							// SI ES CERO SE ACTUALIZA LA TABLA LAVADORAS_ESTATUS_OPERACION
							if (validarCantidad(folio) == 0) {

								lavadorasEstatusDao.actualizarLavadoraFalse(
										lavadoraTipo(Integer.parseInt(lavadorasOp.getLavadoras().getDescripcion())),
										folio);

								ILavadorasDao lavDao = new LavadorasDaoImpl();
								lavDao.actualizarEstadoLavadora(0, lavadorasOp.getLavadoras().getDescripcion());
							}

							// ACTUALIZAR EL ESTATUS DE LAS LAVADORAS CHICAS EN LAVADORAS, ESTATUS EN
							// PROCESO
							ILavadorasDao lavChicasDao = new LavadorasDaoImpl();
							lavChicasDao.actualizarEstadoLavadoraChica(1, "Traspaso",
									lavadoras[i].replaceAll("\\D", ""));

						}
						eDao.guardarEtapas(e);
						e.setCantidadTraspaso(null);
						e.setCantidad(null);
						e.setDiaInicio(null);
						e.setDiaFin(null);
						e.setHoraInicio(null);
						e.setHoraFin(null);
					}

				}
			}
		}
		etapasLavadorasChicas=new Etapas();
		selectedLavadorasChicas= null;
		listarAsignacionKilosLavadorasChicas = null;
		IEtapasDao eDao3 = new EtapasDaoImpl();
		listaDeEtapas3 = eDao3.listaEtapas3(idPrep);
		getListaDeEtapas3();
	}

	public void actualizarTaspasoSilos() {
		ITraspasoSilosDao tDao = new TraspasoDaoImpl();
		tDao.actualizarTraspasoSilos(traspasoSilosEditar);
		traspasoSilosEditar = new TraspasoSilos();

	}

	public int totalOperacion(int idPreparacion) {
		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
		return (int) Math.round(fDao.obtenerTotalProceso(idPreparacion));
	}

	public void colocarTotal() {
		this.distribucionKilos = 0;
		this.distribucionKilosSilos = 0;
		this.distribucionKilosEncimar = 0;
		// this.distribucionKilos = totalOperacion(idPrep);
		this.distribucionKilos = validarCantidad(folio);
		this.distribucionKilosSilos = distribucionKilos;
		this.distribucionKilosEncimar = distribucionKilos;
	}

	// **DATOS DEL OPERADOR, NOMBRE**//
	public List<String> buscarNombreOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.completeOperador(nombre, "Lavadoras");
	}

	// **DATOS DEL OPERADOR, ID**//
	public int buscarOperador(String nombre) throws SQLException {
		IOperadorDao tDao = new OperadorDaoImpl();
		return tDao.buscarOperador(nombre, "Lavadoras");
	}

	public void guardarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		operador.setEstado("Activo");
		operador.setProceso("Lavadoras");
		oDao.guardarOperador(operador);
		operador = new Operador();

	}

	public void actualizarOperador() {
		IOperadorDao oDao = new OperadorDaoImpl();
		oDao.actualizarOperador(operadorEditar);
		operadorEditar = new Operador();
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

	public List<Operador> getListaOperadores() {
		IOperadorDao oDao = new OperadorDaoImpl();
		listaOperadores = oDao.listaOperadorLavadoras();
		return listaOperadores;
	}

	public void guardarResumenTraspasos() {
		IResumenTraspasosDao rDao = new ResumenTraspasoDaoImpl();
		rDao.guardarResumenTraspasos(resumenTraspasos);
		resumenTraspasos = new ResumenTraspasos();
	}

	public String lavadoraTipo(int numeroLav) {

		String tipo = "";

		switch (numeroLav) {
		case 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37 -> tipo = "LAVADORA_GRANDE_" + numeroLav;

		case 4, 5, 6, 7 -> tipo = "TAMBOR_" + numeroLav;

		case 10, 11, 12, 13, 14, 15, 16 -> tipo = "LAVADORA_CHICA_" + numeroLav;

		default -> throw new IllegalArgumentException("Unexpected value: " + numeroLav);
		}

		return tipo;

	}

	public void setLavadoraStatus(int numeroLav, boolean valor) {
		switch (numeroLav) {
		case 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37 -> {
			switch (numeroLav) {
			case 26 -> this.estatusOperacion.setLavadoraGrande26(valor);
			case 27 -> this.estatusOperacion.setLavadoraGrande27(valor);
			case 28 -> this.estatusOperacion.setLavadoraGrande28(valor);
			case 29 -> this.estatusOperacion.setLavadoraGrande29(valor);
			case 30 -> this.estatusOperacion.setLavadoraGrande30(valor);
			case 31 -> this.estatusOperacion.setLavadoraGrande31(valor);
			case 32 -> this.estatusOperacion.setLavadoraGrande32(valor);
			case 33 -> this.estatusOperacion.setLavadoraGrande33(valor);
			case 34 -> this.estatusOperacion.setLavadoraGrande34(valor);
			case 35 -> this.estatusOperacion.setLavadoraGrande35(valor);
			case 36 -> this.estatusOperacion.setLavadoraGrande36(valor);
			case 37 -> this.estatusOperacion.setLavadoraGrande37(valor);
			}
		}
		case 4, 5, 6, 7 -> {
			switch (numeroLav) {
			case 4 -> this.estatusOperacion.setTambor4(valor);
			case 5 -> this.estatusOperacion.setTambor5(valor);
			case 6 -> this.estatusOperacion.setTambor6(valor);
			case 7 -> this.estatusOperacion.setTambor7(valor);
			}
		}
		case 10, 11, 12, 13, 14, 15, 16 -> {
			switch (numeroLav) {
			case 10 -> this.estatusOperacion.setLavadoraChica10(valor);
			case 11 -> this.estatusOperacion.setLavadoraChica11(valor);
			case 12 -> this.estatusOperacion.setLavadoraChica12(valor);
			case 13 -> this.estatusOperacion.setLavadoraChica13(valor);
			case 14 -> this.estatusOperacion.setLavadoraChica14(valor);
			case 15 -> this.estatusOperacion.setLavadoraChica15(valor);
			case 16 -> this.estatusOperacion.setLavadoraChica16(valor);
			}
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + numeroLav);
		}
	}

	public int validarCantidad(int noOperacion) {
		IResumenTraspasosDao resumenDao = new ResumenTraspasoDaoImpl();
		return resumenDao.validarCantidadRestante(noOperacion);
	}

	public int validarCantidadEncimado(int noOperacion) {
		IResumenTraspasosDao resumenDao = new ResumenTraspasoDaoImpl();
		return resumenDao.validarCantidadLavdorasChicas(noOperacion);
	}

	public List<Etapas> getListaEtapasLavadorasChicas() {
		return listaEtapasLavadorasChicas;
	}

	public void setListarLavadorasChicas(List<String> listarLavadorasChicas) {
		this.listarLavadorasChicas = listarLavadorasChicas;
	}

	public void cargarLavadorasChicas() throws SQLException {
		IEtapasDao etapasDao = new EtapasDaoImpl();
		listaEtapasLavadorasChicas = etapasDao.listaEtapasLavadorasChicas(idPrep);
		getListarOperaciones();
		getListaEtapasLavadorasChicas();
	}

	public void onRowEdit(RowEditEvent event) {
//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "SISTEMA DE PEDIDOS", "Se ha modificado la cantidad..."));
		validaciones();
	}

	public void onRowCancel(RowEditEvent event) {
//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "SISTEMA DE PEDIDOS", "Se ha cancelado la edición..."));
	}

	public void validaciones() {

		for (Etapas etapas : listaEtapasLavadorasChicas) {
			etapas.setCantidadTraspaso(etapas.getCantidadTraspaso());
			etapas.setLavadoraDestino(etapas.getLavadoraDestino());
			if (etapas.getLavadoraDestino() != null) {
				etapas.setOperacionDestino(folioOperacion);
			}

		}

		getListarLavadorasChicas();
	}

	public void actualizarOperacionesLavadora(int oper, String desc) {
		ILavadorasDao lavadorasDao = new LavadorasDaoImpl();
		lavadorasDao.actualizarOperacionLavadora(oper, desc);
	}

	public void imprimirPro53() throws SQLException {
		@SuppressWarnings("unused")
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String ruta = null;

		ReporteLavadoras reporte = new ReporteLavadoras();

		ruta = servletContext.getRealPath("/REP/reporteLavadoras.jasper");

		reporte.getReporte(ruta, String.valueOf(folio));

		FacesContext.getCurrentInstance().responseComplete();
	}

	public void guardarObservaciones() {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		pDao.actualizarPreparcionPieles(observacionesEditar);
		observacionesEditar = new PreparacionPieles();

	}

}