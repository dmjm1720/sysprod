package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoVotatorA;

public interface IOrdenMantoVotatorADao {
	
	List<OrdenMantenimientoVotatorA> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoVotatorA mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoVotatorA OrdenMantenimiento);
	
	void borrarOrdenManto(OrdenMantenimientoVotatorA mantenimiento);

}
