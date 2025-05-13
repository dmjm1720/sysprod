package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.Etapas;

public interface IEtapasDao {
	
	List<Etapas> listaEtapas(int id); //ETAPA 1
	
	List<Etapas> listaEtapas2(int id); //ETAPA 2
	
	List<Etapas> listaEtapas3(int id); //ETAPA 3 APARTADO 1
	 
	List<Etapas> listaEtapas4(int id); //ETAPA 3 APARTADO 2
	
	List<Etapas> listaEtapas5(int id); //ETAPA 3 APARTADO 3
	
	void guardarEtapas(Etapas etapas);
	
	void actualizarEtapas(Etapas etapas);
	
	void guardarListaEtapas(List<Etapas> lista);
	
	Etapas estado(Etapas etapas);
	
	void actualizarSiguienteEtapa(Etapas e);
	
	void actualizarEtapaCambioLavadora(int idPreparacion, String etapa, String lavadora);
	
	List<String> listaProcesosPendientesEtapa(int idPreparacion);
}
