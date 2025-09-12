package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionMolienda;

public interface IFolioPreparacionMoliendaDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionMolienda retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioMoliendaActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionMolienda> listaFolioMolienda(int folio);

	FolioPreparacionMolienda folioMoliendaMinimo();

	FolioPreparacionMolienda folioMoliendaMaximo();
	
	FolioPreparacionMolienda folioMoliendaFiltro(int folio);
}
