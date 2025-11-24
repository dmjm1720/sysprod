package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionLuwaTres;

public interface IFolioPreparacionLuwaTresDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionLuwaTres retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioLuwaTresActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionLuwaTres> listaFolioLuwaTres(int folio);
}
