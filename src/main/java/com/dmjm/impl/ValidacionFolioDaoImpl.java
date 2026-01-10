package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public List<Date> validarFechasFaltantes(int dias, String tblFolioPrep) {
		List<Date> lista = new ArrayList<>();
	    // Rango esperado
	    //LocalDate fechaFin = LocalDate.now().plusDays(5);
		LocalDate fechaFin = LocalDate.now();
	    LocalDate fechaIni = fechaFin.minusDays(dias);

	    try {
	        ConectarSysProd();

	        // 1. Consultar las fechas guardadas
	        Set<LocalDate> fechasGuardadas = new HashSet<>();
	        PreparedStatement ps = getCnSysProd()
	                .prepareStatement("SELECT FECHA FROM " + tblFolioPrep + " WHERE FECHA BETWEEN ? AND ?");
	        ps.setDate(1, java.sql.Date.valueOf(fechaIni));
	        ps.setDate(2, java.sql.Date.valueOf(fechaFin));

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            fechasGuardadas.add(rs.getDate("FECHA").toLocalDate());
	        }

	        LOGGER.warn("===>>>>>>>>>>FECHAS DE CONSULTA DE: " + fechaIni + " HASTA " + fechaFin + " <<<<<<<<<<===");
	        LOGGER.warn("TABLA CONSULTADA: " + tblFolioPrep);

	        // 2. Calcular la primera fecha registrada en la BD
	        LocalDate primeraFecha = fechasGuardadas.isEmpty() ? fechaIni : Collections.min(fechasGuardadas);

	        // 3. Validar las faltantes desde fechaIni hasta fechaFin
	        LocalDate fecha = fechaIni;
	        while (!fecha.isAfter(fechaFin)) {
	            // Caso 1: fecha es anterior a la primera registrada en BD → faltante
	            if (fecha.isBefore(primeraFecha)) {
	                LOGGER.warn("FECHA FALTANTE (antes de primera en BD): " + fecha);
	                
	            }
	            // Caso 2: fecha en rango pero no existe en BD → faltante
	            else if (!fechasGuardadas.contains(fecha)) {
	                LOGGER.warn("FECHA FALTANTE: " + fecha);
	                lista.add(java.sql.Date.valueOf(fecha));
	            }
	            // Caso 3: fecha existe en BD → se omite

	            fecha = fecha.plusDays(1);
	        }

	        CerrarSysProd();

	    } catch (SQLException ex) {
	        LOGGER.error("ERROR AL OBTENER LA FECHA: " + ex);
	    }

	    return lista;

	}

}
