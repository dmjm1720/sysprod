package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaUltraUno;

public interface ILimpiezaUltraUnoDao {
	
	List<LimpiezaUltraUno> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaUltraUno limpieza);

	void actualizarLimpieza(LimpiezaUltraUno limpieza);
	
	int validarNoLimpieza(int folioPrep);
	
	List <LimpiezaUltraUno> listarTodo();
	
	void actualizarTodoLimpieza();
	
	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
