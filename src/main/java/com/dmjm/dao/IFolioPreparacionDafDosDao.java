package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionDafDos;

public interface IFolioPreparacionDafDosDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionDafDos retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioDafDosActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionDafDos> listaFolioDafDos(int folio);

}
