package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.Remolienda;

public interface IRemoliendaDao {
	
	List<Remolienda> listaRemolienda();

	List<Remolienda> listaFiltroRemolienda();

	List<Remolienda> listaPorFechaRemolienda(Date fecha);

	void guardarRemolienda(Remolienda remolienda);

	void actualizarRemolienda(Remolienda remolienda);

	void borrarRemolienda(Remolienda remolienda);
	

}
