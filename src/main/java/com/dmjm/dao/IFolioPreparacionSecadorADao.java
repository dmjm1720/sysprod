package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionSecadorA;

public interface IFolioPreparacionSecadorADao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionSecadorA retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioSecadorAActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionSecadorA> listaFolioSecadorA(int folio);

}
