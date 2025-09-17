package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionVotatorB;

public interface IFolioPreparacionVotatorBDao {

	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionVotatorB retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioVotatorBActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionVotatorB> listaFolioVotatorB(int folio);

}
