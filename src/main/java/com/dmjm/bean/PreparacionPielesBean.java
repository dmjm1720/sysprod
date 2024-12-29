package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.dao.IFolioPreparacionDao;
import com.dmjm.dao.ILavadorasDao;
import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.dao.IPreparacionPielesDao;
import com.dmjm.dao.IQuimicosDao;
import com.dmjm.impl.EtapaDaoImpl;
import com.dmjm.impl.FacturaPielesDaoImpl;
import com.dmjm.impl.FoliosPreparacionDaoImpl;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.impl.OperacionLavadorasDaoImpl;
import com.dmjm.impl.PreparacionDaoImpl;
import com.dmjm.impl.QuimicosDaoImpl;
import com.dmjm.model.Etapa1;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.model.PreparacionPieles;
import com.dmjm.model.Quimicos;
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

	private List<Integer> listaOperacionesDisponibles;

	private List<String> listarQuimicos;

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

	// **OBTENER LA LISTA DE LAS OPERACIONES DISPONIBLES**//
	public List<Integer> getListaOperacionesDisponibles() {

		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		List<PreparacionPieles> listar = new ArrayList<>();

		listar = pDao.listaPreparacion();

		for (PreparacionPieles preparacionPieles : listar) {
			listaOperacionesDisponibles.add(preparacionPieles.getNoOperacion());
		}

		return listaOperacionesDisponibles;
	}

	// **OBTENER LA LISTA DE LOS QUÍMICOS**//
	public List<String> getListarQuimicos() {
		IQuimicosDao qDao = new QuimicosDaoImpl();
		List<Quimicos> listar = qDao.listarQuimicos();
		for (Quimicos q : listar) {
			listarQuimicos.add(q.getNombre());
		}
		return listarQuimicos;
	}

	// **GUARDAR LA PREPARACIÓN**//
	public void guardarPreparacion() throws SQLException {
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

		String[] listaEtapas = { "Carga", "Lavada de Carga", "Blanqueo", "Lavadas de Blanqueo", "Pre Acidulación",
				"Acidulación", "Control 1", "Control 2", "Control 3", "Control 4", "Control 5", "Control 6",
				"Control 7", "Control 8" };
		List<Etapa1> listaAgregarEtapas = new ArrayList<>();

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
							+ "  title: '¡Error al guardar!',\n" + "  text: '" + info + "',\n"
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

		if (idPrep > 0) {
			IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
			// **OBTENER EL VALOR ACTUAL DEL ID_PRERACIÓN**//
			prePieles.setIdPreparacion(idPrep);
			LOGGER.info("ID DE PREPARACIÓN: " + idPrep);
			facturasPieles.setPreparacionPieles(prePieles);
			fDao.guardarFacturasPieles(facturasPieles);
			facturasPieles = new FacturasPieles();

			IFacturaPielesDao pDao = new FacturaPielesDaoImpl();
			listaFacturaPieles = pDao.listaFacturaPieles(idPrep);
			getListaFacturaPieles();
		} else {
			String info = "FACTURA NO AGREGADA, SE REQUIERE EL NÚMERRO DE OPERACIÓN";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'error',\n"
							+ "  title: '¡Error al guardar!',\n" + "  text: '" + info + "',\n"
							+ "  showConfirmButton: false,\n" + "  timer: 8000\n" + "})");
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
	public void actualizarEtapa() throws ParseException {
		
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
				
				e.setEtapa("Lavada de Carga");
				if (estadoEtapa(e).getEstado().equals(false)) {
					e.setDiaInicio(fecInicio);
					e.setHoraInicio(horaIni);
					e.setEstado(true);
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);
					LOGGER.info("Etapa: Lavada de carga, está en el estado CERO, se actualiza al estado 1 ");
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
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
					e.setOperador(us.getNombre());
					e.setIdEtapa(estadoEtapa(e).getIdEtapa());
					PreparacionPieles prep = new PreparacionPieles();
					prep.setIdPreparacion(estadoEtapa(e).getPreparacionPieles().getIdPreparacion());
					e.setPreparacionPieles(prep);
					etapaDao.actualizarSiguienteEtapa(e);		
					LOGGER.info("Etapa: Control 8, está en el estado CERO, se actualiza al estado 1 ");

				}
			}
		}
		

		eDao.actualizarEtapa1(etapa1);
		etapa1 = new Etapa1();
	}

	public Etapa1 estadoEtapa(Etapa1 e) {
		Etapa1 datoReusltado = new Etapa1();
		IEtapa1Dao eDao = new EtapaDaoImpl();
		 datoReusltado= eDao.estado(e);
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

}
