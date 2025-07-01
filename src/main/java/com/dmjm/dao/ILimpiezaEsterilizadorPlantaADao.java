package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LimpiezaEstA;

public interface ILimpiezaEsterilizadorPlantaADao {
	List<LimpiezaEstA> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaEstA limpieza);

	void actualizarLimpieza(LimpiezaEstA limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List<LimpiezaEstA> listarTodo();
	
	void actualizarTodoLimpieza();
	
}
