package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoLuwaCuatro;

public interface IOrdenMantoLuwaCuatroDao {

	List<OrdenMantenimientoLuwaCuatro> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoLuwaCuatro mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoLuwaCuatro OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoLuwaCuatro mantenimiento);
}
