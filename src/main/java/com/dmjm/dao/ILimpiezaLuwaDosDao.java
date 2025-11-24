package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaLuwaDos;

public interface ILimpiezaLuwaDosDao {
	
	List<LimpiezaLuwaDos> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaLuwaDos limpieza);

	void actualizarLimpieza(LimpiezaLuwaDos limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaLuwaDos> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
