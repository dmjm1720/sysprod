package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaUltraDos;

public interface ILimpiezaUltraDosDao {
	
	List<LimpiezaUltraDos> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaUltraDos limpieza);

	void actualizarLimpieza(LimpiezaUltraDos limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List<LimpiezaUltraDos> listarTodo();
	
	void actualizarTodoLimpieza();
	
	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
