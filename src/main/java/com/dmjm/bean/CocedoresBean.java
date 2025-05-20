package com.dmjm.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.ICocedoresDao;
import com.dmjm.dao.IFolioCocedoresDao;
import com.dmjm.dao.IFolioPreparcionCocedoresDao;
import com.dmjm.dao.ILimpiezaDao;
import com.dmjm.dao.IOrdenMantoDao;
import com.dmjm.dao.IPurgasDao;
import com.dmjm.impl.CocedoresDaoImpl;
import com.dmjm.impl.FolioPreparacionCocedoresDaoImpl;
import com.dmjm.impl.FoliosCocedoresDaoImpl;
import com.dmjm.impl.LimpiezaDaoImpl;
import com.dmjm.impl.OrdenMantoDaoImpl;
import com.dmjm.impl.PurgasDaoImpl;
import com.dmjm.model.Cocedores;
import com.dmjm.model.FolioPreparacionCocedores;
import com.dmjm.model.Limpieza;
import com.dmjm.model.OrdenMantenimiento;
import com.dmjm.model.Purgas;

@Named("cocedoresBean")
@ViewScoped
public class CocedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public CocedoresBean() {

	}

	@PostConstruct
	public void init() {
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

		primera();

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
			limpiezaEditar.setQuimico("Agua");
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
		return purgasEditar;
	}

	public void setPurgasEditar(Purgas purgasEditar) {
		this.purgasEditar = purgasEditar;
	}

	public List<OrdenMantenimiento> getListaOrdenManto() {
		IOrdenMantoDao oDao = new OrdenMantoDaoImpl();
		listaOrdenManto = oDao.listaOrdenManto(folioPrepCocedor);
		return listaOrdenManto;
	}

	public List<Limpieza> getListaLimpieza() {
		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		listaLimpieza = lDao.listarLimpieza(folioPrepCocedor);
		return listaLimpieza;
	}

	public List<Purgas> getListaPurgas() {
		IPurgasDao pDao = new PurgasDaoImpl();
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

	public void actualizarCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();

		String oper = cocedoresEditar.getOperacion();
		cocedoresEditar.setOperacion(oper.replaceAll("(?<=\\D)(?=\\d)", " "));

		cDao.actualizarCocedores(cocedoresEditar);
		actualizarPromedios(cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());

		cocedoresEditar = new Cocedores();
		radioButton = null;
	}

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
		String datosLimpieza[] = { "ALCALINO", "ENJUAGUE", "ÁCIDO", "ENJUAGUE", "SANITIZANTE", "ENJUAGUE" };

		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f.setIdFolioPrep(folioPrepCocedor);

		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		for (String l : datosLimpieza) {
			limpieza.setFolioPreparacionCocedores(f);
			limpieza.setProceso(l);
			lDao.guardarLimpieza(limpieza);
			limpieza = new Limpieza();
		}

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
		purgas.setFolioPreparacionCocedores(f);
		pDao.guardaPurgas(purgas);
		purgas = new Purgas();
	}

	public void actualizarPurgas() {
		IPurgasDao pDao = new PurgasDaoImpl();
		pDao.actualizarPurgas(purgasEditar);
		purgas = new Purgas();
	}

	// **ORDEN DE MANTENIMIENTO**//
	public void guardarOrdenManto() {
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
		getListaCocedores();
	}

}
