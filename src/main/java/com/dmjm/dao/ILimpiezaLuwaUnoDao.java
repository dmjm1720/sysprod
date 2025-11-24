package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaLuwaUno;

public interface ILimpiezaLuwaUnoDao {
	
	List<LimpiezaLuwaUno> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaLuwaUno limpieza);

	void actualizarLimpieza(LimpiezaLuwaUno limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaLuwaUno> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
