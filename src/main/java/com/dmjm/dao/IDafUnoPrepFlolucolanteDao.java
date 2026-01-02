package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DafUnoPrepFlolucolante;

public interface IDafUnoPrepFlolucolanteDao {

	List<DafUnoPrepFlolucolante> listaDafUnoPrepFlolucolante();

	void guardarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf);

	void actualizarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf);

	void borrarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf);
	
	int folioMax();
	
	void actualizarFolio(int year, int folio);
	
	int buscarFolio(int year);

	List<Integer> listarFolios(int year);

}
