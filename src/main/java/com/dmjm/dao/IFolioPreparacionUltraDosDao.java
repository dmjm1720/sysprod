package com.dmjm.dao;

import java.util.Date;

import com.dmjm.model.FolioPreparacionUltraDos;

public interface IFolioPreparacionUltraDosDao {

	int returnIDGuardarFolio(int folio);

	FolioPreparacionUltraDos retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioUltraDosActual(Date fecha);

}
