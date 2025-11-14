package com.dmjm.bean;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.IResumenTotalMolliendaRemoliendaDao;
import com.dmjm.impl.ResumenMoliendaRemoliendaDaoImpl;
import com.dmjm.util.ResumenMoliendaRemolienda;

@Named("resumen")
@ViewScoped
public class ResumenTotalesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResumenMoliendaRemolienda resumenTotales;
	private ResumenMoliendaRemolienda resumenTotalesRemolienda;
	private int year;
	private int mes;

	public ResumenTotalesBean() {

	}

	@PostConstruct
	public void init() {
		resumenTotales = new ResumenMoliendaRemolienda();
		resumenTotalesRemolienda = new ResumenMoliendaRemolienda();
	}

	public ResumenMoliendaRemolienda getResumenTotales() {
		return resumenTotales;
	}

	public void setResumenTotales(ResumenMoliendaRemolienda resumenTotales) {
		this.resumenTotales = resumenTotales;
	}

	public ResumenMoliendaRemolienda getResumenTotalesRemolienda() {
		return resumenTotalesRemolienda;
	}

	public void setResumenTotalesRemolienda(ResumenMoliendaRemolienda resumenTotalesRemolienda) {
		this.resumenTotalesRemolienda = resumenTotalesRemolienda;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void totales() throws SQLException {
		IResumenTotalMolliendaRemoliendaDao mDao = new ResumenMoliendaRemoliendaDaoImpl();
		resumenTotales = mDao.resumenMolienda(year, mes);

		resumenTotales.setTotalProduccion(resumenTotales.getTotalRetal() + resumenTotales.getTotalCerdo()
				+ resumenTotales.getTotalRetalRecuperada() + resumenTotales.getTotalCerdoRecuperada());

		IResumenTotalMolliendaRemoliendaDao rDao = new ResumenMoliendaRemoliendaDaoImpl();
		resumenTotalesRemolienda = rDao.resumenRemolienda(year, mes);

		resumenTotalesRemolienda.setTotalRemoliendaReproceso(
				resumenTotalesRemolienda.getTotalRemolienda() + resumenTotalesRemolienda.getTotalReproceso());

		resumenTotalesRemolienda.setGranTotal(
				resumenTotalesRemolienda.getTotalRemoliendaReproceso() + resumenTotales.getTotalProduccion());
	}

}
