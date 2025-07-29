package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.ResumenVotatorA;

public interface IResumenVotatorADao {
	
	void guardarResumen(ResumenVotatorA resumen);
	
	void borrarResumen(int folio);
	
	List<ResumenVotatorA> listaResumen(int folio);

}
