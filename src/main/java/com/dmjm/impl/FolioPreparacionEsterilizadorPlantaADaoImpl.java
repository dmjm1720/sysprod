package com.dmjm.impl;

import java.util.Date;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioPreparacionEsterilizadorPlantaADao;
import com.dmjm.model.FolioPreparacionEstA;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionEsterilizadorPlantaADaoImpl implements IFolioPreparacionEsterilizadorPlantaADao {

	@Override
	public int returnIDGuardarFolio(int folio) {
		Session session = null;
		FolioPreparacionEstA f = new FolioPreparacionEstA();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			f.setFolioEstA(folio);
			f.setFecha(new Date());
			
			session.save(f);
			transaction.commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return f.getIdFolioPrep();
	}

	@Override
	public FolioPreparacionEstA retornarFechaActual() {
		FolioPreparacionEstA f = new FolioPreparacionEstA();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionEstA ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionEstA> query = session.createQuery(hql, FolioPreparacionEstA.class);


			f = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return f;
	}

	@Override
	public int fechaFolioActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.folioEstA FROM FolioPreparacionEstA f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioEstAActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionEstA f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);
			
			folio = Optional.ofNullable(query.uniqueResult()).orElse(0);

			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

}
