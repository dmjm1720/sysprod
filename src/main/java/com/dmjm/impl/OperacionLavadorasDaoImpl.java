package com.dmjm.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.util.HibernateUtil;

public class OperacionLavadorasDaoImpl implements IOperacionLavadorasDao {

	@Override
	public List<OperacionLavadoras> listaOperacionLavadoras(int id) {

		
		List<OperacionLavadoras> operacionLavadoras = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM OperacionLavadoras o "
			           + "JOIN FETCH o.lavadoras l "
			           + "WHERE o.preparacionPieles.idPreparacion = :idPreparacion";
			Query<OperacionLavadoras> query = session.createQuery(hql, OperacionLavadoras.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			operacionLavadoras = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return operacionLavadoras;
		
		
	}

	@Override
	public void guardarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(operacionLavadoras);
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
	public void actualizarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(operacionLavadoras);
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
