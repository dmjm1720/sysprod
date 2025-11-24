package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaLuwaCinco;

public interface ILimpiezaLuwaCincoDao {
	
	List<LimpiezaLuwaCinco> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaLuwaCinco limpieza);

	void actualizarLimpieza(LimpiezaLuwaCinco limpieza);

	int validarNoLimpieza(int folioPrep);

	List<LimpiezaLuwaCinco> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
