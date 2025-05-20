package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.FolioCocedores;

public interface IFolioCocedoresDao {

	List<FolioCocedores> listaFolioCocedores();

	void guardarFolioCocedores(FolioCocedores fc);

	void actualizarFolioCocedores(FolioCocedores fc);
	
	int buscarFolio(int year);
	
	void actualizarFolio(int year, int folio);

}
	