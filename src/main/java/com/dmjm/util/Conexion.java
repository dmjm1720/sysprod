package com.dmjm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cnSae;
    private Connection cnCoi;
    private Connection cnSysProd;

    public Connection getCnSae() {
        return cnSae;
    }

    public void setCnSae(Connection cnSae) {
        this.cnSae = cnSae;
    }

    public Connection getCnCoi() {
        return cnCoi;
    }

    public void setCnCoi(Connection cnCoi) {
        this.cnCoi = cnCoi;
    }

    public Connection getCnSysProd() {
        return cnSysProd;
    }

    public void setCnSysProd(Connection cnSysProd) {
        this.cnSysProd = cnSysProd;
    }

 
    

    public void ConectarSae() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnSae = DriverManager.getConnection(Configuracion.getConexDBSae(), Configuracion.getConexUser(), Configuracion.getConexPwd());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarSae() throws SQLException {
        try {
            if ((this.cnSae != null)
                    && (!this.cnSae.isClosed())) {
                this.cnSae.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    public void ConectarCoi() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnCoi = DriverManager.getConnection(Configuracion.getConexDBCoi(), Configuracion.getConexUser(), Configuracion.getConexPwd());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarCoi() throws SQLException {
        try {
            if ((this.cnCoi != null)
                    && (!this.cnCoi.isClosed())) {
                this.cnCoi.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    public void ConectarSysProd() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.cnSysProd = DriverManager.getConnection(Configuracion.getConexDBSysProd(), Configuracion.getConexUser(), Configuracion.getConexPwd1());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CerrarSysProd() throws SQLException {
        try {
            if ((this.cnSysProd != null)
                    && (!this.cnSysProd.isClosed())) {
                this.cnSysProd.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
