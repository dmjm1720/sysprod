package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.EsterilizadorPlantaA;

public interface IEsterilizdorPlantaADao {
	List<EsterilizadorPlantaA> listaEsterilizador();

	List<EsterilizadorPlantaA> listaFiltroEsterilizador();

	List<EsterilizadorPlantaA> listaPorFechaEsterilizador(Date fecha);

	void guardarEsterilizador(EsterilizadorPlantaA esterilizador);

	void actualizarEsterilizador(EsterilizadorPlantaA esterilizador);

	void actualizarEsterilizadorPromedio(String operacion, int folio);

	void actualizarPromedioGrenetina(int folio);

	void actualizarPromedioPresionVapor(int folio);

	void actualizarPromedioPresionVacio(int folio);

	void actualizarPromedioPrecalentador(int folio);

	void actualizarPromedioEsterilizador(int folio);

	void actualizarPromedioValDiv(int folio);

	void actualizarPromedioBombaAlim(int folio);

	void actualizarPromedioTimpoEst(int folio);

	void actualizarPromedioBombaSalida(int folio);

	void actualizarPromedioFlujoLitros(int folio);

	void actualizarPromedioPresioSistema(int folio);

	void actualizarPromedioRedoxEntrada(int folio);

	void actualizarPromedioRedoxSalida(int folio);
	

}
