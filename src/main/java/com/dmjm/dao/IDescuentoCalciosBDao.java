package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DescuentoCalciosTablaB;

public interface IDescuentoCalciosBDao {

	List<DescuentoCalciosTablaB> listarDescuentos();

	void guardarDescuentosCalciosA(DescuentoCalciosTablaB calciosTablaB);

	void actualizarDescuentosCalciosB(DescuentoCalciosTablaB calciosTablaB);
	
	void eliminarDescuentosCalciosB(DescuentoCalciosTablaB calciosTablaB);

}
