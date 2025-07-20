package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimiento;

public interface IOrdenMantoDao {

	List<OrdenMantenimiento> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimiento mantenimiento);

	void actualizarOrdenManto(OrdenMantenimiento OrdenMantenimiento);
	
	void borrarOrdenManto(OrdenMantenimiento mantenimiento);
}
