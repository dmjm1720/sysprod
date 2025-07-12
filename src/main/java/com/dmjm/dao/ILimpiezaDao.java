package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Limpieza;

public interface ILimpiezaDao {
	List<Limpieza> listarLimpieza(int folio);

	void guardarLimpieza(Limpieza limpieza);

	void actualizarLimpieza(Limpieza limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List<Limpieza> listarTodo();
	
	void actualizarTodoLimpieza();
	
	List<Integer> noLimpieza(int folio) throws SQLException;
	
	void borrarLimpieza(int folio, int noLimpieza);
	
	void agregarVoBo(int folio, int noLimpieza, int idUsuario);
	
	void borrarVoBo(int folio, int noLimpieza);
}
