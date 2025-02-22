package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Etapa1;

public interface IEtapa1Dao {
	
	List<Etapa1> listaEtapa1(int id);
	
	void guardarEtapa1(Etapa1 etapa1);
	
	void actualizarEtapa1(Etapa1 etapa1);
	
	void guardarListaEtapas(List<Etapa1> lista);
	
	Etapa1 estado(Etapa1 etapa1);
	
	void actualizarSiguienteEtapa(Etapa1 e);
	
	void actualizarEtapaCambioLavadora(int idPreparacion, String etapa, String lavadora);
	
	List<String> listaProcesosPendientesEtapa(int idPreparacion);
}
