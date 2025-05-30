package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dmjm.dao.ITurnosDao;
import com.dmjm.util.Conexion;

public class TurnosDaoImpl extends Conexion implements ITurnosDao {

	@Override
	public List<String> completeTurnos(String nombre) throws SQLException {
		List<String> resultTurnos = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NOMBRE_TURNO) FROM TURNOS WHERE NOMBRE_TURNO LIKE '" + nombre + "%'");
		ResultSet rs = st.executeQuery();
		listarTodo = new ArrayList<>();
		if (!rs.isBeforeFirst()) {
			listarTodo.add("No hay resultados para tu búsqueda");
		} else {
			while (rs.next()) {
				listarTodo.add(rs.getString("NOMBRE_TURNO"));
			}
		}
		for (int i = 0; i < listarTodo.size(); i++) {
			resultTurnos.add(listarTodo.get(i));
		}

		CerrarSysProd();
		return resultTurnos;
	}

	@Override
	public int buscarTurnos(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_TURNO FROM TURNOS WHERE NOMBRE_TURNO = '" + nombre + "'");
		ResultSet rs = st.executeQuery();
		int turno = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				turno = rs.getInt("ID_TURNO");
			}
		}

		CerrarSysProd();
		return turno;
	}

}
