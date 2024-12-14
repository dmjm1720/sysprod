package com.dmjm.dao;

public interface IFoliosImportacion {

	int folioMax(int year, String mes);

	void actualizarFolio(int year, String mes, int folio);

}
