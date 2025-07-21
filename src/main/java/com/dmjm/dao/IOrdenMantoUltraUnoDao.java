package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OrdenMantenimientoUltraUno;

public interface IOrdenMantoUltraUnoDao {
	List<OrdenMantenimientoUltraUno> listaOrdenManto(int folio);

	void guardarOrdenManto(OrdenMantenimientoUltraUno mantenimiento);

	void actualizarOrdenManto(OrdenMantenimientoUltraUno OrdenMantenimiento);
	
	void borrarOrdenManto(OrdenMantenimientoUltraUno mantenimiento);


}
