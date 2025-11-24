package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionLuwaDos;

public interface IFolioPreparacionLuwaDosDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionLuwaDos retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioLuwaDosActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionLuwaDos> listaFolioLuwaDos(int folio);
}
