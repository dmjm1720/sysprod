package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionEstB;

public interface IFolioPreparacionEsterilizadorPlantaBDao {

	int returnIDGuardarFolio(int folio);

	FolioPreparacionEstB retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioEstBActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionEstB> listaFolioEstB(int folio);

}
