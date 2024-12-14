package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DescuentoHumedadTablaA;

public interface IDescuentoHumedadADao {

	List<DescuentoHumedadTablaA> listarDescuentos();

	void guardarHumedadA(DescuentoHumedadTablaA humedadTablaA);

	void actualziarHumedadA(DescuentoHumedadTablaA humedadTablaA);

}
