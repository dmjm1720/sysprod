package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Limpieza;

public interface ILimpiezaDao {
	List<Limpieza> listarLimpieza(int folio);

	void guardarLimpieza(Limpieza limpieza);

	void actualizarLimpieza(Limpieza limpieza);
}
