package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaLuwaTres;

public interface ILimpiezaLuwaTresDao {
	
	List<LimpiezaLuwaTres> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaLuwaTres limpieza);

	void actualizarLimpieza(LimpiezaLuwaTres limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaLuwaTres> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
