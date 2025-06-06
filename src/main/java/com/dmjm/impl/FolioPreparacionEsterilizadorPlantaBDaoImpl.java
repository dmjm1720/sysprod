package com.dmjm.impl;

import java.util.Date;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioPreparacionEsterilizadorPlantaBDao;
import com.dmjm.model.FolioPreparacionEstB;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionEsterilizadorPlantaBDaoImpl implements IFolioPreparacionEsterilizadorPlantaBDao {

	@Override
	public int returnIDGuardarFolio(int folio) {
		Session session = null;
		FolioPreparacionEstB f = new FolioPreparacionEstB();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			f.setFolioEstB(folio);
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
	public FolioPreparacionEstB retornarFechaActual() {
		FolioPreparacionEstB f = new FolioPreparacionEstB();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionEstB ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionEstB> query = session.createQuery(hql, FolioPreparacionEstB.class);


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
			String hql = "SELECT f.folioEstB FROM FolioPreparacionEstB f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioEstBActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionEstB f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = Optional.ofNullable(query.uniqueResult()).orElse(0);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

}
