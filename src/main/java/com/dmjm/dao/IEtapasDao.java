package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.Etapas;

public interface IEtapasDao {

	List<Etapas> listaEtapas(int id); // ETAPA 1

	List<Etapas> listaEtapas2(int id); // ETAPA 2

	List<Etapas> listaEtapas3(int id); // ETAPA 3 APARTADO 1

	List<Etapas> listaEtapas4(int id); // ETAPA 3 APARTADO 2

	List<Etapas> listaEtapas5(int id); // ETAPA 3 APARTADO 3

	void guardarEtapas(Etapas etapas);

	void actualizarEtapas(Etapas etapas);

	void guardarListaEtapas(List<Etapas> lista);

	Etapas estado(Etapas etapas);

	void actualizarSiguienteEtapa(Etapas e);

	// void actualizarSiguienteEtapa(Date fecIni, Date horaIni, boolean estado, int
	// idEtapa, String usuario);

	void actualizarEtapaCambioLavadora(int idPreparacion, String etapa, String lavadora);

	List<String> listaProcesosPendientesEtapa(int idPreparacion);

	void actualizarHoraFinEtapaAnterior(Date fecFinal, int idEtapa);

	List<Etapas> listaEtapasLavadorasChicas(int idPreparacion);
	
	int cantidadTraspaso(int idEtapa);
	
	int cantidadMasEncimado(int idEtapa);
	
	void actualizarCantidad(int nuevaCantidad, int idEtapa);
	
	Etapas cantidadTraspasoEncimado(int lavadora);
}
