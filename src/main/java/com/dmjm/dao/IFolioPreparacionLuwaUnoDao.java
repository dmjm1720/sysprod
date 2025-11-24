package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionLuwaUno;

public interface IFolioPreparacionLuwaUnoDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionLuwaUno retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioLuwaUnoActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionLuwaUno> listaFolioLuwaUno(int folio);
}
