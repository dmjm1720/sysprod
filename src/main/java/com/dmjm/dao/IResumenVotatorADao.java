package com.dmjm.dao;

import com.dmjm.model.ResumenVotatorA;

public interface IResumenVotatorADao {
	
	void guardarResumen(ResumenVotatorA resumen);
	
	void borrarResumen(int folio);
	

}
