package com.dmjm.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.model.Etapa1;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.util.HibernateUtil;

public class EtapaDaoImpl implements IEtapa1Dao {

	@Override
	public List<Etapa1> listaEtapa1(int id) {
		List<Etapa1> etapa1 = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapa1 e WHERE e.preparacionPieles.idPreparacion = :idPreparacion";
			Query<Etapa1> query = session.createQuery(hql, Etapa1.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapa1 = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapa1;
	}

	@Override
	public void guardarEtapa1(Etapa1 etapa1) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(etapa1);
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
	public void actualizarEtapa1(Etapa1 etapa1) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.merge(etapa1);
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
	public void guardarListaEtapas(List<Etapa1> lista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			for (Etapa1 etapa1 : lista) {
				session.save(etapa1);
			}

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
