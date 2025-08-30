package com.dmjm.dao;

public interface IFolioGeneralDao {
	
	int buscarFolio(String tipo);

	void actualizarFolio(String tipo, int folio);

}
