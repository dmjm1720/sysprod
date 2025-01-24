package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.ISaldoFacturaDao;
import com.dmjm.model.SaldoFactura;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class SaldoFacturaDaoImpl extends Conexion implements ISaldoFacturaDao {

	private static final Logger LOGGER = LogManager.getLogger(SaldoFacturaDaoImpl.class.getName());

	@Override
	public List<SaldoFactura> listaSaldoFactura() {
		@SuppressWarnings("unchecked")
		List<SaldoFactura> saldoFactura = (List<SaldoFactura>) HibernateUtil.getSessionFactory().openSession()
				.createQuery("FROM SaldoFactura").list();
		return saldoFactura;
	}

	@Override
	public void guardarSaldoFactura(SaldoFactura saldoFactura) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(saldoFactura);
			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarSaldoFactura(SaldoFactura saldoFactura) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(saldoFactura);
			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public double saldo(String factura) {
		double saldo = 0.0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT TOP(1) NUEVO_SALDO FROM SALDO_FACTURA WHERE FACTURA='" + factura + "' ORDER BY ID_SALDO DESC");
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				saldo = -99;
				LOGGER.info("NO EXISTE LA FACTURA, EL CÓDIGO ES: " + saldo);
			} else {
				while (rs.next()) {
					saldo = rs.getDouble("NUEVO_SALDO");
					LOGGER.info("EL SALDO DE LA FACTURA '" + factura + "' ES: " + saldo);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL SALDO: " + ex);
		}
		return saldo;
	}

}
