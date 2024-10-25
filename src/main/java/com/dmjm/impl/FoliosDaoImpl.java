package com.dmjm.impl;

import com.dmjm.dao.IFoliosDao;
import com.dmjm.util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoliosDaoImpl extends Conexion implements IFoliosDao {

    @Override
    public int folioMax(int year, String mes) {
        int folio = 0;
        try {

            ConectarSysProd();
            PreparedStatement st = getCnSysProd().prepareStatement("SELECT MAX(" + mes + ")+1 FOLIO FROM FOLIOS WHERE YEAR='" + year + "'");
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
    public void actualizarFolio(int year, String mes, int folio) {

        try {
            ConectarSysProd();
            PreparedStatement ps = getCnSysProd().prepareStatement("UPDATE FOLIOS SET " + mes + "=" + folio + " WHERE YEAR =" + year + "");
            ps.executeUpdate();
            CerrarSysProd();
        } catch (SQLException ex) {
            Logger.getLogger(FoliosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
