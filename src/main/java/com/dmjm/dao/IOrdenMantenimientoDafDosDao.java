package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoDafDos;

public interface IOrdenMantenimientoDafDosDao {

	List<OrdenMantenimientoDafDos> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoDafDos mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoDafDos OrdenMantenimiento);

	void borrarOrdenManto(OrdenMantenimientoDafDos mantenimiento);
}
