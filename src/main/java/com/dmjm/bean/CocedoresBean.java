package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.data.PageEvent;

import com.dmjm.dao.ICocedoresDao;
import com.dmjm.dao.IFolioPreparcionCocedoresDao;
import com.dmjm.impl.CocedoresDaoImpl;
import com.dmjm.impl.FolioPreparacionCocedoresDaoImpl;
import com.dmjm.model.Cocedores;
import com.dmjm.model.FolioPreparacionCocedores;

@Named("cocedoresBean")
@ViewScoped
public class CocedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cocedores> listaCocedores;
	private Cocedores cocedores;
	private Cocedores cocedoresEditar;
	private Date fecha;

	public CocedoresBean() {

	}

	@PostConstruct
	public void init() {
		listaCocedores = new ArrayList<Cocedores>();
		cocedores = new Cocedores();
		cocedoresEditar = new Cocedores();
		primera();

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

	public List<Cocedores> getListaCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();
		listaCocedores = cDao.listaCocedores();
		return listaCocedores;
	}

	String listaHora[] = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
			"17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", ":00", "1:00", "2:00", "3:00", "4:00",
			"5:00", "6:00", "PROM." };

	public void guardarCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();
		cocedores = new Cocedores();
		cocedores.setFolioCocedor(1);
		IFolioPreparcionCocedoresDao fDao = new FolioPreparacionCocedoresDaoImpl();
		FolioPreparacionCocedores fpc = new FolioPreparacionCocedores();
		fpc.setIdFolioPrep(fDao.returnIDGuardarFolio());

		for (String lista : listaHora) {
			cocedores.setHoraLimitesEspecificos(lista);
			cocedores.setFolioPreparacionCocedores(fpc);
			cocedores.setFecha(new Date());
			cDao.guardarCocedores(cocedores);
			cocedores = new Cocedores();
		}

	}

	public void actualizarCocedores() {
		ICocedoresDao cDao = new CocedoresDaoImpl();
		cDao.actualizarCocedores(cocedoresEditar);
		
		actualizarPromedios(cocedoresEditar.getFolioPreparacionCocedores().getIdFolioPrep());
		
		cocedoresEditar = new Cocedores();
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
			this.fecha = paginaActual.get(0).getFecha();
		}
	}
	
	public void primera() {
		IFolioPreparcionCocedoresDao fDao = new FolioPreparacionCocedoresDaoImpl();
		FolioPreparacionCocedores f = new FolioPreparacionCocedores();
		f = fDao.retornarFechaActual();
		this.fecha = f.getFecha();
	}
	
	
	//**ACTUALIZAR PROMEDIOS**//
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

}
