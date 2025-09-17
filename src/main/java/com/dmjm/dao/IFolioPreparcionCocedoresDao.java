package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.FolioPreparacionCocedores;

public interface IFolioPreparcionCocedoresDao {
	
	int returnIDGuardarFolio (int folio, Date fecha);
	
	FolioPreparacionCocedores retornarFechaActual();
	
	int fechaFolioActual(Date fecha);
	
	int folioCocedorActual(Date fecha);
	
	void actualizarNoCocedor(int folio, String noCocedor);
	
	void guardarObservacion(int folio, String observacion);
	
	List<FolioPreparacionCocedores> listaFolioCocedores(int folio);

}
