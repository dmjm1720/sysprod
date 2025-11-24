package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionLuwaCinco;

public interface IFolioPreparacionLuwaCincoDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionLuwaCinco retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioLuwaCincoActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionLuwaCinco> listaFolioLuwaCinco(int folio);
}
