package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.LuwaTres;

public interface ILuwaTresDao {

	List<LuwaTres> listaLuwa();

	List<LuwaTres> listaFiltroLuwa();

	List<LuwaTres> listaPorFechaLuwa(Date fecha);

	void guardarLuwa(LuwaTres luwa);

	void actualizarLuwa(LuwaTres luwa);

	void actualizarLuwaPromedio(String operacion, int folio);

	void actualizarEntradaConcentrado(int folio);

	void actualizarConcentradoPorcentaje(int folio);

	void actualizarPH(int folio);

	void actualizarTempSalida(int folio);

	void actualizarRedox(int folio);

	void actualizarPresionBombaVacio(int folio);

	void actualizarValvulaRegPresion(int folio);

	void actualizarCondensadorTemp(int folio);

	void actualizarCorrienteMotor(int folio);

	void actualizarManto(int folio);

	void actualizarLimpieza(int folio, int estado);

	List<String> listarOperaciones(int folio); // OBTENER LAS DIFERENTES OPERACIONES DE LA CAPTURA

	String obtenerPrimeraHora(String operacion, int folio); // OBTERNER LA PRIMERA HORA DE LA OPERACION

	String obtenerUltimaHora(String operacion, int folio);// OBTENER LA ULTIMA HORA DE LA OPERACION

}
