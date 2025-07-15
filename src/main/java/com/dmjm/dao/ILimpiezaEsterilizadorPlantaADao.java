package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaEstA;

public interface ILimpiezaEsterilizadorPlantaADao {
	List<LimpiezaEstA> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaEstA limpieza);

	void actualizarLimpieza(LimpiezaEstA limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaEstA> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
