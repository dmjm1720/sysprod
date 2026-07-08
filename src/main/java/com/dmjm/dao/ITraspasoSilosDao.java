package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.TraspasoSilos;

public interface ITraspasoSilosDao {

	List<TraspasoSilos> listaTraspasos(int idPreparacion);

	void guardarTraspasoSilos(TraspasoSilos trasSilos);

	void actualizarTraspasoSilos(TraspasoSilos trasSilos);

}
