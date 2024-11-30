package com.dmjm.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.model.Etapa1;
import com.dmjm.model.Lavadoras;
import com.dmjm.util.HibernateUtil;

public class EtapaDaoImpl implements IEtapa1Dao {

	@Override
	public List<Etapa1> listaEtapa1() {
		@SuppressWarnings("JPQLValidation")
		List<Etapa1> etapa1 = (List<Etapa1>) HibernateUtil.getSessionFactory().openSession().createQuery("From Etapa1")
				.list();
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
