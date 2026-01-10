package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaDafDos;

public interface ILimpiezaDafDosDao {

	List<LimpiezaDafDos> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaDafDos limpieza);

	void actualizarLimpieza(LimpiezaDafDos limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaDafDos> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);
}
