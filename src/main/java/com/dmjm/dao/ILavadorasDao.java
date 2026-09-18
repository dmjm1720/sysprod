package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Lavadoras;

public interface ILavadorasDao {

	List<Lavadoras> listaLavadoras();

	void guardarLavadoras(Lavadoras lavadoras);

	void actualizarLavadoras(Lavadoras lavadoras);

	List<String> completeLavadoras(String nombre) throws SQLException;

	int buscarLavadora(String nombre) throws SQLException;

	void actualizarEstadoLavadora(int estado, String etapa, int idEntrada);

	Lavadoras listarCapaciad(Lavadoras l);

	List<Lavadoras> listarLavadorasDisponibles();

	void actualizarEtapaLavadora(String etapa, int id);

	void borrarLavadora(Lavadoras lavadoras);

	List<Lavadoras> listaLavadorasChicas();

	void actualizarEstadoLavadora(int estado, String descripcion);

	void actualizarEstadoLavadoraChica(int estado, String etapa, String descripcion);

	List<Lavadoras> listaLavadorasChicasEncimar();

	void actualizarOperacionLavadora(int operacion, String descripcion);

	List<Lavadoras> listarLavodorasPorOperacion(int operacion);
	
	List<String> listaDeOperaciones(Integer operacion) throws SQLException;
}
