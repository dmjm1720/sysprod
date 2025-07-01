package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LimpiezaUltraUno;

public interface ILimpiezaUltraUnoDao {
	
	List<LimpiezaUltraUno> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaUltraUno limpieza);

	void actualizarLimpieza(LimpiezaUltraUno limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List <LimpiezaUltraUno> listarTodo();
	
	void actualizarTodoLimpieza();

}
