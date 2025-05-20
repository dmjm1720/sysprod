package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaDao;
import com.dmjm.model.Limpieza;
import com.dmjm.model.OrdenMantenimiento;
import com.dmjm.util.HibernateUtil;

public class LimpiezaDaoImpl implements ILimpiezaDao {

	@Override
	public List<Limpieza> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Limpieza c JOIN FETCH c.folioPreparacionCocedores WHERE  c.folioPreparacionCocedores.idFolioPrep = :folio";
			Query<Limpieza> query = session.createQuery(hql, Limpieza.class);
			query.setParameter("folio", folio);

			return query.list();
		}

	}

	@Override
	public void guardarLimpieza(Limpieza limpieza) {
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
	public void actualizarLimpieza(Limpieza limpieza) {
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
