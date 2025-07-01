package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaDao;
import com.dmjm.model.Limpieza;
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

	@Override
	public int validarNoLimpieza(int folioPrep) {
		int folio = 1; // Valor por defecto
	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query<?> query = session.createSQLQuery(
	            "SELECT COALESCE(MAX(NO_LIMPIEZA), 0) + 1 " +
	            "FROM LIMPIEZA " +
	            "WHERE ID_FOLIO_PREP = :idFolioPrep"
	        );
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
	public List<Limpieza> listarTodo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Limpieza WHERE voBo='PENDIENTE'";
			Query<Limpieza> query = session.createQuery(hql, Limpieza.class);
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
					.createSQLQuery("UPDATE LIMPIEZA SET VOBO = 'APROBADO' WHERE VOBO='PENDIENTE'");
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
