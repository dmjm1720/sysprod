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
}
