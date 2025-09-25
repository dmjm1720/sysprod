package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.GelatinaPorMoler;

public interface IGelatinaDao {

	List<GelatinaPorMoler> listaGeltatina(int folio);

	void guardarGelatina(GelatinaPorMoler gelPorMoler);

	void actualizarGelatina(GelatinaPorMoler gelPorMoler);

	void borrarGelatina(GelatinaPorMoler gelPorMoler);
}
