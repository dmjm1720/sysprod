package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.CribasImanes;

public interface ICribasDao {
	
	List<CribasImanes> listaCribasImanes(int folio);
	
	void guardarCribasImanes(CribasImanes cribasImanes);
	
	void actualizarCribasImanes(CribasImanes cribasImanes);
	
	void voboCribasImanes(CribasImanes cribasImanes);
	
	void quitarvoboCribasImanes(CribasImanes cribasImanes);
	
	void borrarCribasImanes(CribasImanes cribasImanes);
	
	int validarNoLimpieza(int folio);

}
