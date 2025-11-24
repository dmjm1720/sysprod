package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoLuwaDos;

public interface IOrdenMantoLuwaDosDao {

	List<OrdenMantenimientoLuwaDos> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoLuwaDos mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoLuwaDos OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoLuwaDos mantenimiento);
}
