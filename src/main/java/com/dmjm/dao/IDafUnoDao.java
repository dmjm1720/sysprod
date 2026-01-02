package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.DafUno;

public interface IDafUnoDao {

	List<DafUno> listaDaf();

	List<DafUno> listaFiltroDaf();

	List<DafUno> listaPorFechaDaf(Date fecha);
	
	void guardarDaf(DafUno daf);

	void actualizarDaf(DafUno daf);
	
	void actualizarDafPromedio(String operacion, int folio);
	
	void actualizarConcentradoPorcentaje(int folio);
	
	void actualizarFlujoGrenetina(int folio);
	
	void actualizarPH(int folio);
	
	void actualizarNTUEntrada(int folio);
	
	void actualizarNTUSalida(int folio);
	
	void actualizarPresionAire(int folio);
	
	void actualizarFrecBombaFlolucolante(int folio);
	
	void actualizarValvulaRegPresion(int folio);
	
	void actualizarManto(int folio);

	void actualizarLimpieza(int folio, int estado);
	
	List<String> listarOperaciones(int folio); // OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA

	String obtenerPrimeraHora(String operacion, int folio); // OBTERNER LA PRIMERA HORA DE LA OPERACION

	String obtenerUltimaHora(String operacion, int folio);// OBTENER LA ULTIMA HORA DE LA OPERACION

}
