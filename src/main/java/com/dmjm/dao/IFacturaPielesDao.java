package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.FacturasPieles;

public interface IFacturaPielesDao {

	List<FacturasPieles> listaFacturaPieles();

	void guardarFacturasPieles(FacturasPieles facturasPieles);

	void actualizarFacturasPieles(FacturasPieles facturasPieles);

}
