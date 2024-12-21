package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.FacturasPieles;

public interface IFacturaPielesDao {

	List<FacturasPieles> listaFacturaPieles(int id);

	void guardarFacturasPieles(FacturasPieles facturasPieles);

	void actualizarFacturasPieles(FacturasPieles facturasPieles);

}
