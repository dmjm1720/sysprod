package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.PreparacionPieles;

public interface IPreparacionPielesDao {
	
	List<PreparacionPieles> listaPreparacionPieles(int folio);
	
	int guardarPreparacionPieles(PreparacionPieles preparacionPieles);
	
	void actualizarPreparcionPieles(PreparacionPieles preparacionPieles);
}
