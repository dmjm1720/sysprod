package com.dmjm.dao;

import java.util.Date;

import com.dmjm.model.FolioPreparacionEstB;

public interface IFolioPreparacionEsterilizadorPlantaBDao {
	
	int returnIDGuardarFolio (int folio);
	
	FolioPreparacionEstB retornarFechaActual();
	
	int fechaFolioActual(Date fecha);
	
	int folioEstBActual(Date fecha);

}
