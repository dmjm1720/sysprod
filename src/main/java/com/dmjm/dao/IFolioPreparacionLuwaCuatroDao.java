package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionLuwaCuatro;

public interface IFolioPreparacionLuwaCuatroDao {
	int returnIDGuardarFolio(int folio, Date fecha);

	FolioPreparacionLuwaCuatro retornarFechaActual();

	int fechaFolioActual(Date fecha);

	int folioLuwaCuatroActual(Date fecha);

	void guardarObservacion(int folio, String observacion);

	List<FolioPreparacionLuwaCuatro> listaFolioLuwaCuatro(int folio);
}
