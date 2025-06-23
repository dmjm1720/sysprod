package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.UltrafiltracionDos;

public interface IUltraFiltracionDosDao {
	List<UltrafiltracionDos> listaUltrafiltracion();

	List<UltrafiltracionDos> listaFiltroUltrafiltracion();

	List<UltrafiltracionDos> listaPorFechaUltrafiltracion(Date fecha);

	void guardarUltrafiltracion(UltrafiltracionDos ultra);

	void actualizarUltrafiltracion(UltrafiltracionDos ultra);

	void actualizarUltrafiltracionPromedio(String operacion, int folio);

	void actualizarPromedioPresionEntrada(int folio);

	void actualizarPromedioPresionSalida(int folio);

	void actualizarPromedioFlujoEnt(int folio);

	void actualizarPromedioTemp01(int folio);

	void actualizarPromedioConcEnt(int folio);

	void actualizarPromedioPH(int folio);

	void actualizarPromedioPresionBar(int folio);

	void actualizarPromedioPorcentajeBomba(int folio);

	void actualizarPromedioTemp02(int folio);

	void actualizarPromedioPresionPSI01(int folio);

	void actualizarPromedioFlujoPerm01(int folio);

	void actualizarPromedioTemp03(int folio);

	void actualizarPromedioPresionPSI02(int folio);

	void actualizarPromedioFlujoPerm02(int folio);

	void actualizarPromedioTemp04(int folio);

	void actualizarPromedioPresionPSI03(int folio);

	void actualizarPromedioFlujoPerm03(int folio);

	void actualizarPromedioTemp05(int folio);

	void actualizarPromedioPresionPSI04(int folio);

	void actualizarPromedioFlujoPerm04(int folio);

	void actualizarPromedioConcSalida(int folio);

	void actualizarPromedioRatio(int folio);

	void actualizarPromedioFlujoSalida(int folio);

	void actualizarPromedioConcPermeado(int folio);

}
