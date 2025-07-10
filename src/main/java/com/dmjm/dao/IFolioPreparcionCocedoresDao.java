package com.dmjm.dao;

import java.util.Date;

import com.dmjm.model.FolioPreparacionCocedores;

public interface IFolioPreparcionCocedoresDao {
	
	int returnIDGuardarFolio (int folio);
	
	FolioPreparacionCocedores retornarFechaActual();
	
	int fechaFolioActual(Date fecha);
	
	int folioCocedorActual(Date fecha);
	
	void actualizarNoCocedor(int folio, String noCocedor);

}
