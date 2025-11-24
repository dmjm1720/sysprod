package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoLuwaTres;

public interface IOrdenMantoLuwaTresDao {

	List<OrdenMantenimientoLuwaTres> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoLuwaTres mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoLuwaTres OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoLuwaTres mantenimiento);
}
