package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.EntradasMaquinariaEquipo;

public interface IEntradasMaqEqDao {
	List<EntradasMaquinariaEquipo> listarEntradas();

	void guardarEntradasImportacion(EntradasMaquinariaEquipo entradasMaquinariaEquipo);

	void actualizarEntradasImportacion(EntradasMaquinariaEquipo entradasMaquinariaEquipo);

}
