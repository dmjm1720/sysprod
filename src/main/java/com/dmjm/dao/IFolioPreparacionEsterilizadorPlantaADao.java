package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionEstA;

public interface IFolioPreparacionEsterilizadorPlantaADao {

	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionEstA retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioEstAActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionEstA> listaFolioEstA(int folio);

}
