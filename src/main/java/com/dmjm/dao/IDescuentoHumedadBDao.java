package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DescuentoHumedadTablaB;

public interface IDescuentoHumedadBDao {

	List<DescuentoHumedadTablaB> listarDescuentos();

	void guardarHumedadB(DescuentoHumedadTablaB humedadTablaB);

	void actualziarHumedadB(DescuentoHumedadTablaB humedadTablaB);

}
