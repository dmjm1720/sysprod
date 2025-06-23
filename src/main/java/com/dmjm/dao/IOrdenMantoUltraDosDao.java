package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoUltraDos;

public interface IOrdenMantoUltraDosDao {
	List<OrdenMantenimientoUltraDos> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoUltraDos mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoUltraDos OrdenMantenimiento);

}
