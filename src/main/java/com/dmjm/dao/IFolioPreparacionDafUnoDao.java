package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionDafUno;

public interface IFolioPreparacionDafUnoDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionDafUno retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioDafUnoActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionDafUno> listaFolioDafUno(int folio);

}
