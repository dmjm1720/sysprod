package com.dmjm.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioPreparcionCocedoresDao;
import com.dmjm.model.FolioPreparacionCocedores;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionCocedoresDaoImpl implements IFolioPreparcionCocedoresDao {

	@Override
	public int returnIDGuardarFolio(int folio) {
		Session session = null;
		FolioPreparacionCocedores fpc = new FolioPreparacionCocedores();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			fpc.setFolioCocedor(folio);
			fpc.setFecha(new Date());
			
			session.save(fpc);
			transaction.commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return fpc.getIdFolioPrep();
	}

	@Override
	public FolioPreparacionCocedores retornarFechaActual() {
		FolioPreparacionCocedores fpc = new FolioPreparacionCocedores();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionCocedores ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionCocedores> query = session.createQuery(hql, FolioPreparacionCocedores.class);


			fpc = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return fpc;
	}

	@Override
	public int fechaFolioActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.folioCocedor FROM FolioPreparacionCocedores f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioCocedorActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionCocedores f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

}
