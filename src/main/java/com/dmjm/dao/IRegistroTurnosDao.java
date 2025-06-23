package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.RegistroTurnos;

public interface IRegistroTurnosDao {
	
	List<RegistroTurnos> listaRegistroTurnos(Date fecha);
	
	void guardaRegistroTurnos(RegistroTurnos registro);
	
	void actualizarRegistroTurnos(RegistroTurnos registro);
	
	void borrarRegistroTurno(RegistroTurnos registro);
	
	
	List<RegistroTurnos> listaRegistroTurnosEstA(Date fecha);
	
	List<RegistroTurnos> listaRegistroTurnosEstB(Date fecha);
	
	List<RegistroTurnos> listaRegistroTurnosUltraUno(Date fecha);
	
	List<RegistroTurnos> listaRegistroTurnosUltraDos(Date fecha);

}
