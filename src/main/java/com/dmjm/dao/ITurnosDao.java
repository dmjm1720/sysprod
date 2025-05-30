package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

public interface ITurnosDao {
	
    List<String> completeTurnos(String nombre) throws SQLException;
    
    int buscarTurnos(String nombre) throws SQLException;

}
