package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaVotatorA;
import com.dmjm.model.VotatorA;

public interface ILimpiezaVotatorADao {

	List<LimpiezaVotatorA> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaVotatorA limpieza);

	void actualizarLimpieza(LimpiezaVotatorA limpieza);

	int validarNoLimpieza(int folioPrep);

	List<VotatorA> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
