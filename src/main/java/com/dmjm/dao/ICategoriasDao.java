package com.dmjm.dao;

import java.sql.SQLException;
import java.util.List;

import com.dmjm.model.Categorias;

public interface ICategoriasDao {
	
	List<Categorias> listaCategorias();	
	
	void guardarCategoria(Categorias categorias);
	
	void actualizarCategoria(Categorias categorias);
	
	void borrarCategoria(Categorias categorias);

	int validarCategoriaExistente(int categoria_sistema) throws SQLException;
}
