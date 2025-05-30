package com.dmjm.dao;

import java.util.Date;
import java.util.List;

import com.dmjm.model.Cocedores;



public interface ICocedoresDao {
	
	List<Cocedores> listaCocedores();
	
	List<Cocedores> listaFiltroCocedores();
	
	List<Cocedores> listaPorFechaCocedores(Date fecha);

	void guardarCocedores(Cocedores cocedores);

	void actualizarCocedores(Cocedores cocedores);
	
	void actualizarCocedoresPromedio(String operacion, int folio);
	
	void actualizarPromedioGrados(int folio);
	void actualizarPromedioFlujo(int folio);
	void actualizarPromedioPH(int folio);
	void actualizarPromedioNTU(int folio);
	void actualizarPromedioCalcios(int folio);
	void actualizarPromedioViscocidad(int folio);
	void actualizarPromedioCondensacion(int folio);
	void actualizarPromedioConcentrado(int folio);
	void actualizarPromedioTempCoc01(int folio);
	void actualizarPromedioConCoc01(int folio);
	void actualizarPromedioTempCoc02(int folio);
	void actualizarPromedioConCoc02(int folio);
	void actualizarPromedioTempCoc03(int folio);
	void actualizarPromedioConCoc03(int folio);
	void actualizarPromedioTempCoc04(int folio);
	void actualizarPromedioConCoc04(int folio);
	void actualizarPromedioTempCoc05(int folio);
	void actualizarPromedioConCoc05(int folio);
	void actualizarPromedioTempCoc06(int folio);
	void actualizarPromedioConCoc06(int folio);
	void actualizarPromedioTempCoc07(int folio);
	void actualizarPromedioConCoc07(int folio);
	void actualizarPromedioTempCoc08(int folio);
	void actualizarPromedioConCoc08(int folio);
	void actualizarPromedioTempCoc09(int folio);
	void actualizarPromedioConCoc09(int folio);
	void actualizarPromedioTempCoc10(int folio);
	void actualizarPromedioConCoc10(int folio);
	

}
