package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.ResumenVotatorB;

public interface IResumenVotatorBDao {
	
	void guardarResumen(ResumenVotatorB resumen);
	
	void borrarResumen(int folio);
	
	List<ResumenVotatorB> listaResumen(int folio);

}
