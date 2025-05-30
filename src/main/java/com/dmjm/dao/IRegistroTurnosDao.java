package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.RegistroTurnos;

public interface IRegistroTurnosDao {
	
	List<RegistroTurnos> listaRegistroTurnos(Date fecha);
	
	void guardaRegistroTurnos(RegistroTurnos registro);
	
	void actualizarRegistroTurnos(RegistroTurnos registro);

}
