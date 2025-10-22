package com.dmjm.dao;

import java.sql.SQLException;

import com.dmjm.util.ResumenMoliendaRemolienda;

public interface IResumenTotalMolliendaRemoliendaDao {
	
	ResumenMoliendaRemolienda resumenMolienda(int year, int mes) throws SQLException;
	
	ResumenMoliendaRemolienda resumenRemolienda(int year, int mes) throws SQLException;

}
