package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.Molienda;

public interface IMoliendaDao {

	List<Molienda> listaMolienda();

	List<Molienda> listaFiltroMolienda();

	List<Molienda> listaPorFechaMolienda(Date fecha);

	void guardarMolienda(Molienda molienda);

	void actualizarMolienda(Molienda molienda);

	void borrarMolienda(Molienda molienda);
	

}
