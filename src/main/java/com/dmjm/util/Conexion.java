package com.dmjm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion  extends Configuracion{


	private Connection cnSysProd;



	public Connection getCnSysProd() {
		return cnSysProd;
	}

	public void setCnSysProd(Connection cnSysProd) {
		this.cnSysProd = cnSysProd;
	}
	
	



	public void ConectarSysProd() {
		leerConfig();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.cnSysProd = DriverManager.getConnection(getConexDBSysProd(),
					getConexUser(), getConexPwd());
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void CerrarSysProd() throws SQLException {
		try {
			if ((this.cnSysProd != null) && (!this.cnSysProd.isClosed())) {
				this.cnSysProd.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

}
