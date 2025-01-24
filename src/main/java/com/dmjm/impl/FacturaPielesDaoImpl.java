package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.model.FacturasPieles;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class FacturaPielesDaoImpl extends Conexion implements IFacturaPielesDao {
	private static final Logger LOGGER = LogManager.getLogger(FacturaPielesDaoImpl.class.getName());

	@Override
	public List<FacturasPieles> listaFacturaPieles(int idPreparacion) {
		List<FacturasPieles> facturaPieles = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FacturasPieles f WHERE f.preparacionPieles.idPreparacion = :idPreparacion";
			Query<FacturasPieles> query = session.createQuery(hql, FacturasPieles.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", idPreparacion);
			facturaPieles = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return facturaPieles;
	}

	@Override
	public void guardarFacturasPieles(FacturasPieles facturasPieles) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(facturasPieles);
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
	public void actualizarFacturasPieles(FacturasPieles facturasPieles) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(facturasPieles);
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
	public double sumaSaldo(int idPrep) {
		double saldo = 0.0;

		try {
			ConectarSysProd();
			String query = "SELECT SUM(TOTAL) AS TOTAL FROM FACTURAS_PIELES WHERE ID_PREPARACION=?";
			PreparedStatement st = getCnSysProd().prepareStatement(query);
			st.setInt(1, idPrep);
			ResultSet rs = st.executeQuery();
			if (!rs.isBeforeFirst()) {
				saldo = 0.0;
			} else {
				while (rs.next()) {
					saldo = rs.getDouble("TOTAL");
					LOGGER.info("SUMA DEL SALDO: " + saldo);
				}
			}

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL SALDO: " + ex);
		}

		return saldo;
	}

}
