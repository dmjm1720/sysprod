package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoLuwaCinco;

public interface IOrdenMantoLuwaCincoDao {

	List<OrdenMantenimientoLuwaCinco> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoLuwaCinco mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoLuwaCinco OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoLuwaCinco mantenimiento);
}
