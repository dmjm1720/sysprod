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

	List<RegistroTurnos> listaRegistroTurnosVotatorA(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosVotatorB(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosMolienda(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosLuwaUnoPlantaA(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosLuwaDosPlantaA(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosLuwaTresPlantaA(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosLuwaCincoPlantaA(Date fecha);

	List<RegistroTurnos> listaRegistroTurnosLuwaCuatroPlantaB(Date fecha);

}
