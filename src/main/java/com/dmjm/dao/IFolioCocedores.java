package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.FolioCocedores;

public interface IFolioCocedores {

	List<FolioCocedores> listaFolioCocedores();

	void guardarFolioCocedores(FolioCocedores fc);

	void actualizarFolioCocedores(FolioCocedores fc);

}
	