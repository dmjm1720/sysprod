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

	@Override
	public int validarNoLimpieza(int folioPrep) {
		int folio = 1; // Valor por defecto
	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query<?> query = session.createSQLQuery(
	            "SELECT COALESCE(MAX(NO_LIMPIEZA), 0) + 1 " +
	            "FROM LIMPIEZA_EST_A " +
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
	public List<LimpiezaEstA> listarTodo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaEstA WHERE voBo='PENDIENTE'";
			Query<LimpiezaEstA> query = session.createQuery(hql, LimpiezaEstA.class);
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
					.createSQLQuery("UPDATE LIMPIEZA_EST_A SET VOBO = 'APROBADO' WHERE VOBO='PENDIENTE'");
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
