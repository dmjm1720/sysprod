package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.UltrafiltracionUno;

public interface IUltraFiltracionUnoDao {
	List<UltrafiltracionUno> listaUltrafiltracion();

	List<UltrafiltracionUno> listaFiltroUltrafiltracion();

	List<UltrafiltracionUno> listaPorFechaUltrafiltracion(Date fecha);

	void guardarUltrafiltracion(UltrafiltracionUno ultra);

	void actualizarUltrafiltracion(UltrafiltracionUno ultra);

	void actualizarUltrafiltracionPromedio(String operacion, int folio);

	void actualizarPromedioFlujoEnt(int folio);

	void actualizarPromedioTemp01(int folio);

	void actualizarPromedioConcEnt(int folio);

	void actualizarPromedioTemp02(int folio);

	void actualizarPromedioPresionPSI01(int folio);

	void actualizarPromedioFlujoPerm01(int folio);

	void actualizarPromedioConcSalida(int folio);

	void actualizarPromedioFlujoSalida(int folio);

	void actualizarPromedioConcPermeado(int folio);

}
