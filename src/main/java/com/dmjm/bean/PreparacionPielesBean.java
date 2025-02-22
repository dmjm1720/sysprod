package com.dmjm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

import com.dmjm.dao.IEntradasDao;
import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.dao.IFolioPreparacionDao;
import com.dmjm.dao.ILavadorasDao;
import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.dao.IPreparacionPielesDao;
import com.dmjm.dao.IQuimicosDao;
import com.dmjm.dao.ISaldoFacturaDao;
import com.dmjm.impl.EntradasDaoImpl;
import com.dmjm.impl.EtapaDaoImpl;
import com.dmjm.impl.FacturaPielesDaoImpl;
import com.dmjm.impl.FoliosPreparacionDaoImpl;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.impl.OperacionLavadorasDaoImpl;
import com.dmjm.impl.PreparacionDaoImpl;
import com.dmjm.impl.QuimicosDaoImpl;
import com.dmjm.impl.SaldoFacturaDaoImpl;
import com.dmjm.model.Entradas;
import com.dmjm.model.Etapa1;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.model.PreparacionPieles;
import com.dmjm.model.Quimicos;
import com.dmjm.model.SaldoFactura;
import com.dmjm.model.Usuarios;

@Named(value = "preparacionBean")
@ViewScoped
public class PreparacionPielesBean implements Serializable {

	private static final Logger LOGGER = LogManager.getLogger(PreciosBean.class.getName());
	private static final long serialVersionUID = 1L;
	private List<PreparacionPieles> listarPreparacion;
	private PreparacionPieles prePieles;
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	private List<Etapa1> listaEtapa1;
	private Etapa1 etapa1;
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
	private List<String> procesos;

	private String estadoLavadora="";
	
	@PostConstruct
	public void init() {
		listarPreparacion = new ArrayList<>();
		prePieles = new PreparacionPieles();
		listarPreparacion = new ArrayList<>();
		facturasPieles = new FacturasPieles();
		lavadoras = new Lavadoras();
		prePieles.setNoOperacion(buscarFolio());
		etapa1 = new Etapa1();
		listaEtapa1 = new ArrayList<>();
		operacionLavadoras = new OperacionLavadoras();
		listaOperaciones = new ArrayList<>();
		listaOperacionesDisponibles = new ArrayList<>();
		listarQuimicos = new ArrayList<>();
		listarLavadorasDisponibles = new ArrayList<>();
		listarLavadorasDisponibles2 = new ArrayList<>();
		listaFacturasTolvas = new ArrayList<>();

		procesos = new ArrayList<>();
		procesos.add("Carga");
		procesos.add("Lavado Enzimático");
		procesos.add("Lavada de Carga");
		procesos.add("Blanqueo");
		procesos.add("Lavadas de Blanqueo");
		procesos.add("Pre Acidulación");
		procesos.add("Acidulación");
		procesos.add("Control 1");
		procesos.add("Control 2");
		procesos.add("Control 3");
		procesos.add("Control 4");
		procesos.add("Control 5");
		procesos.add("Control 6");
		procesos.add("Control 7");
		procesos.add("Control 8");
		

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

	public Etapa1 getEtapa1() {
		return etapa1;
	}

	public void setEtapa1(Etapa1 etapa1) {
		this.etapa1 = etapa1;
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
	
	public String getEstadoLavadora() {
		return estadoLavadora;
	}

	public void setEstadoLavadora(String estadoLavadora) {
		this.estadoLavadora = estadoLavadora;
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

	// **OBTENER LA LISTA DE LAS LAVADORAS DISPONIBLES**//
	public List<String> getListarLavadorasDisponibles() {
		ILavadorasDao lava = new LavadorasDaoImpl();
		List<Lavadoras> listar = lava.listarLavadorasDisponibles();
		listarLavadorasDisponibles.clear();
		for (Lavadoras l : listar) {
			listarLavadorasDisponibles.add(l.getNombre());
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
			listaFacturasTolvas.add(e.getFactura());
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

		String[] listaEtapas = { "Carga", "Lavado Enzimático", "Lavada de Carga", "Blanqueo", "Lavadas de Blanqueo",
				"Pre Acidulación", "Acidulación", "Control 1", "Control 2", "Control 3", "Control 4", "Control 5",
				"Control 6", "Control 7", "Control 8" };
		List<Etapa1> listaAgregarEtapas = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // tuFechaBase es un Date;

		Date fecInicio = calendar.getTime();

		int hora = calendar.get(Calendar.HOUR_OF_DAY); // Hora en formato 24 horas
		int minuto = calendar.get(Calendar.MINUTE); // Minuto
		int segundo = calendar.get(Calendar.SECOND); // Segundo

		calendar.set(Calendar.HOUR, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);

		// Obtener la hora actual
//        LocalTime ahora = LocalTime.now();
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm:ss a");
//        String horaFormateada = ahora.format(formato);
//        
//        // Define el formato de la hora
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
//        Date horaIni = sdf.parse(horaFormateada);
		Date horaIni = calendar.getTime();

		for (String lEtapas : listaEtapas) {
			Etapa1 e = new Etapa1();
			e.setPreparacionPieles(pieles);
			e.setEstado(false);
			if (lEtapas.equals("Carga")) {
				e.setDiaInicio(fecInicio);
				e.setHoraInicio(horaIni);
				e.setOperador(us.getNombre());
				e.setEstado(true);
			}
			e.setLavadora(filterLavadora);
			e.setEtapa(lEtapas);
			listaAgregarEtapas.add(e);
		}

		IEtapa1Dao eDao = new EtapaDaoImpl();
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

		IEtapa1Dao etDao = new EtapaDaoImpl();
		listaEtapa1 = etDao.listaEtapa1(idPre);
		getListaEtapa1();

		prePieles = new PreparacionPieles();
		lavadoras = new Lavadoras();
		pieles = new PreparacionPieles();
		operacionLavadoras = new OperacionLavadoras();
		facturasPieles = new FacturasPieles();

	}

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
		if (facturasPieles.getCn() != null && Double.valueOf(facturasPieles.getCn().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCn().doubleValue();
		}

		if (facturasPieles.getCz() != null && Double.valueOf(facturasPieles.getCz().toString()) > 0) {
			cantidadIngresada = facturasPieles.getCz().doubleValue();
		}

		if (facturasPieles.getDc() != null && Double.valueOf(facturasPieles.getDc().toString()) > 0) {
			cantidadIngresada = facturasPieles.getDc().doubleValue();
		}

		if (facturasPieles.getDs() != null && Double.valueOf(facturasPieles.getDs().toString()) > 0) {
			cantidadIngresada = facturasPieles.getDs().doubleValue();
		}

		if (facturasPieles.getRp() != null && Double.valueOf(facturasPieles.getRp().toString()) > 0) {
			cantidadIngresada = facturasPieles.getRp().doubleValue();
		}

		capacidad = Double.valueOf(lavadoras.getLavadoras().getCapacidadKilos().doubleValue());
		saldoFactura = saldoFactura + cantidadIngresada;

		System.err.println("Capacidad" + capacidad + "saldoFactura" + saldoFactura);

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
				if (facturasPieles.getCn() != null && Double.valueOf(facturasPieles.getCn().toString()) > 0) {
					facturasPieles.setTotal(facturasPieles.getCn());
				}
				if (facturasPieles.getCz() != null && Double.valueOf(facturasPieles.getCz().toString()) > 0) {
					facturasPieles.setTotal(facturasPieles.getCz());
				}
				if (facturasPieles.getDc() != null && Double.valueOf(facturasPieles.getDc().toString()) > 0) {
					facturasPieles.setTotal(facturasPieles.getDc());
				}
				if (facturasPieles.getDs() != null && Double.valueOf(facturasPieles.getDs().toString()) > 0) {
					facturasPieles.setTotal(facturasPieles.getDs());
				}
				if (facturasPieles.getRp() != null && Double.valueOf(facturasPieles.getRp().toString()) > 0) {
					facturasPieles.setTotal(facturasPieles.getRp());
				}
				LOGGER.info("ID DE PREPARACIÓN: " + idPrep);
				facturasPieles.setPreparacionPieles(prePieles);
				fDao.guardarFacturasPieles(facturasPieles);
				facturasPieles = new FacturasPieles();

				IFacturaPielesDao pDao = new FacturaPielesDaoImpl();
				listaFacturaPieles = pDao.listaFacturaPieles(idPrep);
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

	public List<Etapa1> getListaEtapa1() {

		return listaEtapa1;
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

		IEtapa1Dao eDao = new EtapaDaoImpl();

		Etapa1 e = new Etapa1();

		PreparacionPieles p = new PreparacionPieles();
		p.setIdPreparacion(etapa1.getPreparacionPieles().getIdPreparacion());

		e.setPreparacionPieles(p);
		IEtapa1Dao etapaDao = new EtapaDaoImpl();
		if (etapa1.getEtapa().equals("Carga")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavado Enzimático");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
					e.setLavadora(lavadoraActual);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);

					actualizarEtapas(e.getPreparacionPieles().getIdPreparacion(), e.getEtapa());

					LOGGER.info("Etapa: Lavado Enzimático, está en el estado CERO, se actualiza al estado 1 ");
				}
			}
		}

		if (etapa1.getEtapa().equals("Lavado Enzimático")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavada de Carga");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Lavada de Carga")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Blanqueo");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Blanqueo")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Lavadas de Blanqueo");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Lavadas de Blanqueo")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Pre Acidulación");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Pre Acidulación")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Acidulación");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Acidulación")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 1");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 1")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 2");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 2")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 3");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 3")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 4");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 4")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 5");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 5")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 6");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 6")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 7");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 7")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				e.setEtapa("Control 8");

				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		if (etapa1.getEtapa().equals("Control 8")) {
			if (etapa1.getHoraFin() != null && !etapa1.getHoraFin().equals("NULL")) {

				if (estadoEtapa(e).getEstado().equals(true)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
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

		eDao.actualizarEtapa1(etapa1);
		etapa1 = new Etapa1();

		IEtapa1Dao eTDao = new EtapaDaoImpl();
		listaEtapa1 = eTDao.listaEtapa1(idPrep);
		getListaEtapa1();
	}

	public Etapa1 estadoEtapa(Etapa1 e) {
		Etapa1 datoReusltado = new Etapa1();
		IEtapa1Dao eDao = new EtapaDaoImpl();
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

		IEtapa1Dao eDao = new EtapaDaoImpl();
		listaEtapa1 = eDao.listaEtapa1(idPrep);
		getListaEtapa1();

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
		pDao.actualizaEstatusEtapa(oDao.listaOperacionLavadorasEtapa(idPreparacion).getIdOperacionLavadoras(), etapa);

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

		String tipoM = "";

		IEntradasDao eDao = new EntradasDaoImpl();
		Entradas e = new Entradas();
		e = eDao.obtenerIdEntrada(fact);
		validarSaldo = sDao.saldo(fact);// hace un top a la selección
		SaldoFactura sf = new SaldoFactura();
		if (validarSaldo == -99.0) {
			facturasPieles = new FacturasPieles();
			ISaldoFacturaDao saldoDao = new SaldoFacturaDaoImpl();

			sf.setFactura(fact);
			sf.setNuevoSaldo(e.getKgRecibidos());
			sf.setTotalFactura(e.getKgRecibidos());
			sf.setFecha(new Date());

			saldoDao.guardarSaldoFactura(sf);

			tipoM = e.getMateria().getTipo();

			// **VALIDAR EL TIPO DE MATERIAL**//
			ISaldoFacturaDao vDao = new SaldoFacturaDaoImpl();
			double saldoFactura = 0.0;
			saldoFactura = vDao.saldo(fact);// hace un top a la selección
			validarMaterial(tipoM, saldoFactura);

			LOGGER.info("SE HA IDENTIFICADO LA MATERIA: " + tipoM);

			LOGGER.info(
					"NO EXISTE LA FACTURA, SE INGRESA FACTURA Y TOTAL DE KILOS: " + fact + "->" + e.getKgRecibidos());
		} else {
			tipoM = e.getMateria().getTipo();
			LOGGER.info("Mostramos el saldo de kilos: " + validarSaldo);
			LOGGER.info("SE HA IDENTIFICADO LA MATERIA: " + tipoM);
			facturasPieles = new FacturasPieles();

			ISaldoFacturaDao vDao = new SaldoFacturaDaoImpl();
			double saldoFactura = 0.0;
			saldoFactura = vDao.saldo(fact);// hace un top a la selección
			validarMaterial(tipoM, saldoFactura);

		}
	}

	public void validarMaterial(String tipoDeMaterial, double saldoRestante) {

		switch (tipoDeMaterial) {
		// CZ
		case "CARNAZA 1", "CARNAZA 2", "CARNZA SALADA", "CUERO DEPILADO INTEGRAL", "DESBARBE / RECORTE", "GARRA" -> {
			LOGGER.info("Procesar material tipo CZ");
			// Acciones para CZ
			facturasPieles.setCz(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCz().doubleValue();
		}

		// OC
		case "CACHETE", "PEDACERÍA", "DESCARNE ADHERIDO" -> {
			LOGGER.info("Procesar material tipo OC");
			// Acciones para DC
			facturasPieles.setDc(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getDc().doubleValue();
		}

		// DS
		case "DESCARNE SEPARADO" -> {
			LOGGER.info("Procesar material tipo DS");
			// Acciones para DS
			facturasPieles.setDs(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getDs().doubleValue();
		}

		// CN
		case "CERDO MEXICANO" -> {
			LOGGER.info("Procesar material tipo CN");
			// Acciones para CN
			facturasPieles.setCn(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getCn().doubleValue();
		}

		// RP
		case "CARNAZA CON PELO", "PEDACERÍA CON PELO", "CUERO EN SANGRE" -> {
			LOGGER.info("Procesar material RP");
			// Acciones para RP
			facturasPieles.setRp(new BigDecimal(saldoRestante));
			saldoDisponibleParaAgregar = facturasPieles.getRp().doubleValue();
		}

		default -> System.out.println("Tipo de material desconocido.");
		}
	}

	public String validarSaldoAntesDeGuardar(String factura, int idP) {

		String validar = "";
		if (idP > 0) {
			ISaldoFacturaDao sDao = new SaldoFacturaDaoImpl();
			double validarSaldo = 0.0;

			String tipoM = "";

			IEntradasDao eDao = new EntradasDaoImpl();
			Entradas e = new Entradas();
			e = eDao.obtenerIdEntrada(factura);
			validarSaldo = sDao.saldo(factura);// hace un top a la selección
			SaldoFactura sf = new SaldoFactura();

			ISaldoFacturaDao saldoDao = new SaldoFacturaDaoImpl();
			tipoM = e.getMateria().getTipo();

			if (cantidadIngresada(tipoM) > 0 && cantidadIngresada(tipoM) <= validarSaldo) {
				sf.setFactura(factura);
				sf.setNuevoSaldo(new BigDecimal(validarSaldo - cantidadIngresada(tipoM)));
				sf.setTotalFactura(e.getKgRecibidos());
				sf.setFecha(new Date());

				saldoDao.guardarSaldoFactura(sf);
				validar = "OK";
			} else {
				validar = "REVISAR";
			}
		}

		return validar;
	}

	public double cantidadIngresada(String identificacionMaterial) {

		double cantidad = 0.0;

		switch (identificacionMaterial) {
		// CZ
		case "CARNAZA 1", "CARNAZA 2", "CARNZA SALADA", "CUERO DEPILADO INTEGRAL", "DESBARBE / RECORTE", "GARRA" -> {
			LOGGER.info("Procesar material tipo CZ");
			// Acciones para CZ
			cantidad = Double.valueOf(facturasPieles.getCz().toString());
		}

		// OC
		case "CACHETE", "PEDACERÍA", "DESCARNE ADHERIDO" -> {
			LOGGER.info("Procesar material tipo OC");
			// Acciones para DC
			cantidad = Double.valueOf(facturasPieles.getDc().toString());
		}

		// DS
		case "DESCARNE SEPARADO" -> {
			LOGGER.info("Procesar material tipo DS");
			// Acciones para DS
			cantidad = Double.valueOf(facturasPieles.getDs().toString());
		}

		// CN
		case "CERDO MEXICANO" -> {
			LOGGER.info("Procesar material tipo CN");
			// Acciones para RP
			cantidad = Double.valueOf(facturasPieles.getCn().toString());
		}

		// RP
		case "CARNAZA CON PELO", "PEDACERÍA CON PELO", "CUERO EN SANGRE" -> {
			LOGGER.info("Procesar material con RP");
			// Acciones para materiales con pelo
			cantidad = Double.valueOf(facturasPieles.getRp().toString());
		}

		default -> System.out.println("Tipo de material desconocido.");
		}
		return cantidad;

	}

	public void mostrar(String lavadora, int estadoLavadora) throws SQLException {

		// **ACTUALIZA EL CAMBIO DE LAVADORA EN LA ETAPA**//
		IEtapa1Dao eDao = new EtapaDaoImpl();
		//**VALIDAR LOS PROCESOS RESTANTES**//
		IEtapa1Dao etaDao = new EtapaDaoImpl();
		List<String> listaEtapasPendientes = new ArrayList<String>();
		
		listaEtapasPendientes = etaDao.listaProcesosPendientesEtapa(idPrep);
		for (String etapa : listaEtapasPendientes) {
			eDao.actualizarEtapaCambioLavadora(idPrep, etapa, lavadora);
			LOGGER.info(
					"Actualización de Lavdora: " + lavadora + " id preparación: " + idPrep + " etapa: " + etapa);
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

		// **ACTUALIZAR EL ESTADO DE LA LAVADORA CON EL ESTATUS DONDE TOMARA EL PROCESO**//
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

}