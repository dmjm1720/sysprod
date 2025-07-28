package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.VotatorA;

public interface IVotatorADao {
	List<VotatorA> listaVotator();

	List<VotatorA> listaFiltroVotator();

	List<VotatorA> listaPorFechaVotator(Date fecha);

	void guardarVotator(VotatorA votator);

	void actualizarVotator(VotatorA votator);

	void actualizarVotatorPromedio(String operacion, int folio);

	void actualizarConcentradoRefractometro(int folio);

	void actualizarPH(int folio);

	void actualizarRedoxHumeda(int folio);

	void actualizarTempEntVotator(int folio);

	void actualizarTempSalVotator(int folio);

	void actualizarReceptorPSI(int folio);

	void actualizarSuccion(int folio);

	void actualizarDescarga(int folio);

	void actualizarAceite(int folio);

	void actualizarMotor(int folio);

	void actualizarBombaAlim(int folio);

	void actualizarRedoxSeco(int folio);

	void actualizarManto(int folio);

	void actualizarLimpieza(int folio, int estado);
	
	List<String> listarOperaciones(int folio); //OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA
	
	String obtenerPrimeraHora(String operacion, int folio); //OBTERNER LA PRIMERA HORA DE LA OPERACION
	
	String obtenerUltimaHora(String operacion, int folio);//OBTENER LA ULTIMA HORA DE LA OPERACION
}
