package com.dmjm.util;

public class ResumenMoliendaRemolienda {

	private int totalRetal;
	private int totalCerdo;
	private int totalRetalRecuperada;
	private int totalCerdoRecuperada;
	private int totalProduccion;
	private int totalRemolienda;
	private int totalReproceso;
	private int totalRemoliendaReproceso;
	private int granTotal;

	
	
	public ResumenMoliendaRemolienda() {

	}

	public ResumenMoliendaRemolienda(int totalRetal, int totalCerdo, int totalRetalRecuperada, int totalCerdoRecuperada,
			int totalProduccion, int totalRemolienda, int totalReproceso, int totalRemoliendaReproceso, int granTotal) {
		this.totalRetal = totalRetal;
		this.totalCerdo = totalCerdo;
		this.totalRetalRecuperada = totalRetalRecuperada;
		this.totalCerdoRecuperada = totalCerdoRecuperada;
		this.totalProduccion = totalProduccion;
		this.totalRemolienda = totalRemolienda;
		this.totalReproceso = totalReproceso;
		this.totalRemoliendaReproceso = totalRemoliendaReproceso;
		this.granTotal = granTotal;
	}

	public int getTotalRetal() {
		return totalRetal;
	}

	public void setTotalRetal(int totalRetal) {
		this.totalRetal = totalRetal;
	}

	public int getTotalCerdo() {
		return totalCerdo;
	}

	public void setTotalCerdo(int totalCerdo) {
		this.totalCerdo = totalCerdo;
	}

	public int getTotalRetalRecuperada() {
		return totalRetalRecuperada;
	}

	public void setTotalRetalRecuperada(int totalRetalRecuperada) {
		this.totalRetalRecuperada = totalRetalRecuperada;
	}

	public int getTotalCerdoRecuperada() {
		return totalCerdoRecuperada;
	}

	public void setTotalCerdoRecuperada(int totalCerdoRecuperada) {
		this.totalCerdoRecuperada = totalCerdoRecuperada;
	}

	public int getTotalProduccion() {
		return totalProduccion;
	}

	public void setTotalProduccion(int totalProduccion) {
		this.totalProduccion = totalProduccion;
	}

	public int getTotalRemolienda() {
		return totalRemolienda;
	}

	public void setTotalRemolienda(int totalRemolienda) {
		this.totalRemolienda = totalRemolienda;
	}

	public int getTotalReproceso() {
		return totalReproceso;
	}

	public void setTotalReproceso(int totalReproceso) {
		this.totalReproceso = totalReproceso;
	}

	public int getTotalRemoliendaReproceso() {
		return totalRemoliendaReproceso;
	}

	public void setTotalRemoliendaReproceso(int totalRemoliendaReproceso) {
		this.totalRemoliendaReproceso = totalRemoliendaReproceso;
	}

	public int getGranTotal() {
		return granTotal;
	}

	public void setGranTotal(int granTotal) {
		this.granTotal = granTotal;
	}

}
