package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaEsterilizadorPlantaADao;
import com.dmjm.model.LimpiezaEstA;
import com.dmjm.util.HibernateUtil;

public class LimpiezaEsterilizadorPlantaADaoImpl implements ILimpiezaEsterilizadorPlantaADao {

	@Override
	public List<LimpiezaEstA> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaEstA c JOIN FETCH c.folioPreparacionEstA WHERE  c.folioPreparacionEstA.idFolioPrep = :folio";
			Query<LimpiezaEstA> query = session.createQuery(hql, LimpiezaEstA.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarLimpieza(LimpiezaEstA limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limpieza);
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
	public void actualizarLimpieza(LimpiezaEstA limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(limpieza);
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
