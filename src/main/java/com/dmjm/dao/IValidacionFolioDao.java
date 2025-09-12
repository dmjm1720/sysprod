package com.dmjm.dao;

import java.util.Date;
import java.util.List;

public interface IValidacionFolioDao {

	boolean validarFolio(Date fecha, String tabla);

	List<Date> validarFechasFaltantes(int dias, String tblFolioPrep);

}
