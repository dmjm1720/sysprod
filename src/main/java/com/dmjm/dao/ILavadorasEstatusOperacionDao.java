package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LavadorasEstatusOperacion;

public interface ILavadorasEstatusOperacionDao {

	List<LavadorasEstatusOperacion> listarLavadorasEstatusOperacion();

	void guardarLavadorasEstatusOperacion(LavadorasEstatusOperacion lavadorasEstatusOperacion);

	void actualizarLavadoraTrue(String columna, int operacion);

	void actualizarLavadoraFalse(String columna, int operacion);
	
	int validarRegistro(int operacion);

}
