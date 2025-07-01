package com.dmjm.dao;

import java.util.Date;

import com.dmjm.model.FolioPreparacionUltraUno;

public interface IFolioPreparacionUltraUnoDao {

	int returnIDGuardarFolio(int folio);

	FolioPreparacionUltraUno retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioUltraUnoActual(Date fecha);
	

}
