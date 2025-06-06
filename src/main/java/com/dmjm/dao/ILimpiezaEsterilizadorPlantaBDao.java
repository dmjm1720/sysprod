package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LimpiezaEstB;

public interface ILimpiezaEsterilizadorPlantaBDao {
	List<LimpiezaEstB> listarLimpieza(int folio);

	void guardarLimpieza(LimpiezaEstB limpieza);

	void actualizarLimpieza(LimpiezaEstB limpieza);
}
