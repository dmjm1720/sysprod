package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.LimpiezaVotatorB;
import com.dmjm.model.VotatorB;

public interface ILimpiezaVotatorBDao {

	List<LimpiezaVotatorB> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaVotatorB limpieza);

	void actualizarLimpieza(LimpiezaVotatorB limpieza);

	int validarNoLimpieza(int folioPrep);

	List<VotatorB> listarTodo();

	void actualizarTodoLimpieza();

	List<Integer> noLimpieza(int folio) throws SQLException;

	void borrarLimpieza(int folio, int noLimpieza);

	void agregarVoBo(int folio, int noLimpieza, int idUsuario);

	void borrarVoBo(int folio, int noLimpieza);

}
