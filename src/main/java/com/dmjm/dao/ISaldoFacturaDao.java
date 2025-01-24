package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.SaldoFactura;

public interface ISaldoFacturaDao {

	List<SaldoFactura> listaSaldoFactura();

	void guardarSaldoFactura(SaldoFactura saldoFactura);

	void actualizarSaldoFactura(SaldoFactura saldoFactura);
	
	double saldo (String factura);
	
}
