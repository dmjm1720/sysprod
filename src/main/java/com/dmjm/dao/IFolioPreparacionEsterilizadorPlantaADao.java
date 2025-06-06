package com.dmjm.dao;

import java.util.Date;

import com.dmjm.model.FolioPreparacionEstA;

public interface IFolioPreparacionEsterilizadorPlantaADao {
	
	int returnIDGuardarFolio (int folio);
	
	FolioPreparacionEstA retornarFechaActual();
	
	int fechaFolioActual(Date fecha);
	
	int folioEstAActual(Date fecha);

}
