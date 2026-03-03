package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionSecadorB;

public interface IFolioPreparacionSecadorBDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionSecadorB retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioSecadorBActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionSecadorB> listaFolioSecadorB(int folio);

}
