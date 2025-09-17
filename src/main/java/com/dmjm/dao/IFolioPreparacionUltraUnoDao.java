package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionUltraUno;

public interface IFolioPreparacionUltraUnoDao {

	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionUltraUno retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioUltraUnoActual(Date fecha);
	
	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionUltraUno> listaDeFolioUltraUno(int folio);
	

}
