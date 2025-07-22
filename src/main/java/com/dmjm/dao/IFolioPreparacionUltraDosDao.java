package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionUltraDos;

public interface IFolioPreparacionUltraDosDao {

	int returnIDGuardarFolio(int folio);

	FolioPreparacionUltraDos retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioUltraDosActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionUltraDos> listaDeFolioUltraDos(int folio);

}
