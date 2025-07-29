package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoVotatorB;

public interface IOrdenMantoVotatorBDao {
	
	List<OrdenMantenimientoVotatorB> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoVotatorB mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoVotatorB OrdenMantenimiento);
	
	void borrarOrdenManto(OrdenMantenimientoVotatorB mantenimiento);

}
