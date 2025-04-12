package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DescuentoCalciosTablaA;

public interface IDescuentoCalciosADao {

	List<DescuentoCalciosTablaA> listarDescuentos();

	void guardarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA);

	void actualizarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA);

	void eliminarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA);
}
