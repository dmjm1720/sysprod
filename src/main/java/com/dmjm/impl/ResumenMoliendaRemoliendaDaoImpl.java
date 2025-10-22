package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dmjm.dao.IResumenTotalMolliendaRemoliendaDao;
import com.dmjm.util.Conexion;
import com.dmjm.util.ResumenMoliendaRemolienda;

public class ResumenMoliendaRemoliendaDaoImpl extends Conexion implements IResumenTotalMolliendaRemoliendaDao {

	@Override
	public ResumenMoliendaRemolienda resumenMolienda(int year, int mes) throws SQLException {
		ResumenMoliendaRemolienda resumen = new ResumenMoliendaRemolienda();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd().prepareStatement(
				"SELECT SUM(CASE WHEN OPERACION LIKE 'BRA%' AND OPERACION NOT LIKE '%R' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS RETAL,"
						+ " SUM(CASE WHEN OPERACION LIKE 'BS%' AND OPERACION NOT LIKE '%R' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS CERDO,"
						+ " SUM(CASE WHEN OPERACION LIKE 'BRA%' AND OPERACION LIKE '%R' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS RETAL_RECUPERADO,"
						+ " SUM(CASE WHEN OPERACION LIKE 'BS%' AND OPERACION LIKE '%R' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS CERDO_RECUPERADO"
						+ " FROM MOLIENDA" + " WHERE YEAR(FECHA) = ? AND MONTH(FECHA) = ?;");

		st.setInt(1, year);
		st.setInt(2, mes);
		ResultSet rs = st.executeQuery();

		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				resumen.setTotalRetal(rs.getInt("RETAL"));
				resumen.setTotalCerdo(rs.getInt("CERDO"));
				resumen.setTotalRetalRecuperada(rs.getInt("RETAL_RECUPERADO"));
				resumen.setTotalCerdoRecuperada(rs.getInt("CERDO_RECUPERADO"));
			}
		}

		CerrarSysProd();

		return resumen;
	}

	@Override
	public ResumenMoliendaRemolienda resumenRemolienda(int year, int mes) throws SQLException {
		ResumenMoliendaRemolienda resumen = new ResumenMoliendaRemolienda();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd().prepareStatement(
				"SELECT SUM(CASE WHEN REMOLIENDA_REPROCESO ='RM' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS REMOLIENDA,"
						+ " SUM(CASE WHEN REMOLIENDA_REPROCESO ='RP' THEN M100_KG_TOTALES + M60_KG_TOTALES + M30_KG_TOTALES + M8_KG_TOTALES ELSE 0 END) AS REPROCESO"
						+ " FROM REMOLIENDA" + " WHERE YEAR(FECHA) = ? AND MONTH(FECHA) = ?;");

		st.setInt(1, year);
		st.setInt(2, mes);
		ResultSet rs = st.executeQuery();

		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				resumen.setTotalRemolienda(rs.getInt("REMOLIENDA"));
				resumen.setTotalReproceso(rs.getInt("REPROCESO"));
			}
		}

		CerrarSysProd();

		return resumen;
	}

}
