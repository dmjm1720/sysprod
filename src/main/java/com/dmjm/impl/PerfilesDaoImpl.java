package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dmjm.dao.IPerfilesDao;
import com.dmjm.util.Conexion;

public class PerfilesDaoImpl extends Conexion implements IPerfilesDao {

	@Override
	public List<String> completePerfiles(String nombre) throws SQLException {

		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NOMBRE_PERFIL) FROM PERFILES WHERE NOMBRE_PERFIL LIKE '" + nombre + "%'");
		ResultSet rs = st.executeQuery();
		listarTodo = new ArrayList<>();
		if (!rs.isBeforeFirst()) {
			listarTodo.add("No hay resultados para tu búsqueda");
		} else {
			while (rs.next()) {
				listarTodo.add(rs.getString("NOMBRE_PERFIL"));
			}
		}

		CerrarSysProd();
		return listarTodo;
	}

	@Override
	public int buscarPerfil(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_PERFIL FROM PERFILES WHERE NOMBRE_PERFIL = '" + nombre + "'");
		ResultSet rs = st.executeQuery();
		int transportista = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				transportista = rs.getInt("ID_PERFIL");
			}
		}

		CerrarSysProd();
		return transportista;
	}

}
