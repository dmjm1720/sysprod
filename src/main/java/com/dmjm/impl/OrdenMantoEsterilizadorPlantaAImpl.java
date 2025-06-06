package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaADao;
import com.dmjm.model.OrdenMantenimientoEstA;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoEsterilizadorPlantaAImpl implements IOrdenMantoEsterilizadorPalantaADao {

	@Override
	public List<OrdenMantenimientoEstA> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimientoEstA c JOIN FETCH c.folioPreparacionEstA WHERE c.folioPreparacionEstA.idFolioPrep = :folio";
		    Query<OrdenMantenimientoEstA> query = session.createQuery(hql, OrdenMantenimientoEstA.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoEstA mantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(mantenimiento);
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
	public void actualizarOrdenManto(OrdenMantenimientoEstA OrdenMantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(OrdenMantenimiento);
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
