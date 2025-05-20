package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IPurgasDao;
import com.dmjm.model.Purgas;
import com.dmjm.util.HibernateUtil;

public class PurgasDaoImpl implements IPurgasDao {

	@Override
	public List<Purgas> listaPurgas(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM Purgas c JOIN FETCH c.folioPreparacionCocedores WHERE c.folioPreparacionCocedores.idFolioPrep = :folio";
		    Query<Purgas> query = session.createQuery(hql, Purgas.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}

	}

	@Override
	public void guardaPurgas(Purgas purgas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(purgas);
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
	public void actualizarPurgas(Purgas purgas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(purgas);
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
