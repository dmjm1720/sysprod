package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.ResumenLavadoras;

public interface IResumenLavadorasDao {

	List<ResumenLavadoras> listaResumenLavadoras();

	void guardarResumenLavadoras(ResumenLavadoras lavadoras);

	void actualizarResumenLavadoras(ResumenLavadoras lavadoras);

}
