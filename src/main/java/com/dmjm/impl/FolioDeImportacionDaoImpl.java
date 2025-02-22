package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dmjm.dao.IFolioDeImportacionDao;
import com.dmjm.util.Conexion;

public class FolioDeImportacionDaoImpl extends Conexion implements IFolioDeImportacionDao {

	@Override
	public int buscarFolio() {
		int folio = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT MAX(FOLIO)+1 FOLIO FROM FOLIO_IMPORTACION");
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					folio = rs.getInt("FOLIO");
					System.out.println(folio);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			Logger.getLogger(FoliosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return folio;
	}

	@Override
	public void actualizarFolio(long folio) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd().prepareStatement("UPDATE FOLIO_IMPORTACION SET FOLIO = ?");
			ps.setLong(1, folio);
			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			Logger.getLogger(FolioDeImportacionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
