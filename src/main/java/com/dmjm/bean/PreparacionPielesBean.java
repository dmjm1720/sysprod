package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.dao.IFolioPreparacionDao;
import com.dmjm.dao.ILavadorasDao;
import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.dao.IPreparacionPielesDao;
import com.dmjm.impl.EtapaDaoImpl;
import com.dmjm.impl.FacturaPielesDaoImpl;
import com.dmjm.impl.FoliosPreparacionDaoImpl;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.impl.OperacionLavadorasDaoImpl;
import com.dmjm.impl.PreparacionDaoImpl;
import com.dmjm.model.Etapa1;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.model.PreparacionPieles;

@Named(value = "preparacionBean")
@ViewScoped
public class PreparacionPielesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PreparacionPieles> listarPreparacion;
	private PreparacionPieles prePieles;

	private List<Etapa1> listaEtapa1;
	private Etapa1 etapa1;
	private OperacionLavadoras operacionLavadoras;

	private List<FacturasPieles> listaFacturaPieles;
	private FacturasPieles facturasPieles;

	private Lavadoras lavadoras;

	private String filterLavadoras;

	private List<OperacionLavadoras> listaOperaciones;

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
	}

	public PreparacionPieles getPrePieles() {
		return prePieles;
	}

	public void setPrePieles(PreparacionPieles prePieles) {
		this.prePieles = prePieles;
	}

	public List<PreparacionPieles> getListarPreparacion() {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		listarPreparacion = pDao.listaPreparacionPieles();
		return listarPreparacion;
	}

	public List<FacturasPieles> getListaFacturaPieles() {
		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
		listaFacturaPieles = fDao.listaFacturaPieles();
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

	public List<OperacionLavadoras> getListaOperaciones() {
		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();
		listaOperaciones = oDao.listaOperacionLavadoras();
		return listaOperaciones;
	}

	public void guardarPreparacion() throws SQLException {
		IPreparacionPielesDao pDao = new PreparacionDaoImpl();
		prePieles.setIdPreparacion(buscarFolio());

		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();

		int idPre = 0;

		idPre = pDao.guardarPreparacionPieles(prePieles);

		PreparacionPieles pPieles = new PreparacionPieles();
		pPieles.setIdPreparacion(idPre);
		facturasPieles.setPreparacionPieles(pPieles);

		IOperacionLavadorasDao oDao = new OperacionLavadorasDaoImpl();

		PreparacionPieles pieles = new PreparacionPieles();
		pieles.setIdPreparacion(idPre);
		operacionLavadoras.setPreparacionPieles(pieles);
		oDao.guardarOperacionLavadoras(operacionLavadoras);

		fDao.guardarFacturasPieles(facturasPieles);

		String[] listaEtapas = { "Carga", "Lavada de Carga", "Blanqueo", "Lavadas de Blanqueo", "Pre Acidulación",
				"Acidulación", "Control 1", "Control 2", "Control 3", "Control 4", "Control 5", "Control 6",
				"Control 7", "Control 8" };
		List<Etapa1> listaAgregarEtapas = new ArrayList<>();

		for (String lEtapas : listaEtapas) {
			Etapa1 e = new Etapa1();
			e.setPreparacionPieles(pieles);
			e.setEtapa(lEtapas);
			listaAgregarEtapas.add(e);
		}

		IEtapa1Dao eDao = new EtapaDaoImpl();
		eDao.guardarListaEtapas(listaAgregarEtapas);

		actualizarFolio(prePieles.getNoOperacion());
		prePieles = new PreparacionPieles();
		lavadoras = new Lavadoras();
		pieles = new PreparacionPieles();
		operacionLavadoras = new OperacionLavadoras();
		facturasPieles = new FacturasPieles();

	}

	public void guardarFacturaPieles() {
		IFacturaPielesDao fDao = new FacturaPielesDaoImpl();
		fDao.guardarFacturasPieles(facturasPieles);
		facturasPieles = new FacturasPieles();
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
		IEtapa1Dao eDao = new EtapaDaoImpl();
		listaEtapa1 = eDao.listaEtapa1();
		return listaEtapa1;
	}

	public void actualizarEtapa() {
		IEtapa1Dao eDao = new EtapaDaoImpl();
		eDao.actualizarEtapa1(etapa1);
		etapa1 = new Etapa1();
	}

	public void onRowEdit(RowEditEvent<Etapa1> event) {

		FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getIdEtapa()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent<Etapa1> event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getEtapa()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
