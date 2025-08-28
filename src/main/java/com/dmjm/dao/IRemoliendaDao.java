package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.Remolienda;

public interface IRemoliendaDao {
	
	List<Remolienda> listaRemolienda();

	List<Remolienda> listaFiltroRemolienda();

	List<Remolienda> listaPorFechaRemolienda(Date fecha);

	void guardarMolienda(Remolienda remolienda);

	void actualizarMolienda(Remolienda remolienda);

	void borrarMolienda(Remolienda remolienda);
	

}
