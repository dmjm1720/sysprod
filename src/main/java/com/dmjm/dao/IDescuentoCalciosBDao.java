package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.DescuentoCalciosTablaB;

public interface IDescuentoCalciosBDao {

	List<DescuentoCalciosTablaB> listarDescuentos();

	void guardarDescuentosCalciosA(DescuentoCalciosTablaB calciosTablaB);

	void actualziarDescuentosCalciosB(DescuentoCalciosTablaB calciosTablaB);

}
