package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoLuwaUno;

public interface IOrdenMantoLuwaUnoDao {

	List<OrdenMantenimientoLuwaUno> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoLuwaUno mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoLuwaUno OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoLuwaUno mantenimiento);
}
