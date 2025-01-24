package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.dmjm.model.CuentasContables;

public interface ICuentasContablesDao {

	List<CuentasContables> listaCuentasContables();

	void guardarCuentasContables(CuentasContables cuentasContables);

	void actualizarCuentasContables(CuentasContables cuentasContables);

	List<String> completeCuentasContablesImp(String nombre) throws SQLException;

	int buscarCuentaContable(String nombre) throws SQLException;

}
