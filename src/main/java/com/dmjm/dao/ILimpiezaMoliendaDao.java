package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaMolienda;

public interface ILimpiezaMoliendaDao {

	List<LimpiezaMolienda> listarLimpieza(int folio);

	void guardarLimpiezaMolienda(LimpiezaMolienda molienda);

	void actualizarLimpiezaMolienda(LimpiezaMolienda molienda);

	void borrarLimpiezaMolienda(LimpiezaMolienda molienda);

	int validarNoLimpieza(int folio);

	List<LimpiezaMolienda> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
