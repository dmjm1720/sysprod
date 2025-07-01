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

	@Override
	public int validarNoLimpieza(int folioPrep) {
		int folio = 1; // Valor por defecto
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<?> query = session.createSQLQuery("SELECT COALESCE(MAX(NO_LIMPIEZA), 0) + 1 "
					+ "FROM LIMPIEZA_ULTRA_UNO " + "WHERE ID_FOLIO_PREP = :idFolioPrep");
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
	public List<LimpiezaUltraUno> listarTodo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaUltraUno WHERE voBo='PENDIENTE'";
			Query<LimpiezaUltraUno> query = session.createQuery(hql, LimpiezaUltraUno.class);
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
					.createSQLQuery("UPDATE LIMPIEZA_ULTRA_UNO SET VOBO = 'APROBADO' WHERE VOBO='PENDIENTE'");
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
