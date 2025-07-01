package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LimpiezaUltraDos;

public interface ILimpiezaUltraDosDao {
	
	List<LimpiezaUltraDos> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaUltraDos limpieza);

	void actualizarLimpieza(LimpiezaUltraDos limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List<LimpiezaUltraDos> listarTodo();
	
	void actualizarTodoLimpieza();

}
