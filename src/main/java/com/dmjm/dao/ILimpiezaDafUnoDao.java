package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaDafUno;




public interface ILimpiezaDafUnoDao {
	List<LimpiezaDafUno> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaDafUno limpieza);

	void actualizarLimpieza(LimpiezaDafUno limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaDafUno> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);
}
