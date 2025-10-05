package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoMolienda;

public interface IOrdenMantenimientoMoliendaDao {

	List<OrdenMantenimientoMolienda> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoMolienda orden);

	void actualizarOrdenManto(OrdenMantenimientoMolienda orden);

	void borrarrOrdenManto(OrdenMantenimientoMolienda orden);

}
