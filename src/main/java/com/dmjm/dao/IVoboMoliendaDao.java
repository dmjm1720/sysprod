package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.VoboMolienda;

public interface IVoboMoliendaDao {
	
	List<VoboMolienda> listaVoboMolienda(int folio);
	
	void guardarVoboMolienda(VoboMolienda vobo);
	
	void actualizarVoboMolienda(VoboMolienda vobo);
	
	void borrarVoboMolienda(VoboMolienda vobo);
	
	VoboMolienda molienda(int folio);

}
