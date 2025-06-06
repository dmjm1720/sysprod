package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoEstB;

public interface IOrdenMantoEsterilizadorPalantaBDao {
	List<OrdenMantenimientoEstB> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoEstB mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoEstB OrdenMantenimiento);
}
