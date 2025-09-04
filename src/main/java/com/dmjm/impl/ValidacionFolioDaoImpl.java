package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmjm.dao.IValidacionFolioDao;
import com.dmjm.util.Conexion;

public class ValidacionFolioDaoImpl extends Conexion implements IValidacionFolioDao {
	private static final Logger LOGGER = LogManager.getLogger(ValidacionFolioDaoImpl.class.getName());

	@Override
	public boolean validarFolio(Date fecha, String tabla) {
		boolean validacion = false;
		try {
			ConectarSysProd();
			String sql = "SELECT FECHA FROM " + tabla + " WHERE FECHA=?";
			PreparedStatement st = getCnSysProd().prepareStatement(sql);
			st.setDate(1, new java.sql.Date(fecha.getTime()));
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				validacion = true;
				LOGGER.info("LA FECHA EXISTE: " + rs.getDate("FECHA") + " " + tabla);
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTENER LA FECHA: " + ex);
		}
		return validacion;
	}

}
