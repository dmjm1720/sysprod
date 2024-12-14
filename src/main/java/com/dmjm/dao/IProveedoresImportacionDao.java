package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.ProveedoresImportacion;

public interface IProveedoresImportacionDao {

	List<ProveedoresImportacion> listaProvImp();

	void guardarProvImp(ProveedoresImportacion proveedoresImportacion);

	void actualizarProvImp(ProveedoresImportacion proveedoresImportacion);

	List<String> completeProveedorImp(String nombre) throws SQLException;

	int buscarProvImp(String nombre) throws SQLException;

	void borrarProvImp(ProveedoresImportacion proveedoresImportacion);

}
