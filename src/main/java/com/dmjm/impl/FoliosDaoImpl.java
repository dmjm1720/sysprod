package com.dmjm.impl;

import com.dmjm.bean.EntradasBean;
import com.dmjm.dao.IFoliosDao;
import com.dmjm.util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FoliosDaoImpl extends Conexion implements IFoliosDao {

	private static final Logger LOGGER = LogManager.getLogger(FoliosDaoImpl.class.getName());

	@Override
	public int folioMax(int year, String mes) {
		int folio = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT MAX(" + mes + ")+1 FOLIO FROM FOLIOS WHERE YEAR='" + year + "'");
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					folio = rs.getInt("FOLIO");
					LOGGER.info("EL FOLIO DE LA CONSULTA ES: " + folio);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return folio;
	}

	@Override
	public void actualizarFolio(int year, String mes, int folio) {

		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE FOLIOS SET " + mes + "=" + folio + " WHERE YEAR =" + year + "");
			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: " + ex);
		}

	}

	@Override
	public void habilitarFolio() {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ACTIVACION SET ESTADO = 1 WHERE ID_ACTIVACION=1");
			ps.executeUpdate();
			CerrarSysProd();
			LOGGER.info("BANDERA ACTIVADA PARA CAPTURA DEL MES ANTERIOR");
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTIVAR EL ESTADO DEL MES ANTERIOR: " + ex);
		}

	}

	@Override
	public void deshabilitarFolio() {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ACTIVACION SET ESTADO = 0 WHERE ID_ACTIVACION=1");
			ps.executeUpdate();
			CerrarSysProd();
			LOGGER.info("BANDERA DESACTIVADA PARA CAPTURA DEL MES ANTERIOR");
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL DESACTIVAR EL ESTADO DEL MES ANTERIOR: " + ex);
		}

	}

	@Override
	public int validarFolioMesAnt() {
		int bandera = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT ESTADO FROM ACTIVACION WHERE ID_ACTIVACION=1");
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					bandera = rs.getInt("ESTADO");
					LOGGER.info("ESTADO DE LA BANDERA PARA CAPTURA DEL MES ANTERIOR: " + bandera);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTERNER EL ESTADO DEL MES ANTERIOR: " + ex);
		}
		return bandera;

	}

}
