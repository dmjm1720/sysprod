package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.OperacionLavadoras;

public interface IOperacionLavadorasDao {
	List<OperacionLavadoras> listaOperacionLavadoras();
	
	void guardarOperacionLavadoras(OperacionLavadoras operacionLavadoras);
	
	void actualizarOperacionLavadoras(OperacionLavadoras operacionLavadoras);
}
