package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Purgas;

public interface IPurgasDao {

	List<Purgas> listaPurgas(int folio);

	void guardaPurgas(Purgas purgas);

	void actualizarPurgas(Purgas purgas);
	
	void borrarPurgas(Purgas purgas);

}
