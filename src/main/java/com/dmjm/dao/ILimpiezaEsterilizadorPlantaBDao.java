package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaEstB;

public interface ILimpiezaEsterilizadorPlantaBDao {
	List<LimpiezaEstB> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaEstB limpieza);

	void actualizarLimpieza(LimpiezaEstB limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaEstB> listarTodo();
	
	void actualizarTodoLimpieza();
	
	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
