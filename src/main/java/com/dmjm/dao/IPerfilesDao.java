package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

public interface IPerfilesDao {
	List<String> completePerfiles(String nombre) throws SQLException;
	
	 int buscarPerfil(String nombre) throws SQLException;
}
