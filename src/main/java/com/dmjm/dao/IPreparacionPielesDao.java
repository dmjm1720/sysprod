package com.dmjm.dao;

import java.util.List;

import javax.faces.model.SelectItem;

import com.dmjm.model.PreparacionPieles;

public interface IPreparacionPielesDao {
	
	List<PreparacionPieles> listaPreparacionPieles(int folio);
	
	int guardarPreparacionPieles(PreparacionPieles preparacionPieles);
	
	void actualizarPreparcionPieles(PreparacionPieles preparacionPieles);
	
	List<PreparacionPieles> listaPreparacion();
}
