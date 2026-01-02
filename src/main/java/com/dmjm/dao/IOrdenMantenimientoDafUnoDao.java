package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoDafUno;

public interface IOrdenMantenimientoDafUnoDao {

	List<OrdenMantenimientoDafUno> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoDafUno mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoDafUno OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoDafUno mantenimiento);
}
