package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioCocedoresDao;
import com.dmjm.model.FolioCocedores;
import com.dmjm.util.HibernateUtil;

public class FoliosCocedoresDaoImpl implements IFolioCocedoresDao {

	@Override
	public List<FolioCocedores> listaFolioCocedores() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM FolioCocedores", FolioCocedores.class).list();
		}
	}

	@Override
	public void guardarFolioCocedores(FolioCocedores fc) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(fc);
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
	public void actualizarFolioCocedores(FolioCocedores fc) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(fc);
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
	public int buscarFolio(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folio), 0) + 1 FROM FolioCocedores f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolio(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioCocedores f SET f.folio = :folio WHERE f.year = : year";
			Query query = session.createQuery(hql);
			query.setParameter("folio", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
