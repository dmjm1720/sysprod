package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.FolioProcesos;

public interface IFolioProcesosDao {

	List<FolioProcesos> listaFolioProcesos();

	void guardarFolioProcesos(FolioProcesos fc);

	void actualizarFolioProcesos(FolioProcesos fc);

	int buscarFolio(int year);

	void actualizarFolio(int year, int folio);

	int buscarFolioB(int year);

	void actualizarFolioB(int year, int folio);

	int buscarFolioUltraUno(int year);

	void actualizarFolioUltraUno(int year, int folio);

	int buscarFolioUltraDos(int year);

	void actualizarFolioUltraDos(int year, int folio);

	int buscarFolioVotatorA(int year);

	void actualizarFolioVotatorA(int year, int folio);

	int buscarFolioVotatorB(int year);

	void actualizarFolioVotatorB(int year, int folio);
	
	int buscarFolioLuwaUno(int year);

	void actualizarFolioLuwaUno(int year, int folio);
	
	int buscarFolioLuwaDos(int year);

	void actualizarFolioLuwaDos(int year, int folio);

	int buscarFolioLuwaTres(int year);

	void actualizarFolioLuwaTres(int year, int folio);
	
	int buscarFolioLuwaCuatro(int year);

	void actualizarFolioLuwaCuatro(int year, int folio);
	
	int buscarFolioLuwaCinco(int year);

	void actualizarFolioLuwaCinco(int year, int folio);
	
	int buscarFolioDafUno(int year);

	void actualizarFolioDafUno(int year, int folio);
	
	int buscarFolioDafDos(int year);

	void actualizarFolioDafDos(int year, int folio);
}
