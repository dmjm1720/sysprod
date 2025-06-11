package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Operador;

public interface IOperadorDao {

	List<String> completeOperador(String nombre, String depto) throws SQLException;

	int buscarOperador(String nombre, String depto) throws SQLException;
	
	void guardarOperador(Operador operador);

	void actualizarOperador(Operador operador);
	
	List<Operador> listaOperadorCocedores();

	List<Operador> listaOperadorEstPlantaA();
	
	List<Operador> listaOperadorEstPlantaB();
}
