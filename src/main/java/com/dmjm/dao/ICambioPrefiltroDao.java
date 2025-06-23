package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.CambioPrefiltro;

public interface ICambioPrefiltroDao {
	List<CambioPrefiltro> listarCambioPrefiltro(int folio);

	void guardarCambioPre(CambioPrefiltro cambio);

	void actualizarCambioPre(CambioPrefiltro cambio);
}
