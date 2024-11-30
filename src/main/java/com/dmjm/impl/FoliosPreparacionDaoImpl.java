package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dmjm.dao.IFolioPreparacionDao;
import com.dmjm.util.Conexion;

public class FoliosPreparacionDaoImpl extends Conexion implements IFolioPreparacionDao {

	@Override
	public int buscarFolio() {
		int folio = 0;
        try {

            ConectarSysProd();
            PreparedStatement st = getCnSysProd().prepareStatement("SELECT MAX(FOLIO)+1 FOLIO FROM FOLIOS_PREPARACION");
            ResultSet rs = st.executeQuery();

            if (!rs.isBeforeFirst()) {
            } else {
                while (rs.next()) {
                    folio = rs.getInt("FOLIO");

                }
            }

            CerrarSysProd();

        } catch (SQLException ex) {
            Logger.getLogger(FoliosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return folio;
	}

	@Override
	public void actualizarFolio(int folio) {

        try {
            ConectarSysProd();
            PreparedStatement ps = getCnSysProd().prepareStatement("UPDATE FOLIOS_PREPARACION SET FOLIO='"+ folio +"'");
            ps.executeUpdate();
            CerrarSysProd();
        } catch (SQLException ex) {
            Logger.getLogger(FoliosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
