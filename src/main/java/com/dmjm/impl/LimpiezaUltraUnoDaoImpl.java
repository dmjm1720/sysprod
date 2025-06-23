package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaUltraUnoDao;
import com.dmjm.model.LimpiezaUltraUno;
import com.dmjm.util.HibernateUtil;

public class LimpiezaUltraUnoDaoImpl implements ILimpiezaUltraUnoDao {

	@Override
	public List<LimpiezaUltraUno> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaUltraUno c JOIN FETCH c.folioPreparacionUltraUno WHERE  c.folioPreparacionUltraUno.idFolioPrep = :folio";
			Query<LimpiezaUltraUno> query = session.createQuery(hql, LimpiezaUltraUno.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarLimpieza(LimpiezaUltraUno limpieza) {
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
	public void actualizarLimpieza(LimpiezaUltraUno limpieza) {
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
