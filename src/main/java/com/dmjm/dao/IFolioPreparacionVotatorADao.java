package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionVotatorA;

public interface IFolioPreparacionVotatorADao {

	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionVotatorA retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioVotatorAActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionVotatorA> listaFolioVotatorA(int folio);

}
