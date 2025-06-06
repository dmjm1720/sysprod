package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoEstA;

public interface IOrdenMantoEsterilizadorPalantaADao {
	List<OrdenMantenimientoEstA> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoEstA mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoEstA OrdenMantenimiento);
}
