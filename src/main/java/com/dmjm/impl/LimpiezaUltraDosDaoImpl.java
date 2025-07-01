package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaUltraDosDao;
import com.dmjm.model.LimpiezaUltraDos;
import com.dmjm.util.HibernateUtil;

public class LimpiezaUltraDosDaoImpl implements ILimpiezaUltraDosDao {

	@Override
	public List<LimpiezaUltraDos> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaUltraDos c JOIN FETCH c.folioPreparacionUltraDos WHERE  c.folioPreparacionUltraDos.idFolioPrep = :folio";
			Query<LimpiezaUltraDos> query = session.createQuery(hql, LimpiezaUltraDos.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarLimpieza(LimpiezaUltraDos limpieza) {
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
	public void actualizarLimpieza(LimpiezaUltraDos limpieza) {
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

	@Override
	public int validarNoLimpieza(int folioPrep) {
		int folio = 1; // Valor por defecto
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<?> query = session.createSQLQuery("SELECT COALESCE(MAX(NO_LIMPIEZA), 0) + 1 "
					+ "FROM LIMPIEZA_ULTRA_DOS " + "WHERE ID_FOLIO_PREP = :idFolioPrep");
			query.setParameter("idFolioPrep", folioPrep);

			Object result = query.uniqueResult();
			if (result != null) {
				folio = ((Number) result).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace(); //
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return folio;
	}

	@Override
	public List<LimpiezaUltraDos> listarTodo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaUltraDos WHERE voBo='PENDIENTE'";
			Query<LimpiezaUltraDos> query = session.createQuery(hql, LimpiezaUltraDos.class);
			return query.list();
		}
	}

	@Override
	public void actualizarTodoLimpieza() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query<?> query = session
					.createSQLQuery("UPDATE LIMPIEZA_ULTRA_DOS SET VOBO = 'APROBADO' WHERE VOBO='PENDIENTE'");
			query.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); // logger
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

}
