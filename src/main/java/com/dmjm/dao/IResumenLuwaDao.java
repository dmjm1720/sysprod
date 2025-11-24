package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.ResumenLuwaCinco;
import com.dmjm.model.ResumenLuwaCuatro;
import com.dmjm.model.ResumenLuwaDos;
import com.dmjm.model.ResumenLuwaTres;
import com.dmjm.model.ResumenLuwaUno;

public interface IResumenLuwaDao {
	void guardarResumenUno(ResumenLuwaUno resumen);
	void borrarResumenUno(int folio);
	List<ResumenLuwaUno> listaResumenUno(int folio);

	void guardarResumenDos(ResumenLuwaDos resumen);
	void borrarResumenDos(int folio);
	List<ResumenLuwaDos> listaResumenDos(int folio);

	void guardarResumenTres(ResumenLuwaTres resumen);
	void borrarResumenTres(int folio);
	List<ResumenLuwaTres> listaResumenTres(int folio);

	void guardarResumenCuatro(ResumenLuwaCuatro resumen);
	void borrarResumenCuatro(int folio);
	List<ResumenLuwaCuatro> listaResumenCuatro(int folio);

	void guardarResumenCinco(ResumenLuwaCinco resumen);
	void borrarResumenCinco(int folio);
	List<ResumenLuwaCinco> listaResumenCinco(int folio);
}
