package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.EntradasImportacion;

public interface IEntradasImportacionDao {
	
	List<EntradasImportacion> listarEntradas();

	void guardarEntradasImportacion(EntradasImportacion entradasImportacion);

	void actualizarEntradasImportacion(EntradasImportacion entradasImportacion);

}
