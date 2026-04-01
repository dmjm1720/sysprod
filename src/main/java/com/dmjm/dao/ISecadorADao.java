package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.SecadorA;

public interface ISecadorADao {

	List<SecadorA> listaSecador();

	List<SecadorA> listaFiltroSecador();

	List<SecadorA> listaPorFechaSecador(Date fecha);

	void guardarSecador(SecadorA secador);

	void actualizarSecador(SecadorA secador);

	void actualizarSecadorPromedio(String operacion, int folio);

	void actualizarLimiteUno(int folio);

	void actualizarLimiteDos(int folio);

	void actualizarLimiteTres(int folio);

	void actualizarLimiteCuatro(int folio);

	void actualizarLimiteCinco(int folio);

	void actualizarLimiteSeis(int folio);

	void actualizarLimiteSiete(int folio);

	void actualizarLimiteOcho(int folio);

	void actualizarLimiteNueve(int folio);
	
	void actualizarVapor(int folio);

	void actualizarVelTapete(int folio);

	void actualizarManto(int folio);

	void actualizarLimpieza(int folio, int estado);

	List<String> listarOperaciones(int folio); // OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA

	String obtenerPrimeraHora(String operacion, int folio); // OBTERNER LA PRIMERA HORA DE LA OPERACION

	String obtenerUltimaHora(String operacion, int folio);// OBTENER LA ULTIMA HORA DE LA OPERACION

}
