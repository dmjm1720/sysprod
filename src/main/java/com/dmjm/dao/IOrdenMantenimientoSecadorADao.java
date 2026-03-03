package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoDafUno;
import com.dmjm.model.OrdenMantenimientoSecadorA;

public interface IOrdenMantenimientoSecadorADao {

	List<OrdenMantenimientoSecadorA> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoSecadorA mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoSecadorA OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoSecadorA mantenimiento);
}
