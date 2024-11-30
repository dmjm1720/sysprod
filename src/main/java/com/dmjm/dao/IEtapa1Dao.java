package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Etapa1;

public interface IEtapa1Dao {
	
	List<Etapa1> listaEtapa1();
	
	void guardarEtapa1(Etapa1 etapa1);
	
	void actualizarEtapa1(Etapa1 etapa1);
	
	void guardarListaEtapas(List<Etapa1> lista);
}
