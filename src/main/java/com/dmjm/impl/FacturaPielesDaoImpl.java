package com.dmjm.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.model.FacturasPieles;

import com.dmjm.util.HibernateUtil;

public class FacturaPielesDaoImpl implements IFacturaPielesDao {

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

}
