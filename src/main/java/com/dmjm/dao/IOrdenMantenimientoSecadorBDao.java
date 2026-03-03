package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoSecadorB;

public interface IOrdenMantenimientoSecadorBDao {

	List<OrdenMantenimientoSecadorB> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoSecadorB mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoSecadorB OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoSecadorB mantenimiento);
}
