package com.dmjm.util;

public class Configuracion {

	// **Windows**//
	//private static final String conexDBSysProd = "jdbc:sqlserver://189.203.14.124;databaseName=SYSPROD";
	
	private static final String conexDBSysProd = "jdbc:sqlserver://192.168.0.11;databaseName=SYSPROD";
	private static final String conexUser = "sa";
	private static final String conexPwd = "S1st3m4S*Duch3";
	private static final String conexPwd1 = "S1st3m4S*Duch3";

//	private static final String conexDBSysProd = "jdbc:sqlserver://192.168.1.6;databaseName=SYSPROD";
//	private static final String conexUser = "sa";
//	private static final String conexPwd = "sistemas24";
//	private static final String conexPwd1 = "sistemas24";

	// **Linux**//

	public static String getConexUser() {
		return conexUser;
	}

	public static String getConexPwd() {
		return conexPwd;
	}

	public static String getConexDBSysProd() {
		return conexDBSysProd;
	}

	public static String getConexPwd1() {
		return conexPwd1;
	}

}
