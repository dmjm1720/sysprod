package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.ResumenTraspasos;

public interface IResumenTraspasosDao {

	List<ResumenTraspasos> listarResumenTraspasos();

	void guardarResumenTraspasos(ResumenTraspasos resumenTraspasos);

	void actualizarResumenTraspasos(ResumenTraspasos resumenTraspasos);

	void borarResumenTraspasos(ResumenTraspasos resumenTraspasos);

	int validarCantidadRestante(int noOperacion);
	
	int validarCantidadLavdorasChicas(int noOperacion);

}
