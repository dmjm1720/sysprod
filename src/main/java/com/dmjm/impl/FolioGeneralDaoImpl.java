package com.dmjm.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioGeneralDao;
import com.dmjm.util.HibernateUtil;

public class FolioGeneralDaoImpl implements IFolioGeneralDao {

	@Override
	public int buscarFolio(String tipo) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folio), 0) + 1 FROM FolioGeneral f WHERE f.tipo = :tipo";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("tipo", tipo);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolio(String tipo, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioGeneral f SET f.folio = :folio WHERE f.tipo = : tipo";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folio", folio);
			query.setParameter("tipo", tipo);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
